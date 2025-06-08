package de.xcraft.xcraftcarts;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import de.xcraft.xcraftcarts.commands.HelpCommand;
import de.xcraft.xcraftcarts.commands.ReloadCommand;
import de.xcraft.xcraftcarts.commands.SlowdownCommand;
import de.xcraft.xcraftcarts.commands.SpeedupCommand;
import de.xcraft.xcraftcarts.commands.VersionCommand;

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
			completions.addAll(Arrays.asList("help", "speedup", "version"));

			if (sender.isOp() || sender.hasPermission("xcarts.*")) {
				completions.addAll(Arrays.asList("slowdown", "reload"));
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
	    			
	    			SpeedupCommand.Speedup(sender);
	    			return true;

	    		case "slowdown":
	    			
	    			SlowdownCommand.Slowdown(sender);
	    			return true;

	    		case "help":
	    			
	    			HelpCommand.PluginHelp(sender);
	    			return true;

	    		case "reload":
	    			
					ReloadCommand.PluginReload(sender);
	                return true;
	    			
	    		case "version":
	    			
	    			VersionCommand.PluginInfo(sender);
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
		} else {
			sender.sendMessage(ChatColor.RED + PLUGIN.getConfig().getString("MESSAGES.NoPerms"));
			return true;
				
		}
	
	}

}
