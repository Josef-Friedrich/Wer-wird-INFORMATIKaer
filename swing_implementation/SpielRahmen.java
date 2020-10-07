package swing_implementation;

import javax.swing.JFrame;

/**
 * Eine Hüllklasse um {@link javax.swing.JFrame}.
 *
 * In dieser Klasse wird das Aussehen des Fensters festegelegt.
 */
public class SpielRahmen extends JFrame {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public SpielRahmen() {
    this("Wer wird INFORMATIKär!");
  }

  public SpielRahmen(String titel) {
    setTitle(titel);
    setResizable(false);
    setSize(Aussehen.FENSTER_BREITE, Aussehen.FENSTER_HÖHE);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBackground(Aussehen.FARBE_HINTERGRUND);
    setLayout(null);
  }

  public SpielRahmen(String titel, boolean färbeInhalt) {
    this(titel);
    if (färbeInhalt)
      getContentPane().setBackground(Aussehen.FARBE_HINTERGRUND);
  }

}
