package de.xcraft.xcraftcarts;

import org.bukkit.plugin.java.JavaPlugin;

public final class XcraftCarts extends JavaPlugin {

    private final CommandHandler commands = new CommandHandler();

    public boolean runtimer = false;

    public static XcraftCarts PLUGIN;

    //on startup
    public void onEnable() {
        PLUGIN = this;

        saveDefaultConfig();

        //register commands
        getCommand("xcarts").setExecutor(commands);
        getCommand("xcarts").setTabCompleter(commands);


    }

    //on shutdown
    public void onDisable() {

    }
}
