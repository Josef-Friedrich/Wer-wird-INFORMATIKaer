package fragen_verwaltung;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Gibt einen Überblick über alle verfügbaren Themenbereiche und Themengebiete.
 * Diese Klasse liest die Datei <code>/FRAGEN/index.xml</code>
 * ein.
 *
 * Zu den Begriffen: Das Spiel enthält nur einen Themenkatalog. Der
 * Themenkatalog enthält mehrere Themenbereiche (vergleichbar mit
 * Unterrichtsfächern). Ein Themenbereich ist wiederrum in mehrere Themengebiete
 * (vergleichbar mit einzelnen Themen aus dem Lehrplan oder Jahrgangsstufen)
 * unterteilt.
 *
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 * <themenKatalog>
 *     <themenBereich>
 *         <name>Allgemeine Wissensfragen</name>
 *         <gebiete>
 *             <themenGebiet pfad="allgemein/allg02.xml" titel=""/>
 *         </gebiete>
 *     </themenBereich>
 * </themenKatalog>
 * }
 * </pre>
 */
public class ThemenKatalog extends XMLDatei {

  /**
   * Eine Liste mit Knoten der XML-Tags {@code <themenBereich>}.
   */
  private NodeList bereiche;

  /**
   * Eine Liste mit Knoten der XML-Tags {@code <themenGebiet>}.
   */
  private NodeList gebiete;

  public ThemenKatalog() {
    super("/FRAGEN/index.xml");
    bereiche = gibDokument().getElementsByTagName("themenBereich");
    gebiete = gibDokument().getElementsByTagName("themenGebiet");
  }

  /**
   * Gib den Text der XML-Attribute des gegeben XML-Knoten.
   *
   * @param knoten       Der XML-Knoten des Themengebiets
   *                     {@code <themenGebiet pfad="" titel=""/>}.
   * @param attributName Der Name des XML-Attributs.
   *
   * @return Der Text des XML-Attributs.
   */
  private String gibTextAttributVonKnoten(Node knoten, String attributName) {
    NamedNodeMap attributes = knoten.getAttributes();
    Node pfad = attributes.getNamedItem(attributName);
    return pfad.getTextContent();
  }

  /**
   * Gib den Pfad des Themengebiets.
   *
   * @param knoten Der XML-Knoten des Themengebiets
   *               {@code <themenGebiet pfad="" titel=""/>}.
   *
   * @return Der Text des Pfades.
   */
  private String gibPfadVonKnoten(Node knoten) {
    return gibTextAttributVonKnoten(knoten, "pfad");
  }

  /**
   * Gib den Titel des Themengebiets.
   *
   * @param knoten Der XML-Knoten des Themengebiets
   *               {@code <themenGebiet pfad="" titel=""/>}.
   *
   * @return Der Text des Titels.
   */
  private String gibTitelVonKnoten(Node knoten) {
    return gibTextAttributVonKnoten(knoten, "titel");
  }

  /**
   * Gibt die Anzahl der Themenbereiche zurück.
   *
   * @return Die Anzahl der Themenbereiche.
   */
  public int gibAnzahlBereiche() {
    return bereiche.getLength();
  }

  /**
   * Gibt die Anzahl der Themengebiete zurück.
   *
   * @return Die Anzahl der Themengebiete.
   */
  public int gibAnzahlGebiete() {
    return gebiete.getLength();
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
    Node knoten = gebiete.item(nummer);
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
    Node knoten = gebiete.item(nummer);
    return gibPfadVonKnoten(knoten);
  }

  /**
   * @param nummer Die Nummer in der Themenliste beginnend mit 0.
   *
   * @return Das ausgewählte Themengebiet.
   */
  public ThemenGebiet gibGebietDurchNummer(int nummer) {
    Node knoten = gebiete.item(nummer);
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/" + gibPfadVonKnoten(knoten));
    return gebiet;
  }

}
