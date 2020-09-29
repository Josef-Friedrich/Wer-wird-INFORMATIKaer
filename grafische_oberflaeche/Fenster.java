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
    // Damit das JGameGrid-Fenster zentriert wird.

    Container kontainer = getContentPane();

    // Image image = new ImageIcon("BILDER/hintergrund.jpg").getImage();
    // BackgroundPanel panel = new BackgroundPanel(image, BackgroundPanel.SCALED);
    // kontainer.add(BorderLayout.CENTER, panel);

    // HintergrundBild hintergrund = new HintergrundBild();
    // kontainer.add(BorderLayout.CENTER, hintergrund);

    kontainer.setLayout(new GridBagLayout());

    kontainer.add(spielfeld);
    kontainer.setBackground(new Color(152, 152, 255));
    pack(); // Must be called before actors are added!
  }

}
