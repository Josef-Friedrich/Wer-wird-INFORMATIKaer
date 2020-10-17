package swing_implementation;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import spiel_logik.Konfiguration;

import java.awt.GridBagLayout;

/**
 * Diese Klasse zeigt die Einstellungen.
 */
public class AnsichtEinstellungen extends Ansicht {

  /**
   * Textfeld für die Einstellung „Anzahl an geladenen Fragen“.
   */
  private JTextField textFeldAnzahlFragen;

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public AnsichtEinstellungen() {
    setLayout(new GridBagLayout());
    JLabel überschrift = Aussehen.macheÜberschrift("Einstellungen");
    add(überschrift);

    textFeldAnzahlFragen = erzeugeTextFeldAnzahlFragen();
  }

  /**
   * Erzeuge das Textfeld für die Einstellung „Anzahl an geladenen Fragen“.
   *
   * @return Das erzeugte Textfeld
   */
  private JTextField erzeugeTextFeldAnzahlFragen() {
    JTextField textFeld = new JTextField(Integer.toString(Konfiguration.anzahlGeladenerFragen), 3);

    textFeld.setFont(Aussehen.SCHRIFT_NORMAL);
    textFeld.setForeground(Aussehen.FARBE_BLAU);
    textFeld.getDocument().addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) {
        setzeAnzahlFragen();
      }

      public void removeUpdate(DocumentEvent e) {
        setzeAnzahlFragen();
      }

      public void insertUpdate(DocumentEvent e) {
        setzeAnzahlFragen();
      }

      private void setzeAnzahlFragen() {
        String zahl = textFeld.getText();
        if (istZahl(zahl)) {
          Konfiguration.anzahlGeladenerFragen = Integer.parseInt(zahl);
        }
      }

    });
    add(textFeld);
    return textFeld;
  }

  /**
   * Überprüfe an der übergebene Text eine ganze Zahl ist.
   *
   * @param zahl Ein Text
   *
   * @return Wahr, wenn der Text eine Zahl ist.
   */
  public static boolean istZahl(String zahl) {
    // https://stackoverflow.com/a/5439547/10193818
    try {
      Integer.parseInt(zahl);
    } catch (NumberFormatException e) {
      return false;
    } catch (NullPointerException e) {
      return false;
    }
    // Wenn man bis hierher gekommen ist, das ist es eine Zahl
    return true;
  }

}
