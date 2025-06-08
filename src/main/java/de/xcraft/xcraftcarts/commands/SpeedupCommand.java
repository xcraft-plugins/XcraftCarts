package de.xcraft.xcraftcarts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.EventListener;
import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.XcraftCarts;
import net.milkbowl.vault.economy.Economy;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SpeedupCommand {

	private static double price = PLUGIN.getConfig().getDouble("CONFIG.price");
	
	@SuppressWarnings("deprecation")
	public static void Speedup(CommandSender sender) {
			
		Economy eco = XcraftCarts.getEconomy();
		
		//Check for runtimer
		if (PLUGIN.runtimer == false) {
			
			//Sender is OP or console
			if ((sender instanceof Player && sender.isOp() == true) || sender instanceof ConsoleCommandSender) runTask(sender);
			
			//Sender is normal player
			if (sender instanceof Player && sender.hasPermission("xcarts.speedup") && sender.isOp() == false);
				
				//Check player money
			    if (price <= 0) runTask(sender);
			
				if (price > 0 && eco.has(sender.getName(), price) == true) {
					
					eco.withdrawPlayer(sender.getName(), price);
					
					runTask(sender);
					
				} else {sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.NoMoney"));}
		
		} else {MessageHandler.informPassengers(sender, "alreadyrunning");}
		
	}
		
/**		
		if (PLUGIN.runtimer == false) {
		
			if (sender.isOp()) {
					
				runTask(sender);
					
			} else { 
				
				if (sender instanceof Player && sender.hasPermission("xcarts.speedup")) {
				
					if (price > 0 && eco.has(sender.getName(), price) == true) {
						
						eco.withdrawPlayer(sender.getName(), price);
						
						runTask(sender);
					
					} else if (price <= 0) {
						
						runTask(sender);
						
					} else {
						
						sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.NoMoney"));
						
					}
						
				} else {
					
					MessageHandler.informPassengers(sender, "noperms");
					
				}
				
			}
			
			
		} else {
			
			MessageHandler.informPassengers(sender, "alreadyrunning");
			
		}
		
	}
**/	
	// Run Speedup BukkitRunnable
	
	private static void runTask(CommandSender sender) {
		
		if (PLUGIN.runtimer == false) {
			
			MessageHandler.informPassengers(sender, "speedup");
			
			HandlerList.unregisterAll(PLUGIN);
			
			PLUGIN.runtimer = true;
			PLUGIN.velocity = PLUGIN.getConfig().getInt("CONFIG.maxSpeed");
	
			PLUGIN.getServer().getPluginManager().registerEvents(new EventListener(), PLUGIN);
			
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					PLUGIN.velocity = 0.4;
					PLUGIN.runtimer = false;
	
					new BukkitRunnable() {
						@Override
						public void run() {
							
							MessageHandler.informPassengers(sender, "slowdown");
							HandlerList.unregisterAll(PLUGIN);
						}
					
					}.runTaskLater(PLUGIN, 5);
	
				}
				
			}.runTaskLater(PLUGIN, PLUGIN.getConfig().getInt("CONFIG.activationTime"));
		
		}
		
	}
	
}
