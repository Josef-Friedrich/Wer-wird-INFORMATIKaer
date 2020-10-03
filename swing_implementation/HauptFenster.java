package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HauptFenster extends JFrame {

  private static final long serialVersionUID = 1L;

  public HauptFenster() {
    setJMenuBar(new MenuLeiste());

    setTitle("Wer wird INFORMATIKär!");
    setSize(350, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel hauptAnsicht = AnsichtenVerwalter.hauptAnsicht;
    hauptAnsicht.setPreferredSize(new Dimension(250, 150));
    CardLayout ansichtenWechsler = AnsichtenVerwalter.ansichtenWechsler;
    hauptAnsicht.setLayout(ansichtenWechsler);
    hauptAnsicht.setBackground(Konfiguration.FARBE_HINTERGRUND);
    AnsichtenVerwalter.initialisiereAnsichten();

    setLayout(new BorderLayout());
    add(hauptAnsicht, BorderLayout.CENTER);
    pack();
    setVisible(true);
    AnsichtenVerwalter.zeige("start");
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HauptFenster fenster = new HauptFenster();
      fenster.setVisible(true);
    });
  }
}
