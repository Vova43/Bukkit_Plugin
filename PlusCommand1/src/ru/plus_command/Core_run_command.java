package ru.plus_command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Core_run_command {
	ColorCodesPause ccp = new ColorCodesPause();
	
	public int StringToInt(String s) {
    	String strNum = s.replaceAll("[^\\d]", "");
        if (0 == strNum.length()) {
            return 0;
        }
		return Integer.parseInt(strNum); 
    }

	public void runCommand(CommandSender sender, String command, String[] args) {
		YamlConfiguration Config = Main.getYamlConfiguration();
		String[] command_ = command.split(":");
		command = command_[0];
		if (sender instanceof ConsoleCommandSender) {
			String str = Config.getString("Command."+command+".RunCommand");
			boolean colorCodesPause = Config.getBoolean("ColorCodesPause");
		    for(String arrayS0 : str.split(Config.getString("MultiCommandSeparatorSymbol"))) {
		        String[] array0 = arrayS0.split(Config.getString("CommandSeparatorSymbol")); 
		        
		        if (StringToInt(Config.getString("Command."+command+".MinArgument")) <= args.length && args.length <= StringToInt(Config.getString("Command."+command+".MaxArgument"))) {
		        	if (array0[0].equalsIgnoreCase("Console")) {
		            	String commandS = array0[1];
		            	for (int i = 0; i < StringToInt(Config.getString("Command."+command+".MaxArgument")); i++) {
	            	    	if (i < args.length) {
	            	    		commandS = commandS.replace("{"+i+"}", args[i]);
	            	    	}else {
	            	    		commandS = commandS.replace("{"+i+"}", "");
	            	    	}
	            	    }
		            	if (colorCodesPause) {
		            		commandS = ccp.FormatPause(commandS);
		            	}
		            	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandS);  			
		            }
		        	
		            if (array0[0].equalsIgnoreCase("Player")) {
		            	String commandD = array0[1];
		            	String commandS = commandD;
		            	for (int i = 0; i < StringToInt(Config.getString("Command."+command+".MaxArgument")); i++) {
	            	    	if (i < args.length) {
	            	    		commandS = commandS.replace("{"+i+"}", args[i]);
	            	    	}else {
	            	    		commandS = commandS.replace("{"+i+"}", "");
	            	    	}
	            	    }
		            	String errorMessage = ccp.FormatPause(Config.getString("ErrorCommandRun"));
		            	errorMessage = errorMessage.replace("{command_name}", commandD);
		            	//errorMessage = errorMessage.replace("{args}", commandS);
		            	sender.sendMessage(errorMessage);
		            }
		            if (array0[0].equalsIgnoreCase("Tell")) {
		            	String commandS = array0[1];
	            	    for (int i = 0; i < StringToInt(Config.getString("Command."+command+".MaxArgument")); i++) {
	            	    	if (i < args.length) {
	            	    		commandS = commandS.replace("{"+i+"}", args[i]);
	            	    	}else {
	            	    		commandS = commandS.replace("{"+i+"}", "");
	            	    	}
	            	    }
	            	    sender.sendMessage(ccp.FormatPause(commandS));
		            }
		        }else {
		        	sender.sendMessage(ccp.FormatPause(Config.getString("Command."+command+".Usenge")));
	        		return;
		        }
		    }
    	}
		
		if (sender instanceof Player) {
    		if (sender.hasPermission(Config.getString("Command."+command+".Permission")) || Config.getBoolean("Command."+command+".Defaultpermission")) {
    			
    			String str = Config.getString("Command."+command+".RunCommand");
    			boolean colorCodesPause = Config.getBoolean("ColorCodesPause");
    		    for(String arrayS0 : str.split(Config.getString("MultiCommandSeparatorSymbol"))) {
    		        String[] array0 = arrayS0.split(Config.getString("CommandSeparatorSymbol")); 
    		        
    		        if (StringToInt(Config.getString("Command."+command+".MinArgument")) <= args.length && args.length <= StringToInt(Config.getString("Command."+command+".MaxArgument"))) {
    		        	if (array0[0].equalsIgnoreCase("Console")) {
    		            	String commandS = array0[1];
    		            	commandS = commandS.replace("{player}", sender.getName());
    		            	for (int i = 0; i < StringToInt(Config.getString("Command."+command+".MaxArgument")); i++) {
    	            	    	if (i < args.length) {
    	            	    		commandS = commandS.replace("{"+i+"}", args[i]);
    	            	    	}else {
    	            	    		commandS = commandS.replace("{"+i+"}", "");
    	            	    	}
    	            	    }
    		            	if (colorCodesPause) {
    		            		commandS = ccp.FormatPause(commandS);
    		            	}
    		            	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandS);  			
    		            }
    		        	
    		            if (array0[0].equalsIgnoreCase("Player")) {
    		            	String commandS = array0[1];
		            	    commandS = commandS.replace("{player}", sender.getName());
		            	    commandS = commandS.replace("{world}", ((Player) sender).getWorld().getName());
		            	    for (int i = 0; i < StringToInt(Config.getString("Command."+command+".MaxArgument")); i++) {
    	            	    	if (i < args.length) {
    	            	    		commandS = commandS.replace("{"+i+"}", args[i]);
    	            	    	}else {
    	            	    		commandS = commandS.replace("{"+i+"}", "");
    	            	    	}
    	            	    }
		            	    if (colorCodesPause) {
			            		commandS = ccp.FormatPause(commandS);
			            	}
		            	    Bukkit.getServer().dispatchCommand(sender, commandS);
    		            }
    		            if (array0[0].equalsIgnoreCase("Tell")) {
    		            	String commandS = array0[1];
		            	    commandS = commandS.replace("{player}", sender.getName());
		            	    commandS = commandS.replace("{world}", ((Player) sender).getWorld().getName());
		            	    for (int i = 0; i < StringToInt(Config.getString("Command."+command+".MaxArgument")); i++) {
    	            	    	if (i < args.length) {
    	            	    		commandS = commandS.replace("{"+i+"}", args[i]);
    	            	    	}else {
    	            	    		commandS = commandS.replace("{"+i+"}", "");
    	            	    	}
    	            	    }
		            	    sender.sendMessage(ccp.FormatPause(commandS));
    		            }
    		        }else {
    		        	sender.sendMessage(ccp.FormatPause(Config.getString("Command."+command+".Usenge")));
		        		return;
    		        }
    		    }
    		}else {
    			sender.sendMessage(ccp.FormatPause(Config.getString("Command."+command+".PermissionMessange")));
    		}
    	}
	}

}
