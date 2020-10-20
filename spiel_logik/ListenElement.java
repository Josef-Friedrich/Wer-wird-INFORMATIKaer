package spiel_logik;

/**
 * Diese abstrakte Klasse wird von der Klasse {@link DatenKnoten} und der Klasse
 * {@link ListenAbschluss} geerbt.
 */
public abstract class ListenElement {

  /**
   * Das nächste Listenelement.
   */
  protected ListenElement nächstes;

  /**
   * Gib das nächste Listenelement.
   *
   * @return Das nächste Listenelement.
   */
  public ListenElement gibNächstes() {
    return nächstes;
  }

  /**
   * Gib die Frage.
   *
   * @return Die Frage des Listenelements.
   */
  public abstract Frage gibFrage();

  /**
   * Füge eine Frage ans Ende der Liste.
   *
   * @param frage Eine Frage.
   *
   * @return Die Instanz des Datenknoten.
   */
  public abstract DatenKnoten fügeHintenEin(Frage frage);

  /**
   * Setze das nächste Listenelement.
   *
   * @param nächstes Ein Listenelement.
   */
  public abstract void setzeNächstes(ListenElement nächstes);

  /**
   * Gib die Anzahl an Datenknoten.
   *
   * Die Anzahl wird rekursiv berechnet.
   *
   * @return Die Anzahl an Datenknoten.
   */
  public abstract int gibAnzahlDatenKnoten();
}
