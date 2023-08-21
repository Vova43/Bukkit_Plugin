package ru.auth;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import com.google.common.collect.HashBiMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Main extends JavaPlugin implements Listener {
	public static Main plugin;
    ColorCodesPause cccp = new ColorCodesPause();

	File сonfig;
	YamlConfiguration Сonfig;
	HashMap<Player, Model> ListPlayer = new HashMap<Player, Model>();
	public void onEnable() {
        plugin = this;
        this.getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("> Author: vova436612");
       
        // TODO Configs
		if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info("> Create dir: "+new File(getDataFolder().getAbsolutePath()).getName());
        	}
        }
		
		сonfig = new File(getDataFolder().getAbsolutePath()+"//Сonfig.yml");
		Сonfig = YamlConfiguration.loadConfiguration(сonfig);
		if (!сonfig.exists()) {
	        try {
	        	
				FileOutputStream out = new FileOutputStream(сonfig);
				out.close();
			} 
			catch (FileNotFoundException e) {
					e.printStackTrace();
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	        
	        Сonfig.set("UseVault", true);
	        
	        try {
	        	Сonfig.save(сonfig);
				getLogger().info("> Create file: "+сonfig.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		getLogger().info("> Load files");
    }

    public void onDisable() {
    	for (Player player : Bukkit.getOnlinePlayers()) {
    		player.kickPlayer("§c♦ §7Выключение сервера");
    	}
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("login"))
		{
			Player player = null;
			if (args.length == 1) {
				if(sender instanceof Player)
				{
					player = (Player) sender;
					ListPlayer.get(player).login(player, args[0]);
					//player.sendMessage(">> Нужно login: "+ args[0]);
					return true;
				}else {
					player = null;
					getLogger().info("> "+"Console can't login!");
					return true;
				}
			}
			
		}else if(commandLabel.equalsIgnoreCase("register")) {
			Player player = null;
			if (args.length == 1) {
				if(sender instanceof Player)
				{
					player = (Player) sender;
					ListPlayer.get(player).register(player, args[0]);
					//player.sendMessage(">> Нужно register: "+args[0]);
					return true;
				}else {
					player = null;
					getLogger().info("> "+"Console can't register!");
					return true;
				}
			}
		}
		return false;
	}
    
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Model Model_ = new Model(player, this);
        //getCommand("login").setExecutor(Model_);
        //getCommand("register").setExecutor(Model_);
        this.getServer().getPluginManager().registerEvents(Model_, this);
        ListPlayer.put(player, Model_);
    }
    
    public void RemoveListPlayer(Player player) {
    	ListPlayer.put(player, null);
    	ListPlayer.remove(player);
    } 
}