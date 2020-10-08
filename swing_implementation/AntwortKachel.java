package swing_implementation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import spiel_logik.Frage;

public class AntwortKachel extends Taste {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private ImageIcon falschesBild;
  private ImageIcon richtigesBild;

  private JLabel text;

  private JLabel buchstabe;

  public AntwortKachel() {
    super(gibBild("blau"), gibBild("orange"), gibBild("rot"));
    falschesBild = klickBild;
    richtigesBild = Aussehen.macheBild(gibBild("gruen"));
    text = Aussehen.macheText();
    text.setBounds(100, 40, 500, 50);
    add(text);
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
  private static String gibBild(String farbe) {
    return String.format("kachel-klein_%s.png", farbe);
  }

  public void setzeAntwort(String antwortText) {
    taueAuf();
    text.setText(antwortText);
  }

  public void setzeBuchstabe(int antwortNummer) {
    String b = Frage.konvertiereAntwortNummer(antwortNummer);
    buchstabe = Aussehen.macheText(b);
    buchstabe.setBounds(60, 40, 50, 50);
    String hilfeText = String.format(
        "Drücke diese Taste, wennn du glaubst die Antwort %s ist richtig. Tastenkürzel: %s", b, b.toLowerCase());
    String hilfe = String.format("<html><p width=\"300\">%s</p></html>", hilfeText);
    setToolTipText(hilfe);
    add(buchstabe);
  }

  public void setzeRichtig() {
    friereEin();
    setIcon(richtigesBild);
  }

  public void setzeFalsch() {
    friereEin();
    setIcon(falschesBild);
  }

}
