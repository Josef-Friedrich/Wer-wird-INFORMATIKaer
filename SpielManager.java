
import grafische_oberflaeche.Spielfeld;

import kommando_zeile.KommandoZeile;

/**
 * Hauptklasse, die die main Methode enthält.
 *
 * In dieser werden die zwei Pakete spiel und gui zusammengefügt.
 */
public class SpielManager {

  public static void main(String [] args) throws Exception {
    if (args.length > 0 && args[0].equals("cli")) {
      new KommandoZeile();
    } else {
      new Spielfeld();
    }
  }
}
