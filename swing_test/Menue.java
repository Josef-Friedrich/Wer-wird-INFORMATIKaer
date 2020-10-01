package swing_test;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

public class Menue extends JMenuBar {
  public Menue() {
    var aktionen = new JMenu("Aktionen");
    aktionen.setMnemonic(KeyEvent.VK_F);

    var schließen = new JMenuItem("schließen");
    schließen.setMnemonic(KeyEvent.VK_E);
    schließen.setToolTipText("Verlasse das Spiel");
    schließen.addActionListener((event) -> System.exit(0));

    var themen = new JMenuItem("Themen");
    themen.addActionListener((event) -> System.out.println("Themen"));

    var ueber = new JMenuItem("Über das Spiel");
    ueber.addActionListener((event) -> System.out.println("Über das Spiel"));

    aktionen.add(schließen);
    add(aktionen);
    add(ueber);
    add(themen);
  }

}
