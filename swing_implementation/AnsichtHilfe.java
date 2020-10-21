package swing_implementation;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Eine einfache zweispaltige Tabelle.
 */
class Tabelle {
  int x;
  int y;
  int aktuellesY;

  int zeilenAbstand;
  int spaltenBreite1;
  int spaltenBreite2;

  JPanel panel;

  /**
   * Standard-Konstruktor für die Tabelle.
   *
   * @param panel          Das übergeordnete JPanel in dem die Tabelle erscheinen
   *                       soll.
   * @param x              Die X-Koordinate der nordwestlichen Ecke der Tabelle.
   * @param y              Die Y-Koordinate der nordwestlichenn Ecke der Tabelle.
   * @param zeilenAbstand  Wie viel Abstand in Pixel zwischen den Zeilen gelassen
   *                       werden soll.
   * @param spaltenBreite1 Wie breit die 1. Spalte ist (in Pixel).
   * @param spaltenBreite2 Wie breit die 2. Spalte ist (in Pixel).
   */
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

/**
 * Diese Ansicht listete die vorhandenen Tastenkürzel auf.
 */
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
    JComponent überschrift = Aussehen.macheZentrierteÜberschrift("Hilfe");
    add(überschrift);

    Tabelle tabelle = new Tabelle(this, 300, 180, 40, 200, 800);
    tabelle.erzeugeZeile("n", "neues Spiel / Startseite");
    tabelle.erzeugeZeile("s", "Aktuelles Spiel");
    tabelle.erzeugeZeile("e", "Ergebnis");
    tabelle.erzeugeZeile("k", "Einstellungen");
    tabelle.erzeugeZeile("h", "Hilfe");

    tabelle.erzeugeZeile("a", "Wähle Antwort A aus");
    tabelle.erzeugeZeile("b", "Wähle Antwort B aus");
    tabelle.erzeugeZeile("c", "Wähle Antwort C aus");
    tabelle.erzeugeZeile("d", "Wähle Antwort D aus");
    tabelle.erzeugeZeile("Leertaste", "nächste Frage");
    tabelle.erzeugeZeile("Return", "nächste Frage");
    tabelle.erzeugeZeile("z", "Zahlenformat ändern");
  }

}
