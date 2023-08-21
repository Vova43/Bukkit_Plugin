package test_Mine_Note_Block12.ru;

import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class MIDI_Receiver implements Receiver, Listener {

    private Player player;
    private Sequencer sequencer;
    private Plugin plugin;
    private String midiFile;
    private String instrumentFile;
    private String drumsFile;
    public boolean InInformation = false;
    public boolean InPlay = false;
    public boolean InPause = false;
    public long midiPauseProg, midiPauseProgMs;
    private String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    //File file;
    File instrument_file;
    File drum_file;
    // list instrument
	ArrayList<Integer> key_instrument = null;
	ArrayList<String> volue_instrument = null;
	ArrayList<String> volue_instrument_note = null;
	// list drums
	ArrayList<Integer> key_drum = null;
	ArrayList<String> volue_drum = null;
	ArrayList<String> volue_drum_note = null;
	
    public MIDI_Receiver(Plugin plugin1, Player player1) {
        this.plugin = plugin1;
        this.player = player1;
    }
    
	//private final List<Player> playerInMidiPlayer = new ArrayList<Player>();
	private final Map<Integer, Byte> channelPatches = new HashMap<Integer, Byte>(); 

	public Player getPlayer() {
		return player;
	}
	
	public int StringToInt(String s) {
    	String strNum = s.replaceAll("[^\\d]", "");
        if (0 == strNum.length()) {
            return 0;
        }
		return Integer.parseInt(strNum);
    }
	
	int[] banChannelsInt = new int[16];
	public void start(String midiFile1, String instrumentFile1, String drumsFile1, boolean inInformation1, String banChannel) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        stop();
        int[] banChannelsInt1 = new int[16];
        String[] banChannels = banChannel.split(",");
        if (banChannel != "") {
        	for (String t : banChannels) {
            	int int1 = StringToInt(t);
            	if (int1 < 16) {
                	banChannelsInt1[int1] = int1;
            	}
            }
        }
        banChannelsInt = banChannelsInt1;
        if (new File(midiFile1).exists() && new File(instrumentFile1).exists()) {
            this.midiFile = midiFile1;
            this.instrumentFile = instrumentFile1;
            this.drumsFile = drumsFile1;
            this.InInformation = inInformation1;
        	startSequencer(midiFile1);
        	readerFile(instrumentFile1, drumsFile1);
            player.sendMessage(ChatColor.YELLOW + " >> " + ChatColor.AQUA + "Воспроизведение: " + ChatColor.YELLOW + new File(midiFile1).getName());
        }else {
        	player.sendMessage(ChatColor.YELLOW + " >> " + ChatColor.AQUA + "Ошибка воспроизведения: " + ChatColor.YELLOW + midiFile1 + " "  + ChatColor.YELLOW + instrumentFile1);
        }
	}
	
	public void readerFile(String instrumentFile, String drumsFile) {
		// чтение instrumentFile
        instrument_file = new File(instrumentFile);
        try {
 			FileInputStream fis = new FileInputStream(instrument_file);
 			BufferedReader bfr = new BufferedReader(new InputStreamReader(fis));
 			String line;
 			StringBuilder builder = new StringBuilder();
 			while((line = bfr.readLine()) != null) {
 				builder.append(line + "\n");
 			}
 			fis.close();
			key_instrument = new ArrayList<Integer>();
			volue_instrument = new ArrayList<String>();
			volue_instrument_note = new ArrayList<String>();
			/*
 			String LIST = builder.toString();
 			for (String  arg1 : LIST.split(",\n")) {
 				String[] arg2 = arg1.split("=");
 				key_instrument.add(Integer.parseInt(arg2[0]));
 				volue_instrument.add(arg2[1]);
 				
 			}
 			*/
 			String volue0IN = builder.toString();
 			//System.out.println(volue0IN);
 			for (String  volue1OUT : volue0IN.split(",\n")) {
 				String[] volue1OU_LIST = volue1OUT.split("=");
 				String[] volue2OU_LIST = volue1OU_LIST[1].split(">");
 				key_instrument.add(Integer.parseInt(volue1OU_LIST[0]));
 				volue_instrument.add(volue2OU_LIST[0]);
 				volue_instrument_note.add(volue2OU_LIST[1]);
 			}
 			
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
        
        // чтение drumsFile
        drum_file = new File(drumsFile);
        try {
 			FileInputStream fis = new FileInputStream(drum_file);
 			BufferedReader bfr = new BufferedReader(new InputStreamReader(fis));
 			String line;
 			StringBuilder builder = new StringBuilder();
 			while((line = bfr.readLine()) != null) {
 				builder.append(line + "\n");
 			}
 			fis.close();
			key_drum = new ArrayList<Integer>();
			volue_drum = new ArrayList<String>();
			volue_drum_note = new ArrayList<String>();
			/*
 			String LIST = builder.toString();
 			for (String  arg1 : LIST.split(",\n")) {
 				String[] arg2 = arg1.split("=");
 				key_instrument.add(Integer.parseInt(arg2[0]));
 				volue_instrument.add(arg2[1]);
 				
 			}
 			*/
 			String volue0IN = builder.toString();
 			//System.out.println(volue0IN);
 			for (String  volue1OUT : volue0IN.split(",\n")) {
 				String[] volue1OU_LIST = volue1OUT.split("=");
 				String[] volue2OU_LIST = volue1OU_LIST[1].split(">");
 				key_drum.add(Integer.parseInt(volue1OU_LIST[0]));
 				volue_drum.add(volue2OU_LIST[0]);
 				volue_drum_note.add(volue2OU_LIST[1]);
 			}
 			
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
	}
	
	public void startSequencer(String midiFile1) {
		InPlay = true;
        try {
			sequencer = MidiSystem.getSequencer(false);
			InputStream inputStream = new FileInputStream(midiFile1);
	        sequencer.setSequence(MidiSystem.getSequence(inputStream));
	        Transmitter transmitter = sequencer.getTransmitter();
	        transmitter.setReceiver(this);
	        sequencer.open();
	        sequencer.start();
	        player.sendMessage(ChatColor.YELLOW + " >> "+ChatColor.AQUA +"Track speed: "+sequencer.getTempoInBPM());
	        sequencer.setTempoInBPM(sequencer.getTempoInBPM()-2);
			
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		if (!InPause) {
			if (InPlay) {
				midiPauseProg = sequencer.getTickPosition();
				midiPauseProgMs = sequencer.getMicrosecondPosition();
				sequencer.stop();
				player.sendMessage(ChatColor.YELLOW + " >> "+ChatColor.AQUA +"Пауза ");
			}
			InPause = true;
		}else {
			startSequencer(midiFile);
			sequencer.setTickPosition(midiPauseProg);
			player.sendMessage(ChatColor.YELLOW + " >> "+ChatColor.AQUA +"Воспроизведение ");
			InPause = false;
		}
	}
	
	public void stop() {
		if (this.InPlay && sequencer.isOpen()) {
			InPlay = false;
			sequencer.stop();
			this.sequencer = null;
			player.sendMessage(ChatColor.YELLOW + " >> " + ChatColor.AQUA + "Воспроизведение остоновлено");
			plugin.getServer().getScheduler().cancelTasks(plugin);
		}
	}
	
	@Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage) {
        	if (!(message instanceof ShortMessage))
    			return;
    		ShortMessage event = (ShortMessage) message;
    		if (event.getCommand() == ShortMessage.NOTE_ON) {		
    			int key_instrument = event.getData1();
                int octave = (key_instrument / 12) - 1;
                int note = key_instrument % 12;
    			int channel = event.getChannel();
    			byte patch = 1;
    			float volume = event.getData2() / 63.5F;
    			
    			String name = NOTE_NAMES[note];
     	        octave = octave < 0 ? 0 : 1;
     	        boolean sharp = name.contains("#");
     	        Note.Tone tone = Note.Tone.valueOf(name.replaceAll("#", ""));
     	        
    			if (channelPatches.containsKey(channel))
    				patch = channelPatches.get(channel);
    			
    			
    			/*
    			try {
                	//float sleep = event.getData2() / 31.75F;
					Thread.sleep((long) octave);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
    	        if(player != null) {
    	        	//player.sendMessage("Information >> " + banChannelsInt[channel] + " " + channel);
    	            
    	        	if (banChannelsInt[channel+1] != channel+1) {
	        			if (InInformation) {
	    	        		int octave1 = (key_instrument % 12) - 1;
	        	            int note1 = Math.abs(octave1 % 12);
	        	            player.sendMessage("Information >> " + " " +NOTE_NAMES[note1] + octave1 + " " + patch + " " + volue_instrument.get(patch) + " " + channel + " " + volue_drum.get(channel)+" " + volume+" " + timeStamp);
	        	        	
	        	        	player.sendMessage("Information >> " + "Octave > " +NOTE_NAMES[note1] + octave1 + " <> Instrument > "+ "code: " + patch + " = " + volue_instrument.get(patch) + " <> Channel > " + channel + " = " + volue_drum.get(channel)+" <> Volume > " + volume+" <> Tone > " + timeStamp);
	    	        	}
	    	        	//player.sendMessage("Play >> "+ patch+"  "+channel+"  "+octave +" "+ key_instrument +" "+ sharp+" "+ octave +" "+ tone);
	    	        	if (channel == 9) {
	    	        		onPlayMidiDrum(player, octave, volume, NotePitch.getPitch(note), key_instrument, sharp, octave, tone);
	        	        	
	    	        	}
	    	        	onPlayMidiInstrument(player, patch, volume, NotePitch.getPitch(note), key_instrument, sharp, octave, tone);
    	        	}
    	        }
    		}else if (event.getCommand() == ShortMessage.PROGRAM_CHANGE) {				
    			channelPatches.put(event.getChannel(), (byte) event.getData1());
    		}else if (event.getCommand() == ShortMessage.STOP) {
    			stop();
    			//playNextSong();
    		}
        }
    }
	
	/*
	@Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage) {
        	if (!(message instanceof ShortMessage))
    			return;
    		ShortMessage event = (ShortMessage) message;
    		if (event.getCommand() == ShortMessage.NOTE_ON) {		
    			int key_instrument = event.getData1();
                int octave = (key_instrument / 12) - 1;
                int note = key_instrument % 12;
    			int channel = event.getChannel();
    			byte patch = 0;
    			float volume = event.getData2() / 63.5F;
    			
    			
    			
    			
                try {
                	//float sleep = event.getData2() / 31.75F;
					Thread.sleep((long) volume);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //System.out.println("Note on, " + noteName + octave + " key=" + key_instrument + " velocity: " + velocity);
    			
    			
    	        String name = NOTE_NAMES[note];
    	        octave = octave < 0 ? 0 : 1;
    	        boolean sharp = name.contains("#");
    	        Note.Tone tone = Note.Tone.valueOf(name.replaceAll("#", ""));
    	        if (channelPatches.containsKey(channel))
    				patch = channelPatches.get(channel);
    	        
    	        
    	        if(player != null) {
    	        	if (InInformation) {
    	        		int octave1 = (key_instrument % 12) - 1;
        	            int note1 = Math.abs(octave1 % 12);
        	        	player.sendMessage("Information >> " + "Octave > " +NOTE_NAMES[note1] + octave1 + " <> Instrument > "+ "code: " + patch + " = " + volue_instrument.get(patch) + " <> Drum > " + "code: " + channel + " = " + volue_drum.get(channel)+" <> Volume > " + volume+" <> Tone > " + timeStamp);
    	        	}
    	        	//player.playNote(player.getLocation(), (byte) note, (byte) octave);
    	        	//onPlayMidiDrum(player, channel, volume, NotePitch.getPitch(note), key_instrument, sharp, octave, tone);
    	        	onPlayMidiInstrument(player, patch, volume, NotePitch.getPitch(note), key_instrument, sharp, octave, tone);
	        	}
    	        //for (Player player : playerInMidiPlayer) {
    	        	//if(player != null) {
    	        	//	onPlayMidi(patch, channel, player, sharp, octave, tone);
    	            //    //player.playNote(player.getLocation(), Instrument.PIANO, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    	        	//}
    	        //}
    		}else if (event.getCommand() == ShortMessage.PROGRAM_CHANGE) {				
    			channelPatches.put(event.getChannel(), (byte) event.getData1());
    		}else if (event.getCommand() == ShortMessage.STOP) {
    			stop();
    			//playNextSong();
    		}
        }
    }
	*/

    @Override
    public void close() {}
    
    
    public void onPlayMidiInstrument(Player player, int patch, float volume, float notePitch, int key_instrument, boolean sharp, int octave, Note.Tone tone) {
        if (volue_instrument.get(patch) != null) {
        	
        	if (volue_instrument.get(patch).equals("BASS_DRUM")) {
				player.playNote(player.getLocation(), Instrument.BASS_DRUM, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("BASS_GUITAR")) {
				player.playNote(player.getLocation(), Instrument.BASS_GUITAR, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("BELL")) {
				player.playNote(player.getLocation(), Instrument.BELL, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("CHIME")) {
				player.playNote(player.getLocation(), Instrument.CHIME, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("FLUTE")) {
				player.playNote(player.getLocation(), Instrument.FLUTE, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("GUITAR")) {
				player.playNote(player.getLocation(), Instrument.GUITAR, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("PIANO")) {
				player.playNote(player.getLocation(), Instrument.PIANO, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("SNARE_DRUM")) {
				player.playNote(player.getLocation(), Instrument.SNARE_DRUM, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("STICKS")) {
				player.playNote(player.getLocation(), Instrument.STICKS, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
			if (volue_instrument.get(patch).equals("XYLOPHONE")) {
				player.playNote(player.getLocation(), Instrument.XYLOPHONE, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
				return;
			}
    		
    		if (!volue_instrument.get(patch).equals("NULL")) {
    			if (!volue_instrument.get(patch).equals("BASS_DRUM") || !volue_instrument.get(patch).equals("BASS_GUITAR") || !volue_instrument.get(patch).equals("BELL") || !volue_instrument.get(patch).equals("CHIME") || !volue_instrument.get(patch).equals("FLUTE") || !volue_instrument.get(patch).equals("GUITAR") || !volue_instrument.get(patch).equals("PIANO") || !volue_instrument.get(patch).equals("SNARE_DRUM") || !volue_instrument.get(patch).equals("STICKS") || !volue_instrument.get(patch).equals("XYLOPHONE")) {
        			
    				if (volue_instrument_note.get(patch).startsWith("-")){
    	            	String volue = volue_instrument_note.get(patch).replace("-", "");
    	            	player.playSound(player.getLocation(), volue_instrument.get(patch), volume - Float.parseFloat(volue), notePitch);
    	    			return;
    				}
        			if (volue_instrument_note.get(patch).startsWith("+")){
    	            	String volue = volue_instrument_note.get(patch).replace("+", "");
    	            	player.playSound(player.getLocation(), volue_instrument.get(patch), volume + Float.parseFloat(volue), notePitch);
    	    			return;
    	            }
    	            
        			//player.playSound(player.getLocation(), volue_instrument.get(patch), volume, notePitch);
	    			
        			return;
        		}
    		}
    		
        }else {
        	player.sendMessage(ChatColor.AQUA + "Size file: " + ChatColor.YELLOW + volue_instrument.size());
			player.sendMessage(ChatColor.AQUA + "Faled file: " + ChatColor.YELLOW + instrument_file.getName());
			stop();
        }
	}
    
    public void onPlayMidiDrum(Player player, int OCTIVE, float volume, float notePitch, int key_instrument, boolean sharp, int octave, Note.Tone tone) {
        if (volue_drum.get(OCTIVE) != null) {
        	if (volue_drum.get(OCTIVE).equals("BASS_DRUM")) {
    			
    			player.playNote(player.getLocation(), Instrument.BASS_DRUM, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("BASS_GUITAR")) {
    			
    			player.playNote(player.getLocation(), Instrument.BASS_GUITAR, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("BELL")) {
    			
    			player.playNote(player.getLocation(), Instrument.BELL, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("CHIME")) {
    			
    			player.playNote(player.getLocation(), Instrument.CHIME, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("FLUTE")) {
    			
    			player.playNote(player.getLocation(), Instrument.FLUTE, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("GUITAR")) {
    			
    			player.playNote(player.getLocation(), Instrument.GUITAR, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("PIANO")) {
    			
    			player.playNote(player.getLocation(), Instrument.PIANO, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("SNARE_DRUM")) {
    			
    			player.playNote(player.getLocation(), Instrument.SNARE_DRUM, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("STICKS")) {
    			
    			player.playNote(player.getLocation(), Instrument.STICKS, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		if (volue_drum.get(OCTIVE).equals("XYLOPHONE")) {
    			
    			player.playNote(player.getLocation(), Instrument.XYLOPHONE, sharp ? Note.sharp(octave, tone) : Note.natural(octave, tone));
    			return;
    		}
    		
    		if (!volue_drum.get(OCTIVE).equals("NULL")) {
    			if (!volue_drum.get(OCTIVE).equals("BASS_DRUM") || !volue_drum.get(OCTIVE).equals("BASS_GUITAR") || !volue_drum.get(OCTIVE).equals("BELL") ||!volue_drum.get(OCTIVE).equals("CHIME") || !volue_drum.get(OCTIVE).equals("FLUTE") || !volue_drum.get(OCTIVE).equals("GUITAR") || !volue_drum.get(OCTIVE).equals("PIANO") || !volue_drum.get(OCTIVE).equals("SNARE_DRUM") || !volue_drum.get(OCTIVE).equals("STICKS") || !volue_drum.get(OCTIVE).equals("XYLOPHONE")) {
        			if (volue_drum_note.get(OCTIVE).startsWith("-")){
    	            	String volue = volue_drum_note.get(OCTIVE).replace("-", "");
    	            	player.playSound(player.getLocation(), volue_drum.get(OCTIVE), volume - Float.parseFloat(volue), notePitch);
    	    			return;
    				}
        			if (volue_drum_note.get(OCTIVE).startsWith("+")){
    	            	String volue = volue_drum_note.get(OCTIVE).replace("+", "");
    	            	player.playSound(player.getLocation(), volue_drum.get(OCTIVE), volume + Float.parseFloat(volue), notePitch);
    	    			return;
    	            }
        			return;
        		}
    		}
    		
        }
	}
}
