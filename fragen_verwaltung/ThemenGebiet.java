package fragen_verwaltung;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
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
   * Liest alle Fragen eines Themengebiets in die Klasse Spiel ein.
   *
   * @param spiel Eine Instanz der Klasse {@link spiel_logik.Spiel}
   */
  public void leseFragenInsSpiel(Spiel spiel) {
    leseFragenInsSpiel(spiel, 0);
  }

  /**
   * Wähle eine bestimmte Anzahl an Fragen oder alle Frage aus der XML-Datei aus.
   * Bringe die Fragen in eine zufällige Reihenfolge.
   *
   * @param knotenListe   Die Knotenliste die alle {@code <frage>}-Tags enthält.
   * @param anzahlAuswahl Die Anzahl an Fragen, die ausgewählt werden soll.
   *
   * @return Ein Feld mit {@code <frage>}-Knoten.
   */
  private Node[] wähleFragenAus(NodeList knotenListe, int anzahlAuswahl) {
    Random zufall = new Random();
    int maxAnzahlFragen = knotenListe.getLength();

    if (anzahlAuswahl > maxAnzahlFragen || anzahlAuswahl <= 0)
      anzahlAuswahl = maxAnzahlFragen;

    // https://stackoverflow.com/a/22829036/10193818
    // Wir bilden eine Liste mit den Indexnummer aller Fragen. Hat die
    // Themengebiets-XML-Datei beispielsweise 10 Fragen, so entsteht die Liste:
    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    List<Integer> indexAlleFragen = IntStream.rangeClosed(0, maxAnzahlFragen - 1).boxed().collect(Collectors.toList());

    Node[] fragen = new Node[anzahlAuswahl];

    // https://www.baeldung.com/java-random-list-element
    // Aus der oben erzeugten Liste mit den Indexnummer entnehmen wir an einer
    // zufälligen Stelle eine Zahl (zufälligerIndex).
    // Mit diesem zufälligen Index entnehmen wir den XML-Konten aus der Kontenliste.
    for (int i = 0; i < anzahlAuswahl; i++) {
      int j = zufall.nextInt(indexAlleFragen.size());
      int zufälligerIndex = indexAlleFragen.get(j);
      indexAlleFragen.remove(j);
      fragen[i] = knotenListe.item(zufälligerIndex);
    }
    return fragen;
  }

  /**
   * Liest eine bestimmte Anzahl an Fragen, die zufällig ausgewählt werden, ins
   * die Klasse Spiel ein.
   *
   * @param spiel  Eine Instanz der Klasse {@link spiel_logik.Spiel}
   * @param anzahl Die Anzahl an Fragen, die ins Spiel geladen werden sollen.
   */
  public void leseFragenInsSpiel(Spiel spiel, int anzahl) {
    NodeList knotenListe = gibDokument().getElementsByTagName("frage");
    Node[] fragen = wähleFragenAus(knotenListe, anzahl);
    for (int i = 0; i < fragen.length; i++) {
      Node frage = fragen[i];

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
