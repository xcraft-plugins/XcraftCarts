package de.xcraft.xcraftcarts;

import org.bukkit.Bukkit;
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
    @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
    public void onVehicleMove(VehicleMoveEvent event) {
    	
    	//TODO: Test Slowdown
    	//cart.getScoreboardTags.equals("ardabahn")
    	//final String entitytag = PLUGIN.getConfig().getString("CONFIG.EntityTag");
    	
        Minecart cart = (Minecart) event.getVehicle();
    	
    		   	
        if (PLUGIN.runtimer == true) {
        	
            //Minecart cart = (Minecart) event.getVehicle();
            
            //
            // boost minecart speed
            //
            
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn") && !cart.isEmpty())
            
            	//vel = new SetVelocity(cart, PLUGIN.velocity);
            	setVelocity(cart, PLUGIN.velocity);
            
            //
            // remove derailed/emty minecart
            //
                
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn") && cart.isEmpty())
            
            	cart.remove();
            	
            return;
            
        }else{
        	
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn")) {
            	
            	//vel = new SetVelocity(cart, 0.4);
            	setVelocity(cart, 0.4);
            	
            }
        	
        }
        
    }
      
    //
    // checks for barrier blocks and set velocity
    //

    private void setVelocity(Minecart cart, double vel) {
    	 
    	System.out.println(PLUGIN.runtimer + ", " + vel);
    	
        //Barriers 3 blocks underneath speeds up the cart
        if (cart.getLocation().getBlock().getRelative(BlockFace.DOWN, 3).getType() == Material.BARRIER) {

            
        	//set allowed max speed
            cart.setMaxSpeed(vel);
        	
        	
        } else {
        	
        	cart.setMaxSpeed(0.4);
        	
        }

    }
        
}

/**

package de.xcraft.xcraftcarts;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Minecart;

public class SetVelocity {
	
    //
    // checks for barrier blocks and set velocity
    //

    private double vel;

	public SetVelocity(Minecart cart, double vel) {
        
        //Barriers 3 blocks underneath speeds up the cart
        if (cart.getLocation().getBlock().getRelative(BlockFace.DOWN, 3).getType() == Material.BARRIER) {

            
        	//set allowed max speed
            cart.setMaxSpeed(vel);
        	
        	
        } else {
        	
        	cart.setMaxSpeed(0.4);
        	
        }
        
        Bukkit.getConsoleSender().sendMessage(PLUGIN.runtimer + ", " + vel);
	
	}
    
	public void setVel(double vel) {
		this.vel = vel;
	}			
	
	public double getVel() {
		return vel;
	}

}

 **/




