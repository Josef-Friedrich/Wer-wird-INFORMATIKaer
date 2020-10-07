package swing_implementation;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;


public class AktiverText extends JLabel {

  public AktiverText(String text) {
    setText(text);
    setFont(Aussehen.SCHRIFT);
    setForeground(Aussehen.FARBE_VIOLETT);

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        setForeground(Aussehen.FARBE_ROT);
      }

      public void mouseEntered(MouseEvent evt) {
        setForeground(Aussehen.FARBE_ORANGE);
      }

      public void mouseExited(MouseEvent evt) {
        setForeground(Aussehen.FARBE_VIOLETT);
      }
    });

  }
}
