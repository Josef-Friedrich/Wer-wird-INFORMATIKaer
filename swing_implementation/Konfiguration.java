package swing_implementation;

import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Color;

public class Konfiguration {

  public static final int SCHRIFT_GROESSE = 20;
  public static final String SCHRIFT_ART = "Sans";

  public static final Font SCHRIFT = new Font(SCHRIFT_ART, Font.PLAIN, SCHRIFT_GROESSE);

  public static final Color FARBE = new Color(255, 255, 255);
  public static final Color FARBE_RICHTIG = new Color(0, 255, 0);
  public static final Color FARBE_FALSCH = new Color(255, 0, 0);

  public static final Color FARBE_HINTERGRUND = new Color(152, 152, 255);

  public static JLabel erzeugeÜberschrift(String text) {
    JLabel ueberschrift = new JLabel(text);
    ueberschrift.setFont(SCHRIFT);
    return ueberschrift;
  }

}
