package swing_implementation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import spiel_logik.Konfiguration;

class MusikAusschnitt {

  private Clip musikStueck;

  public MusikAusschnitt(String dateiName) {
    try {
      musikStueck = AudioSystem.getClip();
      musikStueck.open(AudioSystem.getAudioInputStream(new File("MUSIK/" + dateiName)));
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private void starte(boolean endlos) {
    if (Konfiguration.spieleMusik) {
      musikStueck.setMicrosecondPosition(0);
      musikStueck.start();
      if (endlos)
        musikStueck.loop(Clip.LOOP_CONTINUOUSLY);
    }
  }

  public void starte() {
    starte(false);
  }

  public void starteEndlos() {
    starte(true);
  }

  public void stoppe() {
    musikStueck.stop();
  }

}

public class MusikSpieler {

  private HashMap<String, MusikAusschnitt> ausschnitte;

  private String aktuelles;

  public MusikSpieler() {
    ausschnitte = new HashMap<String, MusikAusschnitt>();
    initalisiereAusschnitt("haupt", "Hauptthema.wav");
    initalisiereAusschnitt("fragen", "Fragenthema.wav");
    initalisiereAusschnitt("falsch", "falsch.wav");
    initalisiereAusschnitt("richtig", "richtig.wav");
  }

  private void initalisiereAusschnitt(String kürzel, String dateiName) {
    ausschnitte.put(kürzel, new MusikAusschnitt(dateiName));
  }

  public void starte(String kürzel) {
    if (aktuelles != null)
      ausschnitte.get(aktuelles).stoppe();
    aktuelles = kürzel;
    ausschnitte.get(kürzel).starte();
  }

  public void starteEndlos(String kürzel) {
    if (aktuelles != null)
      ausschnitte.get(aktuelles).stoppe();
    aktuelles = kürzel;
    ausschnitte.get(kürzel).starteEndlos();
  }

  public void stoppe(String kürzel) {
    ausschnitte.get(kürzel).stoppe();
    aktuelles = null;
  }

}
