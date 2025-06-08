package de.xcraft.xcraftcarts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.xcraft.xcraftcarts.MessageHandler;
import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class HelpMessage extends MessageHandler {

	@Override
	public boolean informPassengers(CommandSender sender) {

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Help")));

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("COMMANDS.Reload")) 
		         + ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("DESCRIPTION.Reload")));		
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("COMMANDS.Speedup")) 
				         + ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("DESCRIPTION.Speedup")));
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("COMMANDS.Slowdown")) 
		                 + ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("DESCRIPTION.Slowdown")));
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("COMMANDS.Version")) 
                         + ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("DESCRIPTION.Version")));
		return true;
	}

}