package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import java.awt.Point;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Eine Klasse um Taster (Buttons) anzuzeigen. Es gibt drei Zustande: 1. Das
 * Hauptbild wird angezeigt, 2. Das Schwebebild wird angezeigt, 3. Das Klickbild
 * wird angezeigt.
 *
 * Wir verwenden dazu drei separate Actors aus dem JGameGrid Framework. Wir
 * arbeiten nicht mit einem Actor und drei verschiedenen Sprites, weil JGameGrid
 * die Bilder nicht instantan verarbeitet, sondern erst im nächsten „run“-Zyklus
 * abarbeitet. Der Bildwechsel funktioniert zwar. Ist aber auch sehr träge.
 */
public class ReaktiverTaster implements GGMouseListener, GGKeyListener {

  private HashMap<String, Actor> akteure;

  /**
   * In diesem Attribut wird gespeichert, welches Bild momentan angezeigt wird.
   * Das funktioniert zuverlässiger, als bei jedem Actor die Methode „isRemoved()“
   * aufzurufen.
   */
  private String angezeigtesBild;

  private int taste;

  private boolean istSichtbar;

  private int linksObenX;
  private int linksObenY;
  private int rechtsUntenX;
  private int rechtsUntenY;

  private Location platz;

  private GameGrid spielfeld;

  public ReaktiverTaster(String hauptBild, String schwebeBild, String klickBild, int taste) {
    this.taste = taste;
    akteure = new HashMap<String, Actor>();
    akteure.put("haupt", new Actor(hauptBild));
    akteure.put("schwebe", new Actor(schwebeBild));
    akteure.put("klick", new Actor(klickBild));
  }

  public Actor gibAkteur(String bildName) {
    return akteure.get(bildName);
  }

  public void fügeZumSpielfeldHinzu(GameGrid spielfeld, Location platz) {
    this.spielfeld = spielfeld;
    this.platz = platz;
    istSichtbar = true;
    angezeigtesBild = "haupt";

    Actor hauptAkteur = gibAkteur("haupt");
    spielfeld.addActor(hauptAkteur, platz);

    int höhe = hauptAkteur.getHeight(0);
    int breite = hauptAkteur.getWidth(0);

    Point pixelPlatz = hauptAkteur.getPixelLocation();

    linksObenX = (int) pixelPlatz.getX() - breite / 2;
    linksObenY = (int) pixelPlatz.getY() - höhe / 2;
    rechtsUntenX = linksObenX + breite;
    rechtsUntenY = (int) pixelPlatz.getY() + höhe / 2;

    spielfeld.addMouseListener(this, GGMouse.move);
    spielfeld.addMouseListener(this, GGMouse.lRelease);
    spielfeld.addMouseListener(this, GGMouse.lClick);

    spielfeld.addKeyListener(this);
  }

  private void fügeAkteurHinzu(String bildName) {
    spielfeld.addActor(gibAkteur(bildName), platz);
  }

  private void entferneAkteur(String bildName) {
    spielfeld.removeActor(gibAkteur(bildName));
  }

  private void zeigeBild(String bildName) {
    if (angezeigtesBild.equals(bildName))
      return;
    entferneAkteur(angezeigtesBild);
    fügeAkteurHinzu(bildName);
    angezeigtesBild = bildName;
  }

  public void zeige() {
    if (istSichtbar == true)
      return;
    istSichtbar = true;
    zeigeBild(angezeigtesBild);
  }

  public void verstecke() {
    if (istSichtbar == false)
      return;
    istSichtbar = false;
    entferneAkteur(angezeigtesBild);
  }

  private void klicke() {
    String altesBild = angezeigtesBild;
    zeigeBild("klick");
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    zeigeBild(altesBild);
  }

  public boolean mouseEvent(GGMouse mouse) {
    int x = mouse.getX();
    int y = mouse.getY();

    String eventType = mouse.getEventType();

    if (x >= linksObenX && x <= rechtsUntenX && y >= linksObenY && y <= rechtsUntenY) {
      if (eventType.equals("lRelease")) {
        klicke();
      }

      if (eventType.equals("move")) {
        zeigeBild("schwebe");
      }
    } else {
      if (eventType.equals("move")) {
        zeigeBild("haupt");
      }
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
  public boolean keyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == taste) {
      klicke();
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

  public static void main(String[] args) {
    ReaktiverTaster taster = new ReaktiverTaster("BILDER/pfeil-blau.png", "BILDER/pfeil-gelb.png",
        "BILDER/pfeil-rot.png", KeyEvent.VK_RIGHT);

    GameGrid gg = new GameGrid(10, 10, 60);
    gg.show();
    gg.doRun();

    taster.fügeZumSpielfeldHinzu(gg, new Location(2, 4));
  }
}
