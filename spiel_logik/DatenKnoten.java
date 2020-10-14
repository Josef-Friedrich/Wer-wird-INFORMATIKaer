package spiel_logik;

/**
 * Die Klasse {@link DatenKnoten} ist eine Kontainerklasse, die eine Frage
 * einschließt.
 */
public class DatenKnoten extends ListenElement {

  /**
   * Eine Frage, die im Datenknoten gespeichert werden soll.
   */
  private Frage frage;

  /**
   * Dieser Konstruktor initialisiert einen Datenknoten mit einer Frage und dem
   * nächsten Listenelement.
   *
   * @param nächstes Das nächste Listenelement.
   * @param frage    Die Frage.
   */
  public DatenKnoten(ListenElement nächstes, Frage frage) {
    this.nächstes = nächstes;
    this.frage = frage;
  }

  /**
   * Setze das nächste Listenelement.
   *
   * @param nächstes Ein Listenelement.
   */
  public void setzeNächstes(ListenElement nächstes) {
    this.nächstes = nächstes;
  }

  /**
   * Setze die Frage.
   *
   * @param frage
   */
  public void setzeFrage(Frage frage) {
    this.frage = frage;
  }

  /**
   * Gib das nächste Listenelement.
   */
  public ListenElement gibNächstes() {
    return nächstes;
  }

  /**
   * Gib die Frage.
   */
  public Frage gibFrage() {
    return frage;
  }

  /**
   * Füge eine Frage ans Ende der Liste.
   *
   * @return Die Instanz des Datenknoten.
   */
  public DatenKnoten fügeHintenEin(Frage frage) {
    nächstes = nächstes.fügeHintenEin(frage);
    return this;
  }

  /**
   * Gib die Anzahl an Datenknoten.
   *
   * Die Anzahl wird rekursiv berechnet.
   *
   * @return Die Anzahl an Datenknoten.
   */
  public int gibAnzahlDatenKnoten() {
    return 1 + nächstes.gibAnzahlDatenKnoten();
  }
}
