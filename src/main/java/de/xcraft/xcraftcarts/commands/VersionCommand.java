package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class VersionCommand {

	public static void PluginInfo(CommandSender sender) {
	
		PluginDescriptionFile v = PLUGIN.getDescription();
		sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Version") + ChatColor.GREEN + v.getVersion());
		sender.sendMessage(ChatColor.DARK_AQUA + "Authors: " + ChatColor.GREEN + v.getAuthors());
		
	}
	
}
