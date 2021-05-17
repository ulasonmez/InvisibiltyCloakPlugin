package me.Blume.InvisibilityCloak;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.Blume.InvisibilityCloak.Commands.addCloakToPlayer;
import me.Blume.InvisibilityCloak.Commands.removeCloak;
import me.Blume.InvisibilityCloak.Listeners.events;

public class Main extends JavaPlugin{
	HashSet<UUID> cloakplayer = new HashSet<UUID>();
	@Override
	public void onEnable() {
		//getCommand("copyandpastestop").setExecutor(new ItemInteract2(this));
		//getServer().getPluginManager().registerEvents(new Items(), this);
		getCommand("addcloak").setExecutor(new addCloakToPlayer(this));
		getCommand("removecloak").setExecutor(new removeCloak(this));
		getServer().getPluginManager().registerEvents(new events(this), this);
	}
	public void onDisable() {
		
	}
	
	public void addplayer(UUID player) {
		cloakplayer.add(player);
	}
	public void removeplayer(UUID player) {
		cloakplayer.remove(player);
	}
	public HashSet<UUID> getcloakplayer() {
		return this.cloakplayer;
	}
	
	
}
