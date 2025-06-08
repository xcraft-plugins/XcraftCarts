package de.xcraft.xcraftcarts;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import java.util.logging.Logger;


public final class XcraftCarts extends JavaPlugin {

    private final CommandHandler commands = new CommandHandler();
    
    public boolean runtimer = false;
    public double velocity = 0.4; 
    
    public static Economy eco = null;
    
    public static XcraftCarts PLUGIN;
    private Logger LOG;
    
    //on startup
    public void onEnable() {
        PLUGIN = this;

        saveDefaultConfig();

        //register commands
        getCommand("xcarts").setExecutor(commands);
        getCommand("xcarts").setTabCompleter(commands);
        
        //check vault
        setupEconomy();
        
        if (!setupEconomy() ) {
            LOG.info("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
            
        }
        
    }

    //on shutdown
    public void onDisable() {

    }
	
    //setup vault
    private boolean setupEconomy() {
    	
        RegisteredServiceProvider<Economy> economyProvider = PLUGIN.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
            
        }

        return (eco != null);
    }
    
    //get vault
    public static Economy getEconomy() {
        return eco;
    }
    
}
