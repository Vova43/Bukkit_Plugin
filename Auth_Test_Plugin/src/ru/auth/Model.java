package ru.auth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class Model implements Listener {
	
	public Boolean IsLogin = false;
	public Player player;
	Main main;
	File сonfigPass;
	YamlConfiguration СonfigPass;
	int kick = 0;
	int kick_ = 0;

	public Model(Player player, Main main) {
		// TODO Автоматически созданная заглушка конструктора
		this.player = player;
		this.main = main;
		сonfigPass = new File(main.getDataFolder().getAbsolutePath()+"//Passwords.yml");
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
				main.getLogger().info("> Create file >> "+сonfigPass.getName());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		new BukkitRunnable() {
			 @Override
			 public void run() {
				 if (СonfigPass.getString(player.getName()+".Password") == null) {
					 player.sendMessage("§c♦ §7Нужно зарегистрировотся : /§cregister §7[§cпороль§7]");
					 kick++;
				 } else if (!IsLogin) {
					 player.sendMessage("§c♦ §7Нужно авторизироватся: /§clogin §7[§cпороль§7]");
					 kick++;
				 }
				 if (kick > 10) {
					 player.kickPlayer("§c♦ §7Прошло время ожидания");
				 }
	         }
		 }.runTaskTimer(main, 0, 120);
	}
	
	public void login(Player player, String pass) {
		if (СonfigPass.getString(player.getName()+".Password") != null) {
			if(СonfigPass.getString(player.getName()+".Password").equals(pass)) {
				IsLogin = true;
				player.sendMessage("§c♦ §7Успешная авторизация");
			}else {
				//if (kick_ > 10)
				//	player.kickPlayer("§c♦ §7Пороль не верный");
				//else
					player.sendMessage("§c♦ §7Пороль не верный");
				IsLogin = false;
			}
		}else{
			player.sendMessage("§c♦ §7Вы не зарегистрировонны");
		}
	}
	
	public void register(Player player, String pass) {
		if (СonfigPass.getString(player.getName()+".Password") == null) {
			СonfigPass.set(player.getName()+".Password", pass);
			player.sendMessage("§c♦ §7Успешная регистрация");
			try {
				СonfigPass.save(сonfigPass);
				сonfigPass = new File(main.getDataFolder().getAbsolutePath()+"//Passwords.yml");
				СonfigPass = YamlConfiguration.loadConfiguration(сonfigPass);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			player.sendMessage("§c♦ §7Вы уже зарегистрировонны");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerIssueCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String cmd = event.getMessage();
		if(this.player == player) {
			if(!(cmd.contains("register") || cmd.contains("login")))
			{ 
				if (!IsLogin) {
					//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
					event.setCancelled(true);
				}
			}
		}
	}
	
	

	@EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        main.RemoveListPlayer(player);
    }
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerBreakBlocks(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPlaceBlocks(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				//event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPickupItem(PlayerPickupItemEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if(this.player == player) {
			if (!IsLogin) {
				//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
				event.setCancelled(true);
			}
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
				if (player == this.player) {
					if(player.getGameMode() == GameMode.CREATIVE) return;
					if(!IsLogin)
					{
						player.setFireTicks(0);
						player.setRemainingAir(player.getMaximumAir());
						player.setHealth(player.getHealth());
						player.setFoodLevel(player.getFoodLevel());
						event.setCancelled(true);
					}
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
			main.getLogger().info("Error Code: EntityDamage");
			main.getLogger().info("Please post this to: " + main.getDescription().getWebsite().toString());
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInventoryClick(InventoryClickEvent event)
	{
		try {
			Player player = ((Player) event).getPlayer();
			if(this.player == player) {
				if (!IsLogin) {
					//player.sendMessage(">> Нужно авторизироватся: /login [пороль]");
					event.setCancelled(true);
				}
			}
		}catch (Exception e) { }
	}
}
