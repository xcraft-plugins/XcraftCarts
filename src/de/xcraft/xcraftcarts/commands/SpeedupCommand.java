package de.xcraft.xcraftcarts.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import de.xcraft.xcraftcarts.EventListener;
import de.xcraft.xcraftcarts.MessageHandler;
import de.xcraft.xcraftcarts.XcraftCarts;
import de.xcraft.xcraftcarts.messages.AlreadyRunningMessage;
import de.xcraft.xcraftcarts.messages.NoMoneyMessage;
import de.xcraft.xcraftcarts.messages.NoPermsMessage;
import de.xcraft.xcraftcarts.messages.SlowdownMessage;
import de.xcraft.xcraftcarts.messages.SpeedupMessage;
import net.milkbowl.vault.economy.Economy;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SpeedupCommand {

	private static double price = PLUGIN.getConfig().getDouble("CONFIG.price");
	
	public static void Speedup(CommandSender sender) {
		
        if (!(sender instanceof Player || sender instanceof ConsoleCommandSender))
            return;
		
        //Assign every condition/config option a value
		byte status_runtimer = 0;
		byte status_sender = 0;
		byte status_permission = 0;
		byte status_price = 0;
		byte status_money = 0;
		
			//plugin is running?
			if (PLUGIN.RUNTIMER == false) status_runtimer = 100;
			if (PLUGIN.RUNTIMER == true) status_runtimer = -100;
		
			//Sender is player or console?
			if (sender instanceof Player) status_sender = 50;
			if (sender instanceof ConsoleCommandSender) status_sender = -50;
		
			//Sender has permission?
			if (sender.hasPermission("xcarts.speedup") || sender.isOp()) status_permission = 10;
			if (!(sender.hasPermission("xcarts.speedup") || sender.isOp())) status_permission = -10;
	    
			//Price has been enabled?
			if (price <= 0) status_price = -5;
			if (price > 0) {status_price = 5;
	    	
			//Sender has money?
	    		Economy eco = XcraftCarts.getEconomy();
	    	
	    		if (eco.has((Player)sender, price) == true) status_money = 1;
	    		if (eco.has((Player)sender, price) == false) status_money = -1;
	    	
			}
			
	    //Plugin is already running
	    if (status_runtimer == -100) {
	    		
			//Send already running message
			MessageHandler already = new AlreadyRunningMessage();
			already.informPassengers(sender);}
	    	
	    	
	    //Sender is console
	    if ((status_runtimer + status_sender) == 50) {
	    		
	    		runTask(sender);}
	    	
	    	
            //Sender is player with no perms
	    if ((status_runtimer + status_sender + status_permission) == 140) {
	    		
			MessageHandler noperms = new NoPermsMessage();
			noperms.informPassengers(sender);}
	    	
	    	
	    //Player has perms, but no costs enabled
	    if ((status_runtimer + status_sender + status_permission + status_price) == 155) {
	    		
	    		runTask(sender);}
	    	
	    	
	    //Player has perms, costs are enabled, player has no money
	    if ((status_runtimer + status_sender + status_permission + status_price + status_money) == 164) {
	    		
			MessageHandler nomoney = new NoMoneyMessage();
			nomoney.informPassengers(sender);}
	    	
	    	
	    //Player has perms, costs are enabled, player has money
	    if ((status_runtimer + status_sender + status_permission + status_price + status_money) == 166) {
	    		
	    		Economy eco = XcraftCarts.getEconomy();
	    		
	    		eco.withdrawPlayer((Player)sender, price);
	    		runTask(sender);}
	    
	}
	
	// Run Speedup BukkitRunnable
	
	private static void runTask(CommandSender sender) {
		
		if (PLUGIN.RUNTIMER == false) {
			
			//Send speedup message
			MessageHandler speedup = new SpeedupMessage();
			speedup.informPassengers(sender);
			
			//Remove all xcart eventlisteners
			HandlerList.unregisterAll(PLUGIN);
			
			PLUGIN.RUNTIMER = true;
			PLUGIN.SLOWDOWN_MESSAGE = true;
	
			PLUGIN.getServer().getPluginManager().registerEvents(new EventListener(), PLUGIN);
			
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					PLUGIN.RUNTIMER = false;
					
					if (PLUGIN.SLOWDOWN_MESSAGE == true) {
					//Send slowdown message
					MessageHandler slowdown = new SlowdownMessage();
					slowdown.informPassengers(sender);}
	
					new BukkitRunnable() {
						@Override
						public void run() {
							
							PLUGIN.SLOWDOWN_MESSAGE = false;
							
							//Remove all xcart eventlisteners
							HandlerList.unregisterAll(PLUGIN);
						}
					
					}.runTaskLater(PLUGIN, 5);
	
				}
				
			}.runTaskLater(PLUGIN, PLUGIN.getConfig().getInt("CONFIG.activationTime"));
		
		}
		
	}
	
}
