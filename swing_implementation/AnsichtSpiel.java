package swing_implementation;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class AnsichtSpiel extends Ansicht {

  private static final long serialVersionUID = 1L;

  JButton antwortA;
  JButton antwortB;
  JButton antwortC;
  JButton antwortD;

  GridBagConstraints layoutEinstellung;

  public AnsichtSpiel() {

    setLayout(new GridBagLayout());

    layoutEinstellung = new GridBagConstraints();

    antwortA = erzeugeTaste(0, 0);
    antwortB = erzeugeTaste(0, 1);
    antwortC = erzeugeTaste(1, 0);
    antwortD = erzeugeTaste(1, 1);
  }

  private JButton erzeugeTaste(int gridx, int gridy) {
    JButton taste = new JButton();
    taste.setPreferredSize(new Dimension(600, 100));
    layoutEinstellung.insets = new Insets(5, 5, 5, 5);
    layoutEinstellung.gridy = gridx;
    layoutEinstellung.gridx = gridy;
    add(taste, layoutEinstellung);
    return taste;
  }

}
