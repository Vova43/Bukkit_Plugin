package ru.vault_permissions.vault;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

import ru.vault_permissions.ColorCodesPause;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class Vault_API implements Listener {

	private static Plugin plugin;
	public boolean showConsole = false;
	private File permissions;
	private YamlConfiguration Permissions;
	ColorCodesPause cccp = new ColorCodesPause();
	
	public Vault_API(Plugin plugin) {
		Vault_API.plugin = plugin;
		permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
        Permissions = YamlConfiguration.loadConfiguration(permissions);
	}
	
    HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
    
	public void setupPermissions(Player player) {
		//permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
        //Permissions = YamlConfiguration.loadConfiguration(permissions);
        
    	PermissionAttachment attachment = player.addAttachment(plugin);
        this.playerPermissions.put(player.getUniqueId(), attachment);
    	String testPlayer = "Players."+player.getName();
    	if (Permissions.getString(testPlayer.toString()) != null) {
            permissionsSetter(player.getUniqueId(), Permissions.getStringList(testPlayer + ".Groups"));
            if (showConsole){ 
            	plugin.getLogger().info(plugin.getName()+" Get groups > "+ player.getName());
            }
    	}else {
    		List<String> listGroups = new ArrayList<>();
    		for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
    			if (Permissions.getBoolean("Groups." + groups + ".default")) {
    				listGroups.add(groups);
   	        	}
    		}
    		Permissions.set(testPlayer+".Uuid", player.getUniqueId().toString()); 
    		Permissions.set(testPlayer+".Prefix", ""); 
    		Permissions.set(testPlayer+".Suffix", ""); 
    		Permissions.set(testPlayer+".Groups", listGroups); 
       		try {
       			Permissions.save(permissions);
       			if (showConsole){ 
       				plugin.getLogger().info(plugin.getName()+" Add player list > "+ player.getName());
       			}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
       		setupPermissions(player);
    	}
    }

    private void permissionsSetter(UUID Uuid, List<String> list) {
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
            	if (Permissions.getString("Groups." + groups + ".parent") != null || Permissions.getString("Groups." + groups + ".parent") != "" || Permissions.getString("Groups." + groups + ".parent") != "root") {
            		String parent = Permissions.getString("Groups." + groups + ".parent");
            		List<String> listGroups = new ArrayList<>();
            		listGroups.add(parent);
            		permissionsSetter(Uuid, listGroups);
            	}
        	}
        }
    }
    
    public void Save_Config() {
    	try {
   			Permissions.save(permissions);
        	if (showConsole){ 
        		plugin.getLogger().info(plugin.getName()+" Save config ");
        	}
        	File permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
			Permissions = YamlConfiguration.loadConfiguration(permissions);  	 	
        	for (Player player : Bukkit.getOnlinePlayers()) {
				givePermissions(player);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void PluginReload() {
    	// TODO Plugin Reload
    	Bukkit.getServer().getPluginManager().disablePlugin(plugin);
    	Bukkit.getServer().getPluginManager().enablePlugin(plugin);
		File permissions = new File(plugin.getDataFolder().getAbsolutePath()+"//Permissions.yml");
		Permissions = YamlConfiguration.loadConfiguration(permissions);  	 	
		//if (showConsole){ 
			plugin.getLogger().info(plugin.getName()+" > Reload >> "+permissions.getName() + " "+permissions.getPath());
		//}
		
		//Vault_API Vault_api = new Vault_API(plugin);
        //Vault_Permissions_Class vault_Permissions_Class = new Vault_Permissions_Class(plugin, Vault_api);
        //vault_Permissions_Class.register();
        //new Vault_Chat_Class(plugin, Vault_api, vault_Permissions_Class).register();
        
		
		//for (Player player : Bukkit.getOnlinePlayers()) {
		//	Vault_api.givePermissions(player);
		//}
		
		//startRegisterVault_API();
		//this.plugin = null;
    }
    
    public static void startRegisterVault_API() {
    	Vault_API Vault_api = new Vault_API(plugin);
        Vault_Permissions_Class vault_Permissions_Class = new Vault_Permissions_Class(plugin, Vault_api);
        vault_Permissions_Class.register();
        new Vault_Chat_Class(plugin, Vault_api, vault_Permissions_Class).register();
        
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			Vault_api.givePermissions(player);
		}
	}
    
    public YamlConfiguration givePermissionsYAML() {
		// TODO Auto-givePermissionsYAML method stub
		return Permissions;
	}
    
    public void givePermissions(Player player) {
		// TODO Auto-givePermissions method stub
    	setupPermissions(player);
	}

    public void addPlayer(String player) {
		// TODO Auto-addPlayer method stub
    	String testPlayer = "Players."+player;
		Permissions.set(testPlayer, "");
		Save_Config();
	}

	public void addPlayerGroups(String player) {
		// TODO Auto-addPlayerGroups method stub
		setupPermissions(Bukkit.getPlayer(player));
	}
	
	public void removePlayer(String player) {
		// TODO Auto-removePlayer method stub
		String testPlayer = "Players."+player;
		Permissions.set(testPlayer, null);
		Save_Config();
	}

	public void removePlayerGroups(String player) {
		// TODO Auto-removePlayerGroups method stub
		Permissions.set("Players."+player+".Groups", null); 
		Save_Config();
	}
	
    // Set | add | remove group player 
	public void removePlayerGroup(String player, String group) {
		// TODO Auto-removePlayerGroup method stub
		String testPlayer = "Players."+player;
		List<String> listGroups = new ArrayList<>();
		listGroups = Permissions.getStringList(testPlayer+".Groups");
		listGroups.remove(group);
		Permissions.set(testPlayer+".Groups", listGroups); 
		Save_Config();
	}

	public void setPlayerGroup(String player, String group) {
		// TODO Auto-setPlayerGroup method stub
		String testPlayer = "Players."+player;
		List<String> listGroups = new ArrayList<>();
		listGroups.add(group);
		Permissions.set(testPlayer+".Groups", listGroups); 
		Save_Config();
	}

	public void addPlayerGroup(String player, String group) {
		// TODO Auto-addPlayerGroup method stub
		String testPlayer = "Players."+player;
		List<String> listGroups = new ArrayList<>();
		listGroups = Permissions.getStringList(testPlayer+".Groups");
		listGroups.add(group);
		Permissions.set(testPlayer+".Groups", listGroups); 
		Save_Config();
	}
	
	

	public List<String> getPlayerGroups(String player) {
		// TODO Auto-getPlayerGroups method stub
		return Permissions.getStringList("Players." + player + ".Groups");
	}

	public List<String> getAllGroups() {
		// TODO Auto-getAllGroups method stub
		List<String> list = new ArrayList<String>();
		for (String groups : Permissions.getConfigurationSection("Groups").getKeys(false)) {
			list.add(groups);
		}
		return list;
	}

	public Map<String, Boolean> getGroupPermissions(String group, String world) {
		// TODO Auto-getGroupPermissions method stub
		Map<String, Boolean> reslit = new HashMap<String, Boolean>();
		for (String permission : Permissions.getStringList("Groups."+group+".permissions")) {
			reslit.put(permission.startsWith("-") ? permission.replaceFirst("-", ""): permission, permission.startsWith("-") ? false : true);
		}
		return reslit;
	}

	public Map<String, Boolean> getGroupPermissions(String group) {
		// TODO Auto-getGroupPermissions method stub
		Map<String, Boolean> reslit = new HashMap<String, Boolean>();
		for (String permission : Permissions.getStringList("Groups."+group+".permissions")) {
			reslit.put(permission.startsWith("-") ? permission.replaceFirst("-", ""): permission, permission.startsWith("-") ? false : true);
		}
		return reslit;
	}

	public void addGroupPermission(String group, String world, String permission, boolean value) {
		// TODO Auto-addGroupPermission method stub
		List<String> listPermissions = new ArrayList<String>();
		listPermissions = Permissions.getStringList("Groups."+group+".permissions");
		if (value) {
			listPermissions.add(permission);
		}else {
			listPermissions.add("-" + permission);
		}
		Permissions.set("Groups."+group+".permissions", listPermissions);
		Save_Config();
	}

	public void addGroupPermission(String group, String permission, boolean value) {
		// TODO Auto-addGroupPermission method stub
		List<String> listPermissions = new ArrayList<String>();
		listPermissions = Permissions.getStringList("Groups."+group+".permissions");
		if (value) {
			listPermissions.add(permission);
		}else {
			listPermissions.add("-" + permission);
		}
		Permissions.set("Groups."+group+".permissions", listPermissions);
		Save_Config();
	}

	public void removeGroupPermission(String group, String world, String permission) {
		// TODO Auto-removeGroupPermission method stub
		List<String> listPermissions = new ArrayList<String>();
		listPermissions = Permissions.getStringList("Groups."+group+".permissions");
		if (permission.startsWith("-")) {
			listPermissions.remove("-" + permission);
		}else {
			listPermissions.remove(permission);
		}
		Permissions.set("Groups."+group+".permissions", listPermissions);
		Save_Config();
	}

	public void removeGroupPermission(String group, String permission) {
		// TODO Auto-removeGroupPermission method stub
		List<String> listPermissions = new ArrayList<String>();
		listPermissions = Permissions.getStringList("Groups."+group+".permissions");
		if (permission.startsWith("-")) {
			listPermissions.remove("-" + permission);
		}else {
			listPermissions.remove(permission);
		}
		Permissions.set("Groups."+group+".permissions", listPermissions);
		Save_Config();
		
	}

	public void removeGroupPermissions(String group) {
		// TODO Auto-removeGroupPermissions method stub
		List<String> listPermissions = new ArrayList<String>();
		listPermissions = Permissions.getStringList("Groups."+group+".permissions");
		listPermissions.add(null);
		Permissions.set("Groups."+group+".permissions", listPermissions);
		Save_Config();
	}

	public void removeGroup(String group) {
		// TODO Auto-removeGroup method stub
		Permissions.set("Groups."+group, null);
		Save_Config();
	}
	
	public void addGroup(String group) {
		// TODO Auto-addGroup method stub
		List<String> list1 = new ArrayList<String>();
        Permissions.set("Groups."+group+".parent", "root");
        Permissions.set("Groups."+group+".default", false);
        Permissions.set("Groups."+group+".permissions", list1);
        Save_Config();
	}

	public void removePlayerPermission(String player, String world, String permission) {
		// TODO Auto-removePlayerPermission method stub
		PermissionAttachment attachment = this.playerPermissions.get(Bukkit.getPlayer(player).getUniqueId());
        attachment.setPermission(permission, false);
	}

	public void removePlayerPermission(String player, String permission) {
		// TODO Auto-removePlayerPermission method stub
		PermissionAttachment attachment = this.playerPermissions.get(Bukkit.getPlayer(player).getUniqueId());
        attachment.setPermission(permission, false);
	}

	public void addPlayerPermission(String player, String permission, boolean b) {
		// TODO Auto-addPlayerPermission method stub
		PermissionAttachment attachment = this.playerPermissions.get(Bukkit.getPlayer(player).getUniqueId());
		if (permission.startsWith("-")) {
    		String permissions1 = permission.replaceFirst("-", "");
            attachment.setPermission(permissions1, false);
    	}else {
            attachment.setPermission(permission, true);
    	}
	}

	public void addPlayerPermission(String player, String world, String permission, boolean b) {
		// TODO Auto-addPlayerPermission method stub
		PermissionAttachment attachment = this.playerPermissions.get(Bukkit.getPlayer(player).getUniqueId());
		if (permission.startsWith("-")) {
    		String permissions1 = permission.replaceFirst("-", "");
            attachment.setPermission(permissions1, false);
    	}else {
            attachment.setPermission(permission, true);
    	}
	}
	
	
	public String chatGetPlayerPrefix(String world, String player) {
		// TODO Auto-chatGetPlayerPrefix method stub
		String Prefix = cccp.FormatPause(Permissions.getString("Players." + player + ".Prefix"));
		if (Prefix.equals("")) {
			List<String> getGroupPrefix = Permissions.getStringList("Players." + player + ".Groups");
			String PrefixGroup = cccp.FormatPause(Permissions.getString("Groups." + getGroupPrefix.get(getGroupPrefix.size() - 1) + ".prefix"));
			return PrefixGroup == null ? "Prefix" : PrefixGroup;
		}else {
			return Prefix == null ? "Prefix" : Prefix;
		}
	}

	public String chatGetPlayerSuffix(String world, String player) {
		// TODO Auto-chatGetPlayerSuffix method stub
		String Suffix = cccp.FormatPause(Permissions.getString("Players." + player + ".Suffix"));
		if (Suffix.equals("")) {
			List<String> getGroupSuffix = Permissions.getStringList("Players." + player + ".Groups");
			String SuffixGroup = cccp.FormatPause(Permissions.getString("Groups." + getGroupSuffix.get(getGroupSuffix.size() - 1) + ".suffix"));
			return SuffixGroup == null ? "Prefix" : SuffixGroup;
		}else {
			return Suffix == null ? "Suffix" : Suffix;
		}
	}
	
	public void chatSetPlayerPrefix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		Permissions.set("Players." + player + ".Prefix", valueString);
		Save_Config();
	}

	public void chatSetPlayerSuffix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		Permissions.set("Players." + player + ".Suffix", valueString);
		Save_Config();
	}
}
