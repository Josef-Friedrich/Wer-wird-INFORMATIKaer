package kommandozeilen_implementation;

/**
 * Kleine Hüll-Klasse um die Kommandozeile aufzurufen.
 *
 * Dient dazu um Fehler abzufangen. Es gibt in der Swing-Implementation des
 * Spiels auch eine korrespondierende Klasse, die
 * {@link swing_implementation.SwingerStarter} heißt.
 *
 */
public class KommandozeilenStarter {

  public KommandozeilenStarter() {
    try {
      new KommandoZeile();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
