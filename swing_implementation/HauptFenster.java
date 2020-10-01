package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class HauptFenster extends JFrame {

  private final CardLayout ansichtenWechsler;
  private final JPanel hauptAnsicht;

  public HauptFenster() {
    setJMenuBar(baueMenü());

    setTitle("Wer wird INFORMATIKär!");
    setSize(350, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    hauptAnsicht = new JPanel();
    hauptAnsicht.setPreferredSize(new Dimension(250, 150));
    ansichtenWechsler = new CardLayout();
    hauptAnsicht.setLayout(ansichtenWechsler);
    hauptAnsicht.setBackground(Konfiguration.FARBE_HINTERGRUND);
    initialisiereAnsichten();

    setLayout(new BorderLayout());
    add(hauptAnsicht, BorderLayout.CENTER);
    pack();
    setVisible(true);
  }

  private void initialisiereAnsichten() {
    hauptAnsicht.add("themen", new AnsichtThemenGebiete());
    hauptAnsicht.add("über", new AnsichtÜberProjekt());
  }

  private void zeigeAnsicht(String ansichtenName) {
    ansichtenWechsler.show(hauptAnsicht, ansichtenName);
  }

  public JMenuBar baueMenü() {
    var menuLeiste = new JMenuBar();

    var navigation = new JMenu("Navigation");
    var themen = new JMenuItem("Themen");
    themen.addActionListener((event) -> zeigeAnsicht("themen"));
    var ueber = new JMenuItem("Über das Spiel");
    ueber.addActionListener((event) -> zeigeAnsicht("über"));
    navigation.add(themen);
    navigation.add(ueber);

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
