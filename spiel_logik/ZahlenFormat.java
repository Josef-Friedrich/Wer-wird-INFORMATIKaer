package spiel_logik;

/**
 * Im Spiel können mehrere Zahlenformate beispielsweise für die Gewinnsumme
 * angezeigt werden.
 *
 * Es werden folgende Zahlenformate unterstützt: dezimal, binär, hexadezimal.
 */
public enum ZahlenFormat {
  DEZIMAL, BINÄR, HEXALDEZIMAL;

  private static ZahlenFormat[] werte = values();

  /**
   * Gib das nächste Zahlenformat.
   *
   * Mit Hilfe dieser Methode kann von einem Zahlenformat zu nächsten geschaltet
   * werden. Die Methode wird eingesetzt für ein Tastenkürzel in der
   * Swing-Implementation, so kann beispielsweise mit der Taste „z“ zwischen den
   * Zahlenformaten umgeschaltete werden.
   *
   * @return Das nächste Zahlenformat.
   */
  public ZahlenFormat nächstes() {
    return werte[(this.ordinal() + 1) % werte.length];
  }
}
