package swing_implementation;

import java.util.ArrayList;

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
  private ArrayList<JLabel> zeilen;

  /**
   * Dieser Konstruktor erzeug eine Kachel ohne Text.
   */
  public AntwortKachel() {
    super();
    falschesBild = klickBild;
    richtigesBild = Aussehen.macheBild(gibBild("gruen"));
    zeilen = new ArrayList<JLabel>();
  }

  /**
   * Setze den Antworttext.
   *
   * @param antwortText Der Antworttext.
   */
  public void setzeAntwort(String antwortText) {
    taueAuf();

    ArrayList<String> textZeilen = teileText(antwortText, 35);
    if (textZeilen.size() > 1) {
      // Das JLabel-Objekt für die einzeilige Anzeige deaktivieren.
      text.setVisible(false);
      macheZeilen(textZeilen);
    } else {
      entferneZeilen();
      text.setVisible(true);
      text.setText(antwortText);
    }
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
   * Teile den Text in mehrere Zeilen auf.
   *
   * @param text      Der Text, der geteilt werden soll.
   * @param textWeite Anzahl der Zeichen, die in eine Textzeile maximal passen
   *                  dürfen.
   */
  private ArrayList<String> teileText(String text, int textWeite) {
    ArrayList<String> textZeilen = new ArrayList<String>();
    // Wir teilen den Text in Wörter. Leerzeichen können mehrer Sonderzeichen, wie
    // z. B. Zeilenumbruch oder Tabulatar sein.
    String[] wörter = text.split("[ \t\n\r]+");
    String zeile = "";

    for (int i = 0; i < wörter.length; i++) {
      if (zeile.length() > textWeite) {
        textZeilen.add(zeile);
        zeile = "";
      }
      zeile = zeile + wörter[i] + " ";
    }
    // Die restlichen Wörter hinzufügen.
    textZeilen.add(zeile);
    return textZeilen;
  }

  /**
   * Entferne die JLabel-Objekt vorhergehender Antworten mit mehrzeiligen Text.
   */
  private void entferneZeilen() {
    for (JLabel zeile : zeilen) {
      remove(zeile);
    }
  }

  /**
   * Erzeuge die JLabel-Objekte für eine mehrzeilige Antwort. Es wird zwischen
   * zwei und mehreren Zeilen unterschieden. Mehr als drei Zeilen passen nicht in
   * eine Antwortkachel.
   *
   * @param textZeilen Der Antworttext breits in mehrere Zeilen geteilt.
   */
  private void macheZeilen(ArrayList<String> textZeilen) {
    // Zuerst alte Zeilen löschen
    entferneZeilen();

    // Die y-Koordinate der ersten Zeile
    int y;
    int zeilenAbstand = 20;
    if (textZeilen.size() == 2) {
      y = 25;
      zeilenAbstand = 30;
    } else {
      y = 18;
      zeilenAbstand = 20;
    }

    for (String textZeile : textZeilen) {
      JLabel zeile = Aussehen.macheText(textZeile);
      zeilen.add(zeile);
      zeile.setBounds(70, y, 500, 50);
      add(zeile);
      y = y + zeilenAbstand;
    }
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
