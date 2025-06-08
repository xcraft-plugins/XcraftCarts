package de.xcraft.xcraftcarts.commands;

import org.bukkit.command.CommandSender;

import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.messages.HelpMessage;
import de.xcraft.xcraftcarts.messages.NoPermsMessage;

public class HelpCommand {

	public static void PluginHelp(CommandSender sender) {
		
		if (sender.isOp() || sender.hasPermission("xcarts.help")) {
			
			//Lists all commands
			MessageHandler help = new HelpMessage();
			help.informPassengers(sender);
		
		} else {
			
			//Deny command
			MessageHandler noperms = new NoPermsMessage();
			noperms.informPassengers(sender);
	
		}
		
	}
}
