package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HauptFenster extends JFrame {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt diese statisches Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does
   * not declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public HauptFenster() {
    setJMenuBar(new MenuLeiste());

    setTitle("Wer wird INFORMATIKär!");
    setSize(Aussehen.FENSTER_BREITE, Aussehen.FENSTER_HÖHE);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel hauptAnsicht = AnsichtenVerwalter.hauptAnsicht;
    hauptAnsicht.setPreferredSize(new Dimension(Aussehen.FENSTER_BREITE, Aussehen.FENSTER_HÖHE));
    CardLayout ansichtenWechsler = AnsichtenVerwalter.ansichtenWechsler;
    hauptAnsicht.setLayout(ansichtenWechsler);
    hauptAnsicht.setBackground(Aussehen.FARBE_HINTERGRUND);
    AnsichtenVerwalter.initialisiereAnsichten();

    setLayout(new BorderLayout());
    add(hauptAnsicht, BorderLayout.CENTER);
    pack();
    setVisible(true);
    AnsichtenVerwalter.zeige("über");
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HauptFenster fenster = new HauptFenster();
      fenster.setVisible(true);
    });
  }
}
