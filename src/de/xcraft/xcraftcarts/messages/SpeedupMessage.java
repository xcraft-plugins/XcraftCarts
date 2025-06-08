package de.xcraft.xcraftcarts.messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

import de.xcraft.xcraftcarts.MessageHandler;
import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class SpeedupMessage extends MessageHandler {

	@Override
	public boolean informPassengers(CommandSender sender) {

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Speedup")));
		
        for (Player player : Bukkit.getOnlinePlayers()) {

        	Minecart cart = (Minecart) player.getVehicle();
        	
            if (cart instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals(PLUGIN.getConfig().getString("CONFIG.EntityName")) && !player.equals(sender) && !cart.isEmpty()) {
            	
            	String message_SpeedupOther = PLUGIN.getConfig().getString("MESSAGES.SpeedupOther").replace("%sendername%", sender.getName());
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&',message_SpeedupOther));
            }
            
            if (cart instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals(PLUGIN.getConfig().getString("CONFIG.EntityName")) && sender instanceof ConsoleCommandSender && !cart.isEmpty()) {
            	
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&',PLUGIN.getConfig().getString("MESSAGES.Speedup")));
            }
            
        }
		
		return true;
	}

}