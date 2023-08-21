package ru.vault_permissions.vault;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

public class Vault_Chat_Class extends Chat {

	private final Plugin plugin;
	Vault_API vault_API;
	//private boolean show;
    public Vault_Chat_Class(Plugin plugin, Vault_API vault_API, Vault_Permissions_Class permissionApi) {
        super(permissionApi);
        this.plugin = plugin;
        this.vault_API = vault_API;
    }

    private final String name = "§6§o§nVaultPermissions§7-§c§oAPI§7-§2§oVault§a§oPermissions§r";
	
	@Override
	public String getName() {
		return name;
	}

    public void register() {
        Bukkit.getServicesManager().register(Chat.class, this, plugin, ServicePriority.Highest);
        plugin.getLogger().info(plugin.getName()+" > Vault register start Chat");
    }

	@Override
	public boolean getGroupInfoBoolean(String world, String player, String valueString, boolean arg3) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return false;
	}

	@Override
	public double getGroupInfoDouble(String world, String player, String valueString, double doubleValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return 0;
	}

	@Override
	public int getGroupInfoInteger(String world, String player, String valueString, int intValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return 0;
	}

	@Override
	public String getGroupInfoString(String world, String player, String valueString, String node) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return null;
	}

	@Override
	public String getGroupPrefix(String world, String player) {
		// TODO Auto-generated method stub
		return vault_API.chatGetPlayerPrefix(world, player);
	}

	@Override
	public String getGroupSuffix(String world, String player) {
		// TODO Auto-generated method stub
		return vault_API.chatGetPlayerSuffix(world, player);
	}

	@Override
	public boolean getPlayerInfoBoolean(String world, String player, String valueString, boolean arg3) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return false;
	}

	@Override
	public double getPlayerInfoDouble(String world, String player, String valueString, double doubleValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return 0;
	}

	@Override
	public int getPlayerInfoInteger(String world, String player, String valueString, int intValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return 0;
	}

	@Override
	public String getPlayerInfoString(String world, String player, String valueString, String node) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return null;
	}

	@Override
	public String getPlayerPrefix(String world, String player) {
		// TODO Auto-generated method stub
		return vault_API.chatGetPlayerPrefix(world, player);
	}

	@Override
	public String getPlayerSuffix(String world, String player) {
		// TODO Auto-generated method stub
		return vault_API.chatGetPlayerSuffix(world, player);
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGroupInfoBoolean(String world, String player, String valueString, boolean arg3) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setGroupInfoDouble(String world, String player, String valueString, double doubleValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setGroupInfoInteger(String world, String player, String valueString, int intValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setGroupInfoString(String world, String player, String valueString, String node) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setGroupPrefix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		vault_API.chatSetPlayerPrefix(world, player, valueString);
	}

	@Override
	public void setGroupSuffix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		vault_API.chatSetPlayerSuffix(world, player, valueString);
	}

	@Override
	public void setPlayerInfoBoolean(String world, String player, String valueString, boolean arg3) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setPlayerInfoDouble(String world, String player, String valueString, double doubleValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setPlayerInfoInteger(String world, String player, String valueString, int intValue) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setPlayerInfoString(String world, String player, String valueString, String node) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setPlayerPrefix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		vault_API.chatSetPlayerPrefix(world, player, valueString);
	}

	@Override
	public void setPlayerSuffix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		vault_API.chatSetPlayerSuffix(world, player, valueString);
	}
}