package swing_implementation;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import spiel_logik.Konfiguration;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Diese Klasse zeigt die Einstellungen.
 */
public class AnsichtEinstellungen extends Ansicht {

  /**
   * Textfeld für die Einstellung „Anzahl an geladenen Fragen“.
   */
  private JTextField textFeldAnzahlFragen;

  private JCheckBox kästchenKo;

  private JCheckBox kästchenNachSchwierigkeit;

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
    GridBagConstraints l = erzeugeLayoutEinstellungen();
    l.gridy = 0;
    l.gridwidth = 2;
    add(überschrift, l);

    kästchenKo = erzeugeKästchen("ko");
    macheNeueZeile(1, "Spiele im K.o.-System", kästchenKo);

    textFeldAnzahlFragen = erzeugeTextFeldAnzahlFragen();
    macheNeueZeile(2, "Anzahl an Fragen", textFeldAnzahlFragen);

    kästchenNachSchwierigkeit = erzeugeKästchen("nachSchwierigkeit");
    macheNeueZeile(3, "Zeige die Fragen nach Schwierigkeit geordnet", kästchenNachSchwierigkeit);

  }

  /**
   * Ein ItemListener für die Kontrollkästchen (Checkbox), der direkt auf einen
   * boolschen Konfigurationswert zugreift und den beim anklicken abspeichert.
   *
   * @param konfigurationsName
   *
   * @return Eine Instanz eines ItemListeners.
   */
  public ItemListener erzeugeKästchenLauscher(String konfigurationsName) {
    return new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("ausgewählt " + konfigurationsName);
          Konfiguration.setze(konfigurationsName, true);
        } else {
          System.out.println("nicht ausgewählt " + konfigurationsName);
          Konfiguration.setze(konfigurationsName, false);
        }
        System.out.println(Konfiguration.gib(konfigurationsName));
      }
    };
  }

  /**
   * Erzeuge eine Kontrollkästchen (Checkbox).
   */
  public JCheckBox erzeugeKästchen(String konfigurationsName) {
    JCheckBox kästchen = new JCheckBox();
    kästchen.setOpaque(false);
    kästchen.setSelected((boolean) Konfiguration.gib(konfigurationsName));
    kästchen.addItemListener(erzeugeKästchenLauscher(konfigurationsName));
    return kästchen;
  }

  /**
   * Erzeuge Einstellungen mit einigen Anpassungen für das GridBagLayout.
   *
   * @return Eine Instanz der Einstellungsklasse.
   */
  private GridBagConstraints erzeugeLayoutEinstellungen() {
    GridBagConstraints einstellungen = new GridBagConstraints();
    einstellungen.insets = new Insets(Aussehen.ABSTAND, Aussehen.ABSTAND, Aussehen.ABSTAND, Aussehen.ABSTAND);
    return einstellungen;
  }

  /**
   * Mache eine Zeile im GridBagLayout
   *
   * @param y         Die Zeilennummer in Gitter.
   * @param textLinks Der Text der in der linken Spalte erscheinen soll.
   * @param rechts    Die Komponente, die in der rechten Spalte erscheinen soll.
   */
  private void macheNeueZeile(int y, String textLinks, JComponent rechts) {
    GridBagConstraints einstellungen = erzeugeLayoutEinstellungen();
    // links
    einstellungen.gridx = 0;
    einstellungen.gridy = y;
    add(Aussehen.macheText(textLinks), einstellungen);
    // rechts
    einstellungen.gridx = 1;
    einstellungen.gridy = y;
    add(rechts, einstellungen);
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
