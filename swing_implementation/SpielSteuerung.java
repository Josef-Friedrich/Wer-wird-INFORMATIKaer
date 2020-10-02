package swing_implementation;

import spiel_logik.Spiel;

public class SpielSteuerung {
  private static Spiel spiel;

  private static String pfadXmlDatei;

  /**
   * Gib ein neues oder ein bereits geladenes Spiel zurück.
   *
   * @param dateiPfad Eine Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   *
   * @return Die Instanz eines neues Spiels oder die Instanz eines bereits geladenen Spiels.
   */
  public static Spiel lade(String dateiPfad) {
    if (pfadXmlDatei != null && pfadXmlDatei.equals(dateiPfad) && spiel != null)
      return spiel;

    pfadXmlDatei = dateiPfad;
    spiel = new Spiel("/FRAGEN/test/15_fragen.xml");
    return spiel;
  }

  public static Spiel gib() {
    return spiel;
  }
}
