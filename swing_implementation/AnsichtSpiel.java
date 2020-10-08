package swing_implementation;

import javax.swing.JLabel;

import spiel_logik.Frage;
import spiel_logik.Spiel;

public class AnsichtSpiel extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does
   * not declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  Spiel spiel;

  JLabel fragenText;

  AntwortKachel[] antwortKacheln;

  public AnsichtSpiel() {
    setLayout(null);

    fragenText = erzeugeFragenText();

    int x1 = 10;
    int x2 = 520;
    int y1 = 450;
    int y2 = 550;

    antwortKacheln = new AntwortKachel[] { erzeugeAntwortKachel(x1, y1), erzeugeAntwortKachel(x1, y2),
        erzeugeAntwortKachel(x2, y1), erzeugeAntwortKachel(x2, y2) };

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortKacheln[antwortNummer].fügeLauscherHinzu(() -> beantworteFrage(antwortNummer));
    }

    Taste nächsteFrage = new Taste("pfeil-blau.png", "pfeil-gelb.png", "pfeil-rot.png");
    nächsteFrage.setLocation(800, 700);
    add(nächsteFrage);

    nächsteFrage.fügeLauscherHinzu(() -> zeigeNächsteFrage());
  }

  /**
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public void starteNeuesSpiel(String dateiPfad) {
    SpielSteuerung.lade(dateiPfad);
    spiel = SpielSteuerung.gib();
    zeigeNächsteFrage();
  }

  private JLabel erzeugeFragenText() {
    JLabel fragenText = Aussehen.erzeugeText();
    fragenText.setBounds(100, 100, 500, 500);
    add(fragenText);
    return fragenText;
  }

  private AntwortKachel erzeugeAntwortKachel(int x, int y) {
    AntwortKachel kachel = new AntwortKachel();
    kachel.setLocation(x, y);
    add(kachel);
    return kachel;
  }

  private void zeigeFrage(Frage frage) {
    fragenText.setText(frage.gibFragenText());
    String[] antwortenTexte = frage.gibAntworten();

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortKacheln[antwortNummer].setzeAntwort(antwortenTexte[antwortNummer]);
    }
  }

  private void beantworteFrage(int antwort) {
    spiel.beantworteFrage(antwort);
    Frage frage = spiel.gibAktuelleFrage();
    if (frage.istRichtigBeantwortet()) {
      antwortKacheln[frage.gibRichtigeAntwort()].setzeRichtig();
    } else {
      antwortKacheln[frage.gibGegebeneAntwort()].setzeFalsch();
      antwortKacheln[frage.gibRichtigeAntwort()].setzeRichtig();
    }
  }

  private void zeigeNächsteFrage() {
    Frage frage = spiel.gibNächsteFrage();
    frage.mischeAntworten();
    zeigeFrage(frage);
  }

}
