package grafische_oberflaeche;

import javax.swing.*;
import java.awt.*;

/**
 * Ein Swing-Fenster, der das JGamegrid Spielfeld beinhaltet.
 */
public class Fenster extends JFrame {

  public Fenster() {
    Spielfeld spielfeld = new Spielfeld();

    // Wie hier beschrieben http://www.aplu.ch/home/apluhomex.jsp?site=46
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getContentPane().add(spielfeld, BorderLayout.NORTH);
    JTextField f = new JTextField("Test Textfeld");
    getContentPane().add(f, BorderLayout.SOUTH);
    pack();  // Must be called before actors are added!
  }

}
