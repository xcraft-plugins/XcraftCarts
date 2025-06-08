package de.xcraft.xcraftcarts;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

//Polymorphis instead of switch
//https://www.javatpoint.com/runtime-polymorphism-in-java
public abstract class MessageHandler {

	//register main class
	public MessageHandler() {}

	public boolean informPassengers(CommandSender sender) {
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Error")));
		
		return true;
	}
	
}
	
