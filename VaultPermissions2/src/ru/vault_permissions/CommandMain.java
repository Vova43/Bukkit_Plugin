package ru.vault_permissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import ru.vault_permissions.vault.Vault_API;

public class CommandMain implements CommandExecutor, TabCompleter {
	
    Plugin plugin;
    private File permissions;
	private YamlConfiguration Permissions;
    Main main;
	Vault_API vault_api;
	boolean showConsole = false;
	ColorCodesPause cccp = new ColorCodesPause();
	
	public CommandMain(Plugin plugin, Main main) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		this.main = main;
		vault_api = new Vault_API(plugin);
		permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
        Permissions = YamlConfiguration.loadConfiguration(permissions);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	// TODO command register
    	if(cmd.getName().equalsIgnoreCase("Vault_Permissions") || cmd.getName().equalsIgnoreCase("vp")){
    		if(args.length == 0){
    			
    		}
    		else if(args[0].equalsIgnoreCase("help")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("HelpPermissions"))) {
    				for (String listMessage : Main.getСonfigYAML().getStringList("ShowMessageUseCommand")) {
        				sender.sendMessage(cccp.FormatPause(listMessage));
                    }
        		}else {
        			sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("HelpPermissionsMessange")));
        		}
    		}
    		else if(args[0].equalsIgnoreCase("reload")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("ReloadPermissions"))) {
    				vault_api.PluginReload();
    				permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
    		        Permissions = YamlConfiguration.loadConfiguration(permissions);
        			for (Player player : Bukkit.getOnlinePlayers()) {
        		        vault_api.setupPermissions(player);
        			}
        		    sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("ReloadMessange")));
        		}else {
        			sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("ReloadPermissionsMessange")));
        		}
    		}else if(args[0].equalsIgnoreCase("edit")){
    			/*
    			 * if(args.length < 4 && args.length == 1){
    	    			sender.sendMessage(cccp.FormatPause("edit list"));
    	    		}
    			 */
    			if (sender.hasPermission(Main.getСonfigYAML().getString("EditPermissions"))) {
    				if(args.length == 1){
    					for (String use_user : Main.getСonfigYAML().getStringList("UseCommand_Edit_Use")) {
    	    				sender.sendMessage(cccp.FormatPause(use_user));
    	    			}
    	    		}
    				else if (args[1].equalsIgnoreCase("default")) {
	    				if(args.length == 4){
	    					String setDefault = "";
    	    				if (args[3].equalsIgnoreCase("true")) {
    	    					 Permissions.set("Groups."+args[2]+".default", true);
    	    					 setDefault = "true";
    	    				}else{
    	    					Permissions.set("Groups."+args[2]+".default", false);
    	    					setDefault = "false";
    	    				}
    	    		       
    	    		        try {
    	    	       			Permissions.save(permissions);
    	    	       			if (showConsole){ plugin.getLogger().info(plugin.getName()+" Edit default > "+ args[2] + " set "+ setDefault);}
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
    	    		        vault_api.PluginReload();
    	    				String Successfully_Edit_Default = Main.getСonfigYAML().getString("Successfully_Edit_Default");
    	    				Successfully_Edit_Default = Successfully_Edit_Default.replace("%args1", args[2]);
    	    				Successfully_Edit_Default = Successfully_Edit_Default.replace("%args2", setDefault);
    	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Default));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Default")));
	    				}
    				}
    				else if (args[1].equalsIgnoreCase("parent")) {
	    				if(args.length == 4){
	    					Permissions.set("Groups."+args[2]+".parent", args[3]);
		    		        try {
		    	       			Permissions.save(permissions);
		    	       			if (showConsole){ 
		    	       				plugin.getLogger().info(plugin.getName()+" Edit parent > "+ args[2] + " set "+ args[3]);
		    	       			}
		    				} 
		    				catch (IOException e) {
		    					e.printStackTrace();
		    				}
		    		        vault_api.PluginReload();
		    				String Successfully_Edit_Parent = Main.getСonfigYAML().getString("Successfully_Edit_Parent");
		    				Successfully_Edit_Parent = Successfully_Edit_Parent.replace("%args1", args[2]);
		    				Successfully_Edit_Parent = Successfully_Edit_Parent.replace("%args2", args[3]);
		    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Parent));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Parent")));
	    				}
	    			}
					else if (args[1].equalsIgnoreCase("permissions")) {
						if(args.length == 2){
							sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Permissions")));
						}
						else if(args.length == 3){
							sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Permissions")));
						}
						else if (args[3].equalsIgnoreCase("set")) {
	    	    				if(args.length == 5){
	    	    					List<String> listPermissions = new ArrayList<String>();
	    	    					listPermissions.add(args[4]);
	    	    					Permissions.set("Groups."+args[2]+".permissions", listPermissions);
	        	    		        try {
	        	    	       			Permissions.save(permissions);
	        	    	       			if (showConsole){ 
	        	    	       				plugin.getLogger().info(plugin.getName()+" Edit permissions > "+ args[2] + " set "+ args[4]);
	        	    	       			}
	        	    				} 
	        	    				catch (IOException e) {
	        	    					e.printStackTrace();
	        	    				}
	        	    		        vault_api.PluginReload();
	        	    				String Successfully_Edit_Permissions_Set = Main.getСonfigYAML().getString("Successfully_Edit_Permissions_Set");
	        	    				Successfully_Edit_Permissions_Set = Successfully_Edit_Permissions_Set.replace("%args1", args[2]);
	        	    				Successfully_Edit_Permissions_Set = Successfully_Edit_Permissions_Set.replace("%args2", args[4]);
	        	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Permissions_Set));
	        	    			}else {
	        	    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Permissions_Set")));
	    	    				}
	    				}else if (args[3].equalsIgnoreCase("add")) {
	    					if(args.length == 5){
	    						List<String> listPermissions = new ArrayList<String>();
    	    					listPermissions = Permissions.getStringList("Groups."+args[2]+".permissions");
    	    					listPermissions.add(args[4]);
    	    					Permissions.set("Groups."+args[2]+".permissions", listPermissions);
        	    		        try {
        	    	       			Permissions.save(permissions);
        	    	       			if (showConsole){ 
        	    	       				plugin.getLogger().info(plugin.getName()+" Edit permissions > "+ args[2] + " add "+ args[3]);
        	    	       			}
        	    				} 
        	    				catch (IOException e) {
        	    					e.printStackTrace();
        	    				}
        	    		        vault_api.PluginReload();
        	    				String Successfully_Edit_Permissions_Add = Main.getСonfigYAML().getString("Successfully_Edit_Permissions_Add");
        	    				Successfully_Edit_Permissions_Add = Successfully_Edit_Permissions_Add.replace("%args1", args[2]);
        	    				Successfully_Edit_Permissions_Add = Successfully_Edit_Permissions_Add.replace("%args2", args[4]);
        	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Permissions_Add));
    	    					
	    					}else {
	    						sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Permissions_Add")));
		    				}
	    				}else if (args[3].equalsIgnoreCase("remove")) {
	    					if(args.length == 5){
	    						List<String> listPermissions = new ArrayList<String>();
    	    					listPermissions = Permissions.getStringList("Groups."+args[2]+".permissions");
    	    					listPermissions.remove(args[4]);
    	    					Permissions.set("Groups."+args[2]+".permissions", listPermissions);
        	    		        try {
        	    	       			Permissions.save(permissions);
        	    	       			if (showConsole){ 
        	    	       				plugin.getLogger().info(plugin.getName()+" Edit permissions > "+ args[2] + " remove "+ args[4]);
        	    	       			}
        	    				} 
        	    				catch (IOException e) {
        	    					e.printStackTrace();
        	    				}
        	    		        vault_api.PluginReload();
        	    				String Successfully_Edit_Permissions_Remove = Main.getСonfigYAML().getString("Successfully_Edit_Permissions_Remove");
        	    				Successfully_Edit_Permissions_Remove = Successfully_Edit_Permissions_Remove.replace("%args1", args[2]);
        	    				Successfully_Edit_Permissions_Remove = Successfully_Edit_Permissions_Remove.replace("%args2", args[4]);
        	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Permissions_Remove));
    	    					
	    					}else {
	    						sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_Permissions_Remove")));
	    					}
	    				}
	    			}
					else if (args[1].equalsIgnoreCase("setPrefix")) {
	    				if(args.length == 4){
	    					Permissions.set("Groups."+args[2]+".prefix", args[3]);
    	    		       
    	    		        try {
    	    	       			Permissions.save(permissions);
    	    	       			if (showConsole){ 
    	    	       				plugin.getLogger().info(plugin.getName()+" Edit prefix > "+ args[2] + " set "+ args[3]);
    	    	       			}
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
    	    		        vault_api.PluginReload();
    	    				String Successfully_Edit_SetPrefix = Main.getСonfigYAML().getString("Successfully_Edit_SetPrefix");
    	    				Successfully_Edit_SetPrefix = Successfully_Edit_SetPrefix.replace("%args1", args[2]);
    	    				Successfully_Edit_SetPrefix = Successfully_Edit_SetPrefix.replace("%args2", args[3]);
    	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_SetPrefix));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_SetPrefix")));
	    				}
    				}
					else if (args[1].equalsIgnoreCase("setSuffix")) {
	    				if(args.length == 4){
	    					Permissions.set("Groups."+args[2]+".suffix", args[3]);
    	    		       
    	    		        try {
    	    	       			Permissions.save(permissions);
    	    	       			if (showConsole){ 
    	    	       				plugin.getLogger().info(plugin.getName()+" Edit suffix > "+ args[2] + " set "+ args[3]);
    	    	       			}
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
    	    		        vault_api.PluginReload();
    	    				String Successfully_Edit_SetSuffix = Main.getСonfigYAML().getString("Successfully_Edit_SetSuffix");
    	    				Successfully_Edit_SetSuffix = Successfully_Edit_SetSuffix.replace("%args1", args[2]);
    	    				Successfully_Edit_SetSuffix = Successfully_Edit_SetSuffix.replace("%args2", args[3]);
    	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_SetSuffix));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Edit_SetSuffix")));
	    				}
    				}
        		}else {
        			sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UserPermissionsMessange")));
        		}
    		}
    		else if(args[0].equalsIgnoreCase("user")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("UserPermissions"))) {
    				if(args.length == 0){
    					sender.sendMessage(cccp.FormatPause("user list"));
    	    		}
    				else if(args.length == 1){ 
    	    			for (String use_user : Main.getСonfigYAML().getStringList("UseCommand_User_Use")) {
    	    				sender.sendMessage(cccp.FormatPause(use_user));
    	    			}
    	    		}
    	    		else if (args[1].equalsIgnoreCase("resetUser")) {
    	    				if(args.length == 3){
    	    					String testPlayer = "Players."+args[2];
        	    				Permissions.set(testPlayer, null); 
        	    				try {
        	    	       			Permissions.save(permissions);
        	    	       			if (showConsole){ 
        	    	       				plugin.getLogger().info(plugin.getName()+" Add player list > "+ args[2]);
        	    	       			}
        	    				} 
        	    				catch (IOException e) {
        	    					e.printStackTrace();
        	    				}
                				vault_api.PluginReload();
                				String Successfully_User_RemoveUser = Main.getСonfigYAML().getString("Successfully_User_ResetUser");
                				Successfully_User_RemoveUser = Successfully_User_RemoveUser.replace("%args1", args[2]);
                				sender.sendMessage(cccp.FormatPause(Successfully_User_RemoveUser));
                				
    	    				}else {
    	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_Reset_User")));
    	    				}	
    	    			}
    	    		else if (args[1].equalsIgnoreCase("add")) {
    	    			if(args.length == 4){
    	    				String testPlayer = "Players."+args[3];
            				
            				List<String> listGroups = new ArrayList<>();
            				listGroups = Permissions.getStringList(testPlayer+".Groups");
            				listGroups.add(args[2]);
            	    		Permissions.set(testPlayer+".Groups", listGroups); 
            	       		try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Add player groups > "+ args[3]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            	       		vault_api.PluginReload();
            				
            				String Successfully_Add = Main.getСonfigYAML().getString("Successfully_User_Add");
            				Successfully_Add = Successfully_Add.replace("%args1", args[2]);
            				Successfully_Add = Successfully_Add.replace("%args2", args[3]);
            				sender.sendMessage(cccp.FormatPause(Successfully_Add));
    	    			}
    	    			else {
    	    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_Add")));
    	    			}
    	    		}
    	    		else if (args[1].equalsIgnoreCase("set")) {
    	    				if(args.length == 4){
    	    					String testPlayer = "Players."+args[3];
                				
                				List<String> listGroups = new ArrayList<>();
                				listGroups.add(args[2]);
                	    		Permissions.set(testPlayer+".Groups", listGroups); 
                	       		try {
                	       			Permissions.save(permissions);
                	       			if (showConsole){ 
                	       				plugin.getLogger().info(plugin.getName()+" Set player groups > "+ args[3]);
                	       			}
                				} 
                				catch (IOException e) {
                					e.printStackTrace();
                				}
                	       		vault_api.PluginReload();
                				
                				String Successfully_Set = Main.getСonfigYAML().getString("Successfully_User_Set");
                				Successfully_Set = Successfully_Set.replace("%args1", args[2]);
                				Successfully_Set = Successfully_Set.replace("%args2", args[3]);
                				sender.sendMessage(cccp.FormatPause(Successfully_Set));
    	    				}else {
        	    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_Set")));
        	    			}	
    	    			}
    	    		else if (args[1].equalsIgnoreCase("remove")) {
	    				if(args.length == 4){
	    					String testPlayer = "Players."+args[3];
	    					List<String> listGroups = new ArrayList<>();
            				listGroups = Permissions.getStringList(testPlayer+".Groups");
            				listGroups.remove(args[2]);
            	    		Permissions.set(testPlayer+".Groups", listGroups);
            	       		try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Remove player groups > "+ args[3]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            	       		vault_api.PluginReload();
            				
            				String Successfully_Remove = Main.getСonfigYAML().getString("Successfully_User_Remove");
            				Successfully_Remove = Successfully_Remove.replace("%args1", args[2]);
            				Successfully_Remove = Successfully_Remove.replace("%args2", args[3]);
            				sender.sendMessage(cccp.FormatPause(Successfully_Remove));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_Remove")));
	    				}	
	    			}
    	    		else if (args[1].equalsIgnoreCase("setPrefix")) {
	    				if(args.length == 4){
	    					String testPlayer = "Players."+args[2];
            				Permissions.set(testPlayer+".Prefix", args[3]);
            				try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Set prefix player > "+args[2]+" "+ args[3]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				vault_api.PluginReload();
            				
            				String Successfully_setPrefix = Main.getСonfigYAML().getString("Successfully_User_SetPrefix");
            				Successfully_setPrefix = Successfully_setPrefix.replace("%args1", args[2]);
            				Successfully_setPrefix = Successfully_setPrefix.replace("%args2", args[3]);
            				sender.sendMessage(cccp.FormatPause(Successfully_setPrefix));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_SetPrefix")));
	    				}	
	    			}
    	    		else if (args[1].equalsIgnoreCase("setSuffix")) {
	    				if(args.length == 4){
	    					String testPlayer = "Players."+args[2];
            				Permissions.set(testPlayer+".Suffix", args[3]);
            				try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Set suffix player > "+args[2]+" "+ args[3]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				vault_api.PluginReload();
            				
            				String Successfully_setSuffix = Main.getСonfigYAML().getString("Successfully_User_SetSuffix");
            				Successfully_setSuffix = Successfully_setSuffix.replace("%args1", args[2]);
            				Successfully_setSuffix = Successfully_setSuffix.replace("%args2", args[3]);
            				sender.sendMessage(cccp.FormatPause(Successfully_setSuffix));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_SetSuffix")));
	    				}	
	    			}
    	    		else if (args[1].equalsIgnoreCase("clearPrefix")) {
	    				if(args.length == 3){
	    					String testPlayer = "Players."+args[2];
            				Permissions.set(testPlayer+".Prefix", "");
            				try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Clear prefix player > "+ args[2]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				vault_api.PluginReload();
            				
            				String Successfully_clearPrefix = Main.getСonfigYAML().getString("Successfully_User_ClearPrefix");
            				Successfully_clearPrefix = Successfully_clearPrefix.replace("%args1", args[2]);
            				sender.sendMessage(cccp.FormatPause(Successfully_clearPrefix));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_ClearPrefix")));
	    				}	
	    			}
    	    		else if (args[1].equalsIgnoreCase("clearSuffix")) {
	    				if(args.length == 3){
	    					String testPlayer = "Players."+args[2];
            				Permissions.set(testPlayer+".Suffix", "");
            				try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Clear suffix player > "+ args[2]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				vault_api.PluginReload();
            				
            				String Successfully_clearSuffix = Main.getСonfigYAML().getString("Successfully_User_ClearSuffix");
            				Successfully_clearSuffix = Successfully_clearSuffix.replace("%args1", args[2]);
            				sender.sendMessage(cccp.FormatPause(Successfully_clearSuffix));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_User_ClearSuffix")));
	    				}	
	    			}
    	    	}
    		}
    		else if(args[0].equalsIgnoreCase("create")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("CreatePermissions"))) {
    				if (args.length == 2) {
        				if (Permissions.getString("Groups."+args[1]) == null) {
    		        		List<String> list1 = new ArrayList<String>();

    		     	        list1.add("");
    	    		        Permissions.set("Groups."+args[1]+".parent", "root");
    	    		        Permissions.set("Groups."+args[1]+".default", false);
    	    		        Permissions.set("Groups."+args[1]+".permissions", list1);
    	    		        try {
    	    	       			Permissions.save(permissions);
    	    	       			if (showConsole){ 
    	    	       				plugin.getLogger().info(plugin.getName()+" Create group > "+ args[1]);
    	    	       			}
    	    	       			vault_api.PluginReload();
                				
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
    	    		        vault_api.PluginReload();
    	    		        
    	    				String Successfully_Create = Main.getСonfigYAML().getString("Successfully_Create");
    	    				Successfully_Create = Successfully_Create.replace("%args1", args[1]);
    	    				sender.sendMessage(cccp.FormatPause(Successfully_Create));
    		        	}
        			}
    			}else {
    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("CreatePermissionsMessange")));
    			}
    		}
    		else if(args[0].equalsIgnoreCase("delete")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("DeletePermissions"))) {
    				if (args.length == 2) {
        				if (Permissions.getString("Groups."+args[1]) != null) {
        					Permissions.set("Groups."+args[1], null);
            		        try {
            	       			Permissions.save(permissions);
            	       			if (showConsole){ 
            	       				plugin.getLogger().info(plugin.getName()+" Delete group > "+ args[1]);
            	       			}
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            		        vault_api.PluginReload();
            		        
            				String Successfully_Delete = Main.getСonfigYAML().getString("Successfully_Delete");
            				Successfully_Delete = Successfully_Delete.replace("%args1", args[1]);
            				sender.sendMessage(cccp.FormatPause(Successfully_Delete));
        				}else {
        					
        				}
        			}
        			else {
        				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_Delete")));
        			}
    			}else {
    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("DeletePermissionsMessange")));
    			}
    		}
    		else if(args[0].equalsIgnoreCase("groupList")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("GroupListPermissions"))) {
    				String groupsList = "";
    				for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
    					groupsList += groups + " ";
    	    		}
    				String GroupListFormat = Main.getСonfigYAML().getString("GroupListFormat");
    				GroupListFormat = GroupListFormat.replace("%groupListFormat", groupsList);
    				sender.sendMessage(cccp.FormatPause(GroupListFormat));
    			}else {
    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("GroupListPermissionsMessange")));
    			}
    		}
    		else if(args[0].equalsIgnoreCase("groupListPlayer")){
    			if (sender.hasPermission(Main.getСonfigYAML().getString("GroupListPlayerPermissions"))) {
    				if (args.length == 2) {		
        		    	String testPlayer = "Players."+args[1];
        				String groupsList = "";
        				for (String groups : Permissions.getStringList(testPlayer + ".Groups")) {
        					groupsList += groups + " ";
        	    		}
        				String GroupListPlayerFormat = Main.getСonfigYAML().getString("GroupListPlayerFormat");
        				GroupListPlayerFormat = GroupListPlayerFormat.replace("%groupListPlayerFormat", groupsList);
        				sender.sendMessage(cccp.FormatPause(GroupListPlayerFormat));
        			}else {
        				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("UseCommand_GroupListPlayer")));
        			}
    			}else {
    				sender.sendMessage(cccp.FormatPause(Main.getСonfigYAML().getString("GroupListPlayerPermissionsMessange")));
    			}
    		}
    	}
    	return false; 
    }
	

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO command list register
				List<String> list = new ArrayList<String>();
				list.add("help");
				list.add("user");
				list.add("create");
				list.add("delete");
				list.add("edit");
				list.add("reload");
				list.add("groupList");
				list.add("groupListPlayer");
				Collections.sort(list);
				if (arg3.length == 1) {
					return StringUtil.copyPartialMatches(arg3[0], list, new ArrayList<>());	
				}else if (arg3.length == 2 && arg3[0].equalsIgnoreCase("delete")) {
					List<String> listGroups = new ArrayList<String>();
					for (String groups : vault_api.givePermissionsYAML().getConfigurationSection("Groups").getKeys(false)) {
		    			listGroups.add(groups);
		    		}
					Collections.sort(listGroups);
					return StringUtil.copyPartialMatches(arg3[1], listGroups, new ArrayList<>());
				}
				else if (arg3.length == 2 && arg3[0].equalsIgnoreCase("user")) {
					List<String> listUser = new ArrayList<String>();
					listUser.add("add");
					listUser.add("set");
					listUser.add("remove");
					listUser.add("resetUser");
					listUser.add("setPrefix");
					listUser.add("setSuffix");
					listUser.add("clearPrefix");
					listUser.add("clearSuffix");
					Collections.sort(listUser);
					return StringUtil.copyPartialMatches(arg3[1], listUser, new ArrayList<>());
				}
				else if (arg3[1].equalsIgnoreCase("removeUser") || arg3.length == 3 && arg3[1].equalsIgnoreCase("addUser")) {
					List<String> listPlayers = new ArrayList<String>();
					for (Player player : Bukkit.getOnlinePlayers()) {
						listPlayers.add(player.getName());
					}
					Collections.sort(listPlayers);
					return StringUtil.copyPartialMatches(arg3[2], listPlayers, new ArrayList<>());
				}
				else if (arg3.length == 3 && arg3[1].equalsIgnoreCase("add") || arg3.length == 3 && arg3[1].equalsIgnoreCase("set") || arg3.length == 3 && arg3[1].equalsIgnoreCase("remove")) {
					List<String> listGroups = new ArrayList<String>();
					for (String groups : vault_api.givePermissionsYAML().getConfigurationSection("Groups").getKeys(false)) {
		    			listGroups.add(groups);
		    		}
					Collections.sort(listGroups);
					return StringUtil.copyPartialMatches(arg3[2], listGroups, new ArrayList<>());
				}
				else if (arg3.length == 3 && arg3[1].equalsIgnoreCase("clearSuffix") || arg3.length == 3 && arg3[1].equalsIgnoreCase("clearPrefix") || arg3.length == 3 && arg3[1].equalsIgnoreCase("setSuffix") || arg3.length == 3 && arg3[1].equalsIgnoreCase("setPrefix")) {
					List<String> listPlayers = new ArrayList<String>();
					for (Player player : Bukkit.getOnlinePlayers()) {
						listPlayers.add(player.getName());
		    		}
					Collections.sort(listPlayers);
					return StringUtil.copyPartialMatches(arg3[2], listPlayers, new ArrayList<>());
				}
				else if (arg3.length == 2 && arg3[0].equalsIgnoreCase("edit")) {
					List<String> listEdit = new ArrayList<String>();
					listEdit.add("parent");
					listEdit.add("default");
					listEdit.add("permissions");
					listEdit.add("setPrefix");
					listEdit.add("setSuffix");
					Collections.sort(listEdit);
					return StringUtil.copyPartialMatches(arg3[1], listEdit, new ArrayList<>());
				}
				else if (arg3.length == 3 && arg3[1].equalsIgnoreCase("default") || arg3.length == 3 && arg3[1].equalsIgnoreCase("parent") || arg3.length == 3 && arg3[1].equalsIgnoreCase("permissions")) {
					List<String> listGroups = new ArrayList<String>();
					for (String groups : vault_api.givePermissionsYAML().getConfigurationSection("Groups").getKeys(false)) {
		    			listGroups.add(groups);
		    		}
					Collections.sort(listGroups);
					return StringUtil.copyPartialMatches(arg3[2], listGroups, new ArrayList<>());
				}
				else if (arg3.length == 4 && arg3[1].equalsIgnoreCase("default")) {
					List<String> listdDefault = new ArrayList<String>();
					listdDefault.add("true");
					listdDefault.add("false");
					Collections.sort(listdDefault);
					return StringUtil.copyPartialMatches(arg3[3], listdDefault, new ArrayList<>());
				}
				else if (arg3.length == 4 && arg3[1].equalsIgnoreCase("parent")) {
					List<String> listGroups = new ArrayList<String>();
					for (String groups : vault_api.givePermissionsYAML().getConfigurationSection("Groups").getKeys(false)) {
		    			listGroups.add(groups);
		    		}
					Collections.sort(listGroups);
					return StringUtil.copyPartialMatches(arg3[3], listGroups, new ArrayList<>());
				}
				else if (arg3.length == 4 && arg3[1].equalsIgnoreCase("permissions")) {
					List<String> listPermissions = new ArrayList<String>();
					listPermissions.add("set");
					listPermissions.add("add");
					listPermissions.add("remove");
					Collections.sort(listPermissions);
					return StringUtil.copyPartialMatches(arg3[3], listPermissions, new ArrayList<>());
				}
				else if (arg3.length == 5 && arg3[1].equalsIgnoreCase("permissions") && arg3[3].equalsIgnoreCase("remove")) {
					List<String> listPermissions = new ArrayList<String>();
					listPermissions = vault_api.givePermissionsYAML().getStringList("Groups."+arg3[2]+".permissions");
					Collections.sort(listPermissions);
					return StringUtil.copyPartialMatches(arg3[4], listPermissions, new ArrayList<>());
				}
				else if (arg3.length == 5 && arg3[1].equalsIgnoreCase("permissions")) {
					List<String> listPermissions = new ArrayList<String>();

					Collections.sort(listPermissions);
					return StringUtil.copyPartialMatches(arg3[3], listPermissions, new ArrayList<>());
				}
				return null;
	}
}
