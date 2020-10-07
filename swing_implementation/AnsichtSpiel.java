package swing_implementation;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import spiel_logik.Frage;
import spiel_logik.Spiel;

public class AnsichtSpiel extends Ansicht {

  public class AntwortTaste extends JButton {

    /**
     * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
     * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
     * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
     * beispielsweise diese Warnmeldung an: „The serializable class ... does
     * not declare a static final serialVersionUID field of type longJava(536871008)“
     */
    private static final long serialVersionUID = 1L;

    public AntwortTaste() {
      setPreferredSize(new Dimension(Aussehen.ANTWORT_TASTE_BREITE, Aussehen.ANTWORT_TASTE_HÖHE));
    }

    public void zeigeRichtig() {
      setBackground(Aussehen.FARBE_RICHTIG);
    }

    public void zeigeFalsch() {
      setBackground(Aussehen.FARBE_FALSCH);
    }

    public void zeigeNormal() {
      setBackground(Aussehen.FARBE);
    }

  }

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

  AntwortTaste[] antwortTasten;

  GridBagConstraints layoutEinstellung;

  public AnsichtSpiel() {

    setLayout(new GridBagLayout());

    layoutEinstellung = new GridBagConstraints();

    fragenText = erzeugeFragenText(0);

    antwortTasten = new AntwortTaste[] { erzeugeAntwortTaste(1, 0), erzeugeAntwortTaste(1, 1),
        erzeugeAntwortTaste(2, 0), erzeugeAntwortTaste(2, 1), };

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortTasten[antwortNummer].addActionListener((event) -> beantworteFrage(antwortNummer));
    }

    JButton nächsteFrage = new JButton("nächste Frage");
    layoutEinstellung.gridx = 1;
    layoutEinstellung.gridy = 3;
    add(nächsteFrage, layoutEinstellung);

    nächsteFrage.addActionListener((event) -> zeigeNächsteFrage());
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

  private JLabel erzeugeFragenText(int gridx) {
    JLabel fragenText = new JLabel();
    layoutEinstellung.gridx = gridx;
    add(fragenText, layoutEinstellung);
    return fragenText;
  }

  private AntwortTaste erzeugeAntwortTaste(int gridx, int gridy) {
    AntwortTaste taste = new AntwortTaste();
    layoutEinstellung.insets = new Insets(5, 5, 5, 5);
    layoutEinstellung.gridy = gridx;
    layoutEinstellung.gridx = gridy;
    add(taste, layoutEinstellung);
    return taste;
  }

  private void zeigeFrage(Frage frage) {
    fragenText.setText(frage.gibFragenText());
    String[] antwortenTexte = frage.gibAntworten();

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortTasten[antwortNummer].setText(antwortenTexte[antwortNummer]);
      antwortTasten[antwortNummer].zeigeNormal();
    }
  }

  private void beantworteFrage(int antwort) {
    spiel.beantworteFrage(antwort);
    Frage frage = spiel.gibAktuelleFrage();
    if (frage.istRichtigBeantwortet()) {
      antwortTasten[frage.gibRichtigeAntwort()].zeigeRichtig();
    } else {
      antwortTasten[frage.gibGegebeneAntwort()].zeigeFalsch();
      antwortTasten[frage.gibRichtigeAntwort()].zeigeRichtig();
    }
  }

  private void zeigeNächsteFrage() {
    Frage frage = spiel.gibNächsteFrage();
    frage.mischeAntworten();
    zeigeFrage(frage);
  }

}
