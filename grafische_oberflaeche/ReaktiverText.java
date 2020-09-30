package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class ReaktiverText implements GGMouseListener {
  private TextActor hauptText;
  private TextActor schwebeText;

  private boolean istHauptTextSichtbar = true;

  private int linksObenX;
  private int linksObenY;
  private int rechtsUntenX;
  private int rechtsUntenY;

  private Location platz;

  private GameGrid spielfeld;

  public ReaktiverText(String text, Color farbe, Color schwebeFarbe, Color hintergrundFarbe, Font schriftart) {
    hauptText = new TextActor(text, farbe, hintergrundFarbe, schriftart);
    schwebeText = new TextActor(text, schwebeFarbe, hintergrundFarbe, schriftart);
  }

  public void fügeZumSpielfeldHinzu(GameGrid spielfeld, Location platz) {
    this.spielfeld = spielfeld;
    this.platz = platz;
    spielfeld.addActor(hauptText, platz);

    int textHöhe = hauptText.getTextHeight();
    int textWeite = hauptText.getTextWidth();

    Point pixelPlatz = hauptText.getPixelLocation();

    linksObenX = (int) pixelPlatz.getX();
    linksObenY = (int) pixelPlatz.getY() - textHöhe / 2;
    rechtsUntenX = linksObenX + textWeite;
    rechtsUntenY = (int) pixelPlatz.getY() + textHöhe / 2;

    spielfeld.addMouseListener(this, GGMouse.move);
  }

  public boolean mouseEvent(GGMouse mouse) {
    int x = mouse.getX();
    int y = mouse.getY();

    if (x >= linksObenX && x <= rechtsUntenX && y >= linksObenY && y <= rechtsUntenY) {
      if (istHauptTextSichtbar) {
        System.out.println(spielfeld.getActors().size());
        spielfeld.removeActor(hauptText);
        spielfeld.addActor(schwebeText, platz);
        istHauptTextSichtbar = false;
      }
    } else {
      if (!istHauptTextSichtbar) {
        spielfeld.removeActor(schwebeText);
        spielfeld.addActor(hauptText, platz);
        istHauptTextSichtbar = true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    ReaktiverText text1 = new ReaktiverText("Some example text.", new Color(2, 2, 2), new Color(2, 255, 2),
        new Color(255, 255, 255, 0), new Font("Sans", 0, 20));

    GameGrid gg = new GameGrid(10, 10, 60, Color.red);
    gg.show();

    text1.fügeZumSpielfeldHinzu(gg, new Location(2, 4));
  }
}
