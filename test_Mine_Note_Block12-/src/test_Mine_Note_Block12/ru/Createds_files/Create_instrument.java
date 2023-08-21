package test_Mine_Note_Block12.ru.Createds_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Create_instrument {
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
				fos.write("0=NULL>+0.0,\n".getBytes());
				fos.write("1=STICKS>+0.0,\n".getBytes());
				fos.write("2=PIANO>+0.0,\n".getBytes());
				fos.write("3=STICKS>+0.0,\n".getBytes());
				fos.write("4=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("5=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("6=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("7=PIANO>+0.0,\n".getBytes());
				fos.write("8=BELL>+0.0,\n".getBytes());
				fos.write("9=BELL>+0.0,\n".getBytes());
				fos.write("10=BELL>+0.0,\n".getBytes());
				fos.write("11=BELL>+0.0,\n".getBytes());
				fos.write("12=BELL>+0.0,\n".getBytes());
				fos.write("13=XYLOPHONE>+0.0,\n".getBytes());
				fos.write("14=BELL>+0.0,\n".getBytes());
				fos.write("15=BELL>+0.0,\n".getBytes());
				fos.write("16=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("17=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("18=PIANO>+0.0,\n".getBytes());
				fos.write("19=PIANO>+0.0,\n".getBytes());
				fos.write("20=PIANO>+0.0,\n".getBytes());
				fos.write("21=PIANO>+0.0,\n".getBytes());
				fos.write("22=PIANO>+0.0,\n".getBytes());
				fos.write("23=PIANO>+0.0,\n".getBytes());
				fos.write("24=PIANO>+0.0,\n".getBytes());
				fos.write("25=GUITAR>+0.0,\n".getBytes());
				fos.write("26=GUITAR>+0.0,\n".getBytes());
				fos.write("27=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("28=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("29=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("30=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("31=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("32=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("33=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("34=PIANO>+0.0,\n".getBytes());
				fos.write("35=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("36=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("37=GUITAR>+0.0,\n".getBytes());
				fos.write("38=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("39=BASS_GUITAR>+0.0,\n".getBytes());
				fos.write("40=FLUTE>+0.0,\n".getBytes());
				fos.write("41=FLUTE>+0.0,\n".getBytes());
				fos.write("42=FLUTE>+0.0,\n".getBytes());
				fos.write("43=FLUTE>+0.0,\n".getBytes());
				fos.write("44=FLUTE>+0.0,\n".getBytes());
				fos.write("45=FLUTE>+0.0,\n".getBytes());
				fos.write("46=FLUTE>+0.0,\n".getBytes());
				fos.write("47=FLUTE>+0.0,\n".getBytes());
				fos.write("48=PIANO>+0.0,\n".getBytes());
				fos.write("49=PIANO>+0.0,\n".getBytes());
				fos.write("50=PIANO>+0.0,\n".getBytes());
				fos.write("51=PIANO>+0.0,\n".getBytes());
				fos.write("52=PIANO>+0.0,\n".getBytes());
				fos.write("53=PIANO>+0.0,\n".getBytes());
				fos.write("54=PIANO>+0.0,\n".getBytes());
				fos.write("55=PIANO>+0.0,\n".getBytes());
				fos.write("56=PIANO>+0.0,\n".getBytes());
				fos.write("57=PIANO>+0.0,\n".getBytes());
				fos.write("58=PIANO>+0.0,\n".getBytes());
				fos.write("59=PIANO>+0.0,\n".getBytes());
				fos.write("60=PIANO>+0.0,\n".getBytes());
				fos.write("61=PIANO>+0.0,\n".getBytes());
				fos.write("62=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("63=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("64=FLUTE>+0.0,\n".getBytes());
				fos.write("65=FLUTE>+0.0,\n".getBytes());
				fos.write("66=FLUTE>+0.0,\n".getBytes());
				fos.write("67=FLUTE>+0.0,\n".getBytes());
				fos.write("68=FLUTE>+0.0,\n".getBytes());
				fos.write("69=FLUTE>+0.0,\n".getBytes());
				fos.write("70=FLUTE>+0.0,\n".getBytes());
				fos.write("71=FLUTE>+0.0,\n".getBytes());
				fos.write("72=FLUTE>+0.0,\n".getBytes());
				fos.write("73=FLUTE>+0.0,\n".getBytes());
				fos.write("74=FLUTE>+0.0,\n".getBytes());
				fos.write("75=GUITAR>+0.0,\n".getBytes());
				fos.write("76=FLUTE>+0.0,\n".getBytes());
				fos.write("77=FLUTE>+0.0,\n".getBytes());
				fos.write("78=FLUTE>+0.0,\n".getBytes());
				fos.write("79=FLUTE>+0.0,\n".getBytes());
				fos.write("80=PIANO>+0.0,\n".getBytes());
				fos.write("81=PIANO>+0.0,\n".getBytes());
				fos.write("82=PIANO>+0.0,\n".getBytes());
				fos.write("83=PIANO>+0.0,\n".getBytes());
				fos.write("84=PIANO>+0.0,\n".getBytes());
				fos.write("85=PIANO>+0.0,\n".getBytes());
				fos.write("86=PIANO>+0.0,\n".getBytes());
				fos.write("87=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("88=PIANO>+0.0,\n".getBytes());
				fos.write("89=PIANO>+0.0,\n".getBytes());
				fos.write("90=PIANO>+0.0,\n".getBytes());
				fos.write("91=CHIME>+0.0,\n".getBytes());
				fos.write("92=CHIME>+0.0,\n".getBytes());
				fos.write("93=CHIME>+0.0,\n".getBytes());
				fos.write("94=PIANO>+0.0,\n".getBytes());
				fos.write("95=PIANO>+0.0,\n".getBytes());
				fos.write("96=NULL>+0.0,\n".getBytes());
				fos.write("97=NULL>+0.0,\n".getBytes());
				fos.write("98=CHIME>+0.0,\n".getBytes());
				fos.write("99=NULL>+0.0,\n".getBytes());
				fos.write("100=CHIME>+0.0,\n".getBytes());
				fos.write("101=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("102=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("103=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("104=PIANO>+0.0,\n".getBytes());
				fos.write("105=PIANO>+0.0,\n".getBytes());
				fos.write("106=PIANO>+0.0,\n".getBytes());
				fos.write("107=PIANO>+0.0,\n".getBytes());
				fos.write("108=PIANO>+0.0,\n".getBytes());
				fos.write("109=PIANO>+0.0,\n".getBytes());
				fos.write("110=PIANO>+0.0,\n".getBytes());
				fos.write("111=PIANO>+0.0,\n".getBytes());
				fos.write("112=BELL>+0.0,\n".getBytes());
				fos.write("113=PIANO>+0.0,\n".getBytes());
				fos.write("114=PIANO>+0.0,\n".getBytes());
				fos.write("115=STICKS>+0.0,\n".getBytes());
				fos.write("116=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("117=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("118=SNARE_DRUM>+0.0,\n".getBytes());
				fos.write("119=NULL>0.1F,\n".getBytes());
				fos.write("120=NULL>+0.0,\n".getBytes());
				fos.write("121=NULL>+0.0,\n".getBytes());
				fos.write("122=NULL>+0.0,\n".getBytes());
				fos.write("123=NULL>+0.0,\n".getBytes());
				fos.write("124=NULL>+0.0,\n".getBytes());
				fos.write("125=NULL>+0.0,\n".getBytes());
				fos.write("126=NULL>+0.0,\n".getBytes());
				fos.write("127=SNARE_DRUM>+0.0,\n".getBytes());


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
