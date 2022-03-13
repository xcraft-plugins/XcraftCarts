package de.xcraft.xcraftcarts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.xcraft.xcraftcarts.XcraftCarts.PLUGIN;

public class CommandHandler implements CommandExecutor, TabCompleter {
	
	//register main class
	public CommandHandler() {}
	
	//
	// tab complete
	//
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();

		if (args.length == 1) {
			completions.addAll(Arrays.asList("help", "speedup", "slowdown", "version"));

			if (sender.isOp()) {
				completions.add("reload");
			}
		}
		
		return completions;
	}
	
	//
	// Valid command handling
	//
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof ConsoleCommandSender || sender instanceof Player)
			&& args.length >= 1) {
			
		    switch (args[0]) {
	    		case "speedup":
	    			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Speedup"));
					PLUGIN.runtimer = true;

	    			PLUGIN.getServer().getPluginManager().registerEvents(new EventListener(), PLUGIN);

					new BukkitRunnable()
					{
						@Override
						public void run()
						{
							PLUGIN.runtimer = false;
							Bukkit.getServer().getConsoleSender().sendMessage("Plugin.RUNTIMER");

							new BukkitRunnable() {
								@Override
								public void run() {
									HandlerList.unregisterAll(PLUGIN);
								}
							}.runTaskLater(PLUGIN, 5);


						}
					}.runTaskLater(PLUGIN, PLUGIN.getConfig().getInt("CONFIG.activationTime"));

	    			return true;

	    		case "slowdown":
	    			if (sender.isOp()) {
						HandlerList.unregisterAll(PLUGIN);

						sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Slowdown"));
						PLUGIN.runtimer = false;
					}
	    			return true;

	    		case "help":
	    			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Help"));
	    			return true;

	    		case "reload":
	                sender.sendMessage(ChatColor.GREEN + PLUGIN.getConfig().getString("MESSAGES.Reload"));

					PLUGIN.reloadConfig();
	                return true;
	    			
	    		case "version":
	    			PluginDescriptionFile v = PLUGIN.getDescription();
	    			sender.sendMessage(ChatColor.DARK_AQUA + PLUGIN.getConfig().getString("MESSAGES.Version") + ChatColor.GREEN + v.getVersion());
	    			return true;
	 
	    		default:
	    			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.Invalid_cmd"));
	    			return true;
		    	}
		    	
		//
		// Error handling
		//
		    
		//Invalid command args
		} else if (args.length < 1 || (args[0].isEmpty())) {
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.Invalid_cmd"));
			return true;
		
		//No Permissions
		} else if (sender.isOp()) {
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.NoPerms"));
			return true;
			
		} else {
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.NoPerms"));
			return true;
				
		}
	
	}

}
