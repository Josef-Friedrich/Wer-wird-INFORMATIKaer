package swing_implementation;

import java.awt.Font;

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

  public static final int SCHRIFT_GRÖSSE = 20;
  public static final String SCHRIFT_ART = "Sans";

  public static final Font SCHRIFT = new Font(SCHRIFT_ART, Font.PLAIN, SCHRIFT_GRÖSSE);

  public static final Color FARBE = new Color(255, 255, 255);
  public static final Color FARBE_RICHTIG = new Color(0, 255, 0);
  public static final Color FARBE_FALSCH = new Color(255, 0, 0);

  /**
   * Die gleiche Farbe wie die Hintergrundfarbe im Logo der Kasten um „INFORMATIK“
   * (3d1e57).
   */
  public static final Color FARBE_VIOLETT = new Color(61, 30, 87);

  public static final Color FARBE_HINTERGRUND = new Color(152, 152, 255);

  public static JLabel erzeugeÜberschrift(String text) {
    JLabel ueberschrift = new JLabel(text);
    ueberschrift.setFont(SCHRIFT);
    return ueberschrift;
  }

}
