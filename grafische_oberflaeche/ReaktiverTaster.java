package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class ReaktiverTaster implements GGMouseListener {
  private Actor akteur;

  private Actor schwebeAkteur;
  private Actor klickAkteur;

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

  public ReaktiverTaster(String... bilder) {
    akteur = new Actor(bilder);
  }

  public void fügeZumSpielfeldHinzu(GameGrid spielfeld, Location platz) {
    this.spielfeld = spielfeld;
    this.platz = platz;
    spielfeld.addActor(akteur, platz);

    int höhe = akteur.getHeight(0);
    int breite = akteur.getWidth(0);

    Point pixelPlatz = akteur.getPixelLocation();

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
  }

  public boolean mouseEvent(GGMouse mouse) {
    int x = mouse.getX();
    int y = mouse.getY();

    String eventType = mouse.getEventType();

    if (x >= linksObenX && x <= rechtsUntenX && y >= linksObenY && y <= rechtsUntenY) {
      if (eventType.equals("lRelease") && !istGeklickt) {
        istGeklickt = true;
        akteur.show(2);
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        akteur.show(1);
        istGeklickt = false;
      }

      if (eventType.equals("move") && istHauptBildSichtbar) {
        akteur.show(1);
        istHauptBildSichtbar = false;
      }
    } else {
      if (eventType.equals("move") && !istHauptBildSichtbar) {
        akteur.show(0);
        istHauptBildSichtbar = true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    ReaktiverTaster taster = new ReaktiverTaster("BILDER/pfeil-blau.png", "BILDER/pfeil-gelb.png", "BILDER/pfeil-rot.png");

    GameGrid gg = new GameGrid(10, 10, 60, Color.red);
    gg.show();
    gg.doRun();

    taster.fügeZumSpielfeldHinzu(gg, new Location(2, 4));
  }
}
