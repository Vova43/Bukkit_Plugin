package ru.plus_command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;

public class Execute_command_class extends BukkitCommand {
	Plugin plugin;
    Core_run_command Core_run_command_ = new Core_run_command();
	
    protected Execute_command_class(String name, Plugin p) {
		super(name);
		this.plugin = p;
		// TODO Auto-generated constructor stub
	}
    
	@Override
    public boolean execute(CommandSender sender, String command, String[] args) { // Config.set("MultiCommandSeparatorSymbol", "; ");
		Core_run_command_.runCommand(sender, command, args);
        return true;
    }
}
