package ru.auth;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AuthModel {
	
	public Boolean IsLogin = false;
	public Player player;
	AuthMain main;
	File сonfigPass;
	YamlConfiguration СonfigPass;
	int kick = 0;
	int kick_ = 0;

	public AuthModel(Player player, AuthMain main) {
		// TODO Автоматически созданная заглушка конструктора
		this.player = player;
		this.main = main;
		СonfigPass();
		
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
	
	private void СonfigPass() {
		сonfigPass = new File(main.getDataFolder().getAbsolutePath()+"//Passwords.yml");
		СonfigPass = YamlConfiguration.loadConfiguration(сonfigPass);
	}

	
	public void login(Player player, String pass) {
		СonfigPass();
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
		СonfigPass();
		if (СonfigPass.getString(player.getName()+".Password") == null) {
			СonfigPass.set(player.getName()+".Password", pass);
			player.sendMessage("§c♦ §7Успешная регистрация");
			try {
				СonfigPass.save(сonfigPass);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			player.sendMessage("§c♦ §7Вы уже зарегистрировонны");
		}
	}
}
