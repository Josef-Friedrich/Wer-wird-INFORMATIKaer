package swing_implementation;

import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fragen_verwaltung.ThemenKatalog;

/**
 * Die Klasse {@link MenuLeiste} basiert auf der Swing-Klasse
 * {@link javax.swing.JMenuBar}. Neben einigen statischen Einträgen (z. B
 * Menüeinträge um zwischen den Ansichten zu schalten) enthält die Menüleiste
 * vor allem dynamische Einträge die aus der XML-Datei {@code index.xml}
 * erzeugen werden.
 */
public class MenuLeiste extends JMenuBar {

  /**
   * Das Menü „Themenbereiche“.
   */
  JMenu bereiche;

  private static final long serialVersionUID = 1L;

  public MenuLeiste() {
    // Navigation
    var navigation = new JMenu("Navigation");
    navigation.add(erzeugeAnsichtenEintrag("Startseite", "start"));
    navigation.add(erzeugeAnsichtenEintrag("Themengebiete", "themen"));
    navigation.add(erzeugeAnsichtenEintrag("Aktuelles Spiel", "spiel"));
    navigation.add(erzeugeAnsichtenEintrag("Über das Spiel", "über"));
    add(navigation);

    bereiche = new JMenu("Themenbereiche");
    erzeugeBereichsEinträge();
    add(bereiche);

    // Aktionen
    var aktionen = new JMenu("Aktionen");
    var schließen = new JMenuItem("schließen");
    schließen.setToolTipText("Verlasse das Spiel");
    schließen.addActionListener((event) -> System.exit(0));
    aktionen.add(schließen);

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
    eintrag.addActionListener((event) -> AnsichtenVerwalter.zeige(anzeigenName));
    return eintrag;
  }

  /**
   * Erzeuge dynamisch die Menüs und Menüeinträge für alle im Themenkatalog
   * konfigurierten Themenbereiche und Themengebiete.
   */
  private void erzeugeBereichsEinträge() {
    ThemenKatalog katalog = new ThemenKatalog();
    for (int i = 0; i < katalog.gibAnzahlBereiche(); i++) {
      String name = katalog.gibBereichsNameDurchNummer(i);
      JMenu bereichMenu = new JMenu(name);
      List<Map<String, String>> bereich = katalog.gibBereichDurchNummer(i);
      for (Map<String, String> gebiet : bereich) {
        JMenuItem gebietEintrag = new JMenuItem(gebiet.get("titel"));
        gebietEintrag.addActionListener((event) -> AnsichtenVerwalter.ladeNeuesSpiel("/FRAGEN/" + gebiet.get("pfad")));
        bereichMenu.add(gebietEintrag);
      }
      bereiche.add(bereichMenu);
    }
  }
}
