package de.xcraft.xcraftcarts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class MessageHandler {

	//register main class
	public MessageHandler() {}
	
	//
	// Messages can differentiate between sender and players on the track.
	//
	
	@SuppressWarnings("deprecation")
	public static boolean informPassengers(CommandSender sender, String message) {
		
	    switch (message) {
	    
		case "speedup":

			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage("Hello World from console.sendMessage()");
			
			sender.sendMessage(ChatColor.RED + "Test");
			
			System.out.println("Hello World from System.out.println()");
			
			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Speedup"));
			
	        for (Player player : Bukkit.getOnlinePlayers()) {

	        	Minecart cart = (Minecart) player.getVehicle();
	        	
	            if (cart instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn") && !player.equals(sender) && !cart.isEmpty()) {
	            	player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.SpeedupOther"));
	                player.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Speedup"));
	            }
	        }
			
			return true;

		case "slowdown":
			
			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Slowdown"));
			
	        for (Player player : Bukkit.getOnlinePlayers()) {

	        	Minecart cart = (Minecart) player.getVehicle();
	        	
	            if (cart instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn") && !player.equals(sender) && !cart.isEmpty()) {
	                player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Slowdown"));
	            }
	        }
			
			return true;
			
		case "alreadyrunning":
			
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.AlreadyRunning"));
			return true;
			
		case "noperms":
			
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.NoPerms"));
			return true;

		default:
			
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.Error"));
			return true;
			
    	}
				
	}
	
}
