package ru.permissions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.RegisteredServiceProvider;
//import org.bukkit.plugin.ServicePriority;
//import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import net.milkbowl.vault.chat.Chat;
import ru.permissions.vault.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {
	public static Main plugin;
    HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
    ColorCodesPause cccp = new ColorCodesPause();
    
	File permissions;
	YamlConfiguration Permissions;
	File сonfig;
	YamlConfiguration Сonfig;
	VaultChat vaultChatnew;
	public void onEnable() {
        plugin = this;
        this.getServer().getPluginManager().registerEvents(this, this);
		getLogger().info(plugin.getName()+" > Author >> vova436612");
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            //VaultChat chat1 = new VaultChat(plugin, new VaultPermission(), false);
            //ServicesManager servicesManager = plugin.getServer().getServicesManager();
            //servicesManager.register(Chat.class, chat1, plugin, ServicePriority.Highest);
            new VaultChat(plugin, new VaultPermission(), false).register();
        }
	
        // TODO Configs
        if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info(plugin.getName()+" > Create dir >> "+new File(getDataFolder().getAbsolutePath()).getName());
        	}
        }
        permissions = new File(getDataFolder().getAbsolutePath()+"//Permissions.yml");
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
				getLogger().info(plugin.getName()+" > Create file >> "+permissions.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info(plugin.getName()+" > Create dir >> "+new File(getDataFolder().getAbsolutePath()).getName());
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
	        
	        List<String> list1 = new ArrayList<String>();
	        //list1.add("permissions.blockbreak");
	        //► ◄ ◊ ♦
	        list1.add("&6◄&2JustPermissions&6►  &7 Command help:");
	        list1.add("&6► &3/&4justperm &7create &6<group> &7- Create group");
	        list1.add("&6► &3/&4justperm &7delete &6<group> &7- Delete group");
	        list1.add("&6► &3/&4justperm &7user <add|set|remove> &6<group> &2<namePlayer> &7- Edit player");
	        list1.add("&6► &3/&4justperm &7groupList &7- Group list");
	        list1.add("&6► &3/&4justperm &7groupListPlayer &2<namePlayer> &7- Info player group");
	        list1.add("&6► &3/&4justperm &7reload &7- Reload plugin");

	        Сonfig.set("ShowMessageUseCommand", list1);
	        
	        Сonfig.set("HelpPermissions", "just_permissions.help");
	        Сonfig.set("HelpPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("ReloadMessange", "&2Reload plugin");
	        Сonfig.set("ReloadPermissions", "just_permissions.reload");
	        Сonfig.set("ReloadPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("EditPermissions", "just_permissions.edit");
	        Сonfig.set("EditPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("UserPermissions", "just_permissions.user");
	        Сonfig.set("UserPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("CreatePermissions", "just_permissions.create");
	        Сonfig.set("CreatePermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("DeletePermissions", "just_permissions.delete");
	        Сonfig.set("DeletePermissionsMessange", "&cYou do not have permission to do that!");

	        Сonfig.set("GroupListPermissions", "just_permissions.groupList");
	        Сonfig.set("GroupListPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("GroupListPlayerPermissions", "just_permissions.groupListPlayer");
	        Сonfig.set("GroupListPlayerPermissionsMessange", "&cYou do not have permission to do that!");
	        
	        Сonfig.set("GroupListFormat", "&7Group list &2> &6%groupListFormat");
	        Сonfig.set("GroupListPlayerFormat", "&7Group list player &2> &6%groupListPlayerFormat");
	        
	        Сonfig.set("UseCommand_Create", "&6► &3/&4justperm &7create &6<group> &7- Create group");
	        Сonfig.set("UseCommand_Delete", "&6► &3/&4justperm &7delete &6<group> &7- Delete group");
	        List<String> list4 = new ArrayList<String>();
	        list4.add("&6► &3/&4justperm &7user <add|set|remove> &6<group> &2<namePlayer> &7- Edit group player");
	        list4.add("&6► &3/&4justperm &7user <addUser|removeUser> &2<namePlayer> &7- Edit list player");
	        Сonfig.set("UseCommand_User_Use", list4);
	        List<String> list5 = new ArrayList<String>();
	        list5.add("&6► &3/&4justperm &7edit <default|parent|permissions> &6<group> &2<value> &7- Edit group");
	        Сonfig.set("UseCommand_Edit_Use", list5);
	        Сonfig.set("UseCommand_Edit_Parent", "&6► &3/&4justperm &7edit parent &6<group> &2<parent group> &7- Edit group parent");
	        Сonfig.set("UseCommand_Edit_Default", "&6► &3/&4justperm &7edit default &6<group> &2<true|false> &7- Edit group default");
	        Сonfig.set("UseCommand_Edit_Permissions", "&6► &3/&4justperm &7edit permissions &6<group> &7<add|set|remove> &2<permissions> &7- Edit group permissions");

	        Сonfig.set("UseCommand_Edit_Permissions_Add", "&6► &3/&4justperm &7edit permissions &6<group> &7add &2<permissions> &7- Edit group add permissions");
	        Сonfig.set("UseCommand_Edit_Permissions_Set", "&6► &3/&4justperm &7edit permissions &6<group> &7set &2<permissions> &7- Edit group set permissions");
	        Сonfig.set("UseCommand_Edit_Permissions_Remove", "&6► &3/&4justperm &7edit permissions &6<group> &7remove &2<permissions> &7- Edit group remove permissions");

	        Сonfig.set("UseCommand_User_Add_User", "&6► &3/&4justperm &7user addUser &2<namePlayer> &7- Add list group");
	        Сonfig.set("UseCommand_User_Remove_User", "&6► &3/&4justperm &7user removeUser &2<namePlayer> &7- Remove list list");
	        Сonfig.set("UseCommand_User_Add", "&6► &3/&4justperm &7user add &6<group> &2<namePlayer> &7- Add player group");
	        Сonfig.set("UseCommand_User_Set", "&6► &3/&4justperm &7user set &6<group> &2<namePlayer> &7- Set player group");
	        Сonfig.set("UseCommand_User_Remove", "&6► &3/&4justperm &7user remove &6<group> &2<namePlayer> &7- Remove player group");
	        
	        Сonfig.set("UseCommand_GroupListPlayer", "&6► &3/&4justperm &7groupListPlayer &2<namePlayer> &7- Info player group");

	        Сonfig.set("Successfully_Create", "&6► &7Create group &6%args1");
	        Сonfig.set("Successfully_Delete", "&6► &7Delete group &6%args1");
	        
	        Сonfig.set("Successfully_User_AddUser", "&6► &7Add player list &6%args1");
	        Сonfig.set("Successfully_User_RemoveUser", "&6► &7Remove player list &6%args1");
	        Сonfig.set("Successfully_User_Add", "&6► &7Add player group &6%args1 &2%args2");
	        Сonfig.set("Successfully_User_Set", "&6► &7Set player group &6%args1 &2%args2");
	        Сonfig.set("Successfully_User_Remove", "&6► &7Remove player group &6%args1 &2%args2");
	        Сonfig.set("Successfully_Edit_Default", "&6► &7Edit default group &6%args1 &7set &2%args2");
	        Сonfig.set("Successfully_Edit_Parent", "&6► &7Edit parent group &6%args1 &7set &2%args2");
	        Сonfig.set("Successfully_Edit_Permissions_Set", "&6► &7Edit permissions group &6%args1 &7set &2%args2");
	        Сonfig.set("Successfully_Edit_Permissions_Add", "&6► &7Edit permissions group &6%args1 &7add &2%args2");
	        Сonfig.set("Successfully_Edit_Permissions_Remove", "&6► &7Edit permissions group &6%args1 &7remove &2%args2");
	        try {
	        	Сonfig.save(сonfig);
				getLogger().info(plugin.getName()+" > Create file >> "+сonfig.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		getLogger().info(plugin.getName()+" > Load file >> "+permissions.getName()+" "+сonfig.getName());
		
		for (Player player : Bukkit.getOnlinePlayers()) {
	        setupPermissions(player);
		}
    }

    public void onDisable() {
        playerPermissions.clear();
    }
    
    Chat chat = null;
	private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
        	chat = rsp.getProvider();
        	return chat != null;
        }else {
        	return false;
        }
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
    	File permissions = new File(getDataFolder().getAbsolutePath()+"//Permissions.yml");
		Permissions = YamlConfiguration.loadConfiguration(permissions);  	
		File сonfig = new File(getDataFolder().getAbsolutePath()+"//Сonfig.yml");
		Сonfig = YamlConfiguration.loadConfiguration(сonfig);  	
        Player player = event.getPlayer();
        setupPermissions(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerPermissions.remove(player.getUniqueId());
    }
    
    public void setupPermissions(Player player) {
    	// TODO Setup Permissions
    	PermissionAttachment attachment = player.addAttachment(this);
        this.playerPermissions.put(player.getUniqueId(), attachment);
    	String testPlayer = "Players."+player.getName();
    	if (Permissions.getString(testPlayer+".Uuid") != null) {
            permissionsSetter(player.getUniqueId(), Permissions.getStringList(testPlayer + ".Groups"));
            Prefix_SuffixSetter(player.getUniqueId(), Permissions.getStringList(testPlayer + ".Groups"));
            getLogger().info(plugin.getName()+" > Get groups >> "+ player.getName());
    	}else {
    		List<String> listGroups = new ArrayList<>();
    		listGroups.addAll(Permissions.getStringList(testPlayer + ".Groups"));
    		for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
    			if (Permissions.getBoolean("Groups." + groups + ".default")) {
    				listGroups.add(groups);
   	        	}
    		}
        	Permissions.set(testPlayer+".Uuid", player.getUniqueId().toString()); 
        	Permissions.set(testPlayer+".Groups", listGroups); 
       		try {
       			Permissions.save(permissions);
	        	getLogger().info(plugin.getName()+" > Add player list >> "+ player.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
       		setupPermissions(player);
    	}
    }

    private void permissionsSetter(UUID Uuid, List<String> list) {
    	// TODO Permissions Setter
        PermissionAttachment attachment = this.playerPermissions.get(Uuid);
        for (String groups : list) {
        	if (groups != null) {
        		for (String permissions : Permissions.getStringList("Groups." + groups + ".permissions")) {
        			if (permissions.startsWith("-")) {
                		String permissions1 = permissions.replaceFirst("-", "");
                        attachment.setPermission(permissions1, false);
                	}else {
                        attachment.setPermission(permissions, true);
                	}
                }
            	if (Permissions.getString("Groups." + groups + ".parent") != "null" ||Permissions.getString("Groups." + groups + ".parent") != null || Permissions.getString("Groups." + groups + ".parent") != "" || Permissions.getString("Groups." + groups + ".parent") != "root") {
            		String parent = Permissions.getString("Groups." + groups + ".parent");
            		List<String> listGroups = new ArrayList<>();
            		listGroups.add(parent);
            		permissionsSetter(Uuid, listGroups);
            	}
        	}
        }
    }
    
    private void Prefix_SuffixSetter(UUID Uuid, List<String> list) {
    	// TODO Prefix & Suffix Setter
    	YamlConfiguration Permissions1 = YamlConfiguration.loadConfiguration(permissions);
        for (String groups : list) {
        	if (groups != null) {
        		if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
					setupChat();
					String Prefix = cccp.FormatPause(Permissions1.getString("Groups." + groups + ".prefix"));
					String Suffix = cccp.FormatPause(Permissions1.getString("Groups." + groups + ".suffix"));
					chat.setPlayerPrefix(Bukkit.getPlayer(Uuid), Prefix);
					chat.setPlayerSuffix(Bukkit.getPlayer(Uuid), Suffix);
				}
            }
        }
    }
    
    public void PluginReload() {
    	// TODO Plugin Reload
    	if (!permissions.exists() && !сonfig.exists()) {
	    	onEnable();
	    }else {
	    	Bukkit.getServer().getPluginManager().disablePlugin(this);
	    	Bukkit.getServer().getPluginManager().enablePlugin(this);
			File permissions = new File(getDataFolder().getAbsolutePath()+"//Permissions.yml");
			Permissions = YamlConfiguration.loadConfiguration(permissions);  	
			File сonfig = new File(getDataFolder().getAbsolutePath()+"//Сonfig.yml");
			Сonfig = YamlConfiguration.loadConfiguration(сonfig);  	
		    getLogger().info(plugin.getName()+" > Reload >> "+permissions.getName() + " " + сonfig.getName());
	    }
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
			for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
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
			listUser.add("addUser");
			listUser.add("removeUser");
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
		else if (arg3.length == 3 && arg3[1].equalsIgnoreCase("add") || arg3.length == 3 && arg3[1].equalsIgnoreCase("set") || arg3.length == 3 && arg3[1].equalsIgnoreCase("remove") || arg3.length == 3) {
			List<String> listGroups = new ArrayList<String>();
			for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
    			listGroups.add(groups);
    		}
			Collections.sort(listGroups);
			return StringUtil.copyPartialMatches(arg3[2], listGroups, new ArrayList<>());
		}
		else if (arg3.length == 2 && arg3[0].equalsIgnoreCase("edit")) {
			List<String> listEdit = new ArrayList<String>();
			listEdit.add("parent");
			listEdit.add("default");
			listEdit.add("permissions");
			Collections.sort(listEdit);
			return StringUtil.copyPartialMatches(arg3[1], listEdit, new ArrayList<>());
		}
		else if (arg3.length == 3 && arg3[1].equalsIgnoreCase("default") || arg3.length == 3 && arg3[1].equalsIgnoreCase("parent") || arg3.length == 3 && arg3[1].equalsIgnoreCase("permissions")) {
			List<String> listGroups = new ArrayList<String>();
			for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
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
			for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
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
			listPermissions = Permissions.getStringList("Groups."+arg3[2]+".permissions");
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
    
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	// TODO command register
    	if(cmd.getName().equalsIgnoreCase("JustPerm") || cmd.getName().equalsIgnoreCase("JP") || cmd.getName().equalsIgnoreCase("Perm") || cmd.getName().equalsIgnoreCase("Just")){
    		if(args.length == 0){
    			
    		}
    		else if(args[0].equalsIgnoreCase("help")){
    			if (sender.hasPermission(Сonfig.getString("HelpPermissions"))) {
    				for (String listMessage : Сonfig.getStringList("ShowMessageUseCommand")) {
        				sender.sendMessage(cccp.FormatPause(listMessage));
                    }
        		}else {
        			sender.sendMessage(cccp.FormatPause(Сonfig.getString("HelpPermissionsMessange")));
        		}
    		}
    		else if(args[0].equalsIgnoreCase("reload")){
    			if (sender.hasPermission(Сonfig.getString("ReloadPermissions"))) {
    				PluginReload();
        			for (Player player : Bukkit.getOnlinePlayers()) {
        				playerPermissions.remove(player.getUniqueId());
        			}
        			for (Player player : Bukkit.getOnlinePlayers()) {
        		        setupPermissions(player);
        			}
        		    sender.sendMessage(cccp.FormatPause(Сonfig.getString("ReloadMessange")));
        		}else {
        			sender.sendMessage(cccp.FormatPause(Сonfig.getString("ReloadPermissionsMessange")));
        		}
    		}else if(args[0].equalsIgnoreCase("edit")){
    			/*
    			 * if(args.length < 4 && args.length == 1){
    	    			sender.sendMessage(cccp.FormatPause("edit list"));
    	    		}
    			 */
    			if (sender.hasPermission(Сonfig.getString("EditPermissions"))) {
    				if(args.length == 1){
    					for (String use_user : Сonfig.getStringList("UseCommand_Edit_Use")) {
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
    	    	       			getLogger().info(plugin.getName()+" Edit default > "+ args[2] + " set "+ setDefault);
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
    	    				PluginReload();
    	    				String Successfully_Edit_Default = Сonfig.getString("Successfully_Edit_Default");
    	    				Successfully_Edit_Default = Successfully_Edit_Default.replace("%args1", args[2]);
    	    				Successfully_Edit_Default = Successfully_Edit_Default.replace("%args2", setDefault);
    	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Default));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Default")));
	    				}
    				}
    				else if (args[1].equalsIgnoreCase("parent")) {
	    				if(args.length == 4){
	    					Permissions.set("Groups."+args[2]+".parent", args[3]);
		    		        try {
		    	       			Permissions.save(permissions);
		    	       			getLogger().info(plugin.getName()+" Edit parent > "+ args[2] + " set "+ args[3]);
		    				} 
		    				catch (IOException e) {
		    					e.printStackTrace();
		    				}
		    				PluginReload();
		    				String Successfully_Edit_Parent = Сonfig.getString("Successfully_Edit_Parent");
		    				Successfully_Edit_Parent = Successfully_Edit_Parent.replace("%args1", args[2]);
		    				Successfully_Edit_Parent = Successfully_Edit_Parent.replace("%args2", args[3]);
		    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Parent));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Parent")));
	    				}
	    			}
					else if (args[1].equalsIgnoreCase("permissions")) {
						if(args.length == 2){
							sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Permissions")));
						}
						else if(args.length == 3){
							sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Permissions")));
						}
						else if (args[3].equalsIgnoreCase("set")) {
	    	    				if(args.length == 5){
	    	    					List<String> listPermissions = new ArrayList<String>();
	    	    					listPermissions.add(args[4]);
	    	    					Permissions.set("Groups."+args[2]+".permissions", listPermissions);
	        	    		        try {
	        	    	       			Permissions.save(permissions);
	        	    	       			getLogger().info(plugin.getName()+" Edit permissions > "+ args[2] + " set "+ args[4]);
	        	    				} 
	        	    				catch (IOException e) {
	        	    					e.printStackTrace();
	        	    				}
	        	    				PluginReload();
	        	    				String Successfully_Edit_Permissions_Set = Сonfig.getString("Successfully_Edit_Permissions_Set");
	        	    				Successfully_Edit_Permissions_Set = Successfully_Edit_Permissions_Set.replace("%args1", args[2]);
	        	    				Successfully_Edit_Permissions_Set = Successfully_Edit_Permissions_Set.replace("%args2", args[4]);
	        	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Permissions_Set));
	        	    			}else {
	        	    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Permissions_Set")));
	    	    				}
	    				}else if (args[3].equalsIgnoreCase("add")) {
	    					if(args.length == 5){
	    						List<String> listPermissions = new ArrayList<String>();
    	    					listPermissions = Permissions.getStringList("Groups."+args[2]+".permissions");
    	    					listPermissions.add(args[4]);
    	    					Permissions.set("Groups."+args[2]+".permissions", listPermissions);
        	    		        try {
        	    	       			Permissions.save(permissions);
        	    	       			getLogger().info(plugin.getName()+" Edit permissions > "+ args[2] + " add "+ args[3]);
        	    				} 
        	    				catch (IOException e) {
        	    					e.printStackTrace();
        	    				}
        	    				PluginReload();
        	    				String Successfully_Edit_Permissions_Add = Сonfig.getString("Successfully_Edit_Permissions_Add");
        	    				Successfully_Edit_Permissions_Add = Successfully_Edit_Permissions_Add.replace("%args1", args[2]);
        	    				Successfully_Edit_Permissions_Add = Successfully_Edit_Permissions_Add.replace("%args2", args[4]);
        	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Permissions_Add));
    	    					
	    					}else {
	    						sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Permissions_Add")));
		    				}
	    				}else if (args[3].equalsIgnoreCase("remove")) {
	    					if(args.length == 5){
	    						List<String> listPermissions = new ArrayList<String>();
    	    					listPermissions = Permissions.getStringList("Groups."+args[2]+".permissions");
    	    					listPermissions.remove(args[4]);
    	    					Permissions.set("Groups."+args[2]+".permissions", listPermissions);
        	    		        try {
        	    	       			Permissions.save(permissions);
        	    	       			getLogger().info(plugin.getName()+" Edit permissions > "+ args[2] + " remove "+ args[4]);
        	    				} 
        	    				catch (IOException e) {
        	    					e.printStackTrace();
        	    				}
        	    				PluginReload();
        	    				String Successfully_Edit_Permissions_Remove = Сonfig.getString("Successfully_Edit_Permissions_Remove");
        	    				Successfully_Edit_Permissions_Remove = Successfully_Edit_Permissions_Remove.replace("%args1", args[2]);
        	    				Successfully_Edit_Permissions_Remove = Successfully_Edit_Permissions_Remove.replace("%args2", args[4]);
        	    				sender.sendMessage(cccp.FormatPause(Successfully_Edit_Permissions_Remove));
    	    					
	    					}else {
	    						sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Edit_Permissions_Remove")));
	    					}
	    				}
	    			}
        		}else {
        			sender.sendMessage(cccp.FormatPause(Сonfig.getString("UserPermissionsMessange")));
        		}
    		}
    		else if(args[0].equalsIgnoreCase("user")){
    			if (sender.hasPermission(Сonfig.getString("UserPermissions"))) {
    				if(args.length == 0){
    					sender.sendMessage(cccp.FormatPause("user list"));
    	    		}
    				else if(args.length == 1){ 
    	    			for (String use_user : Сonfig.getStringList("UseCommand_User_Use")) {
    	    				sender.sendMessage(cccp.FormatPause(use_user));
    	    			}
    	    		}
    	    		else if (args[1].equalsIgnoreCase("addUser")) {
    	    			if(args.length == 3){
    	    				String testPlayer = "Players."+args[2];
    	    				Permissions.set(testPlayer, ""); 
    	    				try {
    	    	       			Permissions.save(permissions);
    	    		        	getLogger().info(plugin.getName()+" Add player list > "+ args[2]);
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
            				String Successfully_User_AddUser = Сonfig.getString("Successfully_User_AddUser");
            				Successfully_User_AddUser = Successfully_User_AddUser.replace("%args1", args[2]);
            				sender.sendMessage(cccp.FormatPause(Successfully_User_AddUser));
    	    				PluginReload();
    	    			}else {
    	    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_User_Add_User")));
	    				}
    	    				
    	    		}
    	    		else if (args[1].equalsIgnoreCase("removeUser")) {
    	    				if(args.length == 3){
    	    					String testPlayer = "Players."+args[2];
        	    				Permissions.set(testPlayer, null); 
        	    				try {
        	    	       			Permissions.save(permissions);
        	    		        	getLogger().info(plugin.getName()+" Add player list > "+ args[2]);
        	    				} 
        	    				catch (IOException e) {
        	    					e.printStackTrace();
        	    				}
                				String Successfully_User_RemoveUser = Сonfig.getString("Successfully_User_RemoveUser");
                				Successfully_User_RemoveUser = Successfully_User_RemoveUser.replace("%args1", args[2]);
                				sender.sendMessage(cccp.FormatPause(Successfully_User_RemoveUser));
        	    				PluginReload();
    	    				}else {
    	    					sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_User_Remove_User")));
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
            		        	getLogger().info(plugin.getName()+" Add player groups > "+ args[3]);
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				
            				String Successfully_Add = Сonfig.getString("Successfully_User_Add");
            				Successfully_Add = Successfully_Add.replace("%args1", args[2]);
            				Successfully_Add = Successfully_Add.replace("%args2", args[3]);
            				sender.sendMessage(cccp.FormatPause(Successfully_Add));
    	    			}
    	    			else {
    	    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_User_Add")));
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
                	       			getLogger().info(plugin.getName()+" Set player groups > "+ args[3]);
                				} 
                				catch (IOException e) {
                					e.printStackTrace();
                				}
                				
                				String Successfully_Set = Сonfig.getString("Successfully_User_Set");
                				Successfully_Set = Successfully_Set.replace("%args1", args[2]);
                				Successfully_Set = Successfully_Set.replace("%args2", args[3]);
                				sender.sendMessage(cccp.FormatPause(Successfully_Set));
    	    				}else {
        	    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_User_Set")));
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
                	    		getLogger().info(plugin.getName()+" Remove player groups > "+ args[3]);
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				String Successfully_Remove = Сonfig.getString("Successfully_User_Remove");
            				Successfully_Remove = Successfully_Remove.replace("%args1", args[2]);
            				Successfully_Remove = Successfully_Remove.replace("%args2", args[3]);
            				sender.sendMessage(cccp.FormatPause(Successfully_Remove));
	    				}else {
	    					sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_User_Remove")));
	    				}	
  	    			
    			}
    	    		}
    		}
    		else if(args[0].equalsIgnoreCase("create")){
    			if (sender.hasPermission(Сonfig.getString("CreatePermissions"))) {
    				if (args.length == 2) {
        				if (Permissions.getString("Groups."+args[1]) == null) {
    		        		List<String> list1 = new ArrayList<String>();

    		     	        list1.add("");
    	    		        Permissions.set("Groups."+args[1]+".parent", "root");
    	    		        Permissions.set("Groups."+args[1]+".default", false);
    	    		        Permissions.set("Groups."+args[1]+".permissions", list1);
    	    		        try {
    	    	       			Permissions.save(permissions);
    	    	       			getLogger().info(plugin.getName()+" Create group > "+ args[1]);
    	    				} 
    	    				catch (IOException e) {
    	    					e.printStackTrace();
    	    				}
    	    				PluginReload();
    	    				String Successfully_Create = Сonfig.getString("Successfully_Create");
    	    				Successfully_Create = Successfully_Create.replace("%args1", args[1]);
    	    				sender.sendMessage(cccp.FormatPause(Successfully_Create));
    		        	}
        			}
    			}else {
    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("CreatePermissionsMessange")));
    			}
    		}
    		else if(args[0].equalsIgnoreCase("delete")){
    			if (sender.hasPermission(Сonfig.getString("DeletePermissions"))) {
    				if (args.length == 2) {
        				if (Permissions.getString("Groups."+args[1]) != null) {
        					Permissions.set("Groups."+args[1], null);
            		        try {
            	       			Permissions.save(permissions);
            	       			getLogger().info(plugin.getName()+" Delete group > "+ args[1]);
            				} 
            				catch (IOException e) {
            					e.printStackTrace();
            				}
            				PluginReload();
            				String Successfully_Delete = Сonfig.getString("Successfully_Delete");
            				Successfully_Delete = Successfully_Delete.replace("%args1", args[1]);
            				sender.sendMessage(cccp.FormatPause(Successfully_Delete));
        				}else {
        					
        				}
        			}
        			else {
        				sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_Delete")));
        			}
    			}else {
    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("DeletePermissionsMessange")));
    			}
    		}
    		else if(args[0].equalsIgnoreCase("groupList")){
    			if (sender.hasPermission(Сonfig.getString("GroupListPermissions"))) {
    				String groupsList = "";
    				for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
    					groupsList += groups + " ";
    	    		}
    				String GroupListFormat = Сonfig.getString("GroupListFormat");
    				GroupListFormat = GroupListFormat.replace("%groupListFormat", groupsList);
    				sender.sendMessage(cccp.FormatPause(GroupListFormat));
    			}else {
    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("GroupListPermissionsMessange")));
    			}
    		}
    		else if(args[0].equalsIgnoreCase("groupListPlayer")){
    			if (sender.hasPermission(Сonfig.getString("GroupListPlayerPermissions"))) {
    				if (args.length == 2) {		
        		    	String testPlayer = "Players."+args[1];
        				String groupsList = "";
        				for (String groups : Permissions.getStringList(testPlayer + ".Groups")) {
        					groupsList += groups + " ";
        	    		}
        				String GroupListPlayerFormat = Сonfig.getString("GroupListPlayerFormat");
        				GroupListPlayerFormat = GroupListPlayerFormat.replace("%groupListPlayerFormat", groupsList);
        				sender.sendMessage(cccp.FormatPause(GroupListPlayerFormat));
        			}else {
        				sender.sendMessage(cccp.FormatPause(Сonfig.getString("UseCommand_GroupListPlayer")));
        			}
    			}else {
    				sender.sendMessage(cccp.FormatPause(Сonfig.getString("GroupListPlayerPermissionsMessange")));
    			}
    		}
    	}
    	return false; 
    }

}