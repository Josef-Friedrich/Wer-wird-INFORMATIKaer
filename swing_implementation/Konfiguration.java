package swing_implementation;

enum ZahlenFormat {
  DEZIMAL, BINÄR, HEXALDEZIMAL;

  private static ZahlenFormat[] werte = values();

  public ZahlenFormat nächstes() {
    return werte[(this.ordinal() + 1) % werte.length];
  }
}

/**
 * Die Klasse Konfiguration enthält statische Attribute, die Einstellungen
 * speichern.
 */
class Konfiguration {

  public static ZahlenFormat ZAHLEN_FORMAT = ZahlenFormat.DEZIMAL;

  public static ZahlenFormat setzeNächstesZahlenformat() {
    return ZAHLEN_FORMAT = ZAHLEN_FORMAT.nächstes();
  }
}
