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

    public AntwortText(int x, int y) {
      textFeld = new GGTextField(spielfeld, new Location(x, y), true);
      textFeld.setTextColor(Konfiguration.FARBE);
      textFeld.setFont(Konfiguration.SCHRIFT);
    }

    public void zeigeRichtig() {
      textFeld.setTextColor(Konfiguration.FARBE_RICHTIG);
    }

    public void zeigeFalsch() {
      textFeld.setTextColor(Konfiguration.FARBE_FALSCH);
    }

    public void setzeText(String text) {
      textFeld.setTextColor(Konfiguration.FARBE);
      textFeld.setText(text);
      textFeld.show();
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
  private AntwortText antwortA;
  private AntwortText antwortB;
  private AntwortText antwortC;
  private AntwortText antwortD;

  private GewinnSumme gewinnSumme;

  private Spiel spiel;

  public AnsichtSpiel(Spielfeld spielfeld) {
    super(spielfeld);
    spiel = spielfeld.gibSpiel();
    gewinnSumme = new GewinnSumme(30, 2);

    // 52 / 2 = 26
    // 0 1 [2] | 26 27 [28]
    antwortA = new AntwortText(2, 20);
    antwortB = new AntwortText(28, 20);
    antwortC = new AntwortText(2, 30);
    antwortD = new AntwortText(28, 30);

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
    String[] antworten = frage.gibAntworten();

    if (frageText != null) {
      frageText.entferneVomSpielfeld();
    }

    frageText = new MehrzeiligerText(frageTextnachricht);
    frageText.setzeImSpielfeld(spielfeld, new Location(10, 10));

    antwortA.setzeText(antworten[0]);
    antwortB.setzeText(antworten[1]);
    antwortC.setzeText(antworten[2]);
    antwortD.setzeText(antworten[3]);
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
        break;

      case KeyEvent.VK_B:

        break;

      case KeyEvent.VK_C:

        break;

      case KeyEvent.VK_D:

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
