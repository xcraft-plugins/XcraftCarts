package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class ReloadCommand {

	public static void PluginReload(CommandSender sender) {
	
		if (sender.isOp()) {
		
			sender.sendMessage(ChatColor.GREEN + PLUGIN.getConfig().getString("MESSAGES.Reload"));
			PLUGIN.reloadConfig();
	
		}
		
	}
	
}
