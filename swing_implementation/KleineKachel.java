package swing_implementation;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Zeigt eine kleine Kachel, die angeklickt werden kann.
 */
public class KleineKachel extends Taste {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  protected JLabel text;

  public KleineKachel() {
    super(gibBild("blau"), gibBild("orange"), gibBild("rot"));
    text = Aussehen.macheText();
    JComponent rahmen = zentiere(text);
    rahmen.setBounds(0, 0, getWidth(), getHeight());
    add(rahmen);
  }

  public KleineKachel(String tastenText) {
    this();
    text.setText(tastenText);
  }

  /**
   * Zentiere eine Komponente sowohl horizontal wie auch vertikal.
   *
   * @param komponente Eine Swing-Komponente.
   *
   * @return Eine Swing-Komponente.
   */
  private JComponent zentiere(JComponent komponente) {
    return zentiereVertikal(zentiereHorizontal(komponente));
  }

  /**
   * Zentiere eine Komponente vertikal.
   *
   * @param komponente Eine Swing-Komponente.
   *
   * @return Eine Swing-Komponente.
   */
  private JComponent zentiereVertikal(JComponent komponente) {
    Box rahmen = Box.createVerticalBox();
    rahmen.add(Box.createVerticalGlue());
    rahmen.add(komponente);
    rahmen.add(Box.createVerticalGlue());
    return rahmen;
  }

  /**
   * Zentiere eine Komponente horizontal.
   *
   * @param komponente Eine Swing-Komponente.
   *
   * @return Eine Swing-Komponente.
   */
  private JComponent zentiereHorizontal(JComponent komponente) {
    Box rahmen = Box.createHorizontalBox();
    rahmen.add(Box.createHorizontalGlue());
    rahmen.add(komponente);
    rahmen.add(Box.createHorizontalGlue());
    return rahmen;
  }

  /**
   * Kleine Hilfsfunktion um die Namen der Bild-Dateien zusammenzubauen. Die
   * Methode muss statisch sein, da sie im Konstrutor in der super-Methode
   * verwendet wird.
   *
   * @param farbe Die Farbe der Bild-Kachel.
   *
   * @returns Der Name der Bilddatei.
   */
  protected static String gibBild(String farbe) {
    return String.format("kachel-klein_%s.png", farbe);
  }

}
