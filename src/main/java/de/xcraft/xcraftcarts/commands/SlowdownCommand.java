package de.xcraft.xcraftcarts.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.SetVelocity;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SlowdownCommand {

	public static void Slowdown(CommandSender sender) {
	
		if (sender instanceof Player && sender.hasPermission("xcarts.slowdown") || sender instanceof ConsoleCommandSender) {
			
			PLUGIN.velocity = 0.4;
			PLUGIN.runtimer = false;
			
			//Bukkit.getServer().getConsoleSender().sendMessage("Plugin.RUNTIMER");
			
			new BukkitRunnable() {
				@Override
				public void run() {
					HandlerList.unregisterAll(PLUGIN);
				}
			
			}.runTaskLater(PLUGIN, 7);
			
			MessageHandler.informPassengers(sender, "slowdown");
	
		} else {
			
			MessageHandler.informPassengers(sender, "noperms");
			
		}
	
	}
	
}
