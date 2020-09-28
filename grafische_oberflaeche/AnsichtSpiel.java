package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import spiel_logik.Spiel;
import spiel_logik.Frage;

import java.awt.event.KeyEvent;

/**
 * Zeigt die Ansicht für die das eigentliche Spiel. Also blendet den Fragentext
 * und die vier Fragen ein.
 */
public class AnsichtSpiel extends Ansicht implements GGKeyListener {

  private class AntwortText {

    private GGTextField textFeld;

    private Actor kachel;

    private String buchstabe;

    public AntwortText(int x, int y, int antwortNummer) {
      kachel = new Actor("BILDER/blau.png", "BILDER/gruen.png", "BILDER/rot.png", "BILDER/orange.png");
      spielfeld.addActor(kachel, new Location(x + 10, y));
      textFeld = new GGTextField(spielfeld, new Location(x, y), true);
      textFeld.setTextColor(Konfiguration.FARBE);
      textFeld.setFont(Konfiguration.SCHRIFT);
      buchstabe = Frage.konvertiereAntwortNummer(antwortNummer);
    }

    public void zeigeRichtig() {
      textFeld.setTextColor(Konfiguration.FARBE_RICHTIG);
      textFeld.show();
      System.out.println("richtig");
      kachel.show(1);
    }

    public void zeigeFalsch() {
      textFeld.setTextColor(Konfiguration.FARBE_FALSCH);
      textFeld.show();
      kachel.show(2);
    }

    public void setzeText(String text) {
      textFeld.setTextColor(Konfiguration.FARBE);
      textFeld.setText(String.format("%s. %s", buchstabe, text));
      textFeld.show();
      kachel.show(0);
    }
  }

  private class GewinnSumme {
    private GGTextField textFeld;

    public GewinnSumme(int x, int y) {
      textFeld = new GGTextField(spielfeld, new Location(x, y), true);
      textFeld.setTextColor(Konfiguration.FARBE);
      textFeld.setFont(Konfiguration.SCHRIFT);
      textFeld.show();
      aktualisiere(0);
    }

    public void aktualisiere(long summe) {
      textFeld.setText(String.format("%,d €", summe));
    }
  }

  private MehrzeiligerText frageText;

  private AntwortText[] antworten;

  private GewinnSumme gewinnSumme;

  private Spiel spiel;

  public AnsichtSpiel(Spielfeld spielfeld) {
    super(spielfeld);
    spiel = spielfeld.gibSpiel();
    gewinnSumme = new GewinnSumme(30, 2);

    // 52 / 2 = 26
    // 0 1 [2] | 26 27 [28]
    antworten = new AntwortText[] { new AntwortText(2, 20, 0), new AntwortText(28, 20, 1), new AntwortText(2, 30, 2),
        new AntwortText(28, 30, 3) };

    spielfeld.addKeyListener(this);
  }

  public TextAkteur zeigeText(TextAkteur textBaustein, String text, int x, int y) {
    if (textBaustein != null) {
      textBaustein.hide();
      spielfeld.removeActor(textBaustein);
    }

    textBaustein = new TextAkteur(text);
    spielfeld.addMouseListener(textBaustein, GGMouse.lPress);
    spielfeld.addActor(textBaustein, new Location(x, y));

    return textBaustein;
  }

  public void zeigeFrage(Frage frage) {
    String frageTextnachricht = frage.gibFragenText();
    String[] antwortenTexte = frage.gibAntworten();

    if (frageText != null) {
      frageText.entferneVomSpielfeld();
    }

    frageText = new MehrzeiligerText(frageTextnachricht);
    frageText.setzeImSpielfeld(spielfeld, new Location(10, 10));

    for (int antwortNummer : Frage.ANTWORT_NUMMERN) {
      antworten[antwortNummer].setzeText(antwortenTexte[antwortNummer]);
    }
  }

  public void beantworteFrage(int antwort) {
    spiel.beantworteFrage(antwort);
    Frage frage = spiel.gibAktuelleFrage();
    if (frage.istRichtigBeantwortet()) {
      antworten[frage.gibRichtigeAntwort()].zeigeRichtig();
    } else {
      antworten[frage.gibGegebeneAntwort()].zeigeFalsch();
      antworten[frage.gibRichtigeAntwort()].zeigeRichtig();
    }
  }

  public void zeigeNächsteFrage() {
    gewinnSumme.aktualisiere(spiel.gibGewinnSumme());
    Frage frage = spiel.gibNächsteFrage();
    frage.mischeAntworten();
    zeigeFrage(frage);
  }

  /**
   * Diese Methode muss implementiert sein, da das Interface GGKeyListener es
   * verlangt. Wir geben false zurück, damit weitere Klassen, die das Interface
   * benutzen das Drücken von Tasten empfangen können.
   *
   * @see <a href=
   *      "http://www.aplu.ch/classdoc/jgamegrid/ch/aplu/jgamegrid/GGKeyListener.html">
   *      Dokumentation des Interfaces</a>
   */
  public boolean keyPressed(KeyEvent evt) {
    int taste = evt.getKeyCode();

    switch (taste) {
      case KeyEvent.VK_SPACE:
      case KeyEvent.VK_ENTER:
        zeigeNächsteFrage();
        break;

      case KeyEvent.VK_A:
        beantworteFrage(0);
        break;

      case KeyEvent.VK_B:
        beantworteFrage(1);
        break;

      case KeyEvent.VK_C:
        beantworteFrage(2);
        break;

      case KeyEvent.VK_D:
        beantworteFrage(3);
        break;

      default:
        break;
    }
    return false;
  }

  /**
   * Diese Methode muss implementiert sein, da das Interface GGKeyListener es
   * verlangt. Wir geben false zurück, damit weitere Klassen, die das Interface
   * benutzen das Drücken von Tasten empfangen können.
   *
   * @see <a href=
   *      "http://www.aplu.ch/classdoc/jgamegrid/ch/aplu/jgamegrid/GGKeyListener.html">
   *      Dokumentation des Interfaces</a>
   */
  public boolean keyReleased(KeyEvent evt) {
    return false;
  }

  public void zeige() {
    zeigeNächsteFrage();
  }
}
