package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import java.awt.event.KeyEvent;

public class ReaktiverTaster implements GGMouseListener, GGKeyListener {
  private Actor hauptAkteur;

  private Actor schwebeAkteur;
  private Actor klickAkteur;

  private int taste;

  /**
   *
   */
  private boolean istHauptBildSichtbar = true;

  /**
   *
   */
  private boolean istGeklickt = false;

  private int linksObenX;
  private int linksObenY;
  private int rechtsUntenX;
  private int rechtsUntenY;

  private Location platz;

  private GameGrid spielfeld;

  private void fügeSicherHinzu (Actor akteur) {
    if (akteur.isRemoved()) {
      spielfeld.addActor(akteur, platz);
    }
  }

  private void zeige (String bildName) {
    if (bildName.equals("klick")) {
      fügeSicherHinzu(klickAkteur);
      spielfeld.removeActor(schwebeAkteur);
      spielfeld.removeActor(hauptAkteur);
    } else if (bildName.equals("schwebe")) {
      fügeSicherHinzu(klickAkteur);
      spielfeld.removeActor(schwebeAkteur);
      spielfeld.removeActor(hauptAkteur);
    } else {
      fügeSicherHinzu(klickAkteur);
      spielfeld.removeActor(schwebeAkteur);
      spielfeld.removeActor(hauptAkteur);
    }

  }

  public ReaktiverTaster(String hauptBild, String schwebeBild, String klickBild, int taste) {
    this.taste = taste;
    hauptAkteur = new Actor(hauptBild);
    schwebeAkteur = new Actor(schwebeBild);
    klickAkteur = new Actor(klickBild);
  }

  public void fügeZumSpielfeldHinzu(GameGrid spielfeld, Location platz) {
    this.spielfeld = spielfeld;
    this.platz = platz;
    spielfeld.addActor(hauptAkteur, platz);

    int höhe = hauptAkteur.getHeight(0);
    int breite = hauptAkteur.getWidth(0);

    Point pixelPlatz = hauptAkteur.getPixelLocation();

    linksObenX = (int) pixelPlatz.getX() - breite / 2;
    linksObenY = (int) pixelPlatz.getY() - höhe / 2;
    rechtsUntenX = linksObenX + breite;
    rechtsUntenY = (int) pixelPlatz.getY() + höhe / 2;

    GGBackground bg = spielfeld.getBg();
    bg.setPaintColor(new Color(255, 0, 0));
    bg.fillRectangle(new Point(linksObenX, linksObenY), new Point(rechtsUntenX, rechtsUntenY));
    spielfeld.addMouseListener(this, GGMouse.move);
    spielfeld.addMouseListener(this, GGMouse.lRelease);
    spielfeld.addMouseListener(this, GGMouse.lClick);

    spielfeld.addKeyListener(this);
  }

  private void klicke () {
    istGeklickt = true;
    spielfeld.addActor(klickAkteur, platz);
    spielfeld.removeActor(schwebeAkteur);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    spielfeld.addActor(schwebeAkteur, platz);
    spielfeld.removeActor(klickAkteur);
    istGeklickt = false;
  }

  public boolean mouseEvent(GGMouse mouse) {
    int x = mouse.getX();
    int y = mouse.getY();

    String eventType = mouse.getEventType();

    if (x >= linksObenX && x <= rechtsUntenX && y >= linksObenY && y <= rechtsUntenY) {
      if (eventType.equals("lRelease") && !istGeklickt) {
        klicke();
      }

      if (eventType.equals("move") && istHauptBildSichtbar) {
        spielfeld.addActor(schwebeAkteur, platz);
        spielfeld.act();
        spielfeld.removeActor(hauptAkteur);
        istHauptBildSichtbar = false;
      }
    } else {
      if (eventType.equals("move") && !istHauptBildSichtbar) {
        spielfeld.addActor(hauptAkteur, platz);
        spielfeld.removeActor(schwebeAkteur);
        istHauptBildSichtbar = true;
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
    System.out.println(evt.getKeyCode());
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

    GameGrid gg = new GameGrid(10, 10, 60, Color.red);
    gg.show();
    gg.doRun();

    taster.fügeZumSpielfeldHinzu(gg, new Location(2, 4));
  }
}
