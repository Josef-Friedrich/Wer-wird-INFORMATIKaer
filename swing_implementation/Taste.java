package swing_implementation;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;

/**
 * Die Methode beantworte aus diesem Interface wird ausgeführt, wenn man auf die
 * Antwortkachel klickt oder das entsprechende Tastaturkürzel anklickt.
 */
interface Aktion {
  public void führeAus();
}

public class Taste extends JLabel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private ImageIcon hauptBild;
  private ImageIcon schwebeBild;
  private ImageIcon klickBild;

  private Aktion aktion;

  public Taste(String hauptBildName, String schwebeBildName, String klickBildName) {
    hauptBild = macheBild(hauptBildName);
    schwebeBild = macheBild(schwebeBildName);
    klickBild = macheBild(klickBildName);

    setIcon(hauptBild);
    setSize(hauptBild.getIconWidth(), hauptBild.getIconHeight());

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        setIcon(klickBild);
        führeAktionAus();
      }

      public void mouseEntered(MouseEvent evt) {
        setIcon(schwebeBild);
      }

      public void mouseExited(MouseEvent evt) {
        setIcon(hauptBild);
      }
    });
  }

  private void führeAktionAus() {
    if (aktion != null)
      aktion.führeAus();
  }

  public void fügeLauscherHinzu(Aktion aktion) {
    this.aktion = aktion;
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
