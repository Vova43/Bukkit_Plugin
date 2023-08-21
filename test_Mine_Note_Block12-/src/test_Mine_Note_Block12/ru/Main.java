package test_Mine_Note_Block12.ru;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import test_Mine_Note_Block12.ru.Createds_files.Main_Creater;

public class Main extends JavaPlugin implements Listener {
	PluginManager pm;
	String arg2;
	File dirMIDI;
	File dirINSTRUMENT;
	File dirDRUM;
	//MIDI_Receiver mCReceiver;
	List<MIDI_Receiver> mCReceiver = new ArrayList<MIDI_Receiver>();
	//List<Player> mPlayer = new ArrayList<Player>();
	
	@Override
	public void onEnable(){  
		pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		dirMIDI = new File(getDataFolder().getAbsolutePath()+"/midi");
		dirINSTRUMENT = new File(getDataFolder().getAbsolutePath()+"/instruments");
		dirDRUM = new File(getDataFolder().getAbsolutePath()+"/drums");
		//mCReceiver = new MIDI_Receiver(this);
		new Main_Creater().onCreate_files(getDataFolder().getAbsolutePath(), this);
	}
	
	@Override
	public void onDisable() {
		for (int i = 0; i < mCReceiver.size(); i++) {
			if (mCReceiver.get(i) != null) {
				mCReceiver.get(i).stop();
				mCReceiver.set(i, null);
			}
			//mCReceiver.set(i, null);
			//mPlayer.set(i, null);
		}
		System.gc();
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player playerEvent = event.getPlayer();
		for (int i = 0; i < mCReceiver.size(); i++) {
			if (mCReceiver.get(i) != null) {
				if (mCReceiver.get(i).getPlayer() == playerEvent) {
					mCReceiver.get(i).stop();
					mCReceiver.set(i, null);
				}
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player playerEvent = event.getPlayer();
		mCReceiver.add(new MIDI_Receiver(this, playerEvent));
	}
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO command list register
		//Player playerTabComplete = (Player) arg0;
		List<String> listDefaldCommand = new ArrayList<String>();
		listDefaldCommand.add("help");
		Collections.sort(listDefaldCommand);
		if (arg3.length == 1) {
			List<String> list0 = new ArrayList<String>();
			list0.add("start");
			list0.add("stop");
			list0.add("pause");
			list0.add("play");
			list0.add("file_manager");
			Collections.sort(list0);
			return StringUtil.copyPartialMatches(arg3[0], list0, new ArrayList<>());	
		}
		if (arg3.length == 2 && arg3[0].equals("play")) {
			//File dir = new File(getDataFolder().getAbsolutePath()+"//midi");
			List<String> listMIDI = new ArrayList<String>();
			ArrayList<File> files = filterMIDI(dirMIDI.listFiles());
			
			String[] names = new String[files.size()];
			int i = 0;
			for(File file: files) {
				names[i] = file.getName();
				i++;
			}
			for (String listOUT : names) {
				listMIDI.add(listOUT);
			}
			return listMIDI;
		}
		if (arg3.length == 3 && arg3[0].equals("play")) {
			//File dir = new File(getDataFolder().getAbsolutePath()+"//instruments");
			List<String> listTXT = new ArrayList<String>();
			ArrayList<File> files = filterTXT(dirINSTRUMENT.listFiles());
			
			String[] names = new String[files.size()];
			int i = 0;
			for(File file: files) {
				names[i] = file.getName();
				i++;
			}
			for (String listOUT : names) {
				listTXT.add(listOUT);
			}
			return listTXT;	
		}
		if (arg3.length == 4 && arg3[0].equals("play")) {
			//File dir = new File(getDataFolder().getAbsolutePath()+"//instruments");
			List<String> listTXT = new ArrayList<String>();
			ArrayList<File> files = filterTXT(dirDRUM.listFiles());
			
			String[] names = new String[files.size()];
			int i = 0;
			for(File file: files) {
				names[i] = file.getName();
				i++;
			}
			for (String listOUT : names) {
				listTXT.add(listOUT);
			}
			return listTXT;	
		}
		return listDefaldCommand;
	}
	
	
	String[] okFileExtensionsMIDI = {".mid",".midi",".kar",".txt"};
	public ArrayList<File> filterMIDI(File[] file_list) {
		ArrayList<File> files = new ArrayList<File>();
		for (File file : file_list) {
			if (file.isDirectory()){
			    files.add(file);
			}else{
				for (String extension : okFileExtensionsMIDI){
					if (file.getName().toLowerCase().endsWith(extension)){
						files.add(file);
					}
				}
			}
		}
		return files;
	}
	String[] okFileExtensionsTXT = {".txt"};
	public ArrayList<File> filterTXT(File[] file_list) {
		ArrayList<File> files = new ArrayList<File>();
		for (File file : file_list) {
			if (file.isDirectory()){
			    files.add(file);
			}else{
				for (String extension : okFileExtensionsTXT){
					if (file.getName().toLowerCase().endsWith(extension)){
						files.add(file);
					}
				}
			}
		}
		return files;
	}
	
	public int StringToInt(String s) {
    	String strNum = s.replaceAll("[^\\d]", "");
        if (0 == strNum.length()) {
            return 0;
        }
		return Integer.parseInt(strNum);
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if (cmd.getName().equals("play_midi") && sender instanceof Player) {
    		Player playerCommand = (Player) sender;
    		if (args.length == 0) {
    			return false;
    		}
    		if (args[0].equals("file_manager")) {
    			if (args.length == 2) {
    				File[] dirListMIDI = dirMIDI.listFiles();
        			ArrayList<File> files = new ArrayList<File>();
        			for (File fileAdd : dirListMIDI) {
        				files.add(fileAdd);
        			}
        			Collections.sort(files, new Comparator<File>() {
        				public int compare(File lhsS, File rhsS){
    						File lhs = new File(lhsS.toString());
    						File rhs= new File(rhsS.toString());
    						if (lhs.isDirectory() && rhs.isFile()){
    							return -1;
    						} else if (lhs.isFile() && rhs.isDirectory()){
    							return 1;  
    						}
    						return lhs.getName().compareTo(rhs.getName());
    					}
        			});
        			ArrayList<String> FileListSendPlayer = new ArrayList<String>();
        			for (File FileNameToString : files) {
        				if (FileNameToString.isDirectory()) {
        					FileListSendPlayer.add("[Directory] "+FileNameToString.getName());
        				}
                        if (FileNameToString.isFile()) {
                        	FileListSendPlayer.add("[File] "+FileNameToString.getName());
        				}
        			}
    				
    				int numberList = StringToInt(args[1]);
    				
    				
    				int i = 0;
    				while(numberList+8 > numberList+i && numberList+i < FileListSendPlayer.size()) {
    					TextComponent message = new TextComponent(FileListSendPlayer.get(numberList+i));
    					message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/play_midi play "+files.get(numberList+i).getName()));
    					playerCommand.spigot().sendMessage(message);
    					
    					i++;
    				}
    				
    				
    				//for(int i1 = 0; i1 < FileListSendPlayer.size(); i1++) {
    				//	System.out.println(FileListSendPlayer.get(i1));
    				//}
    				//String jsonString = "[\"\", {\"text\":\"" + playerName + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"test\"}},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + hoverText + "\"}}]";
    				//{"text":"Clik","clickEvent":{"action":"run_command","value":"test"}}
    				
    				
    				playerCommand.sendMessage("[List size] "+numberList);
    			}
    		}
    		if (args[0].equals("play")) {
    			if (args.length == 6) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						try {
            						mCReceiver.get(i).start(getDataFolder().getAbsolutePath()+"/midi/"+args[1], getDataFolder().getAbsolutePath()+"/instruments/"+args[2], getDataFolder().getAbsolutePath()+"/drums/"+args[3], Boolean.parseBoolean(args[4]), args[5]);
            						return true;
            					} catch (MidiUnavailableException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (InvalidMidiDataException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (IOException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
            				}
        				}
        			}
    			}
    			if (args.length == 5) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						try {
            						mCReceiver.get(i).start(getDataFolder().getAbsolutePath()+"/midi/"+args[1], getDataFolder().getAbsolutePath()+"/instruments/"+args[2], getDataFolder().getAbsolutePath()+"/drums/"+args[3], Boolean.parseBoolean(args[4]), "");
            						return true;
            					} catch (MidiUnavailableException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (InvalidMidiDataException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (IOException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
            				}
        				}
        			}
    			}
    			if (args.length == 4) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						try {
            						mCReceiver.get(i).start(getDataFolder().getAbsolutePath()+"/midi/"+args[1], getDataFolder().getAbsolutePath()+"/instruments/"+args[2], getDataFolder().getAbsolutePath()+"/drums/"+args[3], false, "");
            						return true;
            					} catch (MidiUnavailableException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (InvalidMidiDataException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (IOException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
            				}
        				}
        			}
    			}
    			if (args.length == 3) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						try {
            						mCReceiver.get(i).start(getDataFolder().getAbsolutePath()+"/midi/"+args[1], getDataFolder().getAbsolutePath()+"/instruments/"+args[2], getDataFolder().getAbsolutePath()+"/drums/drumFile.txt", false, "");
            						return true;
            					} catch (MidiUnavailableException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (InvalidMidiDataException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (IOException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
            				}
        				}
        			}
    			}
    			if (args.length == 2) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						try {
            						mCReceiver.get(i).start(getDataFolder().getAbsolutePath()+"/midi/"+args[1], getDataFolder().getAbsolutePath()+"/instruments/instrumentFile.txt", getDataFolder().getAbsolutePath()+"/drums/drumFile.txt", false, "");
            						return true;
            					} catch (MidiUnavailableException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (InvalidMidiDataException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					} catch (IOException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
        					}
        				}
    				}
    			}
    		}
    		if (args[0].equals("start")) {
    			if (args.length == 1) {
    				mCReceiver.add(new MIDI_Receiver(this, playerCommand));
    				playerCommand.sendMessage(ChatColor.YELLOW + " >> " + ChatColor.AQUA + "Плеер создан");
    			}
    		}
    		if (args[0].equals("stop")) {
    			if (args.length == 1) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						mCReceiver.get(i).stop();
        						return true;
        					}
        				}
    				}
    			}
    		}
    		if (args[0].equals("pause")) {
    			if (args.length == 1) {
    				for (int i = 0; i < mCReceiver.size(); i++) {
        				if (mCReceiver.get(i) != null) {
        					if (mCReceiver.get(i).getPlayer() == playerCommand) {
        						mCReceiver.get(i).pause();
        						return true;
        					}
        				}
    				}
    			}
			}
		}
		return false;
	}
}
