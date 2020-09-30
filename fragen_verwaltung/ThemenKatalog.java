package fragen_verwaltung;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Gibt einen Überblick über alle verfügbaren Themengebiete. Die Themengebiete
 * werden in der Datei <code>/FRAGEN/index.xml</code> konfiguriert.
 */
public class ThemenKatalog extends XMLDatei {

  /**
   * Eine Liste mit Knoten der XML-Tags {@code <themenGebiet>}.
   */
  NodeList knotenListe;

  public ThemenKatalog() {
    super("/FRAGEN/index.xml");
    knotenListe = dokument.getElementsByTagName("themenGebiet");
  }

  private String gibTextAttributVonKnoten(Node knoten, String attributName) {
    NamedNodeMap attributes = knoten.getAttributes();
    Node pfad = attributes.getNamedItem(attributName);
    return pfad.getTextContent();
  }

  private String gibPfadVonKnoten(Node knoten) {
    return gibTextAttributVonKnoten(knoten, "pfad");
  }

  private String gibTitelVonKnoten(Node knoten) {
    return gibTextAttributVonKnoten(knoten, "titel");
  }

  /**
   * Gibt die Anzahl der Themengebiete zurück.
   *
   * @return Die Anzahl der Themengebiete.
   */
  public int gibAnzahlThemenGebiete() {
    return knotenListe.getLength();
  }

  /**
   * Gib den Titel des Themengebiets durch eine Indexnummer.
   *
   * @param nummer Die Indexnummer des Themengebiets in dem Themenkatalog
   *               beginnend mit 0.
   *
   * @return Der Titel des Themengebiets.
   */
  public String gibGebietTitelDurchNummer(int nummer) {
    Node knoten = knotenListe.item(nummer);
    return gibTitelVonKnoten(knoten);
  }

  /**
   * Gib den relative Pfad zur XML-Datei des des Themengebiets durch eine
   * Indexnummer.
   *
   * @param nummer Die Indexnummer des Themengebiets in dem Themenkatalog
   *               beginnend mit 0.
   *
   * @return Der relative Pfad zur XML-Datei des Themengebiets.
   */
  public String gibGebietPfadDurchNummer(int nummer) {
    Node knoten = knotenListe.item(nummer);
    return gibPfadVonKnoten(knoten);
  }

  /**
   * @param nummer Die Nummer in der Themenliste beginnend mit 0.
   *
   * @return Das ausgewählte Themengebiet.
   */
  public ThemenGebiet gibGebietDurchNummer(int nummer) {
    Node knoten = knotenListe.item(nummer);
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/" + gibPfadVonKnoten(knoten));
    return gebiet;
  }

}
