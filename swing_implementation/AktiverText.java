package swing_implementation;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;


public class AktiverText extends JLabel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does
   * not declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public AktiverText(String text) {
    setText(text);
    setFont(Aussehen.SCHRIFT_NORMAL);
    setForeground(Aussehen.FARBE_VIOLETT);

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        setForeground(Aussehen.FARBE_ROT);
      }

      public void mouseEntered(MouseEvent evt) {
        setForeground(Aussehen.FARBE_ORANGE);
      }

      public void mouseExited(MouseEvent evt) {
        setForeground(Aussehen.FARBE_VIOLETT);
      }
    });

  }
}
