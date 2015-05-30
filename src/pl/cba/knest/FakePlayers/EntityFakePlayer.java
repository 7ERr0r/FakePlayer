package pl.cba.knest.FakePlayers;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.server.v1_7_R4.WorldServer;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class EntityFakePlayer extends EntityPlayer {

	public EntityFakePlayer(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
		super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
	}
}
