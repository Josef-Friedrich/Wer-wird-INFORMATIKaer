
import kommandozeilen_implementation.KommandoZeile;

import java.awt.*;

//import jgamegrid_implementation.Fenster;

/**
 * Hauptklasse, die die main Methode enthält.
 *
 * In dieser werden die zwei Pakete spiel und gui zusammengefügt.
 */
public class SpielManager {

  public static void main(String[] args) throws Exception {
    if (args.length > 0 && args[0].equals("cli")) {
      new KommandoZeile();
    } else {
      // Wie hier beschrieben: http://www.aplu.ch/home/apluhomex.jsp?site=46
      // EventQueue.invokeLater(new Runnable() {
      //   public void run() {
      //     new Fenster().setVisible(true);
      //   }
      // });
    }
  }
}
