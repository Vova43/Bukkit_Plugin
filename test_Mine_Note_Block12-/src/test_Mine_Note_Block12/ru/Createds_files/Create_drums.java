package test_Mine_Note_Block12.ru.Createds_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Create_drums {
	public void onCreate_file(String path){
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try (FileOutputStream fos = new FileOutputStream(file)) {
				System.out.println("Strart writer");
				
				//for (int i =0; i <= 127; i++) {
				//	String in = i+"="+"test"+i+",\n";
				//	fos.write(in.getBytes());
				//}
				
                fos.write("0=STICKS>+0.0,\n".getBytes());
				fos.write("1=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("2=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("3=STICKS>+0.0,\n".getBytes());
				fos.write("4=STICKS>+0.0,\n".getBytes());
				fos.write("5=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("6=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("7=STICKS>+0.0,\n".getBytes());
				fos.write("8=STICKS>+0.0,\n".getBytes());
				fos.write("9=SNARE_DRUM>+0.0,\n".getBytes());

				fos.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
