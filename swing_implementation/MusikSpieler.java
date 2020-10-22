package swing_implementation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import spiel_logik.Konfiguration;

/**
 * Die Klasse {@code MusikAusschnitt} verwaltet eine WAVE-Datei und kann diese
 * einmal abspielen, endlos abspielen oder stoppen.
 */
class MusikAusschnitt {

  private Clip musikStueck;

  /**
   * Der Konstruktor lädt die WAVE-Datei und fägt dabei einige Ausnahmen ab.
   *
   * @param dateiName Der Dateiname der WAVE-Datei im Verzeichnis „MUSIK“.
   */
  public MusikAusschnitt(String dateiName) {
    try {
      musikStueck = AudioSystem.getClip();
      musikStueck.open(AudioSystem.getAudioInputStream(new File("MUSIK/" + dateiName)));
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Starte den Musikausschnitt entweder einaml oder in einer Endlosschleife.
   *
   * @param endlos Wenn wahr, dann spiele den Musikausschnitt in einer
   *               Endlosschleife.
   */
  private void starte(boolean endlos) {
    if (Konfiguration.spieleMusik) {
      musikStueck.setMicrosecondPosition(0);
      musikStueck.start();
      if (endlos)
        musikStueck.loop(Clip.LOOP_CONTINUOUSLY);
    }
  }

  /**
   * Starte den Musikausschnitt und spiele ihn nur einmal.
   */
  public void starte() {
    starte(false);
  }

  /**
   * Starte den Musikausschnitt und spiele ihn in einer Endlosschleife.
   */
  public void starteEndlos() {
    starte(true);
  }

  /**
   * Stoppe den Musikausschnitt.
   */
  public void stoppe() {
    musikStueck.stop();
  }

}

/**
 * Die Klasse {@link MusikSpieler} verwaltete alle im Spiel benötigten
 * Musikausschnitte.
 */
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

  /**
   * Initialisiere den Musikausschnitt.
   *
   * @param kürzel    Ein Kürzel für einen Musikausschnitt.
   * @param dateiName Der Dateiname der WAVE-Datei im Verzeichnis „MUSIK“.
   */
  private void initalisiereAusschnitt(String kürzel, String dateiName) {
    ausschnitte.put(kürzel, new MusikAusschnitt(dateiName));
  }

  /**
   * Starte den mit einem Kürzel angegebenen Musikausschnitt, spiele den
   * Musikausschnitt nur einmal und stoppe zuerst den Musikausschnitt, der gerade
   * abgespielt wird.
   *
   * @param kürzel Das Kürzel für den Musikausschnitt.
   */
  public void starte(String kürzel) {
    if (aktuelles != null)
      ausschnitte.get(aktuelles).stoppe();
    aktuelles = kürzel;
    ausschnitte.get(kürzel).starte();
  }

  /**
   * Starte den mit einem Kürzel angegebenen Musikausschnitt, spiele den
   * Musikausschnitt in einer Endlosschleife und stoppe zuerst den
   * Musikausschnitt, der gerade abgespielt wird.
   *
   * @param kürzel Das Kürzel für den Musikausschnitt.
   */
  public void starteEndlos(String kürzel) {
    if (aktuelles != null)
      ausschnitte.get(aktuelles).stoppe();
    aktuelles = kürzel;
    ausschnitte.get(kürzel).starteEndlos();
  }

  /**
   * Initialisiere den Musikausschnitt.
   *
   * @param kürzel Das Kürzel für den Musikausschnitt.
   */
  public void stoppe(String kürzel) {
    ausschnitte.get(kürzel).stoppe();
    aktuelles = null;
  }

}
