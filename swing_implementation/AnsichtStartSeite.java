package swing_implementation;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;

public class AnsichtStartSeite extends Ansicht {

  private static final long serialVersionUID = 1L;

  public AnsichtStartSeite() {
    BufferedImage bild;
    setLayout(new GridBagLayout());
    try {
      bild = ImageIO.read(getClass().getResource("/BILDER/logo.png"));
      JLabel picLabel = new JLabel(new ImageIcon(bild));
      add(picLabel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
