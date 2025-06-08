package de.xcraft.xcraftcarts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;


public final class XcraftCarts extends JavaPlugin {

    private final CommandHandler commands = new CommandHandler();
    
    public static XcraftCarts PLUGIN;
    
    public boolean SLOWDOWN_MESSAGE = false;
    public boolean RUNTIMER = false;
    
    public static Economy eco = null;

    
    //
    //on startup
    //
    
    public void onEnable() {
        PLUGIN = this;

        saveDefaultConfig();

        //register commands
        getCommand("xcarts").setExecutor(commands);
        getCommand("xcarts").setTabCompleter(commands);
        
        //check vault       
        if (PLUGIN.getConfig().getDouble("CONFIG.price") > 0) {
        	
        	setupEconomy();
        
        	if (!setupEconomy() ) {
        	
        		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        		console.sendMessage(ChatColor.RED + "[XcraftCarts] Disabled due to no Vault/Economy dependency found!");
        	
        		getServer().getPluginManager().disablePlugin(this);
        		return;
            
        	}
            
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
