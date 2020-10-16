package swing_implementation;

import spiel_logik.Spiel;

/**
 * Eine Klasse mit statischen Methoden, die eine Instanz des aktuellen Spiels
 * verwaltet.
 */
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

  /**
   * Wiederhole ein bereits gestartetes Spiel.
   *
   * @return Die Instanz des wiederholten Spiels.
   */
  public static Spiel wiederholeSpiel() {
    if (gibSpiel() != null) {
      spiel = new Spiel(spiel.gibDateiPfad());
    }
    return spiel;
  }

  /**
   * Gib das aktuelle Spiel.
   *
   * @return Eine Instanz des aktuellen Spiels
   */
  public static Spiel gibSpiel() {
    return spiel;
  }

  public static MusikSpieler musikSpieler = new MusikSpieler();
}
