package swing_implementation;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;

import spiel_logik.Frage;
import spiel_logik.Konfiguration;
import spiel_logik.Spiel;

/**
 * Diese Klasse enthält die Hauptansicht des Spiels, d. h. sie zeigt den
 * Fragentext und die vier Antowrtten.
 */
public class AnsichtSpiel extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  /**
   * Eine Instanz der Klasse {@link Spiel}.
   */
  private Spiel spiel;

  /**
   * Der Text der das ausgewählte Themengebiet bzw. den Themenbereich anzeigt.
   */
  private JLabel textThemenGebiet;

  /**
   * Der Text der aktuellen Gewinnsumme.
   */
  private JLabel textGewinnSumme;

  /**
   * Der Text, der die aktuelle Fragenummer bzw. die Anzahl der geladenen Fragen
   * anzeigt.
   */
  private JLabel textFrageNummer;

  /**
   * Der Text der Frage als mehrzeiliger Text.
   */
  private MehrzeiligerText textFrage;

  /**
   * Ein Feld mit den vier Antwortkacheln.
   */
  private AntwortKachel[] antwortKacheln;

  /**
   * Ein kleiner Pfeil, der zur nächsten Fragen führt, wenn man darauf klickt.
   */
  private Taste tasteNächsteFrage;

  /**
   * Nach 1.5 Sekunden wird automatisch die nächste Frage angezeigt. Die Zeit
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
    zeitmesser = new Timer(Konfiguration.automatischWeiterDauer, this::zeigeNächsteFrageEreignis);
    zeitmesser.setRepeats(false);

    setLayout(null);

    add(Aussehen.gibLogo("sehr-klein", 286));

    textThemenGebiet = Aussehen.macheText(Aussehen.ABSTAND, Aussehen.ABSTAND, 500, Aussehen.ZEILEN_ABSTAND);
    add(textThemenGebiet);

    add(erzeugeTextGewinnSumme());

    textFrageNummer = Aussehen.macheText(Aussehen.ABSTAND,
        Aussehen.FENSTER_HÖHE - Aussehen.ZEILEN_ABSTAND - Aussehen.ABSTAND, 500, Aussehen.ZEILEN_ABSTAND);
    add(textFrageNummer);

    int x1 = 10;
    int x2 = 520;
    int y1 = 500;
    int y2 = 600;

    antwortKacheln = new AntwortKachel[] { erzeugeAntwortKachel(x1, y1), erzeugeAntwortKachel(x2, y1),
        erzeugeAntwortKachel(x1, y2), erzeugeAntwortKachel(x2, y2) };

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortKacheln[antwortNummer].fügeAktionenLauscherHinzu(() -> beantworteFrage(antwortNummer));
      antwortKacheln[antwortNummer].setzeBuchstabe(antwortNummer);
    }

    textFrage = erzeugeFragenText();

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
    if (spiel.istVerloren()) // nur aktualisieren, wenn nicht verloren
      return;

    textGewinnSumme.setText(spiel.gibFormatierteGewinnSumme());
  }

  /**
   * Erzeuge den Text der Frage.
   *
   * @return
   */
  private MehrzeiligerText erzeugeFragenText() {
    MehrzeiligerText text = new MehrzeiligerText(90, 400, Aussehen.FENSTER_BREITE - 100, 50);
    add(text);
    JLabel bildGrosseKachel = new JLabel(Aussehen.macheBild("kachel-gross.png"));
    bildGrosseKachel.setBounds(5, 330, 1010, 183);
    add(bildGrosseKachel);
    return text;
  }

  private Taste erzeugeTasteNächsteFrage() {
    Taste taste = new Taste("pfeil-blau.png", "pfeil-gelb.png", "pfeil-rot.png");
    taste.setLocation(800, 715);
    add(taste);
    taste.setVisible(false);
    taste.setToolTipText("Zeige die nächste Fragen an.");
    taste.fügeAktionenLauscherHinzu(() -> zeigeNächsteFrage());
    return taste;
  }

  /**
   * Erzeuge eine Antwortkachel.
   *
   * @param x Die x-Koordinate, an der die Antwortkachel plaziert werden soll.
   * @param y Die y-Koordinate, an der die Antwortkachel plaziert werden soll.
   *
   * @return Die erzeugte Antwortkachel.
   */
  private AntwortKachel erzeugeAntwortKachel(int x, int y) {
    AntwortKachel kachel = new AntwortKachel();
    kachel.setLocation(x, y);
    add(kachel);
    return kachel;
  }

  /**
   * Der Text soll rechtsbündig erscheinen, weshalb er etwas aufwändiger zu
   * erzeugen ist. Deshalb die Auslagerung in diese Methode.
   */
  private JComponent erzeugeTextGewinnSumme() {
    Box schachtel = Box.createHorizontalBox();
    schachtel.add(Box.createHorizontalGlue());
    textGewinnSumme = Aussehen.macheText();
    schachtel.add(textGewinnSumme);
    int halbeFensterBreite = Aussehen.FENSTER_BREITE / 2;
    schachtel.setBounds(halbeFensterBreite, Aussehen.ABSTAND, halbeFensterBreite - Aussehen.ABSTAND,
        Aussehen.ZEILEN_ABSTAND);
    return schachtel;
  }

  /**
   * Zeige eine Frage.
   *
   * Es werden alle Textfelder mit den Texten der aktuellen Frage befüllt.
   *
   * @param frage Eine Instanz der aktuellen Frage.
   */
  private void zeigeFrage(Frage frage) {
    textFrage.setze(frage.gibFragenText(), 80);
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
   * Zeige die nächste Frage nicht sofort, sondern in 1.5 Sekunden. Der/Die
   * Spieler/in hat somit Zeit das Ergebnis zu betrachten, was einen positiven
   * Lerneffekt haben könnte.
   */
  private void zeigeNächsteFrageVerzögert() {
    if (Konfiguration.automatischWeiter) {
      zeitmesser.stop();
      zeitmesser.start();
    }
  }

  /**
   * Diese Methode wird der Stoppuhr übergeben. Sie wird 3 Sekunden, nachdem die
   * Frage beantwortete wurde, ausgeführt.
   *
   * @param ereignis Eine Instanz der Klasse {@link ActionEvent}.
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

    if (spiel.istVerloren() || spiel.istBeendet()) {
      AnsichtenVerwalter.zeige("ergebnis");
    } else if (spiel.gibFragenNummer() == 0 || spiel.istAktuelleFrageBeantwortet()) {
      Frage frage = spiel.gibNächsteFrage();
      frage.mischeAntworten();
      zeigeFrage(frage);
    }
  }

  public void zeige() {
    zeitmesser.setInitialDelay(Konfiguration.automatischWeiterDauer);
    zeitmesser.setDelay(Konfiguration.automatischWeiterDauer);
  }

}
