package de.xcraft.xcraftcarts.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.messages.NoPermsMessage;
import de.xcraft.xcraftcarts.messages.SlowdownMessage;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SlowdownCommand {

	public static void Slowdown(CommandSender sender) {
	
        if (!(sender instanceof Player || sender instanceof ConsoleCommandSender))
            return;
		
		if (sender instanceof Player && sender.hasPermission("xcarts.slowdown") || sender instanceof ConsoleCommandSender) {
			
			PLUGIN.RUNTIMER = false;
			
			//Send slowdown message
			MessageHandler slowdown = new SlowdownMessage();
			slowdown.informPassengers(sender);
			
			new BukkitRunnable() {
				@Override
				public void run() {
					HandlerList.unregisterAll(PLUGIN);
					PLUGIN.SLOWDOWN_MESSAGE = false;
				}
			
			}.runTaskLater(PLUGIN, 7);
			
	
		} else {
			
			//Deny command
			MessageHandler noperms = new NoPermsMessage();
			noperms.informPassengers(sender);
			
		}
	
	}
	
}
