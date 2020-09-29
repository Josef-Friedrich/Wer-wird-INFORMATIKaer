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
    Container kontainer = getContentPane();
    // Damit das JGameGrid-Fenster zentriert wird.
    kontainer.setLayout(new GridBagLayout());
    kontainer.add(spielfeld);
    kontainer.setBackground(new Color(0, 255, 0));
    pack();  // Must be called before actors are added!
  }

}
