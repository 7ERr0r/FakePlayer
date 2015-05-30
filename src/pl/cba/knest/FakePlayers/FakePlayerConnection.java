package pl.cba.knest.FakePlayers;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PlayerConnection;

public class FakePlayerConnection extends PlayerConnection {

	public FakePlayerConnection(MinecraftServer minecraftserver,
			NetworkManager networkmanager, EntityPlayer entityplayer) {
		super(minecraftserver, networkmanager, entityplayer);
	}

	
	public void sendPacket(Packet packet){
		
	}


}
