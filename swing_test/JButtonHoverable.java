package swing_test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JButtonHoverable extends JButton {

  private ImageIcon imageIconHovered;
  private ImageIcon imageIcon;
  private boolean hoverable;

  public JButtonHoverable(String imageIcon, String imageIconHovered, String text) throws MalformedURLException {
    super(imageIcon);
    this.hoverable = true;
    this.imageIconHovered = loadImage(imageIconHovered);
    this.imageIcon = loadImage(imageIcon);
    this.setText(text);
    this.addHoverMouseListener();
  }

  public JButtonHoverable(String imageIcon, String imageIconHovered) throws MalformedURLException {
    super(imageIcon);

    this.hoverable = true;
    this.imageIconHovered = loadImage(imageIconHovered);
    this.imageIcon = loadImage(imageIcon);
    this.addHoverMouseListener();
  }

  private ImageIcon loadImage(String path) throws MalformedURLException {
    return new ImageIcon(new URL(path));
  }

  public ImageIcon getImageIconHovered() {
    return imageIconHovered;
  }

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  public void setHoverable(boolean hoverable) {
    this.hoverable = hoverable;
  }

  private void addHoverMouseListener() {
    this.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        if (hoverable) {
          JButtonHoverable button = ((JButtonHoverable) e.getSource());
          button.setIcon(button.getImageIconHovered());
        }
      }

      public void mouseExited(MouseEvent e) {
        JButtonHoverable button = ((JButtonHoverable) e.getSource());
        button.setIcon(button.getImageIcon());
      }
    });
  }

  public static void main(String[] args) throws MalformedURLException {
    JButtonHoverable buttonHoverable = new JButtonHoverable(
        "file:/home/jf/git-repositories/fau/wer-wird-informatik-millionaer/BILDER/pfeil-blau.png",
        "file:/home/jf/git-repositories/fau/wer-wird-informatik-millionaer/BILDER/pfeil-gelb.png");
    JFrame meinJFrame = new JFrame();
    meinJFrame.setTitle("JButton Beispiel");
    JPanel panel = new JPanel();

    // JButton wird dem Panel hinzugefügt
    panel.add(buttonHoverable);

    meinJFrame.add(panel);

    // Fenstergröße wird so angepasst, dass
    // der Inhalt reinpasst
    meinJFrame.pack();

    meinJFrame.setVisible(true);
  }
}
