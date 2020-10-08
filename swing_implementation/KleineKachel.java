package swing_implementation;

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
    text.setBounds(100, 40, 500, 50);
    add(text);
  }

  public KleineKachel(String tastenText) {
    this();
    text.setText(tastenText);
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
