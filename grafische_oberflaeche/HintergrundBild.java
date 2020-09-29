package grafische_oberflaeche;

import java.awt.*;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * https://stackoverflow.com/questions/11959758/java-maintaining-aspect-ratio-of-jpanel-background-image/11959928#11959928
 */
public class HintergrundBild extends JPanel {

  private Image image = new ImageIcon("BILDER/hintergrund.jpg").getImage();

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (image != null) {

      int breite, höhe;
      double kontainerVerhältnis = (double) getWidth() / (double) getHeight();
      double bildVerhältnis = (double) image.getWidth(this) / (double) image.getHeight(this);

      // Breite limitieren
      if (kontainerVerhältnis < bildVerhältnis) {
        breite = getWidth();
        höhe = (int) (getWidth() / bildVerhältnis);
        // Höhe limitieren
      } else {
        breite = (int) (getHeight() * bildVerhältnis);
        höhe = getHeight();
      }

      // Zentieren.
      int x = (int) (((double) getWidth() / 2) - ((double) breite / 2));
      int y = (int) (((double) getHeight() / 2) - ((double) höhe / 2));

      g.drawImage(image, x, y, breite, höhe, this);
    }
  }

  public static void main(String[] args) throws MalformedURLException {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    HintergrundBild hintergrund = new HintergrundBild();
    frame.getContentPane().add(BorderLayout.CENTER, hintergrund);
    frame.setSize(300, 400);
    frame.setVisible(true);
  }
}
