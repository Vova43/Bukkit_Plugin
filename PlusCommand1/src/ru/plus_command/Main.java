package ru.plus_command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
//import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
	
	 String command = "";
     String description = "";
     List<String> alias = null;
     String usage = "";
     String permMessage = "";
     Plugin plugin;
    
	File config;
	static YamlConfiguration Config;
    //static ServicesManager sm = Bukkit.getServer().getServicesManager();

    public void loadConfig() {
    	config = new File(getDataFolder().getAbsolutePath()+"//Config.yml");
    	Config = YamlConfiguration.loadConfiguration(config);
    	if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info(this.getName()+" Create dir > "+new File(getDataFolder().getAbsolutePath()).getName());
        	}
        }
    	if (!config.exists()) {
			try {
				FileOutputStream out = new FileOutputStream(config);
				out.close();
			} 
			catch (FileNotFoundException e) {
					e.printStackTrace();
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
			Config.set("MultiCommandSeparatorSymbol", "; ");
			Config.set("CommandSeparatorSymbol", ":");
			Config.set("ErrorCommandRun", "&c♦ &7This command can only execute player &6>> &c{command_name}");
			Config.set("ColorCodesPause", true);
			
			Config.set("Command.gm_set.RunCommand", "player:gm {0} {1}; console:say test1 {0} {1}; player:say test2 {player} {0} {1}");
			Config.set("Command.gm_set.MinArgument", 1);
			Config.set("Command.gm_set.MaxArgument", 2);
			Config.set("Command.gm_set.Usenge", "set gamemode");
			Config.set("Command.gm_set.Defaultpermission", false); 
			Config.set("Command.gm_set.Permission", "test.use"); 
			Config.set("Command.gm_set.PermissionMessange", "&cYou do not have permission to do that!"); 
			
			Config.set("Command.gm_set1.RunCommand", "player:gm 1;");
			Config.set("Command.gm_set1.MinArgument", 1);
			Config.set("Command.gm_set1.MaxArgument", 1);
			Config.set("Command.gm_set1.Usenge", "set gamemode");
			Config.set("Command.gm_set1.Defaultpermission", false); 
			Config.set("Command.gm_set1.Permission", "test.use"); 
			Config.set("Command.gm_set1.PermissionMessange", "&cYou do not have permission to do that!"); 
			try {
				Config.save(config);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			getLogger().info("PlusCommand: Create Config.yml");
		}
    	
    	for (String command : Config.getConfigurationSection("Command").getKeys(false)) {
			registerCommand(command);
		}
    }
    
    public static YamlConfiguration getYamlConfiguration() {
		return Config;
    }
    
    public void registerCommand(String command) {
        try{
    		//pm = getServer().getPluginManager();
        	Execute_command_class command_class_  = new Execute_command_class(command, this);
        	final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(command, command_class_);
            //pm.registerEvents(command_class_, this);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if (sender instanceof Player) {
    		
    	}
    	
    	if (args.length == 1) {
			if (args[0].equalsIgnoreCase("Reload")) {
				PluginReload();
				sender.sendMessage(plugin.getName()+" > Reload");
	    		return true;
			}
		}
		
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("Create")) {
				registerCommand(args[1]);
				sender.sendMessage(plugin.getName()+" > Create "+args[1]);
	    		return true;
			}
            if (args[0].equalsIgnoreCase("Delete")) {
            	//unRegisterCommand(args[1]);
        		return true;
			}
		}else {
			//sender.sendMessage(">> create <name>");
			//sender.sendMessage(">> delete <name>");
		}
		return false;
    }
    
    public void PluginReload() {
    	// TODO Plugin Reload
    	Bukkit.getServer().getPluginManager().disablePlugin(plugin);
    	Bukkit.getServer().getPluginManager().enablePlugin(plugin);
		loadConfig();
		//if (showConsole){ 
			plugin.getLogger().info(plugin.getName()+" > Reload");
		//}
    }
    
    PluginManager pm;
	public void onEnable() {
		pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new Command_preprocess_class(this), this);
		plugin = this;
		loadConfig();
		
        //registerCommand("test_z");
        
        getLogger().info("PlusCommand: Author Vova436612(Vova43#5468)");
    }
	
    public void onDisable() {
        //getLogger().info("ChatPrefix is disabled! by Gorodlilov.ru");
    }
}
