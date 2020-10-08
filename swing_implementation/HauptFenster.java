package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HauptFenster extends SpielRahmen {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public HauptFenster() {
    setJMenuBar(new MenuLeiste());

    JPanel hauptAnsicht = AnsichtenVerwalter.hauptAnsicht;
    hauptAnsicht.setPreferredSize(new Dimension(Aussehen.FENSTER_BREITE, Aussehen.FENSTER_HÖHE));
    CardLayout ansichtenWechsler = AnsichtenVerwalter.ansichtenWechsler;
    hauptAnsicht.setLayout(ansichtenWechsler);
    hauptAnsicht.setBackground(Aussehen.FARBE_HINTERGRUND);
    AnsichtenVerwalter.initialisiereAnsichten();

    setLayout(new BorderLayout());
    add(hauptAnsicht, BorderLayout.CENTER);
    AnsichtenVerwalter.zeige("start");
    pack();

    setFocusable(true);
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_N:
            AnsichtenVerwalter.zeige("start");
            break;

          case KeyEvent.VK_H:
            AnsichtenVerwalter.zeige("hilfe");
            break;

          case KeyEvent.VK_T:
            AnsichtenVerwalter.zeige("themen");
            break;

          case KeyEvent.VK_U:
            AnsichtenVerwalter.zeige("über");
            break;

          case KeyEvent.VK_S:
            AnsichtenVerwalter.zeige("spiel");
            break;

          case KeyEvent.VK_E:
            AnsichtenVerwalter.zeige("ergebnis");
            break;

          default:
            break;
        }
      }
    });

    setVisible(true);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HauptFenster fenster = new HauptFenster();
      fenster.setVisible(true);
    });
  }
}
