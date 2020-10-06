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
interface AntwortAktion {
  public void beantworte();
}

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
  private ImageIcon falschBild;
  private ImageIcon richtigBild;

  private AntwortAktion antwortAktion;

  /**
   * Wenn die Antwort-Kachel angeklickt wurde, werden die Hintergrundbilder nicht
   * mehr gewechselt.
   */
  private boolean istBeantwortet = false;

  private JLabel text;

  public AntwortKachel() {
    hauptBild = macheBild("blau.png");
    schwebeBild = macheBild("orange.png");
    klickBild = macheBild("rot.png");
    falschBild = klickBild;
    richtigBild = macheBild("gruen.png");

    setIcon(hauptBild);
    setSize(500, 125);

    text = new JLabel();
    text.setForeground(Aussehen.FARBE_FALSCH);
    text.setBounds(0, 0, 100, 50);
    add(text);

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (!istBeantwortet) {
          setIcon(klickBild);
          führeAntwortAktionAus();
        }
      }

      public void mouseEntered(MouseEvent evt) {
        if (!istBeantwortet)
          setIcon(schwebeBild);
      }

      public void mouseExited(MouseEvent evt) {
        if (!istBeantwortet)
          setIcon(hauptBild);
      }
    });
  }

  private void führeAntwortAktionAus() {
    istBeantwortet = true;
    if (antwortAktion != null)
      antwortAktion.beantworte();
  }

  public void fügeAntwortLauscherHinzu(AntwortAktion action) {
    antwortAktion = action;
  }

  public void setzeAntwort(String antwortText) {
    istBeantwortet = false;
    text.setText(antwortText);
  }

  public void setzeRichtig() {
    istBeantwortet = true;
    setIcon(richtigBild);
  }

  public void setzeFalsch() {
    istBeantwortet = true;
    setIcon(falschBild);
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
