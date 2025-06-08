package de.xcraft.xcraftcarts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import de.xcraft.xcraftcarts.MessageHandler;
import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class VersionMessage extends MessageHandler {

	@Override
	public boolean informPassengers(CommandSender sender) {

		
		PluginDescriptionFile v = PLUGIN.getDescription();
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Version") + ChatColor.GREEN + v.getVersion()));
		sender.sendMessage(ChatColor.DARK_AQUA + "Authors: " + ChatColor.GREEN + v.getAuthors());
		
		return true;
	}

}