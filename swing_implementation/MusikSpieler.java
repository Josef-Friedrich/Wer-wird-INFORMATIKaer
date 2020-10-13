package swing_implementation;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusikSpieler {
  public MusikSpieler() {
    Clip clip;
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File("MUSIK/Hauptthema.wav")));
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

}
