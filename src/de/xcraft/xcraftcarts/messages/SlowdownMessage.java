package de.xcraft.xcraftcarts.messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

import de.xcraft.xcraftcarts.MessageHandler;
import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SlowdownMessage extends MessageHandler {

	@Override
	public boolean informPassengers(CommandSender sender) {
		
	if (PLUGIN.SLOWDOWN_MESSAGE == true) {
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Slowdown")));
		
        for (Player player : Bukkit.getOnlinePlayers()) {

        	Minecart cart = (Minecart) player.getVehicle();
        	
            if (cart instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals(PLUGIN.getConfig().getString("CONFIG.EntityName")) && !player.equals(sender) && !cart.isEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Slowdown")));
            }
        } 
    
	}    
        
	if (PLUGIN.SLOWDOWN_MESSAGE == false) {
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.AlreadySlow")));
		
	}	
		
		return true;
	}

}