package swing_implementation;

import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;

import spiel_logik.Frage;
import spiel_logik.Spiel;

public class AnsichtSpiel extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private Spiel spiel;

  private JLabel textFrage;

  private JLabel textThemenGebiet;
  private JLabel textGewinnSumme;
  private JLabel textFrageNummer;

  private AntwortKachel[] antwortKacheln;

  private Taste tasteNächsteFrage;

  /**
   * Nach drei Sekunden wird automatisch die nächste Frage angezeigt. Die Zeit
   * wird in Millisekunden angegeben. Als zweiter Parameter wird eigentlich eine
   * Instanz der Klasse {@link java.awt.event.ActionListener} verlangt. Wir
   * verwenden hier aber eine Referenz auf die Methode
   * {@link zeigeNächsteFrageEreignis}, weil sich das kompakter darstellen lässt.
   * Diese Methode ruft wiederum die Methode {@link zeigeNächsteFrage} auf.
   *
   * https://stackoverflow.com/a/39584264/10193818
   */
  private Timer zeitmesser;

  public AnsichtSpiel() {
    setLayout(null);

    zeitmesser = new Timer(3000, this::zeigeNächsteFrageEreignis);
    zeitmesser.setRepeats(false);

    textFrage = erzeugeFragenText();

    textThemenGebiet = Aussehen.macheText(20, 20, 500, 50);
    add(textThemenGebiet);

    textGewinnSumme = Aussehen.macheText(600, 20, 400, 50);
    add(textGewinnSumme);

    textFrageNummer = Aussehen.macheText(20, Aussehen.FENSTER_HÖHE - 40, 500, 30);
    add(textFrageNummer);

    int x1 = 10;
    int x2 = 520;
    int y1 = 450;
    int y2 = 550;

    antwortKacheln = new AntwortKachel[] { erzeugeAntwortKachel(x1, y1), erzeugeAntwortKachel(x2, y1),
        erzeugeAntwortKachel(x1, y2), erzeugeAntwortKachel(x2, y2) };

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortKacheln[antwortNummer].fügeLauscherHinzu(() -> beantworteFrage(antwortNummer));
      antwortKacheln[antwortNummer].setzeBuchstabe(antwortNummer);
    }

    JLabel bildGrosseKachel = new JLabel(Aussehen.macheBild("kachel-gross.png"));
    bildGrosseKachel.setBounds(5, 280, 1010, 183);
    add(bildGrosseKachel);

    tasteNächsteFrage = erzeugeTasteNächsteFrage();
  }

  /**
   * Starte ein neues Spiel.
   *
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public void starteNeuesSpiel(String dateiPfad) {
    spiel = SpielSteuerung.starteNeuesSpiel(dateiPfad);
    textThemenGebiet.setText(String.format("%s / %s", spiel.gibThemenBereich(), spiel.gibThemenGebiet()));
    aktualisiereFragenNummer();
    aktualisiereGewinnSumme();
    zeigeNächsteFrage();
  }

  /**
   * Aktualisiere den Text der die aktuelle Fragennummer sowie die Gesamtzahl der
   * Fragen anzeigt.
   */
  private void aktualisiereFragenNummer() {
    textFrageNummer.setText(String.format("%d / %d", spiel.gibFragenNummer(), spiel.gibAnzahlFragen()));
  }

  /**
   * Aktualisiere die Gewinnsumme.
   */
  private void aktualisiereGewinnSumme() {
    textGewinnSumme.setText(spiel.gibFormatierteGewinnSumme());
  }

  private JLabel erzeugeFragenText() {
    JLabel fragenText = Aussehen.macheText();
    fragenText.setBounds(80, 350, Aussehen.FENSTER_BREITE - 100, 50);
    add(fragenText);
    return fragenText;
  }

  private Taste erzeugeTasteNächsteFrage() {
    Taste taste = new Taste("pfeil-blau.png", "pfeil-gelb.png", "pfeil-rot.png");
    taste.setLocation(800, 700);
    add(taste);
    taste.setVisible(false);
    taste.setToolTipText("Zeige die nächste Fragen an.");
    taste.fügeLauscherHinzu(() -> zeigeNächsteFrage());
    return taste;
  }

  private AntwortKachel erzeugeAntwortKachel(int x, int y) {
    AntwortKachel kachel = new AntwortKachel();
    kachel.setLocation(x, y);
    add(kachel);
    return kachel;
  }

  /**
   * Zeige eine Frage.
   *
   * Es werden alle Textfelder mit den Texten der aktuellen Frage befüllt.
   *
   * @param frage Eine Instanz der aktuellen Frage.
   */
  private void zeigeFrage(Frage frage) {
    textFrage.setText(frage.gibFragenText());
    aktualisiereFragenNummer();
    String[] antwortenTexte = frage.gibAntworten();

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortKacheln[antwortNummer].setzeAntwort(antwortenTexte[antwortNummer]);
    }
    tasteNächsteFrage.setVisible(false);
    SpielSteuerung.musikSpieler.starteEndlos("fragen");
  }

  /**
   * Beantworte eine Frage.
   *
   * Diese Methode ist öffentlich, da sie verwendet wird, um mittels
   * Tastaturkürzel eine Frage zu beantworten. Es kann deshalb sein, dass die
   * Aktion ausgelöst wird, obwohl noch gar kein Spiel geladen ist.
   *
   * @param antwortNummer Eine Antwortnummer (0 = A, 1 = B, 2 = C, 3 = D).
   */
  public void beantworteFrage(int antwortNummer) {
    if (spiel == null)
      return;
    spiel.beantworteFrage(antwortNummer);
    Frage frage = spiel.gibAktuelleFrage();
    if (frage.istRichtigBeantwortet()) {
      antwortKacheln[frage.gibRichtigeAntwort()].setzeRichtig();
      SpielSteuerung.musikSpieler.starte("richtig");
    } else {
      antwortKacheln[frage.gibGegebeneAntwort()].setzeFalsch();
      antwortKacheln[frage.gibRichtigeAntwort()].setzeRichtig();
      SpielSteuerung.musikSpieler.starte("falsch");
    }
    aktualisiereGewinnSumme();
    aktualisiereFragenNummer();
    tasteNächsteFrage.setVisible(true);
    zeigeNächsteFrageVerzögert();
  }

  /**
   * Zeige die nächste Frage nicht sofort, sondern in drei Sekunden. Der/Die
   * Spieler/in hat somit Zeit das Ergebnis zu betrachten, was einen positiven
   * Lerneffekt haben könnte.
   */
  private void zeigeNächsteFrageVerzögert() {
    zeitmesser.stop();
    zeitmesser.start();
  }

  /**
   * Diese Methode wird der Stoppuhr übergeben. Sie wird 3 Sekunden, nachdem die
   * Frage beantwortete wurde, ausgeführt.
   *
   * @param ereignis
   */
  private void zeigeNächsteFrageEreignis(ActionEvent ereignis) {
    zeigeNächsteFrage();
  }

  /**
   * Zeige die nächste Frage. Ist das Spiel verloren, zeige die Ergebnis-Ansicht.
   *
   * Die Methode ist öffentlich, da eine Tastenkürzel darauf gesetzt ist.
   */
  public void zeigeNächsteFrage() {
    if (spiel == null)
      return;
    if (spiel.istVerloren()) {
      AnsichtenVerwalter.zeige("ergebnis");
    } else if (spiel.gibFragenNummer() == 0 || spiel.istAktuelleFrageBeantwortet()) {
      Frage frage = spiel.gibNächsteFrage();
      frage.mischeAntworten();
      zeigeFrage(frage);
    }
  }

  public Spiel gibAktuellesSpiel() {
    return spiel;
  }

}
