package swing_implementation;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Einfach zweispaltige Tabelle
 */
class Tabelle {
  int x;
  int y;
  int aktuellesY;

  int zeilenAbstand;
  int spaltenBreite1;
  int spaltenBreite2;

  JPanel panel;

  public Tabelle(JPanel panel, int x, int y, int zeilenAbstand, int spaltenBreite1, int spaltenBreite2) {
    this.panel = panel;
    this.zeilenAbstand = zeilenAbstand;
    this.spaltenBreite1 = spaltenBreite1;
    this.spaltenBreite2 = spaltenBreite2;

    this.x = x;
    this.y = y;
    aktuellesY = y;
  }

  private JLabel erzeugeText(String text) {
    JLabel t = new JLabel(text);
    t.setFont(Aussehen.SCHRIFT_NORMAL);
    t.setForeground(Aussehen.FARBE_WEISS);
    return t;
  }

  public void erzeugeZeile(String zelle1, String zelle2) {
    JLabel z1 = erzeugeText(zelle1);
    z1.setBounds(x, aktuellesY, spaltenBreite1, zeilenAbstand);
    panel.add(z1);

    JLabel z2 = erzeugeText(zelle2);
    z2.setBounds(x + spaltenBreite1, aktuellesY, spaltenBreite2, zeilenAbstand);

    panel.add(z2);
    aktuellesY = aktuellesY + zeilenAbstand;
  }
}

public class AnsichtHilfe extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public AnsichtHilfe() {
    setLayout(null);
    JLabel überschrift = Aussehen.macheÜberschrift("Hilfe");
    add(überschrift);

    Tabelle tabelle = new Tabelle(this, 100, 100, 50, 100, 800);
    tabelle.erzeugeZeile("a", "Wähle Antwort A aus");
    tabelle.erzeugeZeile("b", "Wähle Antwort B aus");
    tabelle.erzeugeZeile("c", "Wähle Antwort C aus");
    tabelle.erzeugeZeile("d", "Wähle Antwort D aus");
  }

}
