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

  public void setzeNächstes(ListenElement nächstes) {
    return;
  }

  public int gibAnzahlDatenKnoten() {
    return 0;
  }

}
