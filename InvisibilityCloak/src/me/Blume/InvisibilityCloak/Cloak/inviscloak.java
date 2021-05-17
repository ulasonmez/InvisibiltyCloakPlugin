package me.Blume.InvisibilityCloak.Cloak;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class inviscloak {

	public static ItemStack cloak= new ItemStack(Material.ELYTRA);
	public static ItemStack clay = new ItemStack(Material.CLAY_BALL);
	public ItemStack getCloak() {
		ItemMeta meta = cloak.getItemMeta();
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Invisibilty Cloak");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW+"RIGHT CLICK");
		lore.add("Become Completely");
		lore.add(ChatColor.GREEN+"Invisible"+ChatColor.GRAY+" For"+ChatColor.GREEN+" 30s");
		meta.setLore(lore);
		cloak.setItemMeta(meta);
		return cloak;
	}
	public void removeCloak(Player player) {
		player.getInventory().remove(inviscloak.cloak);
	}
	public ItemStack getClay() {
		ItemMeta meta = clay.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA+"You are Invisible");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE+"Wait for 30s");
		clay.setItemMeta(meta);
		meta.setLore(lore);
		return clay;
	}
	public void removeClay(Player player) {
		player.getInventory().remove(inviscloak.clay);
	}
}
