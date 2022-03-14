package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.EventListener;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;


public class SpeedupCommand {

	public static void Speedup(CommandSender sender) {
	
		sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Speedup"));
		PLUGIN.runtimer = true;

		PLUGIN.getServer().getPluginManager().registerEvents(new EventListener(), PLUGIN);

		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				PLUGIN.runtimer = false;
				//Bukkit.getServer().getConsoleSender().sendMessage("Plugin.RUNTIMER");

				new BukkitRunnable() {
					@Override
					public void run() {
						HandlerList.unregisterAll(PLUGIN);
					}
				
				}.runTaskLater(PLUGIN, 5);

			}
			
		}.runTaskLater(PLUGIN, PLUGIN.getConfig().getInt("CONFIG.activationTime"));

	}
	
}
