package ru.vault_permissions;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.vault_permissions.vault.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
	public static Main plugin;
	Vault_API VaultPermissionsAPI;
    ColorCodesPause cccp = new ColorCodesPause();

	File permissions;
	YamlConfiguration Permissions;
	File messange;
	YamlConfiguration Messange;
	public void onEnable() {
        plugin = this;
        
        this.getServer().getPluginManager().registerEvents(this, this);
        if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info(plugin.getName()+" Create dir > "+new File(getDataFolder().getAbsolutePath()).getName());
        	}
        }
        
        permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
        Permissions = YamlConfiguration.loadConfiguration(permissions);
		if (!permissions.exists()) {
	        try {
	        	
				FileOutputStream out = new FileOutputStream(permissions);
				out.close();
			} 
			catch (FileNotFoundException e) {
					e.printStackTrace();
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	        List<String> list1 = new ArrayList<String>();
	        List<String> list2 = new ArrayList<String>();
	        List<String> list3 = new ArrayList<String>();
	        //list1.add("permissions.blockbreak");
	        list1.add("prefix.default_member");
	        list2.add("prefix.vip");
	        list3.add("minecraft.command.gamemode");
	        list3.add("minecraft.command.gamemode.survival");
	        list3.add("minecraft.command.gamemode.creative");
	        
	        Permissions.set("Groups.default_member.prefix", "&7[&3&nPlayer&7]");
	        Permissions.set("Groups.default_member.suffix", "&7[&6◊&7]");
	        Permissions.set("Groups.default_member.parent", "root");
	        Permissions.set("Groups.default_member.default", true);
	        Permissions.set("Groups.default_member.permissions", list1);
	        
	        Permissions.set("Groups.vip.prefix", "&7[&6&nVip&7]");
	        Permissions.set("Groups.vip.suffix", "&7[&3◊&7]");
	        Permissions.set("Groups.vip.parent", "default_member");
	        Permissions.set("Groups.vip.default", false);
	        Permissions.set("Groups.vip.permissions", list2);
	        
	        Permissions.set("Groups.owner.prefix", "&7[&c&nOwner&7]");
	        Permissions.set("Groups.owner.suffix", "&7[&2◊&7]");
	        Permissions.set("Groups.owner.parent", "vip");
	        Permissions.set("Groups.owner.default", false);
	        Permissions.set("Groups.owner.permissions", list3);
	        
	        try {
	        	Permissions.save(permissions);
	        	plugin.getLogger().info(plugin.getName()+" Create file > "+permissions.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info(plugin.getName()+" Create dir > "+new File(getDataFolder().getAbsolutePath()).getName());
        	}
        }
		messange = new File(getDataFolder().getAbsolutePath()+"//Messange.yml");
		Messange = YamlConfiguration.loadConfiguration(messange);
		if (!messange.exists()) {
	        try {
	        	
				FileOutputStream out = new FileOutputStream(messange);
				out.close();
			} 
			catch (FileNotFoundException e) {
					e.printStackTrace();
			} 
			catch (IOException e) {
					e.printStackTrace();
			}

	        Messange.set("ReloadMessange", "&2Reload plugin");
	        Messange.set("ReloadPermissions", "just_permissions.reload");
	        Messange.set("ReloadPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        try {
	        	Messange.save(messange);
				getLogger().info(plugin.getName()+" Create file > "+messange.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		VaultPermissionsAPI = new Vault_API(this);
        Vault_Permissions_Class vault_Permissions_Class = new Vault_Permissions_Class(this, VaultPermissionsAPI);
        vault_Permissions_Class.register();
        new Vault_Chat_Class(this, VaultPermissionsAPI, vault_Permissions_Class).register();
        
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			VaultPermissionsAPI.givePermissions(player);
		}
    }

    public void onDisable() {
        //playerPermissions.clear();
    }

    /*
    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("permissions.blockbreak")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You do not have permission to do that!");
        }
    }
    */
    
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //setupPermissions(player);
        VaultPermissionsAPI.givePermissions(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }
    
    
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("vault_permissions") && sender instanceof Player){
    		if(args.length == 0){
    			
    		}
    		else if(args[0].equalsIgnoreCase("reload")){
    			if (sender.hasPermission(Messange.getString("ReloadPermissions"))) {
    				if (!permissions.exists() && !messange.exists()) {
        		    	onEnable();
        		    }else {
        		    	VaultPermissionsAPI.PluginReload();
            			File permissions = new File(getDataFolder().getAbsolutePath()+"//Permissions.yml");
            			Permissions = YamlConfiguration.loadConfiguration(permissions);  	
            			File messange = new File(getDataFolder().getAbsolutePath()+"//Messange.yml");
            			Messange = YamlConfiguration.loadConfiguration(messange);  	
            		    getLogger().info(plugin.getName()+" Reload > "+permissions.getName() + " " + messange.getName());
        		    }
        			for (Player player : Bukkit.getOnlinePlayers()) {
        				VaultPermissionsAPI.givePermissions(player);
        			}
        		    sender.sendMessage(cccp.FormatPause(Messange.getString("ReloadMessange")));
        		}else {
        			sender.sendMessage(cccp.FormatPause(Messange.getString("ReloadPermissionsMessange")));
        		}
    		}
    	}else {
            if(args.length == 0){
    			
    		}
    		else if(args[0].equalsIgnoreCase("reload")){
    			if (!permissions.exists() && !messange.exists()) {
    		    	onEnable();
    		    }else {
    		    	VaultPermissionsAPI.PluginReload();
        			File permissions = new File(getDataFolder().getAbsolutePath()+"//Permissions.yml");
        			Permissions = YamlConfiguration.loadConfiguration(permissions);  	
        			File messange = new File(getDataFolder().getAbsolutePath()+"//Messange.yml");
        			Messange = YamlConfiguration.loadConfiguration(messange);  	
        		    getLogger().info(plugin.getName()+" Reload > "+permissions.getName() + " " + messange.getName());
    		    }
    			for (Player player : Bukkit.getOnlinePlayers()) {
    				VaultPermissionsAPI.givePermissions(player);
    			}
    		}
    	}
    	return false; 
    }

}
