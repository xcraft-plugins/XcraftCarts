package de.xcraft.xcraftcarts.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.messages.NoPermsMessage;
import de.xcraft.xcraftcarts.messages.ReloadMessage;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class ReloadCommand {

	public static void PluginReload(CommandSender sender) {
	
		if (sender.isOp() || sender.hasPermission("xcarts.reload")) {
		
			PLUGIN.RUNTIMER = false;
			
			new BukkitRunnable() {
				@Override
				public void run() {
					HandlerList.unregisterAll(PLUGIN);
					PLUGIN.SLOWDOWN_MESSAGE = false;
				}
			
			}.runTaskLater(PLUGIN, 5);

			//Send reload message
			MessageHandler reload = new ReloadMessage();
			reload.informPassengers(sender);
			
			PLUGIN.reloadConfig();
	
		} else {
			
			//Deny command
			MessageHandler noperms = new NoPermsMessage();
			noperms.informPassengers(sender);
			
		}
		
	}
	
}
