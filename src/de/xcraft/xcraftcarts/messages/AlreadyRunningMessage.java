package de.xcraft.xcraftcarts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.xcraft.xcraftcarts.MessageHandler;
import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class AlreadyRunningMessage extends MessageHandler {

	@Override
	public boolean informPassengers(CommandSender sender) {

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.AlreadyRunning")));
		return true;
	}

}
