package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.MessageHandler;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class ReloadCommand {

	public static void PluginReload(CommandSender sender) {
	
		if (sender.isOp() || sender.hasPermission("xcarts.reload")) {
		
			PLUGIN.velocity = 0.4;
			PLUGIN.runtimer = false;

			new BukkitRunnable() {
				@Override
				public void run() {
					HandlerList.unregisterAll(PLUGIN);
				}
			
			}.runTaskLater(PLUGIN, 5);
			
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.Reload"));
			MessageHandler.informPassengers(sender, "slowdown");
			
			PLUGIN.reloadConfig();
	
		} else {
			
			MessageHandler.informPassengers(sender, "noperms");
			
		}
		
	}
	
}
