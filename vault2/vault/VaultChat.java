package ru.permissions.vault;

import net.milkbowl.vault.chat.Chat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
public class VaultChat extends AbstractVaultChat {

	private final Plugin plugin;
    public VaultChat(Plugin plugin, VaultPermission permissionApi) {
        super(permissionApi);
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "VaultChat";
    }


    public void register() {
        Bukkit.getServicesManager().register(Chat.class, this, plugin, ServicePriority.Highest);
        plugin.getLogger().info(plugin.getName()+" Reload > Vault reg");
    }


	@Override
	public String getUserChatPrefix(String world, UUID uuid) {
		// TODO Auto-generated method stub
		if (getPlayerPrefix(world, uuid.toString()) != null) {
			return getPlayerPrefix(world, uuid.toString());
		}
		return "Prefix";
		
	}

	@Override
	public String getUserChatSuffix(String world, UUID uuid) {
		// TODO Auto-generated method stub
		//return getPlayerSuffix(world, uuid.toString());
		if (getPlayerSuffix(world, uuid.toString()) != null) {
			return getPlayerSuffix(world, uuid.toString());
		}
		return "Suffix";
		
	}

	@Override
	public void setUserChatPrefix(String world, UUID uuid, String prefix) {
		// TODO Auto-generated method stub
		setPlayerPrefix(world, uuid.toString(), prefix);
	}

	@Override
	public void setUserChatSuffix(String world, UUID uuid, String suffix) {
		// TODO Auto-generated method stub
		setPlayerSuffix(world, uuid.toString(), suffix);
	}

	@Override
	public String getUserMeta(String world, UUID uuid, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserMeta(String world, UUID uuid, String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGroupChatPrefix(String world, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGroupChatSuffix(String world, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupChatPrefix(String world, String name, String prefix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroupChatSuffix(String world, String name, String suffix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGroupMeta(String world, String name, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupMeta(String world, String name, String key, Object value) {
		// TODO Auto-generated method stub
		
	}
    
}