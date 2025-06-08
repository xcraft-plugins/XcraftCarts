package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import de.xcraft.xcraftcarts.MessageHandler;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class VersionCommand {

	public static void PluginInfo(CommandSender sender) {
	
		if (sender.isOp() || sender.hasPermission("xcarts.version")) {
		
			PluginDescriptionFile v = PLUGIN.getDescription();
			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Version") + ChatColor.GREEN + v.getVersion());
			sender.sendMessage(ChatColor.DARK_AQUA + "Authors: " + ChatColor.GREEN + v.getAuthors());
			
		} else {
			
			MessageHandler.informPassengers(sender, "noperms");
			
		}
		
	}
	
}
