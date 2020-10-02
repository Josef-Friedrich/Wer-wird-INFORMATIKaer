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

    private static final long serialVersionUID = 1L;

    public AntwortTaste() {
      setPreferredSize(new Dimension(600, 100));
    }

    public void zeigeRichtig() {
      setBackground(Konfiguration.FARBE_RICHTIG);
    }

    public void zeigeFalsch() {
      setBackground(Konfiguration.FARBE_FALSCH);
    }

    public void zeigeNormal() {
      setBackground(Konfiguration.FARBE);
    }

  }
  private static final long serialVersionUID = 1L;

  Spiel spiel;

  JLabel fragenText;

  AntwortTaste[] antwortTasten;

  GridBagConstraints layoutEinstellung;

  public AnsichtSpiel() {

    setLayout(new GridBagLayout());

    layoutEinstellung = new GridBagConstraints();

    fragenText = erzeugeFragenText(0);

    antwortTasten = new AntwortTaste[] {
      erzeugeAntwortTaste(1, 0),
      erzeugeAntwortTaste(1, 1),
      erzeugeAntwortTaste(2, 0),
      erzeugeAntwortTaste(2, 1),
    };

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortTasten[antwortNummer].addActionListener((event) -> beantworteFrage(antwortNummer));
    }

    JButton nächsteFrage = new JButton("nächste Frage");
    layoutEinstellung.gridx = 1;
    layoutEinstellung.gridy = 3;
    add(nächsteFrage, layoutEinstellung);

    nächsteFrage.addActionListener((event) -> zeigeNächsteFrage());

    initialisiereSpiel();
  }

  private void initialisiereSpiel() {
    spiel = SpielSteuerung.lade("/FRAGEN/test/15_fragen.xml");
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

  public void zeigeFrage(Frage frage) {
    fragenText.setText(frage.gibFragenText());
    String[] antwortenTexte = frage.gibAntworten();

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antwortTasten[antwortNummer].setText(antwortenTexte[antwortNummer]);
      antwortTasten[antwortNummer].zeigeNormal();
    }
  }

  public void beantworteFrage(int antwort) {
    spiel.beantworteFrage(antwort);
    Frage frage = spiel.gibAktuelleFrage();
    if (frage.istRichtigBeantwortet()) {
      antwortTasten[frage.gibRichtigeAntwort()].zeigeRichtig();
    } else {
      antwortTasten[frage.gibGegebeneAntwort()].zeigeFalsch();
      antwortTasten[frage.gibRichtigeAntwort()].zeigeRichtig();
    }
  }

  public void zeigeNächsteFrage() {
    Frage frage = spiel.gibNächsteFrage();
    frage.mischeAntworten();
    zeigeFrage(frage);
  }

}
