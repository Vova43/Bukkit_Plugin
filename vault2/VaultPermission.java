package ru.permissions.vault;

import java.util.UUID;

public class VaultPermission extends AbstractVaultPermission {

    public VaultPermission() {
        super();
    }

    @Override
    public String getName() {
        return "VaultChat";
    }

	@Override
	public UUID lookupUuid(String player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userHasPermission(String world, UUID uuid, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userAddPermission(String world, UUID uuid, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userRemovePermission(String world, UUID uuid, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userAddTransient(String world, UUID uuid, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userRemoveTransient(String world, UUID uuid, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userInGroup(String world, UUID uuid, String group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userAddGroup(String world, UUID uuid, String group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userRemoveGroup(String world, UUID uuid, String group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] userGetGroups(String world, UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userGetPrimaryGroup(String world, UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean groupHasPermission(String world, String name, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupAddPermission(String world, String name, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupRemovePermission(String world, String name, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getGroups() {
		// TODO Auto-generated method stub
		return null;
	}


}