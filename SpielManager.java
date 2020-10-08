import kommandozeilen_implementation.KommandozeilenStarter;
import swing_implementation.SwingStarter;

/**
 * Hauptklasse, die die main Methode enthält. Über Argumente der Kommandozeile
 * können die verschiedenen Implementationen des Spiels aufrufen werden.
 */
public class SpielManager {

  /**
   * Der Einstiegspunkt des Spiels. Werden keine Kommandozeilenargumente übergeben
   * öffnet sich automatisch die Swing-Implementation des Spiels.
   *
   * @param args Der Text {@code swing} öffnet die Swing-Implementation des
   *             Spiels. Der Text {@code cli} (Command Line Interface) öffnet die
   *             Kommanozeilen-Implementation des Spiels.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      new SwingStarter();
      return;
    }

    String argument = args[0];

    if (argument.equals("cli")) {
      new KommandozeilenStarter();
    } else if (argument.equals("swing")) {
      new SwingStarter();
    } else {
      new SwingStarter();
    }
  }
}
