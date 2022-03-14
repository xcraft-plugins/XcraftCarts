package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SlowdownCommand {

	public static void Slowdown(CommandSender sender) {
	
		if (sender.isOp()) {
		
			PLUGIN.runtimer = false;
			//Bukkit.getServer().getConsoleSender().sendMessage("Plugin.RUNTIMER");

			new BukkitRunnable() {
				@Override
				public void run() {
					HandlerList.unregisterAll(PLUGIN);
				}
			
			}.runTaskLater(PLUGIN, 5);

			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Slowdown"));
		}
	
	}
	
}
