package de.xcraft.xcraftcarts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.xcraft.xcraftcarts.MessageHandler;
import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class NoMoneyMessage extends MessageHandler {

	@Override
	public boolean informPassengers(CommandSender sender) {

		String message_NoMoney = PLUGIN.getConfig().getString("MESSAGES.NoMoney").replace("%price%", PLUGIN.getConfig().getString("CONFIG.price"));
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',message_NoMoney));
		return true;
	}

}