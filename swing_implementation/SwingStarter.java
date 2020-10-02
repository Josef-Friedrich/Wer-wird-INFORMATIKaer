package swing_implementation;

import java.awt.EventQueue;

/**
 * Starte die Swing-Implementation des Spiels.
 */
public class SwingStarter {

  public SwingStarter() {
    EventQueue.invokeLater(() -> {
      HauptFenster fenster = new HauptFenster();
      fenster.setVisible(true);
    });
  }

}
