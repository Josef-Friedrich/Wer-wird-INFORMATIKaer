package swing_implementation;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;

public class AntwortKachel extends JLabel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt diese statisches Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private ImageIcon hauptBild;
  private ImageIcon schwebeBild;
  private ImageIcon klickBild;

  boolean schwebt = false;

  private JLabel text;

  public AntwortKachel(String hauptBildPfad, String schwebeBildPfad, String klickBildPfad) {
    hauptBild = macheBild(hauptBildPfad);
    schwebeBild = macheBild(schwebeBildPfad);
    klickBild = macheBild(klickBildPfad);
    setIcon(hauptBild);
    setSize(500, 125);

    text = new JLabel("lol");
    text.setForeground(Aussehen.FARBE_FALSCH);
    text.setBounds(0, 0, 100, 50);
    add(text);

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        setIcon(klickBild);
        System.out.println("Clicked!");
      }

      public void mouseEntered(MouseEvent evt) {
        setIcon(schwebeBild);
        System.out.println("entered!");

      }

      public void mouseExited(MouseEvent evt) {
        setIcon(hauptBild);
        System.out.println("exited!");
      }
    });
  }

  private ImageIcon macheBild(String pfad) {
    ImageIcon bild = null;
    try {
      bild = new ImageIcon(ImageIO.read(getClass().getResource("/BILDER/" + pfad)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bild;
  }

}
