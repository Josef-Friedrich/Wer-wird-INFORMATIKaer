package fragen_verwaltung;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import spiel_logik.Spiel;

/**
 * Die Fragen für ein Themengebiet werden in einer XML-Datei festgehalten.
 *
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 * <themenGebiet>
 *     <fach>Informatik</fach>
 *     <thema>6. Jahrgangsstufe</thema>
 *     <autor>Michi</autor>
 *     <anzahlFragen>10</anzahlFragen>
 *     <fragen>
 *         <frage>
 *             <fragenText>Wie nennt die Beziehung, wenn zwei Objekte der gleichen Klasse in Beziehung stehen?</fragenText>
 *             <richtigeAntwort>rekursive Beziehung</richtigeAntwort>
 *             <falscheAntwort1>repetetive Beziehung</falscheAntwort1>
 *             <falscheAntwort2>reflexive Beziehung</falscheAntwort2>
 *             <falscheAntwort3>relationale Beziehung</falscheAntwort3>
 *             <schwierigkeit>2</schwierigkeit>
 *         </frage>
 *     </fragen>
 * </themenGebiet>
 * }
 * </pre>
 */
public class ThemenGebiet extends XMLDatei {

  /**
   * Erzeugt anhand eines relativen Pfades zu einer XML-Datei eine neue Instanze
   * der Klasse.
   *
   * @param dateiPfad Eine Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public ThemenGebiet(String dateiPfad) {
    super(dateiPfad);
  }

  /**
   * Gibt den Text des XML-Tags {@code <fach>} zurück.
   *
   * @return Der Text des XML-Tags {@code <fach>}.
   */
  public String gibFach() {
    return leseTextInhalt("fach");
  }

  /**
   * Gibt den Text des XML-Tags {@code <thema>} zurück.
   *
   * @return Der Text des XML-Tags {@code <thema>}.
   */
  public String gibThema() {
    return leseTextInhalt("thema");
  }

  /**
   * Gibt den Text des XML-Tags {@code <autor>} zurück.
   *
   * @return Der Text des XML-Tags {@code <autor>}.
   */
  public String gibAutor() {
    return leseTextInhalt("autor");
  }

  /**
   * Gibt die tatsächliche Anzahl der Fragen zurück.
   *
   * Diese Methode zählt die {@code<frage>}-Tags in der XML-Datei und gibt nicht
   * den Wert des Tags {@code<anzahlFragen>} zurück.
   *
   * @return Die Anzahl der Fragen.
   */
  public int gibAnzahlFragen() {
    return gibDokument().getElementsByTagName("frage").getLength();
  }

  /**
   * Liest die Fragen einer Jahrgangsstufe in die Klasse Spiel ein.
   *
   * @param spiel Eine Instanz der Klasse {@link spiel_logik.Spiel}
   */
  public void leseFragenInsSpiel(Spiel spiel) {
    NodeList knotenListe = gibDokument().getElementsByTagName("frage");
    for (int i = 0; i < knotenListe.getLength(); i++) {
      Node frage = knotenListe.item(i);

      String fragenText = gibTextVonKind(frage, "fragenText");
      String richtigeAntwort = gibTextVonKind(frage, "richtigeAntwort");
      String falscheAntwort1 = gibTextVonKind(frage, "falscheAntwort1");
      String falscheAntwort2 = gibTextVonKind(frage, "falscheAntwort2");
      String falscheAntwort3 = gibTextVonKind(frage, "falscheAntwort3");
      String schwierigkeit = gibTextVonKind(frage, "schwierigkeit");
      spiel.erzeugeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3, schwierigkeit);
    }
  }
}
