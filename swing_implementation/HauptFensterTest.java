package swing_implementation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;

public class HauptFensterTest {

  public static void main(String[] args) {
    JFrame testRahmen = new JFrame("Teste das Hauptfenster");
    testRahmen.setLayout(null);
    testRahmen.setResizable(false);
    testRahmen.setSize(1024, 768);

    JPanel panel1 = new JPanel();
    panel1.setBackground(Aussehen.FARBE_RICHTIG);
    panel1.setLayout(null);
    panel1.setSize(800, 600);
    JLabel text1 = new JLabel("Text 1");
    text1.setBounds(0, -10, 100, 50);
    JLabel text2 = new JLabel("Text 2");
    text2.setBounds(300, 300, 100, 50);
    panel1.add(text1);
    panel1.add(text2);
    panel1.setLocation(100, 100);

    JPanel panel2 = new JPanel();
    panel2.setBackground(Aussehen.FARBE_FALSCH);
    panel2.setSize(800, 600);
    panel2.setLocation(100, 100);

    testRahmen.add(panel2);
    testRahmen.add(panel1);

    testRahmen.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
          panel1.setVisible(true);
          panel2.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
          panel1.setVisible(false);
          panel2.setVisible(true);
        }
      }
    });

    testRahmen.setVisible(true);
  }

}
