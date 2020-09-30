package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import spiel_logik.Spiel;

import fragen_verwaltung.ThemenGebiet;
import fragen_verwaltung.ThemenKatalog;

@SuppressWarnings("serial")
public class Spielfeld extends GameGrid implements GGKeyListener {

  private Spiel spiel;

  private HashMap<String,Ansicht> ansichten;

  private String[] ansichtsNamen = new String[]{"spiel", "hilfe"};

  public Spielfeld() {
    // 1024 × 768 -> 1040 x 760
    super(52, 38, 20);
    setBgColor(0, 51, 153);

    initialisiereSpiel();
    initialisiereAnsichten();

    addKeyListener(this);
    //show();
  }

  private void initialisiereSpiel() {
    spiel = new Spiel();

    ThemenKatalog katalog = new ThemenKatalog();
    // ThemenGebiet gebiet = katalog.gibGebietDurchNummer(0);
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/test/15_fragen.xml");
    gebiet.leseFragenInsSpiel(spiel);
  }

  private void initialisiereAnsichten() {
    ansichten = new HashMap<String,Ansicht>();
    ansichten.put("spiel", new AnsichtSpiel(this));
    ansichten.put("hilfe", new AnsichtThemen(this));
  }

  private void zeigeAnsicht(String ansichtsName) {
    allesLeeren();
    ansichten.get(ansichtsName).zeige();
  }

  public Spiel gibSpiel() {
    return spiel;
  }

  private void allesLeeren() {
    removeAllActors();
    refresh();
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
    if (taste == KeyEvent.VK_1) {
      zeigeAnsicht("spiel");
    } else if (taste == KeyEvent.VK_2) {
      zeigeAnsicht("hilfe");
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
}
