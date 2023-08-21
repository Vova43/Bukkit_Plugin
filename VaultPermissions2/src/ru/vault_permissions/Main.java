package ru.vault_permissions;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
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
	static Vault_API Vault_api;
    ColorCodesPause cccp = new ColorCodesPause();

	File permissions;
	static YamlConfiguration Permissions;
	File config;
	static YamlConfiguration Config;
	public void onEnable() {
        plugin = this;
        
        this.getServer().getPluginManager().registerEvents(this, this); 
        this.getCommand("Vault_Permissions").setExecutor(new CommandMain(plugin, this));
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
		config = new File(getDataFolder().getAbsolutePath()+"//Config.yml");
		Config = YamlConfiguration.loadConfiguration(config);
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

	        //Config.set("ShowMessageUseCommand", list1);
	        List<String> list1 = new ArrayList<String>();
	        //list1.add("permissions.blockbreak");
	        //► ◄ ◊ ♦
	        list1.add("&6◄&2Vault Permissions&6►  &7 Command help:");
	        list1.add("&6► &3/&4Vault_Permissions &7create &6<group> &7- Create group");
	        list1.add("&6► &3/&4Vault_Permissions &7delete &6<group> &7- Delete group");
	        list1.add("&6► &3/&4Vault_Permissions &7user <add|set|remove> &6<group> &2<namePlayer> &7- Edit player");
	        list1.add("&6► &3/&4Vault_Permissions &7groupList &7- Group list");
	        list1.add("&6► &3/&4Vault_Permissions &7groupListPlayer &2<namePlayer> &7- Info player group");
	        list1.add("&6► &3/&4Vault_Permissions &7reload &7- Reload plugin");

	        Config.set("ShowMessageUseCommand", list1);
	        
	        Config.set("HelpPermissions", "Vault_Permissions.help");
	        Config.set("HelpPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("ReloadMessange", "&2Reload plugin");
	        Config.set("ReloadPermissions", "Vault_Permissions.reload");
	        Config.set("ReloadPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("EditPermissions", "Vault_Permissions.edit");
	        Config.set("EditPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("UserPermissions", "Vault_Permissions.user");
	        Config.set("UserPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("CreatePermissions", "Vault_Permissions.create");
	        Config.set("CreatePermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("DeletePermissions", "Vault_Permissions.delete");
	        Config.set("DeletePermissionsMessange", "&cYou do not have permission to do that!");

	        Config.set("GroupListPermissions", "Vault_Permissions.groupList");
	        Config.set("GroupListPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("GroupListPlayerPermissions", "Vault_Permissions.groupListPlayer");
	        Config.set("GroupListPlayerPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Config.set("GroupListFormat", "&7Group list &2> &6%groupListFormat");
	        Config.set("GroupListPlayerFormat", "&7Group list player &2> &6%groupListPlayerFormat");
	        
	        Config.set("UseCommand_Create", "&6► &3/&4Vault_Permissions &7create &6<group> &7- Create group");
	        Config.set("UseCommand_Delete", "&6► &3/&4Vault_Permissions &7delete &6<group> &7- Delete group");
	        List<String> list4 = new ArrayList<String>();
	        list4.add("&6► &3/&4Vault_Permissions &7user <add|set|remove> &6<group> &2<namePlayer> &7- Edit group player");
	        list4.add("&6► &3/&4Vault_Permissions &7user <addUser|removeUser> &2<namePlayer> &7- Edit list player");
	        Config.set("UseCommand_User_Use", list4);
	        List<String> list5 = new ArrayList<String>();
	        list5.add("&6► &3/&4Vault_Permissions &7edit <default|parent|permissions> &6<group> &2<value> &7- Edit group");
	        Config.set("UseCommand_Edit_Use", list5);
	        Config.set("UseCommand_Edit_Parent", "&6► &3/&4Vault_Permissions &7edit parent &6<group> &2<parent group> &7- Edit group parent");
	        Config.set("UseCommand_Edit_Default", "&6► &3/&4Vault_Permissions &7edit default &6<group> &2<true|false> &7- Edit group default");
	        Config.set("UseCommand_Edit_Permissions", "&6► &3/&4Vault_Permissions &7edit permissions &6<group> &7<add|set|remove> &2<permissions> &7- Edit group permissions");

	        Config.set("UseCommand_Edit_Permissions_Add", "&6► &3/&4Vault_Permissions &7edit permissions &6<group> &7add &2<permissions> &7- Edit group add permissions");
	        Config.set("UseCommand_Edit_Permissions_Set", "&6► &3/&4Vault_Permissions &7edit permissions &6<group> &7set &2<permissions> &7- Edit group set permissions");
	        Config.set("UseCommand_Edit_Permissions_Remove", "&6► &3/&4Vault_Permissions &7edit permissions &6<group> &7remove &2<permissions> &7- Edit group remove permissions");

	        Config.set("UseCommand_Edit_SetPrefix", "&6► &3/&4Vault_Permissions &7edit setPrefix &6<group> &2<prefix> &7- Set group prefix");
	        Config.set("UseCommand_Edit_SetSuffix", "&6► &3/&4Vault_Permissions &7edit setSuffix &6<group> &2<suffix> &7- Set group suffix");
	        
	        Config.set("UseCommand_User_Reset_User", "&6► &3/&4Vault_Permissions &7user removeUser &2<namePlayer> &7- Remove list list");
	        Config.set("UseCommand_User_Add", "&6► &3/&4Vault_Permissions &7user add &6<group> &2<namePlayer> &7- Add player group");
	        Config.set("UseCommand_User_Set", "&6► &3/&4Vault_Permissions &7user set &6<group> &2<namePlayer> &7- Set player group");
	        Config.set("UseCommand_User_Remove", "&6► &3/&4Vault_Permissions &7user remove &6<group> &2<namePlayer> &7- Remove player group");
	        
	        Config.set("UseCommand_User_SetPrefix", "&6► &3/&4Vault_Permissions &7user setPrefix &6<namePlayer> &2<prefix> &7- Set player prefix");
	        Config.set("UseCommand_User_ClearPrefix", "&6► &3/&4Vault_Permissions &7user clearPrefix &6<namePlayer> &7- Clear player prefix");
	        Config.set("UseCommand_User_SetSuffix", "&6► &3/&4Vault_Permissions &7user setSuffix &6<namePlayer> &2<suffix> &7- Set player suffix");
	        Config.set("UseCommand_User_ClearSuffix", "&6► &3/&4Vault_Permissions &7user clearSuffix &6<namePlayer> &7- Clear player suffix");
	        
	        Config.set("UseCommand_GroupListPlayer", "&6► &3/&4Vault_Permissions &7groupListPlayer &2<namePlayer> &7- Info player group");

	        Config.set("Successfully_Create", "&6► &7Create group &6%args1");
	        Config.set("Successfully_Delete", "&6► &7Delete group &6%args1");
	        
	        Config.set("Successfully_User_ResetUser", "&6► &7Remove player list &6%args1");
	        Config.set("Successfully_User_Add", "&6► &7Add player group &6%args1 &2%args2");
	        Config.set("Successfully_User_Set", "&6► &7Set player group &6%args1 &2%args2");
	        Config.set("Successfully_User_Remove", "&6► &7Remove player group &6%args1 &2%args2");
	        
	        Config.set("Successfully_User_SetPrefix", "&6► &7Set prefix &6%args1 &2%args2");
	        Config.set("Successfully_User_ClearPrefix", "&6► &7Clear prefix &6%args1");
	        Config.set("Successfully_User_SetSuffix", "&6► &7Set suffix &6%args1 &2%args2");
	        Config.set("Successfully_User_ClearSuffix", "&6► &7Clear suffix &6%args1");
	        
	        Config.set("Successfully_Edit_SetPrefix", "&6► &7Set prefix group &6%args1 &2%args2");
	        Config.set("Successfully_Edit_SetSuffix", "&6► &7Set suffix group &6%args1 &2%args2");
	        
	        Config.set("Successfully_Edit_Default", "&6► &7Edit default group &6%args1 &7set &2%args2");
	        Config.set("Successfully_Edit_Parent", "&6► &7Edit parent group &6%args1 &7set &2%args2");
	        Config.set("Successfully_Edit_Permissions_Set", "&6► &7Edit permissions group &6%args1 &7set &2%args2");
	        Config.set("Successfully_Edit_Permissions_Add", "&6► &7Edit permissions group &6%args1 &7add &2%args2");
	        Config.set("Successfully_Edit_Permissions_Remove", "&6► &7Edit permissions group &6%args1 &7remove &2%args2");
	        
	        try {
	        	Config.save(config);
				getLogger().info(plugin.getName()+" Create file > "+config.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		startRegisterVault_API();
    }
	
	public static void startRegisterVault_API() {
		Vault_api = new Vault_API(plugin);
	    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
	    	Vault_Permissions_Class vault_Permissions_Class = new Vault_Permissions_Class(plugin, Vault_api);
	        vault_Permissions_Class.register();
	        new Vault_Chat_Class(plugin, Vault_api, vault_Permissions_Class).register();
	        
	    }
		for (Player player : Bukkit.getOnlinePlayers()) {
			Vault_api.givePermissions(player);
		}
	}
	
	public static YamlConfiguration getСonfigYAML() {
		return Config;
        //playerPermissions.clear();
    }
	
	public static Plugin getPlugin() {
		return plugin;
        //playerPermissions.clear();
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
        Vault_api.givePermissions(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }
    
}
