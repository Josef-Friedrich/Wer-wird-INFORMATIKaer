package spiel_logik;

/**
 * Diese Klasse bildet den Abschluss der einfach verkettenen Liste. Beinhaltet
 * die Frageliste noch keine Frage, bietet diese Klasse dieselben Methoden wie
 * eine gefüllte Liste mit Datenknoten.
 */
public class ListenAbschluss extends ListenElement {
  public ListenElement gibNächstes() {
    return null;
  }

  /**
   * Gibt keine Frage zurück, denn die Liste ist leer.
   *
   * @return Gibt null zurück, denn die Liste ist leer.
   */
  public Frage gibFrage() {
    return null;
  }

  /**
   * Füge eine Frage ans Ende der Liste.
   *
   * @return Die Instanz des Datenknoten.
   */
  public DatenKnoten fügeHintenEin(Frage frage) {
    return new DatenKnoten(this, frage);
  }

  /**
   * Eine Methode ohne Funktion. Der Listenabschluss hat kein nächstes
   * Datenelement. Er ist das letzte Element in der Liste. Die Methode muss jedoch
   * implementiert werden, da die Klasse {@link ListenElement} sie als abstrakt
   * deklariert hat.
   */
  public void setzeNächstes(ListenElement nächstes) {
  }

  /**
   * Gibt 0 zurück, den wenn eine Instanz dieser Klasse das erste Element in der
   * Liste ist, dann ist diese Liste leer.
   */
  public int gibAnzahlDatenKnoten() {
    return 0;
  }

}
