package ru.permissions.vault;

import net.milkbowl.vault.chat.Chat;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
@SuppressWarnings("deprecation")
public class VaultChat extends Chat {

	ArrayList<String> playerListPrefix = new ArrayList<String>();
	ArrayList<String> playerListSuffix = new ArrayList<String>();
	ArrayList<String> suffixList = new ArrayList<String>();
	ArrayList<String> prefixList = new ArrayList<String>();
	ArrayList<String> worldListPrefix = new ArrayList<String>();
	ArrayList<String> worldListSuffix = new ArrayList<String>();
	private final Plugin plugin;
	private boolean show;
    public VaultChat(Plugin plugin, VaultPermission permissionApi, boolean show) {
        super(permissionApi);
        this.plugin = plugin;
        this.show = show;
    }

    @Override
    public String getName() {
        return "§6§o§nVaultChat§7-§c§oAPI§7-§2§oJust§a§oPermissions§r";
    }


    public void register() {
        Bukkit.getServicesManager().register(Chat.class, this, plugin, ServicePriority.Highest);
        plugin.getLogger().info(plugin.getName()+" > Vault register start");
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
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return null;
	}

	@Override
	public String getGroupSuffix(String world, String player) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
		//return null;
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
		for (int i =0; i < playerListPrefix.size(); i++) {
			if (playerListPrefix.get(i).equals(Bukkit.getPlayer(player).getName())) {
				return prefixList.get(i).toString();
			}
		}
		return "Prefix";
	}

	@Override
	public String getPlayerSuffix(String world, String player) {
		// TODO Auto-generated method stub
		for (int i =0; i < playerListSuffix.size(); i++) {
			if (playerListSuffix.get(i).equals(Bukkit.getPlayer(player).getName())) {
				return suffixList.get(i).toString();
			}
		}
		return "Suffix";
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
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
	}

	@Override
	public void setGroupSuffix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method is unsupported. Wait for the next updates");
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
		if (show) {
			Bukkit.getPlayer(player).sendMessage("PlayerName >> "+Bukkit.getPlayer(player).getName()+" value>> "+valueString);
		}
		if (player != null && valueString != null) {
			int i2 = playerListPrefix.size();
			if (i2 == 0) {
				playerListPrefix.add(Bukkit.getPlayer(player).getName());
				prefixList.add(valueString);
			    worldListPrefix.add(world);
			}
			for (int i =0; i < i2; i++) {
				if (playerListPrefix.get(i).equals(Bukkit.getPlayer(player).getName())) {
					playerListPrefix.set(i, Bukkit.getPlayer(player).getName());
					prefixList.set(i, valueString);
					worldListPrefix.set(i, world);
				}else {
					playerListPrefix.add(Bukkit.getPlayer(player).getName());
					prefixList.add(valueString);
					worldListPrefix.add(world);
				}
			}
		}
	}

	@Override
	public void setPlayerSuffix(String world, String player, String valueString) {
		// TODO Auto-generated method stub
		if (show) {
			Bukkit.getPlayer(player).sendMessage("PlayerName >> "+Bukkit.getPlayer(player).getName()+" value>> "+valueString);
		}
		if (player != null && valueString != null) {
			int i2 = playerListSuffix.size();
			if (i2 == 0) {
				playerListSuffix.add(Bukkit.getPlayer(player).getName());
				suffixList.add(valueString);
				worldListSuffix.add(world);
			}
			for (int i =0; i < i2; i++) {
				if (playerListSuffix.get(i).equals(Bukkit.getPlayer(player).getName())) {
					playerListSuffix.set(i, Bukkit.getPlayer(player).getName());
					suffixList.set(i, valueString);
					worldListSuffix.set(i, world);
				}else {
					playerListSuffix.add(Bukkit.getPlayer(player).getName());
					suffixList.add(valueString);
					worldListSuffix.add(world);
				}
			}
		}
	}
}