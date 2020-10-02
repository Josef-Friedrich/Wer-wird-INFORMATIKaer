package swing_implementation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AnsichtStartSeite extends Ansicht {

  GridBagConstraints layoutEinstellung;

  private static final long serialVersionUID = 1L;

  public AnsichtStartSeite() {
    layoutEinstellung = new GridBagConstraints();

    setLayout(new GridBagLayout());
    try {
      BufferedImage bild = ImageIO.read(getClass().getResource("/BILDER/logo_klein.png"));
      JLabel picLabel = new JLabel(new ImageIcon(bild));
      layoutEinstellung.gridx = 0;
      add(picLabel, layoutEinstellung);
    } catch (IOException e) {
      e.printStackTrace();
    }

    erzeugeTaste("6. Jahrgangsstufe", 1);
    erzeugeTaste("7. Jahrgangsstufe", 2);
  }

  private JButton erzeugeTaste(String text, int gridx) {
    JButton taste = new JButton(text);
    taste.setForeground(Konfiguration.FARBE_VIOLETT);
    taste.setPreferredSize(new Dimension(600, 100));
    taste.setFont(Konfiguration.SCHRIFT);
    taste.addActionListener((event) -> Konfiguration.zeigeAnsicht("spiel"));
    layoutEinstellung.insets = new Insets(5, 5, 5, 5);
    layoutEinstellung.gridy = gridx;
    add(taste, layoutEinstellung);
    return taste;
  }

}
