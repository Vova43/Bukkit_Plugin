package pluschat.ru;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.chat.Chat;

 

public final class Main extends JavaPlugin implements Listener {
	//"comfig.yml"
	    PluginManager pm;
		ColorCodesPause c = new ColorCodesPause();
		 
		
		@Override
		public void onEnable(){  
			File config;
			YamlConfiguration Config;
			
			File chats;
			YamlConfiguration Chats;
			
			File prefixSuffix;
			YamlConfiguration PrefixSuffix;
			
			if (!new File(getDataFolder().getAbsolutePath()).exists()) {
	        	boolean createDir = new File(getDataFolder().getAbsolutePath()).mkdir();
	        	if (createDir) {
	        		getLogger().info(this.getName()+" Create dir > "+new File(getDataFolder().getAbsolutePath()).getName());
	        	}
	        }
			
			config = new File(getDataFolder().getAbsolutePath()+"//Config.yml");
			Config = YamlConfiguration.loadConfiguration(config);
			
			chats = new File(getDataFolder().getAbsolutePath()+"//Chats.yml");
			Chats = YamlConfiguration.loadConfiguration(chats);
			
			prefixSuffix = new File(getDataFolder().getAbsolutePath()+"//PrefixSuffix.yml");
			PrefixSuffix = YamlConfiguration.loadConfiguration(prefixSuffix);
			pm = getServer().getPluginManager();
			pm.registerEvents(this, this);
			getLogger().info("PlusChat: Start");
			if (!chats.exists()) {
				try {
					FileOutputStream out = new FileOutputStream(chats);
					out.close();
				} 
				catch (FileNotFoundException e) {
						e.printStackTrace();
				} 
				catch (IOException e) {
						e.printStackTrace();
				}
				Chats.set("World.Symbol", "#");
				Chats.set("World.Range", -1);
				Chats.set("World.ColorChat", false);
				Chats.set("World.Global_Worlds", true);
				Chats.set("World.Format", "&7{&3[chatName]&7} &7[&4[worldName]&7]&r [prefixPlayer] [suffixPlayer] &7[playerName] #> &2[messagePlayer]");
				Chats.set("World.ChatDefaultPermission", false);
				Chats.set("World.ChatPermission", "useChat.World");
				Chats.set("World.ChatPermission_message", "&c♦ &7Нет прав на данный чат!");
				Chats.set("World.ColorChatDefaultPermission", false);
				Chats.set("World.ColorChatPermission", "useColotChat.World");
				Chats.set("World.ColorChatPermission_message", "&c♦ &7Нет прав на использование цвета в чате!");
				
				Chats.set("Global.Symbol", "!");
				Chats.set("Global.Range", -1);
				Chats.set("Global.ColorChat", false);
				Chats.set("Global.Global_Worlds", true);
				Chats.set("Global.Format", "[prefixPlayer] [suffixPlayer] &3[playerName] &6♦> &f[messagePlayer]");
				Chats.set("Global.ChatDefaultPermission", false);
				Chats.set("Global.ChatPermission", "useChat.Global");
				Chats.set("Global.ChatPermission_message", "&c♦ &7Нет прав на данный чат!");
				Chats.set("Global.ColorChatDefaultPermission", false);
				Chats.set("Global.ColorChatPermission", "useColotChat.Global");
				Chats.set("Global.ColorChatPermission_message", "&c♦ &7Нет прав на использование цвета в чате!");
				
				Chats.set("Local.Symbol", "");
				Chats.set("Local.Range", 50);
				Chats.set("Local.ColorChat", true);
				Chats.set("Local.Global_Worlds", false);
				Chats.set("Local.Format", "[prefixPlayer] [suffixPlayer] &b[playerName] &f> [messagePlayer]");
				Chats.set("Local.ChatDefaultPermission", true);
				Chats.set("Local.ChatPermission", "useChat.Local");
				Chats.set("Local.ChatPermission_message", "&c♦ &7Нет прав на данный чат!");
				Chats.set("Local.ColorChatDefaultPermission", true);
				Chats.set("Local.ColorChatPermission", "useColotChat.Local");
				Chats.set("Local.ColorChatPermission_message", "&c♦ &7Нет прав на использование цвета в чате!");
				try {
					Chats.save(chats);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				getLogger().info("PlusChat: Create Chats.yml");
			}
			if (!prefixSuffix.exists()) {
				try {
					FileOutputStream out = new FileOutputStream(prefixSuffix);
					out.close();
				} 
				catch (FileNotFoundException e) {
						e.printStackTrace();
				} 
				catch (IOException e) {
						e.printStackTrace();
				}
				PrefixSuffix.set("Default.PrefixDefaultPermission", true); 
				PrefixSuffix.set("Default.PrefixPermission", "prefix.default"); 
				PrefixSuffix.set("Default.SuffixDefaultPermision", true); 
				PrefixSuffix.set("Default.SuffixPermision", "suffix.default"); 
				PrefixSuffix.set("Default.Prefix", "&6[&2Default&6]&7"); 
				PrefixSuffix.set("Default.Suffix", "&6[&2♦&6]&7"); 
				
				PrefixSuffix.set("Vip.PrefixDefaultPermission", false); 
				PrefixSuffix.set("Vip.PrefixPermission", "prefix.vip"); 
				PrefixSuffix.set("Vip.SuffixDefaultPermision", false); 
				PrefixSuffix.set("Vip.SuffixPermision", "suffix.vip"); 
				PrefixSuffix.set("Vip.Prefix", "&6[&6Vip&6]&7"); 
				PrefixSuffix.set("Vip.Suffix", "&6[&6♦&6]&7"); 
				
				PrefixSuffix.set("Owner.PrefixDefaultPermission", false); 
				PrefixSuffix.set("Owner.PrefixPermission", "prefix.owner"); 
				PrefixSuffix.set("Owner.SuffixDefaultPermision", false); 
				PrefixSuffix.set("Owner.SuffixPermision", "suffix.owner"); 
				PrefixSuffix.set("Owner.Prefix", "&6[&4Owner&6]&7"); 
				PrefixSuffix.set("Owner.Suffix", "&6[&4♦&6]&7"); 
				try {
					PrefixSuffix.save(prefixSuffix);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				getLogger().info("PlusChat: Create PrefixSuffix.yml");
			}			
			if (!config.exists()) {
				try {
					FileOutputStream out = new FileOutputStream(config);
					out.close();
				} 
				catch (FileNotFoundException e) {
						e.printStackTrace();
				} 
				catch (IOException e) {
						e.printStackTrace();
				}
				Config.set("enable", true); 
				Config.set("useVault", false); 
				try {
					Config.save(config);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				getLogger().info("PlusChat: Create Config.yml");
			}
		}
		@Override
		public void onDisable() {
			getLogger().info("PlusChat: Disabled");
		}
		
		Chat chat = null;
		private boolean setupChat() {
	        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
	        if (rsp != null) {
	        	chat = rsp.getProvider();
	        	return chat != null;
	        }else {
	        	return false;
	        }
	        
	    }
		//boolean useVault = false;
		
		@EventHandler
		public void onChat(AsyncPlayerChatEvent event) {
			if (true) {
				
			}
			if (event.isCancelled()) {
				return;
			}
			File config;
			YamlConfiguration Config;
			
			File chats;
			YamlConfiguration Chats;
			
			File prefixSuffix;
			YamlConfiguration PrefixSuffix;
			
			config = new File(getDataFolder().getAbsolutePath()+"//Config.yml");
			Config = YamlConfiguration.loadConfiguration(config);
			
			chats = new File(getDataFolder().getAbsolutePath()+"//Chats.yml");
			Chats = YamlConfiguration.loadConfiguration(chats);
			
			prefixSuffix = new File(getDataFolder().getAbsolutePath()+"//PrefixSuffix.yml");
			PrefixSuffix = YamlConfiguration.loadConfiguration(prefixSuffix);
			
			if (Config.getBoolean("enable")) {
				for (String command : Chats.getKeys(false)) {
					if(event.getMessage().startsWith(Chats.getString(command+".Symbol"))) {
						//event.getPlayer().sendMessage(command);
						//String format = "//[prefixPlayer//] //[suffixPlayer//] //[playerName//] > //[message//]";
						String message = event.getMessage();
						String name = event.getPlayer().getName();
						String format = Chats.getString(command+".Format");
						double range = Chats.getInt(command+".Range");
						boolean global_Worlds = Chats.getBoolean(command+".Global_Worlds");
						boolean colorChat = (boolean) Chats.getBoolean(command+".ColorChat");
						boolean chatDefaultpermission = (boolean) Chats.getBoolean(command+".ChatDefaultPermission");
						boolean colorChatDefaultpermission = (boolean) Chats.getBoolean(command+".ColorChatDefaultPermission");
						String chatPermission = Chats.getString(command+".ChatPermission");
						String colorChatPermission = Chats.getString(command+".ColorChatPermission");
						
						String prefixPlayer = "";
						String suffixPlayer = "";
						
						boolean usePlaceholderAPI = Config.getBoolean("usePlaceholderAPI", false);
			    		if (usePlaceholderAPI) {
			    			if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			    				format = PlaceholderAPI.setPlaceholders(event.getPlayer(), format);
							}	    
			    		}
			    		
						boolean useVault = Config.getBoolean("useVault");
						if (useVault) {
							    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
									setupChat();
									if (chat.getPlayerPrefix(event.getPlayer()) != "") {
										prefixPlayer = chat.getPlayerPrefix(event.getPlayer());
									}
	                                if (chat.getPlayerSuffix(event.getPlayer()) != "") {
										suffixPlayer = chat.getPlayerSuffix(event.getPlayer());
									}
								}else {
									for (String command1 : PrefixSuffix.getKeys(false)) {
										  String prefixPermission = PrefixSuffix.getString(command1+".PrefixPermission");
										  String suffixPermission = PrefixSuffix.getString(command1+".SuffixPermision");
										  boolean prefixDefaultPermission = (boolean) PrefixSuffix.getBoolean(command1+".PrefixDefaultPermission");
										  boolean suffixDefaultPermission = (boolean) PrefixSuffix.getBoolean(command1+".SuffixDefaultPermision");
										  if (event.getPlayer().hasPermission(prefixPermission) || prefixDefaultPermission) {
											  if (PrefixSuffix.getString(command1+".Prefix") != "") {
												  prefixPlayer = PrefixSuffix.getString(command1+".Prefix");
											  }
										  }
										  if (event.getPlayer().hasPermission(suffixPermission) || suffixDefaultPermission) {
											  if (PrefixSuffix.getString(command1+".Suffix") != "") {
												  suffixPlayer = PrefixSuffix.getString(command1+".Suffix");
											  }
										  }
									  }
								}
						}else {
							for (String command1 : PrefixSuffix.getKeys(false)) {
								  String prefixPermission = PrefixSuffix.getString(command1+".PrefixPermission");
								  String suffixPermission = PrefixSuffix.getString(command1+".SuffixPermision");
								  boolean prefixDefaultPermission = (boolean) PrefixSuffix.getBoolean(command1+".PrefixDefaultPermission");
								  boolean suffixDefaultPermission = (boolean) PrefixSuffix.getBoolean(command1+".SuffixDefaultPermision");
								  if (event.getPlayer().hasPermission(prefixPermission) || prefixDefaultPermission) {
									  if (PrefixSuffix.getString(command1+".Prefix") != "") {
										  prefixPlayer = PrefixSuffix.getString(command1+".Prefix");
									  }
								  }
								  if (event.getPlayer().hasPermission(suffixPermission) || suffixDefaultPermission) {
									  if (PrefixSuffix.getString(command1+".Suffix") != "") {
										  suffixPlayer = PrefixSuffix.getString(command1+".Suffix");
									  }
								  }
							  }
						}
					
						message = message.replaceFirst(Chats.getString(command+".Symbol"), "");
						message = message.replace("&p", "\n");
						message = message.replace("%", "%%");
						format = format.replace("[chatName]", command);
						format = format.replace("[worldName]", event.getPlayer().getWorld().getName());
						format = format.replace("[playerName]", name);
						format = format.replace("[prefixPlayer]", prefixPlayer);
						format = format.replace("[suffixPlayer]", suffixPlayer);
						format = c.FormatPause(format);
						format = format.replace("[messagePlayer]", message);
						
						if (!event.getPlayer().hasPermission(chatPermission) && !chatDefaultpermission) {
							  String chatPermissionMessage = Chats.getString(command+".ChatPermission_message");
							  event.getPlayer().sendMessage(c.FormatPause(chatPermissionMessage));
							  event.setFormat("");
							  event.setMessage("");
							  event.getRecipients().clear();
							  return;
						}
						if (colorChat) {
							if (event.getPlayer().hasPermission(colorChatPermission) || colorChatDefaultpermission) {
								event.setFormat(c.FormatPause(format));
								event.setMessage(c.FormatPause(format));
							}else {
								event.setFormat(format);
								event.setMessage(format);
								String[] ColorNuber = {"&0","&1","&2","&3","&4","&5","&6","&7","&8","&9","&a","&b","&c","&e","&f","&r","&n","&l","&o","&m"};
								for (String ColorTest : ColorNuber) {
									if (format.contains(ColorTest)) {
										event.setFormat(format);
										event.setMessage(format);
										String colorChatPermissionMessage = Chats.getString(command+".ColorChatPermission_message");
										event.getPlayer().sendMessage(c.FormatPause(colorChatPermissionMessage));
									 }
								}
							}  
						}else {
							  event.setFormat(format);
							  event.setMessage(format);
						}  
						//event.getPlayer().sendMessage(range+" > "+command);
						if(range > 0) {
							  event.getRecipients().clear();
							  event.getRecipients().addAll(this.getLocalDistance(event.getPlayer(), range));
							  return;
						}
						if(!global_Worlds) {
							  event.getRecipients().clear();
							  event.getRecipients().addAll(this.getLocalWorld(event.getPlayer()));
							  return;
						}

						//event.setFormat(format);
						//event.setMessage(format);

						return;
					}
					
				}
			}
		}
		protected List<Player> getLocalDistance(Player sender, double range) {
			Location playerLocation = sender.getLocation();
			List<Player> PlayerList = new LinkedList<Player>();
			double squaredDistance = Math.pow(range, 2);
			for (Player PlayerListOnline : Bukkit.getServer().getOnlinePlayers()) {
				if (!PlayerListOnline.getWorld().equals(sender.getWorld())) {
					continue;
				}
				if (playerLocation.distanceSquared(PlayerListOnline.getLocation()) > squaredDistance) {
					continue;
				}
				PlayerList.add(PlayerListOnline);
			}
			return PlayerList;
		}
		protected List<Player> getLocalWorld(Player sender) {
			List<Player> PlayerList = new LinkedList<Player>();
			for (Player PlayerListOnline : Bukkit.getServer().getOnlinePlayers()) {
				if (PlayerListOnline.getWorld().getName().equals(sender.getWorld().getName())) {
					PlayerList.add(PlayerListOnline);
					//sender.sendMessage(PlayerListOnline.getWorld().getName());
				}
				if (PlayerListOnline.getWorld().getName().equals(sender.getWorld().getName()+"_nether")) {
					PlayerList.add(PlayerListOnline);
						
					//sender.sendMessage(PlayerListOnline.getWorld().getName());
				}
				if (PlayerListOnline.getWorld().getName().equals(sender.getWorld().getName()+"_the_end")) {
					PlayerList.add(PlayerListOnline);
						
					//sender.sendMessage(PlayerListOnline.getWorld().getName());
				}
				String _nether = PlayerListOnline.getWorld().getName()+"_nether";
				String _the_end = PlayerListOnline.getWorld().getName()+"_the_end";
					
				if (_nether.equals(sender.getWorld().getName())) {
					PlayerList.add(PlayerListOnline);
						
					//sender.sendMessage(PlayerListOnline.getWorld().getName());
				}
				if (_the_end.equals(sender.getWorld().getName())) {
					PlayerList.add(PlayerListOnline);
						
					//sender.sendMessage(PlayerListOnline.getWorld().getName());
				}
					
			}
			return PlayerList;
		}
}
