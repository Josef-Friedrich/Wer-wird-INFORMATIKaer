package swing_implementation;

import spiel_logik.Spiel;

public class SpielSteuerung {

  /**
   * Eine Instanz der Klasse Spiel, das das aktuelle Spiel darstellt.
   */
  private static Spiel spiel;

  /**
   * Starte ein neues Spiel.
   *
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   *
   * @return Die Instanz eines neues Spiels.
   */
  public static Spiel starteNeuesSpiel(String dateiPfad) {
    spiel = new Spiel(dateiPfad);
    return spiel;
  }

  public static Spiel gib() {
    return spiel;
  }

  public static MusikSpieler musikSpieler = new MusikSpieler();
}
