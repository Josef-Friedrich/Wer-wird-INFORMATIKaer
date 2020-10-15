package swing_implementation;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Diese Klasse spaltet einen längeren Text in mehrere Zeilen und erzeugt für
 * jede Zeile ein JLabel-Objekt, fügt hinzu bzw. entfernt diese von der
 * übergeordneten Komponente.
 */
public class MehrzeiligerText {

  /**
   * Die Standardtextweite, nach der eine neue Zeile begonnen wird.
   */
  private int standardTextWeite = 35;

  /**
   * Wenn der Antworttext länger ist, wird die Antwort in mehreren Zeilen
   * angezeigt.
   */
  private ArrayList<JLabel> zeilen;

  /**
   * Übergeordnete Komponente, zu der die Zeilen hinzugefügt werden.
   */
  JComponent behälter;

  /**
   * @param behälter Übergeordnete Komponente, zu der die Zeilen hinzugefügt
   *                 werden.
   */
  public MehrzeiligerText(JComponent behälter) {
    this.behälter = behälter;
    zeilen = new ArrayList<JLabel>();
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
    // Wir teilen den Text in Wörter. Wir behandeln nicht nur
    // Leerzeichen sondern auch andere Zeichen wie z. B. Zeilenumbruch
    // oder Tabulatar sein.
    String[] wörter = text.split("[ \t\n\r]+");
    String zeile = "";

    for (int i = 0; i < wörter.length; i++) {
      String nächstesWort = wörter[i];
      if (zeile.length() + nächstesWort.length() > textWeite) {
        textZeilen.add(zeile);
        zeile = "";
      }
      zeile = zeile + nächstesWort + " ";
    }
    // Die restlichen Wörter hinzufügen.
    textZeilen.add(zeile);
    return textZeilen;
  }

  /**
   * Berechne die Zahl an ein Zeilen in der ein Text aufgeteilt wird.
   *
   * @param text      Der Text, der geteilt werden soll.
   * @param textWeite Anzahl der Zeichen, die in eine Textzeile maximal passen
   *                  dürfen.
   */
  public int berechneZeilenAnzahl(String text, int textWeite) {
    ArrayList<String> textZeilen = teileText(text, textWeite);
    return textZeilen.size();
  }

  /**
   * Berechne die Zahl an ein Zeilen in der ein Text aufgeteilt wird.
   *
   * @param text Der Text, der geteilt werden soll.
   */
  public int berechneZeilenAnzahl(String text) {
    return berechneZeilenAnzahl(text, standardTextWeite);
  }

  /**
   * Erzeuge die JLabel-Objekte für eine mehrzeilige Antwort. Es wird zwischen
   * zwei und mehreren Zeilen unterschieden. Mehr als drei Zeilen passen nicht in
   * eine Antwortkachel.
   *
   * @param text      Der Text, der geteilt werden soll.
   * @param textWeite Anzahl der Zeichen, die in eine Textzeile maximal passen
   *                  dürfen.
   */
  private void macheZeilen(String text, int textWeite) {
    // Zuerst alte Zeilen löschen
    entferne();

    ArrayList<String> textZeilen = teileText(text, textWeite);

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
      behälter.add(zeile);
      y = y + zeilenAbstand;
    }
  }

  /**
   * Setze einen neuen Text, d. h. spalte ihn und erzeuge neue JLabel-Objekte. Es
   * wird nach 35 Zeichen getrennt.
   *
   * @param text Der Text, der geteilt werden soll.
   */
  public void setze(String text) {
    macheZeilen(text, standardTextWeite);
  }

  /**
   * Setze einen neuen Text, d. h. spalte ihn und erzeuge neue JLabel-Objekte. Es
   * kann angegeben werden, nach wie vielen Zeichen umgebrochen werden kann.
   *
   * @param text      Der Text, der geteilt werden soll.
   * @param textWeite Anzahl der Zeichen, die in eine Textzeile maximal passen
   *                  dürfen.
   */
  public void setze(String text, int textWeite) {
    macheZeilen(text, textWeite);
  }

  /**
   * Entferne die JLabel-Objekt vorhergehender Antworten mit mehrzeiligen Text.
   */
  public void entferne() {
    for (JLabel zeile : zeilen) {
      behälter.remove(zeile);
    }
  }

}
