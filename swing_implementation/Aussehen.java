package swing_implementation;

import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;

/**
 * Eine statische Klasse, in der an einer zentralen Stelle im Paket, die
 * Einstellungen hinterlegt sind, die das Aussehen des Spiels (z. B. Farben,
 * Schriftarten) steuert.
 *
 * Da es sich bei den statischen Attributen um Konstanten handelt, werden sie
 * groß und mit Unterstrichen geschrieben ({@code UPPER_SNAKE}).
 */
public class Aussehen {

  /**
   * Die minimale Fensterbreite in Pixel. Es wird der XGA-Standard (Extended
   * Graphics Array (XGA)) mit einer Auflösung von 1024 × 768 verwendet.
   */
  public static final int FENSTER_BREITE = 1024;

  /**
   * Die minimale Fensterhöhe in Pixel. Es wird der XGA-Standard (Extended
   * Graphics Array (XGA)) mit einer Auflösung von 1024 × 768 verwendet.
   */
  public static final int FENSTER_HÖHE = 768;

  /**
   * Die Breite der Antworttaste in Pixel.
   */
  public static final int ANTWORT_TASTE_BREITE = 400;

  /**
   * Die Höhe der Antworttaste in Pixel.
   */
  public static final int ANTWORT_TASTE_HÖHE = 75;

  /**
   * Die im Spiel verwendete Schriftgröße.
   */
  public static final int SCHRIFT_GRÖSSE = 20;

  /**
   * Die im Spiel verwendete Schriftart.
   */
  public static final String SCHRIFT_ART = "Sans";

  /**
   * Schrift in normaler Textauszeichnung.
   */
  public static final Font SCHRIFT_NORMAL = new Font(SCHRIFT_ART, Font.PLAIN, SCHRIFT_GRÖSSE);

  /**
   * Schrift in fetter Textauszeichnung.
   */
  public static final Font SCHRIFT_FETT = new Font(SCHRIFT_ART, Font.BOLD, SCHRIFT_GRÖSSE);

  public static final Color FARBE = new Color(255, 255, 255);

  /**
   * Ein zur Farbpalette des Spiels passendes Weiß.
   */
  public static final Color FARBE_WEISS = new Color(255, 255, 255);

  /**
   * Ein zur Farbpalette des Spiels passendes Rot.
   */
  public static final Color FARBE_ROT = new Color(204, 0, 0);

  /**
   * Ein zur Farbpalette des Spiels passendes Grün.
   */
  public static final Color FARBE_GRUEN = new Color(102, 153, 0);

  /**
   * Ein zur Farbpalette des Spiels passendes Blau.
   */
  public static final Color FARBE_BLAU = new Color(0, 51, 153);

  /**
   * Die gleiche Farbe wie die Hintergrundfarbe im Logo der Kasten um „INFORMATIK“
   * (3d1e57).
   */
  public static final Color FARBE_VIOLETT = new Color(61, 30, 87);

  /**
   * Ein zur Farbpalette des Spiels passendes Orange.
   */
  public static final Color FARBE_ORANGE = new Color(255, 156, 0);

  /**
   * Ein zur Farbpalette des Spiels passendes helles Violett.
   */
  public static final Color FARBE_VIOLETT_HELL = new Color(152, 152, 255);

  /**
   * Die Farbe die für die Hintergründe verwendet werden soll.
   */
  public static final Color FARBE_HINTERGRUND = FARBE_BLAU;

  /**
   * Die Farbe für die richtigen Antworten.
   */
  public static final Color FARBE_RICHTIG = FARBE_GRUEN;

  /**
   * Die Farbe für die falschen Antworten.
   */
  public static final Color FARBE_FALSCH = FARBE_ROT;

  /**
   * Berechnet die „x“ Koordinate für ein Objekt, das zentiert werden soll.
   *
   * @param breite Die Breite des Objekts in Pixel.
   *
   * @return Die „x“ Koordinate, die verwendet werden kann, damit das Objekt auf
   *         der horizontalen Achse zentriert erscheint.
   */
  public static int zentriereX(int breite) {
    return (FENSTER_BREITE - breite) / 2;
  }

  /**
   * Erzeuge einen normalen Text.
   *
   * @param text Ein Text, der in eine JLabel eingebettet werden soll.
   *
   * @return Ein neues JLabel-Objekt.
   */
  public static JLabel macheText(String text) {
    JLabel textElement = new JLabel(text);
    textElement.setFont(SCHRIFT_NORMAL);
    textElement.setForeground(FARBE_WEISS);
    return textElement;
  }

  /**
   * Erzeuge einen leeren normalen Text.
   *
   * @return Ein neues JLabel-Objekt.
   */
  public static JLabel macheText() {
    return macheText("");
  }

  /**
   * Erzeuge einen leeren Text, der im Spielfeld an den angegeben Bereich plaziert werden kann.
   *
   * @return Ein neues JLabel-Objekt.
   */
  public static JLabel macheText(int x, int y, int breite, int höhe) {
    JLabel text = macheText("");
    text.setBounds(x, y, breite, höhe);
    return text;
  }

  /**
   * Erzeuge eine Überschrift.
   *
   * @param text Ein Text, der in eine JLabel eingebettet werden soll.
   *
   * @return Ein neues JLabel-Objekt.
   */
  public static JLabel macheÜberschrift(String text) {
    JLabel ueberschrift = new JLabel(text);
    ueberschrift.setFont(SCHRIFT_FETT);
    ueberschrift.setForeground(FARBE_WEISS);
    return ueberschrift;
  }

  /**
   * Mache ein Bild der Klasse {@link javax.swing.ImageIcon}.
   *
   * Die kleine Hilfsfunktion übernimmt die Fehlerbehandlung sowie das Laden der
   * Bild-Datei.
   *
   * @param pfad Der Pfad relative zum „BILDER“ Ordner.
   *
   * @return Ein Bild der Klasse {@link javax.swing.ImageIcon}
   */
  public static ImageIcon macheBild(String pfad) {
    ImageIcon bild = null;
    try {
      bild = new ImageIcon(ImageIO.read(Aussehen.class.getResource("/BILDER/" + pfad)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bild;
  }

}
