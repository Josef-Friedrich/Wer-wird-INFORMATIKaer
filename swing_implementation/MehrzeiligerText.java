package swing_implementation;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * Diese Klasse spaltet einen längeren Text in mehrere Zeilen und erzeugt für
 * jede Zeile ein JLabel-Objekt, fügt hinzu bzw. entfernt diese von der
 * übergeordneten Komponente.
 */
public class MehrzeiligerText extends Box {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  /**
   * Die Standardtextweite, nach der eine neue Zeile begonnen wird.
   */
  private int standardTextWeite = 35;

  /**
   * Mit diesem Konstruktor kann die Position und die Größe des mehrzeiligen Texts
   * angegeben werden.
   *
   * @param x      Die X-Koordinate der nordwestlichen Ecke in Pixel.
   * @param y      Die Y-Koordinate der nordwestlichen Ecke in Pixel.
   * @param breite Die Breite in Pixel.
   * @param höhe   Die Höhe in Pixel.
   */
  public MehrzeiligerText(int x, int y, int breite, int höhe) {
    super(BoxLayout.PAGE_AXIS);
    setBounds(x, y, breite, höhe);
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
   * Berechne die Anzahl an Zeilen, in die ein Text aufgeteilt werden soll. Dabei
   * kann die Textweite angegeben werden.
   *
   * @param text      Der Text, der geteilt werden soll.
   * @param textWeite Anzahl der Zeichen, die in eine Textzeile maximal passen
   *                  dürfen.
   *
   * @return Die Anzahl an Zeilen.
   */
  public int berechneZeilenAnzahl(String text, int textWeite) {
    ArrayList<String> textZeilen = teileText(text, textWeite);
    return textZeilen.size();
  }

  /**
   * Berechne die Anzahl an Zeilen, in die ein Text aufgeteilt werden soll.
   *
   * @param text Der Text, der geteilt werden soll.
   *
   * @return Die Anzahl an Zeilen.
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
    removeAll();
    // Manchmal blieben alte Zeilen stehen, deshalb repaint().
    repaint();

    ArrayList<String> textZeilen = teileText(text, textWeite);

    add(Box.createVerticalGlue());
    for (String textZeile : textZeilen) {
      JLabel zeile = Aussehen.macheText(textZeile);
      add(zeile);
    }
    add(Box.createVerticalGlue());
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

}
