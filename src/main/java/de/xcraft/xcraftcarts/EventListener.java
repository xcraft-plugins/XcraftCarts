package de.xcraft.xcraftcarts;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;


public class EventListener implements Listener {

    //register main class
    public EventListener() {
    }

    //EventHandler
    @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
    public void onVehicleMove(VehicleMoveEvent event) {
    	
        if (PLUGIN.runtimer) {
            Minecart cart = (Minecart) event.getVehicle();
            
            //
            // boost minecart speed
            //
            
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn") && !cart.isEmpty())
            
            	getRail(cart);
            
            //
            // remove derailed/emty minecart
            //
                
            if (event.getVehicle() instanceof Minecart && cart.getCustomName() != null && cart.getCustomName().equals("ardabahn") && cart.isEmpty())
            
            	cart.remove();
            	
            return;
            
        }

    }

    //
    // check rail condition
    //
    
    private void getRail(Minecart cart) {
    	
    	//Get orientation/shape of rail
        BlockData blockData = cart.getLocation().getBlock().getBlockData();
        
    	if (blockData instanceof Rail) {
    		Rail rail = (Rail) blockData;
    		Rail.Shape shape = rail.getShape();

	    	//Placing barriers 3 blocks underneath slows down the minecart
    		//Minecarts on ASCENDING "A" rails get slown down
	    	if (cart.getLocation().getBlock().getRelative(BlockFace.DOWN, 3).getType() == Material.BARRIER || shape.toString().charAt(0) == 'A') {
	    	
	            setVelocity(cart, 0.4);
	            
	    	} else {
	    		
	    		setVelocity(cart, 1.0);
	    		
	    	}
    	
    	}
	    	
    }
   
    //
    // check minecart orientation and set velocity
    //

    private void setVelocity(Minecart cart, double v) {
        
    	//set allowed max speed
        cart.setMaxSpeed(v);
        
        //Convert minecraft orientation into 360°
        double rotation = (cart.getLocation().getYaw() - 90.0F) % 360.0F;
        if (rotation < 0.0D) rotation += 360.0D;

        //Get facing of minecart
        //minecarts only recognize EAST, WEST, SOUTH, NORTH
        BlockFace facing = cart.getFacing();
	    						
		switch (facing.name()) {
	                    
	  		case "EAST":

	  			if (rotation == 135.0 || rotation == 180.0)
	  				cart.setVelocity(new Vector(v, 0.0, 0.0));
	                    
	  			return;
	
	  		case "NORTH":
	                    
	  			if (rotation == 0.0D || rotation == 45.0D || rotation == 360.0D || rotation == 315.0D)
	  				cart.setVelocity(new Vector(0.0, 0.0, -v)); 
	                    
	  			return;
	                    
	  		case "SOUTH":
	                    
	  			if (rotation == 180.0D || rotation == 135.0D || rotation == 225.0D)
	  				cart.setVelocity(new Vector(0.0, 0.0, v));
	                    
	  			return;
	
	  		case "WEST":
	                    
	  			if (rotation == 280.0D || rotation == 335.0D || rotation == 235.0D)
	                cart.setVelocity(new Vector(-v, 0.0, 0.0));
	                    
	            return;
	
	  		default:
	                    
	  			return;
	                    
      
        }
        	
    }

}