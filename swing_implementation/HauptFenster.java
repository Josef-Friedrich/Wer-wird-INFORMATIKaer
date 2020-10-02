package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class HauptFenster extends JFrame {

  private static final long serialVersionUID = 1L;

  private final CardLayout ansichtenWechsler;
  private final JPanel hauptAnsicht;

  public HauptFenster() {
    setJMenuBar(baueMenü());

    setTitle("Wer wird INFORMATIKär!");
    setSize(350, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    hauptAnsicht = Konfiguration.hauptAnsicht;
    hauptAnsicht.setPreferredSize(new Dimension(250, 150));
    ansichtenWechsler = Konfiguration.ansichtenWechsler;
    hauptAnsicht.setLayout(ansichtenWechsler);
    hauptAnsicht.setBackground(Konfiguration.FARBE_HINTERGRUND);
    initialisiereAnsichten();

    setLayout(new BorderLayout());
    add(hauptAnsicht, BorderLayout.CENTER);
    pack();
    setVisible(true);
    zeigeAnsicht("start");
  }

  private void initialisiereAnsichten() {
    hauptAnsicht.add("start", new AnsichtStartSeite());
    hauptAnsicht.add("themen", new AnsichtThemenGebiete());
    hauptAnsicht.add("spiel", new AnsichtSpiel());
    hauptAnsicht.add("über", new AnsichtÜberProjekt());
  }

  private void zeigeAnsicht(String ansichtenName) {
    ansichtenWechsler.show(hauptAnsicht, ansichtenName);
  }

  public JMenuBar baueMenü() {
    var menuLeiste = new JMenuBar();

    // Navigation
    var navigation = new JMenu("Navigation");
    // Startseite
    var start = new JMenuItem("Startseite");
    start.addActionListener((event) -> zeigeAnsicht("start"));
    navigation.add(start);
    // Themengebiete
    var themen = new JMenuItem("Themengebiete");
    themen.addActionListener((event) -> zeigeAnsicht("themen"));
    navigation.add(themen);
    // Spiel
    var spiel = new JMenuItem("Aktuelles Spiel");
    spiel.addActionListener((event) -> zeigeAnsicht("spiel"));
    navigation.add(spiel);
    // Über das Spiel
    var ueber = new JMenuItem("Über das Spiel");
    ueber.addActionListener((event) -> zeigeAnsicht("über"));
    navigation.add(ueber);

    // Aktionen
    var aktionen = new JMenu("Aktionen");
    aktionen.setMnemonic(KeyEvent.VK_F);
    var schließen = new JMenuItem("schließen");
    schließen.setMnemonic(KeyEvent.VK_E);
    schließen.setToolTipText("Verlasse das Spiel");
    schließen.addActionListener((event) -> System.exit(0));
    aktionen.add(schließen);

    menuLeiste.add(navigation);
    menuLeiste.add(aktionen);

    return menuLeiste;
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      var ex = new HauptFenster();
      ex.setVisible(true);
    });
  }
}
