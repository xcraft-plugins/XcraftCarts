package de.xcraft.xcraftcarts.commands;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {

	public static void PluginHelp(CommandSender sender) {
	
		sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Help"));
		sender.sendMessage(ChatColor.GREEN + PLUGIN.getConfig().getString("COMMANDS.Speedup") + ChatColor.GRAY + PLUGIN.getConfig().getString("DESCRIPTION.Speedup"));
	
		if (sender.isOp()) {
		
			sender.sendMessage(ChatColor.GREEN + PLUGIN.getConfig().getString("COMMANDS.Slowdown") + ChatColor.GRAY + PLUGIN.getConfig().getString("DESCRIPTION.Slowdown"));
			sender.sendMessage(ChatColor.GREEN + PLUGIN.getConfig().getString("COMMANDS.Reload") + ChatColor.GRAY + PLUGIN.getConfig().getString("DESCRIPTION.Reload"));
		}
	
		sender.sendMessage(ChatColor.GREEN + PLUGIN.getConfig().getString("COMMANDS.Version") + ChatColor.GRAY + PLUGIN.getConfig().getString("DESCRIPTION.Version"));
	
	}
	
}
