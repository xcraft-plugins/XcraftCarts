package de.xcraft.xcraftcarts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;


public class EventListener implements Listener {

    //register main class
    public EventListener() {
    }
    
    
    //EventHandler
	@EventHandler(priority = EventPriority.LOWEST,ignoreCancelled = true)
    public void onVehicleMove(VehicleMoveEvent event) {
		
        Minecart cart = (Minecart) event.getVehicle();
    	
        if (!(cart instanceof Minecart))
            return;
    		   	
        if (PLUGIN.RUNTIMER == true) {
        	
            
            //
            // boost minecart speed
            //
            
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals(PLUGIN.getConfig().getString("CONFIG.EntityName")) && !cart.isEmpty())
            
            	setVelocity(cart, PLUGIN.getConfig().getDouble("CONFIG.maxSpeed"));
            
            //
            // remove derailed/emty minecart
            //
                
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals(PLUGIN.getConfig().getString("CONFIG.EntityName")) && cart.isEmpty())
            
            	cart.remove();
            	
            return;
            
        } else {
        	
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals(PLUGIN.getConfig().getString("CONFIG.EntityName"))) {
            	
            	setVelocity(cart, 0.4);
            	
            }
        	
        }
        
    }
      
    //
    // checks for barrier blocks and set velocity
    //

    private void setVelocity(Minecart cart, double vel) {
    	
    	//For debugging
    	if (PLUGIN.getConfig().getBoolean("CONFIG.debug", true)) {
    	
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.YELLOW + "[XCartsDebug] MaxSpeed:" + vel + " " + PLUGIN.RUNTIMER);
    	
    	}
		
    	//block = PLUGIN.getConfig().getString("CONFIG.blockType");
    	Material speed_block = Material.valueOf(PLUGIN.getConfig().getString("CONFIG.blockType").toUpperCase());
    	
        //Barriers 3 blocks underneath speeds up the cart
        if (cart.getLocation().getBlock().getRelative(BlockFace.DOWN, 3).getType() == speed_block) {

            
            //set allowed max speed
            cart.setMaxSpeed(vel);
        	
        	
        } else {
        	
        	cart.setMaxSpeed(0.4);
        	
        }

    }
        
}




