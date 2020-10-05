package swing_implementation;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AntwortKachel extends JPanel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt diese statisches Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private JLabel hauptBild;
  private JLabel schwebeBild;
  private JLabel klickBild;

  private JLabel text;

  public AntwortKachel(String hauptBildPfad, String schwebeBildPfad, String klickBildPfad) {
    setSize(400, 400);
    // setBackground(Aussehen.FARBE_FALSCH);

    hauptBild = macheBild(hauptBildPfad);
    schwebeBild = macheBild(schwebeBildPfad);
    klickBild = macheBild(klickBildPfad);
    hauptBild.setBounds(0, 0, 100, 100);

    text = new JLabel("lol");
    add(this.hauptBild);

    add(text);

  }

  private JLabel macheBild(String pfad) {
    JLabel bild = null;
    try {
      bild = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/BILDER/" + pfad))));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bild;
  }

}
