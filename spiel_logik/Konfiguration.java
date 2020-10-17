package spiel_logik;

enum ZahlenFormat {
  DEZIMAL, BINÄR, HEXALDEZIMAL;

  private static ZahlenFormat[] werte = values();

  public ZahlenFormat nächstes() {
    return werte[(this.ordinal() + 1) % werte.length];
  }
}

/**
 * Die Klasse {@link Konfiguration} enthält statische Attribute, die
 * Einstellungen speichern.
 */
public class Konfiguration {

  /**
   * Die Anzahl der Fragen, die ins Spiel geladen werden. Ist die Zahl höher, als
   * die XML-Datei Fragen beinhaltet, werden alle Fragen geladen. Die Zahl 0 lädt
   * auch alle Fragen.
   */
  public static int anzahlGeladenerFragen = 15;

  /**
   * Das Zahlenformat, in dem z. B. die Gewinnsumme angezeigt wird. Möglich ist
   * dezimal, binär, und hexadezimal.
   */
  public static ZahlenFormat zahlenFormat = ZahlenFormat.DEZIMAL;

  /**
   * Setze das nächste Zahlenformat. Es wird durch die Aufzählungsklasse
   * {@link ZahlenFormat} gegangen und das nächste Format gesetzt. Wenn das letzte
   * Element gesetzt wurde, wird wieder da erste Element gesetzt.
   *
   * @return Das neue gesetzte nächste Zahlenformat.
   */
  public static ZahlenFormat setzeNächstesZahlenformat() {
    return zahlenFormat = zahlenFormat.nächstes();
  }
}
