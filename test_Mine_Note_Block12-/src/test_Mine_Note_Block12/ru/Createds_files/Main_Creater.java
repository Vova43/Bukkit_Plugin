package test_Mine_Note_Block12.ru.Createds_files;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.Plugin;

public class Main_Creater {
	public void onCreate_files(String path, Plugin plugin){
		if (!new File(path).exists()) {
        	boolean createDir0 = new File(path).mkdir();
        	if (createDir0) {
        		plugin.getLogger().info(plugin.getName()+" Create dir > "+new File(path).getName());
        	}
        }
		if (!new File(path+"//midi").exists()) {
        	boolean createDir1 = new File(path+"//midi").mkdir();
        	if (createDir1) {
        		plugin.getLogger().info(plugin.getName()+" Create dir > "+new File(path+"//midi").getName());
        	}
        }
		if (!new File(path+"//instruments").exists()) {
        	boolean createDir2 = new File(path+"//instruments").mkdir();
        	if (createDir2) {
        		plugin.getLogger().info(plugin.getName()+" Create dir > "+new File(path+"//instruments").getName());
        	}
        }
		if (!new File(path+"//drums").exists()) {
        	boolean createDir3 = new File(path+"//drums").mkdir();
        	if (createDir3) {
        		plugin.getLogger().info(plugin.getName()+" Create dir > "+new File(path+"//drums").getName());
        	}
        }
		
		if (!new File(path+"//instruments//instrumentFile.txt").exists()) {
			plugin.getLogger().info(plugin.getName()+" Create file > "+new File(path+"//instruments//instrumentFile.txt").getName());
			try {
				new File(path+"//instruments//instrumentFile.txt").createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Create_instrument cf = new Create_instrument();
    		cf.onCreate_file(path+"//instruments//instrumentFile.txt");
        }
		
		if (!new File(path+"//instruments//instrumentFile1.txt").exists()) {
			plugin.getLogger().info(plugin.getName()+" Create file > "+new File(path+"//instruments//instrumentFile1.txt").getName());
			try {
				new File(path+"//instruments//instrumentFile1.txt").createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Create_instrument1 cf = new Create_instrument1();
    		cf.onCreate_file(path+"//instruments//instrumentFile1.txt");
        }
		
		if (!new File(path+"//instruments//instrumentFile2.txt").exists()) {
			plugin.getLogger().info(plugin.getName()+" Create file > "+new File(path+"//instruments//instrumentFile2.txt").getName());
			try {
				new File(path+"//instruments//instrumentFile2.txt").createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Create_instrument2 cf = new Create_instrument2();
    		cf.onCreate_file(path+"//instruments//instrumentFile2.txt");
        }
		
		if (!new File(path+"//drums//drumFile.txt").exists()) {
			plugin.getLogger().info(plugin.getName()+" Create file > "+new File(path+"//drums//drumFile.txt").getName());
			try {
				new File(path+"//drums//drumFile.txt").createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Create_drums cf1 = new Create_drums();
    		cf1.onCreate_file(path+"//drums//drumFile.txt");
        }
	}

}
