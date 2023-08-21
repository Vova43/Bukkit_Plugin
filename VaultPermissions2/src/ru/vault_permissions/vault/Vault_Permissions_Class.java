package ru.vault_permissions.vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

@SuppressWarnings("deprecation")
public class Vault_Permissions_Class extends Permission {

    private Vault_API perms;
    public boolean showConsole = false;

    public Vault_Permissions_Class(Plugin plugin, Vault_API perms) {
        this.plugin = plugin;
        this.perms = perms;
    }
    
    public void register() {
    	 Bukkit.getServicesManager().register(Permission.class, this, plugin, ServicePriority.Highest);
         if (showConsole){ 
        	 plugin.getLogger().info(plugin.getName()+" > Vault register start Permission");
         }
    }

    private final String name = "§6§o§nVaultPermissions§7-§c§oAPI§7-§2§oVault§a§oPermissions§r";
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean playerHas(String world, String player, String permission) {
		Player p = plugin.getServer().getPlayer(player);
		return p != null ? p.hasPermission(permission) : false;
	}

	@Override
	public boolean playerAdd(String world, String player, String permission) {
		permission = permission.toLowerCase();
        if (world != null) {
            this.perms.addPlayerPermission(player, world, permission, true);
        } else {
            this.perms.addPlayerPermission(player, permission, true);
        }
        return true;
	}

	// use superclass implementation of playerAddTransient() and playerRemoveTransient()

	@Override
	public boolean playerRemove(String world, String player, String permission) {
		 permission = permission.toLowerCase();
	     if (world != null) {
	         this.perms.removePlayerPermission(player, world, permission);
	     } else {
	         this.perms.removePlayerPermission(player, permission);
	     }
	     return true;
	}

	@Override
	public boolean groupHas(String world, String group, String permission) {
		permission = permission.toLowerCase();
        Map<String, Boolean> groupPermissions = this.perms.getGroupPermissions(group, world);
        return groupPermissions.containsKey(permission) && groupPermissions.get(permission);
	}

	@Override
	public boolean groupAdd(String world, String group, String permission) {
		permission = permission.toLowerCase();
		this.perms.addGroup(group);
        if (world != null) {
            this.perms.addGroupPermission(group, world, permission, true);
        } else {
            this.perms.addGroupPermission(group, permission, true);
        }
        return true;
	}

	@Override
	public boolean groupRemove(String world, String group, String permission) {
		permission = permission.toLowerCase();
		this.perms.removeGroup(group);
		/*
        if (world != null) {
            permission = world + ":" + permission;
            this.perms.removeGroupPermission(group, world, permission);
        } else {
            this.perms.removeGroupPermission(group, permission);
        }
        */
        return true;
	}

	@Override
	public boolean playerInGroup(String world, String player, String group) {
		return playerHas(world, player, "groups." + group);
	}

	@Override
	public boolean playerAddGroup(String world, String player, String group) {
		group = group.toLowerCase();
        this.perms.addPlayerGroup(player, group);
        return true;
	}

	@Override
	public boolean playerRemoveGroup(String world, String player, String group) {
		group = group.toLowerCase();
        this.perms.removePlayerGroup(player, group);
        return true;
	}

	@Override
	public String[] getPlayerGroups(String world, String player) {
		List<String> groupList = new ArrayList<String>();
		 for (String group : perms.getPlayerGroups(player)) {
         	groupList.add(group);
         }
        return groupList.toArray(new String[groupList.size()-1]);
	}

	@Override
	public String getPrimaryGroup(String world, String player) {
		if (perms.getPlayerGroups(player) != null && !perms.getPlayerGroups(player).isEmpty() ) {
            return perms.getPlayerGroups(player).get(0);
        }
        return null;
	}

	@Override
	public String[] getGroups() {
		 return perms.getAllGroups().toArray(new String[perms.getAllGroups().size()-1]);
	}

	@Override
	public boolean hasSuperPermsCompat() {
		return true;
	}

    @Override
    public boolean hasGroupSupport() {
        return false;
    }
}