package swing_implementation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import spiel_logik.Frage;

public class AntwortKachel extends KleineKachel {

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

  private JLabel buchstabe;

  public AntwortKachel() {
    super();
    falschesBild = klickBild;
    richtigesBild = Aussehen.macheBild(gibBild("gruen"));
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
