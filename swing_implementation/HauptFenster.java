package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HauptFenster extends JFrame {

  private static final long serialVersionUID = 1L;

  private final CardLayout ansichtenWechsler;
  private final JPanel hauptAnsicht;

  public HauptFenster() {
    setJMenuBar(new MenuLeiste());

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

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HauptFenster fenster = new HauptFenster();
      fenster.setVisible(true);
    });
  }
}
