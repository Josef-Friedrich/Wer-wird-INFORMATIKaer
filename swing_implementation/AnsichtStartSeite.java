package swing_implementation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AnsichtStartSeite extends Ansicht {

  GridBagConstraints layoutEinstellung;

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does
   * not declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public AnsichtStartSeite() {
    layoutEinstellung = new GridBagConstraints();

    setLayout(new GridBagLayout());
    try {
      BufferedImage bild = ImageIO.read(getClass().getResource("/BILDER/logo_klein.png"));
      JLabel picLabel = new JLabel(new ImageIcon(bild));
      layoutEinstellung.gridx = 0;
      add(picLabel, layoutEinstellung);
    } catch (IOException e) {
      e.printStackTrace();
    }

    erzeugeTaste("6. Jahrgangsstufe", "/FRAGEN/informatik/6_jahrgangsstufe.xml", 1);
    erzeugeTaste("7. Jahrgangsstufe", "/FRAGEN/informatik/7_jahrgangsstufe.xml", 2);
  }

  /**
   *
   * @param text Der Text der Taste erscheinen soll.
   * @param gridx
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   *
   * @return
   */
  private JButton erzeugeTaste(String text, String dateiPfad, int gridx) {
    JButton taste = new JButton(text);
    taste.setForeground(Aussehen.FARBE_VIOLETT);
    taste.setPreferredSize(new Dimension(600, 100));
    taste.setFont(Aussehen.SCHRIFT);
    taste.addActionListener((event) -> {
      AnsichtenVerwalter.ladeNeuesSpiel(dateiPfad);
    });
    layoutEinstellung.insets = new Insets(5, 5, 5, 5);
    layoutEinstellung.gridy = gridx;
    add(taste, layoutEinstellung);
    return taste;
  }

}
