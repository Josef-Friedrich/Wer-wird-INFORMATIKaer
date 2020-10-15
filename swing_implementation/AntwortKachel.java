package swing_implementation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import spiel_logik.Frage;

/**
 * Eine Kachel in Rautenform, die eine Antwort zeigt.
 */
public class AntwortKachel extends KleineKachel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  /**
   * Ein Hintergrundbild, das gezeigt wird, wenn die Antwort falsch beantwortet
   * wurde.
   */
  private ImageIcon falschesBild;

  /**
   * Ein Hintergrundbild, das gezeigt wird, wenn die Antwort richitg beantwortet
   * wurde.
   */
  private ImageIcon richtigesBild;

  /**
   * Der Buchstabe (A, B, C, D), der am Anfang der Antwort gezeigt wird.
   */
  private JLabel buchstabe;

  /**
   * Wenn der Antworttext länger ist, wird die Antwort in mehreren Zeilen
   * angezeigt.
   */
  private MehrzeiligerText zeilen;

  /**
   * Dieser Konstruktor erzeug eine Kachel ohne Text.
   */
  public AntwortKachel() {
    super();
    falschesBild = klickBild;
    richtigesBild = Aussehen.macheBild(gibBild("gruen"));
    zeilen = new MehrzeiligerText(80, 0, getWidth() - 80, getHeight());
    add(zeilen);
    // Das JLabel-Objekt für die einzeilige Anzeige deaktivieren.
    text.setVisible(false);
  }

  /**
   * Setze den Antworttext.
   *
   * @param antwortText Der Antworttext.
   */
  public void setzeAntwort(String antwortText) {
    taueAuf();
    zeilen.setze(antwortText);
  }

  /**
   * Setze den Antwortbuchstaben (A, B, C, D)
   *
   * @param antwortNummer Die Antwortnummer (0, 1, 2, 3)
   */
  public void setzeBuchstabe(int antwortNummer) {
    String b = Frage.konvertiereAntwortNummer(antwortNummer);
    buchstabe = Aussehen.macheText(b);
    buchstabe.setBounds(45, 40, 50, 50);
    String hilfeText = String.format(
        "Drücke diese Taste, wennn du glaubst die Antwort %s ist richtig. Tastenkürzel: %s", b, b.toLowerCase());
    String hilfe = String.format("<html><p width=\"300\">%s</p></html>", hilfeText);
    setToolTipText(hilfe);
    add(buchstabe);
  }

  /**
   * Die Kachel wird eingefroren, d. h. die Hintergrundbilder wechseln nicht mehr,
   * wenn man mit der Maus darüber fährt. Es wird nur noch das Hintergrundbild mit
   * grün gefärbter Kachel angezeigt.
   */
  public void setzeRichtig() {
    friereEin();
    setIcon(richtigesBild);
  }

  /**
   * Die Kachel wird eingefroren, d. h. die Hintergrundbilder wechseln nicht mehr,
   * wenn man mit der Maus darüber fährt. Es wird nur noch das Hintergrundbild mit
   * grün gefärbter Kachel angezeigt.
   */
  public void setzeFalsch() {
    friereEin();
    setIcon(falschesBild);
  }

}
