package de.xcraft.xcraftcarts.commands;

import org.bukkit.command.CommandSender;

import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.messages.NoPermsMessage;
import de.xcraft.xcraftcarts.messages.VersionMessage;

public class VersionCommand {

	public static void PluginInfo(CommandSender sender) {
	
		if (sender.isOp() || sender.hasPermission("xcarts.version")) {
			
			//Send plugin info
			MessageHandler version = new VersionMessage();
			version.informPassengers(sender);
			
		} else {
			
			//Deny command
			MessageHandler noperms = new NoPermsMessage();
			noperms.informPassengers(sender);
			
		}
		
	}
	
}
