package ru.auth;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class AuthMain extends JavaPlugin implements Listener {
	public static AuthMain plugin;
    AuthColorCodesPause cccp = new AuthColorCodesPause();

	File сonfig;
	YamlConfiguration Сonfig;
	File сonfigPass;
	YamlConfiguration СonfigPass;
	
	HashMap<Player, AuthModel> ListPlayer = new HashMap<Player, AuthModel>();
	public void onEnable() {
        plugin = this;
        this.getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("> Author: vova436612");
       
        // TODO Configs
		if (!new File(getDataFolder().getAbsolutePath()).exists()) {
        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
        	if (createDir) {
        		getLogger().info("> Create dir: "+new File(getDataFolder().getAbsolutePath()).getName());
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
	        
	        try {
	        	Сonfig.save(сonfig);
				getLogger().info("> Create file: "+сonfig.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		сonfigPass = new File(getDataFolder().getAbsolutePath()+"//Passwords.yml");
		СonfigPass = YamlConfiguration.loadConfiguration(сonfigPass);
		if (!сonfigPass.exists()) {
	        try {
	        	
				FileOutputStream out = new FileOutputStream(сonfigPass);
				out.close();
			} 
			catch (FileNotFoundException e) {
					e.printStackTrace();
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
	        
	        try {
	        	СonfigPass.save(сonfigPass);
				getLogger().info("> Create file: "+сonfigPass.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		getLogger().info("> Load files");
    }

    public void onDisable() {
    	for (Player player : Bukkit.getOnlinePlayers()) {
    		player.kickPlayer("§c♦ §7Выключение сервера");
    	}
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("login"))
		{
			Player player = null;
			if (args.length == 1) {
				if(sender instanceof Player)
				{
					player = (Player) sender;
					ListPlayer.get(player).login(player, args[0]);
					//player.sendMessage(">> Нужно login: "+ args[0]);
					return true;
				}else {
					player = null;
					getLogger().info("> "+"Console can't login!");
					return true;
				}
			}
			
		}else if(commandLabel.equalsIgnoreCase("register")) {
			Player player = null;
			if (args.length == 1) {
				if(sender instanceof Player)
				{
					player = (Player) sender;
					ListPlayer.get(player).register(player, args[0]);
					//player.sendMessage(">> Нужно register: "+args[0]);
					return true;
				}else {
					player = null;
					getLogger().info("> "+"Console can't register!");
					return true;
				}
			}
		}
		return false;
	}
    
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        AuthModel Model_ = new AuthModel(player, this);
        ListPlayer.put(player, Model_);
    }
    
	@EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        RemoveListPlayer(player);
    }
    
    public void RemoveListPlayer(Player player) {
    	ListPlayer.put(player, null);
    	ListPlayer.remove(player);
    } 
    
    @EventHandler(priority = EventPriority.HIGH)
	public void onPlayerIssueCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String cmd = event.getMessage(); 
		if(!(cmd.contains("register") || cmd.contains("login")))
		{ 
			if (!ListPlayer.get(player).IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerBreakBlocks(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPlaceBlocks(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			//event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPickupItem(PlayerPickupItemEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (!ListPlayer.get(player).IsLogin) {
			//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
			event.setCancelled(true);
		}
	}

	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamage(EntityDamageEvent event)
	{
		Entity entity = event.getEntity();
		Player player = null;
		try
		{
			if(entity instanceof Player)
			{
				player = (Player) event.getEntity();
				if(player.getGameMode() == GameMode.CREATIVE) return;
				if(!ListPlayer.get(player).IsLogin)
				{
					player.setFireTicks(0);
					player.setRemainingAir(player.getMaximumAir());
					player.setHealth(player.getHealth());
					player.setFoodLevel(player.getFoodLevel());
					event.setCancelled(true);
				}
			}else
			{
				return;
			}
		}catch (Exception e)
		{
			try
			{
				if(player.getGameMode() == GameMode.CREATIVE) return;
			}catch (Exception e1){}
			e.printStackTrace();
			getLogger().info("> Error Code: EntityDamage");
			getLogger().info("> Please post this to: " + getDescription().getWebsite().toString());
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInventoryClick(InventoryClickEvent event)
	{
		try {
			Player player = ((Player) event).getPlayer();
			if (!ListPlayer.get(player).IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}catch (Exception e) { }
	}
}