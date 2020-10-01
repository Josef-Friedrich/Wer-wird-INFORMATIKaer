package swing_test;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class HauptFenster extends JFrame {

  public HauptFenster() {

    initUI();
  }

  private void initUI() {
    setJMenuBar(new Menue());

    setTitle("Wer wird INFORMATIär!");
    setSize(350, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {

    EventQueue.invokeLater(() -> {

      var ex = new HauptFenster();
      ex.setVisible(true);
    });
  }
}
