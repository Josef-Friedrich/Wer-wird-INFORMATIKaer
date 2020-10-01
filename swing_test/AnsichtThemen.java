package swing_test;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class AnsichtThemen extends JPanel {

  public AnsichtThemen() {
    setBackground(Color.YELLOW);

    JLabel jlabel = new JLabel("Themen");
    jlabel.setFont(new Font("Verdana", 1, 20));
    add(jlabel);
  }

}
