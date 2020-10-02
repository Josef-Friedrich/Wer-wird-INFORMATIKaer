package swing_implementation;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuLeiste extends JMenuBar {

  private static final long serialVersionUID = 1L;

  public MenuLeiste() {
    // Navigation
    var navigation = new JMenu("Navigation");
    navigation.add(erzeugeAnsichtenEintrag("Startseite", "start"));
    navigation.add(erzeugeAnsichtenEintrag("Themengebiete", "themen"));
    navigation.add(erzeugeAnsichtenEintrag("Aktuelles Spiel", "spiel"));
    navigation.add(erzeugeAnsichtenEintrag("Über das Spiel", "über"));

    // Aktionen
    var aktionen = new JMenu("Aktionen");
    var schließen = new JMenuItem("schließen");
    schließen.setToolTipText("Verlasse das Spiel");
    schließen.addActionListener((event) -> System.exit(0));
    aktionen.add(schließen);

    add(navigation);
    add(aktionen);
  }

  /**
   * Erzeuge einen Menüeintrag der eine Ansicht wechselt.
   *
   * @param beschriftung Der Text der als Menüeintrag zu sehen sein soll.
   * @param anzeigenName Der Namen der Ansicht (z. B. „start“, „spiel“).
   *
   * @return Einen konfigurierten Menüeintrag.
   */
  private JMenuItem erzeugeAnsichtenEintrag(String beschriftung, String anzeigenName) {
    JMenuItem eintrag = new JMenuItem(beschriftung);
    eintrag.addActionListener((event) -> Konfiguration.zeigeAnsicht(anzeigenName));
    return eintrag;
  }

}
