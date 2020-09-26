package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import spiel_logik.Spiel;
import spiel_logik.Frage;
import java.awt.event.KeyEvent;

/**
 * Zeigt die Ansicht für die das eigentliche Spiel. Also blendet
 * den Fragentext und die vier Fragen ein.
 */
@SuppressWarnings("serial")
public class AnsichtSpiel extends Ansicht implements GGKeyListener {

  private MehrzeiligerText frageText;
  private TextAkteur antwortAText;
  private TextAkteur antwortBText;
  private TextAkteur antwortCText;
  private TextAkteur antwortDText;
  private Spielfeld spielfeld;

  private Spiel spiel;

  public AnsichtSpiel(Spielfeld spielfeld) {
    super(spielfeld);
    this.spielfeld = spielfeld;
    this.spiel = spielfeld.gibSpiel();
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

    // 52 / 2 = 26
    // 0 1 [2] | 26 27 [28]
    antwortAText = zeigeText(antwortAText, "A: " + antworten[0], 2, 20);
    antwortBText = zeigeText(antwortBText, "B: " + antworten[1], 28, 20);

    antwortCText = zeigeText(antwortCText, "C: " + antworten[2], 2, 30);
    antwortDText = zeigeText(antwortDText, "D: " + antworten[3], 28, 30);
  }

  public void zeigeNächsteFrage() {
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
    if (taste == KeyEvent.VK_SPACE) {
      zeigeNächsteFrage();
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
