package ru.permissions.vault;

import net.milkbowl.vault.permission.Permission;

public class VaultPermission extends Permission {

    public VaultPermission() {
        super();
    }

    @Override
    public String getName() {
        return "§n§6VaultPermission§7-§cAPI§7-§2Just§aPermissions§r";
    }

	@Override
	public String[] getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayerGroups(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryGroup(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean groupAdd(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupHas(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupRemove(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGroupSupport() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSuperPermsCompat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerAdd(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerAddGroup(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerHas(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerInGroup(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerRemove(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerRemoveGroup(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}