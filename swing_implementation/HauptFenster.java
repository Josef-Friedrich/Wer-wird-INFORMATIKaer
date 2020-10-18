package swing_implementation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JPanel;

import java.awt.event.KeyEvent;

import spiel_logik.Konfiguration;

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
    // Wir brauchen diese Ansicht, um Tastenkürzel zu setzen.
    AnsichtSpiel ansichtSpiel = AnsichtenVerwalter.gibSpiel();
    // Muss fokusierbar sein, da sonst die Tastenkürzel nicht gehen.
    setFocusable(true);
    setVisible(true);

    // https://stackoverflow.com/a/5345036/10193818
    // Wir verwenden globale Tastenkürzel, damit immer gehen auch beim Anklicken der Einstellungen.
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
      @Override
      public boolean dispatchKeyEvent(KeyEvent e) {
        if (KeyEvent.KEY_PRESSED != e.getID()) {
          return false;
        }
        switch (e.getKeyCode()) {

          case KeyEvent.VK_A:
            ansichtSpiel.beantworteFrage(0);
            break;

          case KeyEvent.VK_B:
            ansichtSpiel.beantworteFrage(1);
            break;

          case KeyEvent.VK_C:
            ansichtSpiel.beantworteFrage(2);
            break;

          case KeyEvent.VK_D:
            ansichtSpiel.beantworteFrage(3);
            break;

          case KeyEvent.VK_E:
            AnsichtenVerwalter.zeige("ergebnis");
            break;

          case KeyEvent.VK_H:
            AnsichtenVerwalter.zeige("hilfe");
            break;

          // K: für Konfiguration
          case KeyEvent.VK_K:
            AnsichtenVerwalter.zeige("einstellungen");
            break;

          // N: für neues Spiel
          case KeyEvent.VK_N:
            AnsichtenVerwalter.zeige("start");
            break;

          case KeyEvent.VK_S:
            AnsichtenVerwalter.zeige("spiel");
            break;

          case KeyEvent.VK_T:
            AnsichtenVerwalter.zeige("themen");
            break;

          case KeyEvent.VK_U:
            AnsichtenVerwalter.zeige("über");
            break;

          case KeyEvent.VK_Z:
            Konfiguration.setzeNächstesZahlenformat();
            break;

          case KeyEvent.VK_SPACE:
          case KeyEvent.VK_ENTER:
            ansichtSpiel.zeigeNächsteFrage();
            break;

          default:
            break;
        }
        return false;
      }
    });
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HauptFenster fenster = new HauptFenster();
      fenster.setVisible(true);
    });
  }
}
