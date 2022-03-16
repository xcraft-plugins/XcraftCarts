package de.xcraft.xcraftcarts;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;


public class EventListener implements Listener {

    //register main class
    public EventListener() {
    }

    //EventHandler
    @EventHandler(priority = EventPriority.LOWEST)
    public void onVehicleMove(VehicleMoveEvent event) {

        if (PLUGIN.runtimer) {
            Minecart cart = (Minecart) event.getVehicle();
            
            //
            // boost minecart speed
            //

            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null
                    && cart.getCustomName().equals("ardabahn") && !cart.isEmpty()) {

            	if (cart.getLocation().getBlock().getRelative(BlockFace.UP, 2).getType() == Material.BARRIER) {
            	
                    setVelocity(cart, 0.4);
                
            	} else if (cart.getLocation().getBlock().getRelative(BlockFace.DOWN, 2).getType() == Material.BARRIER) {
                    	
                    setVelocity(cart, 2.0);
                    
            	} else {
            		
            		setVelocity(cart, 1.0);
            		
            	}
            	

            //
            // remove derailed/emty minecart
            //
                
            } else if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null
                    && cart.getCustomName().equals("ardabahn") && cart.isEmpty()) {
                
            	cart.remove();
                return;

            } else {

                return;

            }

        //
        // Reset minecart speed
        //
            
        } else {
            Minecart cart = (Minecart) event.getVehicle();
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null
                    && cart.getCustomName().equals("ardabahn") && !cart.isEmpty()) {
                
            	setVelocity(cart, 0.40);
            }
        }

    }

    private void setVelocity(Minecart cart, double v) {
        BlockFace facing = cart.getFacing();
		
        switch (facing.name()) {

            case "DOWN":
                cart.setMaxSpeed(v);
                cart.setVelocity(new Vector(0.0, -v, 0.0));
                return;

            case "EAST":
                cart.setMaxSpeed(v);
                cart.setVelocity(new Vector(v, 0.0, 0.0));
                return;

            case "NORTH":
                cart.setMaxSpeed(v);
                cart.setVelocity(new Vector(0.0, 0.0, -v));
                return;
                
            case "SOUTH":
                cart.setMaxSpeed(v);
                cart.setVelocity(new Vector(0.0, 0.0, v));
                return;

            case "UP":
                cart.setMaxSpeed(v);
                cart.setVelocity(new Vector(0.0, v, 0.0));
                return;

            case "WEST":
                cart.setMaxSpeed(v);
                cart.setVelocity(new Vector(-v, 0.0, 0.0));
                return;

            default:
                cart.setMaxSpeed(0.40);
                return;
                
        }
        
    }

}
