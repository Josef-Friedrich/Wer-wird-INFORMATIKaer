package spiel_logik;

import java.lang.reflect.Field;

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
   * Spiele nach dem K.o.-System (knockout), d. h. bei einer falschen Frage ist
   * das Spiel verloren. Ist dieses Attribut auf falsch gesetzt, können alle
   * geladenen Fragen durchgespielt werden.
   */
  public static boolean ko = true;

  /**
   * Die Anzahl der Fragen, die ins Spiel geladen werden. Ist die Zahl höher, als
   * die XML-Datei Fragen beinhaltet, werden alle Fragen geladen. Die Zahl 0 lädt
   * auch alle Fragen.
   */
  public static int anzahlGeladenerFragen = 15;

  /**
   * Zeige die Fragen nach Schwierigkeit geordnet, d. h. zuerst die leichten
   * Fragen (Schwierigkeit = 1) und dann die schwereren (bis Schwierigkeit 5).
   */
  public static boolean nachSchwierigkeit = false;

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

  /**
   * Einstellung, ob Musik abgespielt werden soll oder nicht.
   */
  public static boolean spieleMusik = true;

  /**
   * Ob zur nächsten Frage automatisch weiter geschaltete werden soll.
   */
  public static boolean automatischWeiter = true;

  /**
   * Wie lange gewartet werden soll, bis automatisch zur nächsten Frage
   * umgeschaltet wird (in Sekunden).
   */
  public static float automatischWeiterDauer = 3f;

  /**
   * Gib einen Konfigurationswert.
   *
   * @param name Der Name des Konfigurationswerts. Er sollte dem Attribute
   *             entsprechen.
   */
  public static Object gib(String name) {
    Object wert = null;
    try {
      Field feld = Konfiguration.class.getDeclaredField(name);
      wert = feld.get(null);
    } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return wert;
  }

  /**
   * Setze einen Konfigurationswert.
   *
   * @param name Der Name des Konfigurationswerts. Er sollte dem Attribute
   *             entsprechen.
   * @param wert Ein Wert, des Type zum Attribut passen muss.
   */
  public static void setze(String name, Object wert) {
    try {
      Field feld = Konfiguration.class.getDeclaredField(name);
      feld.set(null, wert);
    } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
