package swing_implementation;

import javax.swing.ImageIcon;
import java.awt.event.*;

/**
 * Die Klasse {@link Taste} ist zu vergleichen mit der JButton-Komponente aus dem
 * Swing-Framework.
 *
 * Eine Taste hat drei verschiedene Hintergrundbilder, die je nach Zustand
 * ändern.
 */
public class Taste extends AktivesElement {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  protected ImageIcon hauptBild;
  protected ImageIcon schwebeBild;
  protected ImageIcon klickBild;

  /**
   * Im eingefroren Zustand wechseln die Hintergrundbilder nicht mehr und die
   * Aktion kann nicht mehr ausgeführt werden. Die Funktion wird von den
   * Antwortkacheln benötigt.
   */
  private boolean eingefroren = false;

  public Taste(String hauptBildName, String schwebeBildName, String klickBildName) {
    hauptBild = Aussehen.macheBild(hauptBildName);
    schwebeBild = Aussehen.macheBild(schwebeBildName);
    klickBild = Aussehen.macheBild(klickBildName);

    setIcon(hauptBild);
    setSize(hauptBild.getIconWidth(), hauptBild.getIconHeight());

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (!eingefroren) {
          setIcon(klickBild);
          führeAktionAus();
        }
      }

      public void mouseEntered(MouseEvent evt) {
        if (!eingefroren)
          setIcon(schwebeBild);
      }

      public void mouseExited(MouseEvent evt) {
        if (!eingefroren)
          setIcon(hauptBild);
      }
    });
  }

  /**
   * Setze die Taste in den eingefrorenen Zustand.
   */
  public void friereEin() {
    eingefroren = true;
  }

  /**
   * Taue die Taste auf, d. h. die Hintergrundbilder wechseln wieder und die
   * Aktion kann ausgeführt werden.
   */
  public void taueAuf() {
    eingefroren = false;
    setIcon(hauptBild);
  }

}
