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
    super("blau.png", "orange.png", "rot.png");

    falschesBild = klickBild;
    richtigesBild = macheBild("gruen.png");
    text = Aussehen.erzeugeText();
    text.setBounds(100, 40, 500, 50);
    add(text);
  }

  public void setzeAntwort(String antwortText) {
    taueAuf();
    text.setText(antwortText);
  }

  public void setzeBuchstabe(int antwortNummer) {
    buchstabe = Aussehen.erzeugeText(Frage.konvertiereAntwortNummer(antwortNummer));
    buchstabe.setBounds(60, 40, 50, 50);
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
