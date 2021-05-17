package me.Blume.InvisibilityCloak.Listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Blume.InvisibilityCloak.Main;
import me.Blume.InvisibilityCloak.Cloak.inviscloak;
public class events implements Listener {
	private Main plugin;
	public events(Main plugin) {
		this.plugin=plugin;
	}
	inviscloak invcloak = new inviscloak();
	@EventHandler
	public void elytraDrops(PlayerDropItemEvent event) {
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {
			if(event.getItemDrop().getItemStack().isSimilar(invcloak.getCloak())) {
				event.setCancelled(true);
				return;
			}
		}
	}
	@EventHandler
	public void clayDrops(PlayerDropItemEvent event) {
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {
			if(event.getItemDrop().getItemStack().isSimilar(invcloak.getClay())) {
				event.setCancelled(true);
				return;
			}
		}
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		List<ItemStack> drops = event.getDrops(); 
		ListIterator<ItemStack> litr = drops.listIterator();	  
		while( litr.hasNext() )
		{
			ItemStack stack = litr.next();

			if(stack.isSimilar(invcloak.getCloak()) )
			{
				litr.remove();
			}
		}
	}
	@EventHandler
	public void onEntityDeath2(EntityDeathEvent event) {
		List<ItemStack> drops = event.getDrops(); 
		ListIterator<ItemStack> litr = drops.listIterator();	  
		while( litr.hasNext() )
		{
			ItemStack stack = litr.next();

			if(stack.isSimilar(invcloak.getClay()) )
			{
				litr.remove();
			}
		}
	}
	@EventHandler
	public void elytraRespawn(PlayerRespawnEvent event) {
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {
			event.getPlayer().getInventory().addItem(invcloak.getCloak());
			return;
		}
		else return;
	}

	@EventHandler
	public void rightclickCloak(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		Action action = event.getAction();
		if(plugin.getcloakplayer().contains(event.getPlayer().getUniqueId())) {
			Player player = event.getPlayer();
			if (item != null && item.isSimilar(invcloak.getCloak())) {
				if(action==Action.RIGHT_CLICK_AIR || action==Action.RIGHT_CLICK_BLOCK) {
					if((player.getInventory().getChestplate())==null) {
						player.getInventory().setChestplate(null);
					}
					int slot1 = -1;

					for (int i = 0; i < player.getInventory().getSize(); i++) {
						if(player.getInventory().getItem(i)==null) continue;
						if (!(player.getInventory().getItem(i).isSimilar(invcloak.getCloak()))) continue;

						slot1 = i;
						break;
					}

					if (slot1 == -1) return;
					invcloak.removeCloak(player);
					player.getInventory().setItem(slot1, invcloak.getClay());
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,600,1));
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							int slot = -1;

							for (int i = 0; i < player.getInventory().getSize(); i++) {
								if(player.getInventory().getItem(i)==null) continue;
								if (!(player.getInventory().getItem(i).isSimilar(invcloak.getClay()))) continue;

								slot = i;
								break;
							}


							invcloak.removeClay(player);
							player.getInventory().setItem(slot, invcloak.getCloak());

						}
					},30*20L);
				}
			}
		}

	}
	@EventHandler
	public void gliding(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(plugin.getcloakplayer().contains(player.getUniqueId())) {
			if(player.isGliding()) {
				player.getInventory().setChestplate(null);
				player.getInventory().addItem(invcloak.getCloak());
			}

		}
	}
}
