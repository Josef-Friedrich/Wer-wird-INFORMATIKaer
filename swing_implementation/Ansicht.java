package swing_implementation;

import javax.swing.JPanel;

/**
 * Jede Ansicht muss diese Klasse erben.
 *
 * Die Klasse {@link Ansicht} stellt die Methode {@link zeige} bereit, die von
 * jeder Ansicht überschrieben werden kann.
 */
public class Ansicht extends JPanel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  /**
   * Standard-Konstruktor, der die Hintergrundfarbe setzt.
   */
  public Ansicht() {
    setBackground(Aussehen.FARBE_HINTERGRUND);
  }

  /**
   * Diese Methode soll von den Unterklassen überschrieben werden, wenn
   * Code vor dem Zeigen der Ansicht ausgeführt werden soll.
   */
  public void zeige() {
  }

}
