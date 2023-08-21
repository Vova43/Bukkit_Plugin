package ru.plus_command;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class Command_preprocess_class implements Listener {
	Plugin plugin;
    Core_run_command Core_run_command_ = new Core_run_command();
	
	public Command_preprocess_class(Plugin plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
	  if (event.isCancelled())
	    return; 
	  YamlConfiguration Config = Main.getYamlConfiguration();
	  CommandSender sender = event.getPlayer();
	  
	  String[] commandSeporator = event.getMessage().split(" ");
	  //event.getPlayer().sendMessage("Command>> "+commandSeporator[0]);
	  ArrayList<String> commandArgs = new ArrayList<String>();
	  for (int i = 1; i < commandSeporator.length; i++) {
		  commandArgs.add(commandSeporator[i]);
	  }
	  
	  
	  //event.getPlayer().sendMessage("Agrs>> "+commandArgs.toString());
	  //event.getPlayer().sendMessage("");
	  
	  
	  for (String command : Config.getConfigurationSection("Command").getKeys(false)) {
		  //event.getPlayer().sendMessage("Commands list>> "+command);
		  String test_command = commandSeporator[0];
		  test_command = test_command.replaceFirst("/", "");
		  if (command.equalsIgnoreCase(test_command)) {
			  Core_run_command_.runCommand(sender, command, commandArgs.toArray(new String[0]));
			  event.setCancelled(true);
		  }
	  }
	  
	  
	  //event.getPlayer().sendMessage(">> "+event.getMessage());
	}

}
