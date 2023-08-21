package test_Mine_Note_Block12.ru.Createds_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Create_instrument1 {
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
				fos.write("1=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("2=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("3=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("4=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("5=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("6=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("7=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("8=entity.blaze.hurt>+0.0,\n".getBytes());
				fos.write("9=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("10=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("11=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("12=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("13=XYLOPHONE>+0.0,\n".getBytes());
				fos.write("14=block.anvil.place>+0.0,\n".getBytes());
				fos.write("15=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("16=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("17=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("18=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("19=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("20=PIANO>+0.0,\n".getBytes());
				fos.write("21=PIANO>+0.0,\n".getBytes());
				fos.write("22=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("23=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("24=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("25=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("26=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("27=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("28=block.note_block.snare>+0.0,\n".getBytes());
				fos.write("29=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("30=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("31=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("32=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("33=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("34=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("35=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("36=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("37=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("38=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("39=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("40=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("41=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("42=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("43=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("44=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("45=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("46=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("47=block.lava.pop>+0.0,\n".getBytes());
				fos.write("48=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("49=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("50=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("51=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("52=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("53=PIANO>+0.0,\n".getBytes());
				fos.write("54=PIANO>+0.0,\n".getBytes());
				fos.write("55=block.tripwire.detach>+0.0,\n".getBytes());
				fos.write("56=PIANO>+0.0,\n".getBytes());
				fos.write("57=PIANO>+0.0,\n".getBytes());
				fos.write("58=PIANO>+0.0,\n".getBytes());
				fos.write("59=PIANO>+0.0,\n".getBytes());
				fos.write("60=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("61=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("62=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("63=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("64=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("65=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("66=FLUTE>+0.0,\n".getBytes());
				fos.write("67=FLUTE>+0.0,\n".getBytes());
				fos.write("68=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("69=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("70=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("71=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("72=FLUTE>+0.0,\n".getBytes());
				fos.write("73=FLUTE>+0.0,\n".getBytes());
				fos.write("74=FLUTE>+0.0,\n".getBytes());
				fos.write("75=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("76=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("77=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("78=FLUTE>+0.0,\n".getBytes());
				fos.write("79=FLUTE>+0.0,\n".getBytes());
				fos.write("80=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("81=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("82=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("83=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("84=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("85=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("86=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("87=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("88=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("89=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("90=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("91=CHIME>+0.0,\n".getBytes());
				fos.write("92=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("93=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("94=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("95=mob.horse.donkey.death>+0.0,\n".getBytes());
				fos.write("96=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("97=NULL>+0.0,\n".getBytes());
				fos.write("98=block.anvil.place>+0.0,\n".getBytes());
				fos.write("99=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("100=CHIME>+0.0,\n".getBytes());
				fos.write("101=block.note_block.harp>+0.0,\n".getBytes());
				fos.write("102=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("103=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("104=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("105=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("106=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("107=block.note_block.bass>+0.0,\n".getBytes());
				fos.write("108=mob.chicken.plop>+0.0,\n".getBytes());
				fos.write("109=PIANO>+0.0,\n".getBytes());
				fos.write("110=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("111=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("112=entity.experience_orb.pickup>+0.0,\n".getBytes());
				fos.write("113=entity.item.pickup>+0.0,\n".getBytes());
				fos.write("114=block.note_block.pling>+0.0,\n".getBytes());
				fos.write("115=block.note_block.hat>+0.0,\n".getBytes());
				fos.write("116=block.note_block.snare>+0.0,\n".getBytes());
				fos.write("117=entity.item.pickup>+0.0,\n".getBytes());
				fos.write("118=block.note_block.snare>+0.0,\n".getBytes());
				fos.write("119=weather.rain.above>+0.0,\n".getBytes());
				fos.write("120=block.ender_chest.close>+0.0,\n".getBytes());
				fos.write("121=BASS_DRUM>+0.0,\n".getBytes());
				fos.write("122=weather.rain.above>+0.0,\n".getBytes());
				fos.write("123=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("124=entity.zombie.infect>+0.0,\n".getBytes());
				fos.write("125=weather.rain.above>+0.0,\n".getBytes());
				fos.write("126=weather.rain.above>+0.0,\n".getBytes());
				fos.write("127=entity.lightning_bolt.impact>-0.9000,\n".getBytes());



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
