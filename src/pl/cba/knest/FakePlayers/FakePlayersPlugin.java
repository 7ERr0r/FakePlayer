package pl.cba.knest.FakePlayers;



import java.util.UUID;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.server.v1_7_R4.WorldServer;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class FakePlayersPlugin extends JavaPlugin implements Listener {
	public static FakePlayersPlugin instance;
	
	public static void log(String msg){
		instance.getLogger().info(msg);
	}
	
	public void onEnable(){
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
		log("Enabled");
	}
	
	@EventHandler
	public void onFarClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Action a = e.getAction();
		ItemStack is = p.getItemInHand();
		if(a == Action.LEFT_CLICK_AIR && is != null && is.getType() == Material.SPONGE){
			@SuppressWarnings("deprecation")
			Block b = p.getTargetBlock(null, 50);
			Location l = b.getLocation().add(0, 1, 0);
			EntityPlayer fp = newFakePlayer(l);
			World w = p.getWorld();
			WorldServer ws = ((CraftWorld) w).getHandle();
			ws.addEntity(fp);
		}
		
	}
	
	@EventHandler
	public void onBotClick(EntityDamageByEntityEvent e){
		if(((CraftEntity) e.getEntity()).getHandle() instanceof EntityFakePlayer){
			e.setCancelled(true);
			if(e.getDamager() instanceof Player) ((Player) e.getDamager()).sendMessage("Walnales w bota");
		}
	}

	private EntityPlayer newFakePlayer(Location l){
		UUID uuid = UUID.randomUUID();
		World w = l.getWorld();
		WorldServer ws = ((CraftWorld) w).getHandle();
		MinecraftServer server = ws.getServer().getHandle().getServer();
		
		EntityFakePlayer fp = new EntityFakePlayer(server, ws, new GameProfile(uuid, "twojastara"), new PlayerInteractManager(ws));
		fp.playerConnection = new FakePlayerConnection(server, new NetworkManager(false), fp);
		fp.setPosition(l.getX(), l.getY(), l.getZ());
		return fp;
	}
}
