package swing_implementation;

import java.awt.CardLayout;

import java.util.Map;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Verwaltet alle Ansichten, indem die Klasse statischen Zugriff auf die
 * Ansichten erlaubt.
 *
 * Ansichten sind Spezialisierungen der Swing-Klasse {@link javax.swing.JPanel}.
 * Mit Hilfe eines CardLayouts kann zwischen den einzelnen Ansichten
 * umgeschaltet werden.
 */
public class AnsichtenVerwalter {

  /**
   * Wir benutzen das Klartenlayout, da man es dazu verwenden kann, einfach
   * zwischen den einzelnen Ansichten hin- und her zuschalten.
   */
  public static final CardLayout ansichtenWechsler = new CardLayout();

  /**
   * Die Hauptansicht. Sie wird in der Klasse {@link HauptFenster} zu dem
   * Swing-Komponenten hinzugefügt.
   */
  public static final JPanel hauptAnsicht = new JPanel();

  /**
   * Eine Hashmap in der alle Ansichten gespeichert werden. Mit dem Ansichtenname
   * kann auf sie zugegriffen werden.
   */
  public static final Map<String, Ansicht> ansichten = new HashMap<String, Ansicht>();

  /**
   * Initialisiere eine Ansicht. Verhindere, dass eine Ansicht mehrmals
   * initialisiert wird.
   *
   * @param ansichtenName Der Name der Ansicht, unter dem die Ansicht aufgerufen
   *                      werden kann.
   * @param ansicht       Eine Instanz der Ansicht.
   */
  public static void initialisiereAnsicht(String ansichtenName, Ansicht ansicht) {
    if (ansichten.get(ansichtenName) != null)
      return;
    ansichten.put(ansichtenName, ansicht);
    hauptAnsicht.add(ansichtenName, ansicht);
  }

  /**
   * Initialisiere alle Ansichten.
   */
  public static void initialisiereAnsichten() {
    initialisiereAnsicht("start", new AnsichtStartSeite());
    initialisiereAnsicht("spiel", new AnsichtSpiel());
    initialisiereAnsicht("hilfe", new AnsichtHilfe());
    initialisiereAnsicht("ergebnis", new AnsichtErgebnis());
    initialisiereAnsicht("einstellungen", new AnsichtEinstellungen());
  }

  /**
   * Zeige die gewünschte Ansicht.
   *
   * @param ansichtenName Der Name der Ansicht, unter dem die Ansicht aufgerufen
   *                      werden kann.
   */
  public static void zeige(String ansichtenName) {
    ansichtenWechsler.show(hauptAnsicht, ansichtenName);
    Ansicht ansicht = gib(ansichtenName);
    ansicht.zeige();
  }

  /**
   * Gib die gewünschte Ansicht zurück.
   *
   * @param ansichtenName Der Name der Ansicht, unter dem die Ansicht aufgerufen
   *                      werden kann.
   *
   * @return Die Instanz der gewünschten Ansicht.
   */
  public static Ansicht gib(String ansichtenName) {
    return ansichten.get(ansichtenName);
  }

  /**
   * Gib die Ansicht des aktuellen Spiels.
   *
   * Da die Ansicht des aktuellen Spiels sehr häufig verwendet wird, stellte diese
   * Methode eine kleine Hilfe dar, um weniger tippen zu müssen.
   *
   * @return Die Instanz der Klasse {@link AnsichtSpiel}.
   */
  public static AnsichtSpiel gibSpiel() {
    return (AnsichtSpiel) gib("spiel");
  }

  /**
   * Lade ein neues Spiel, indem der relative Pfad zu einem Themengebiet angegeben
   * wird und zeige die Ansicht des aktuellen Spiels.
   *
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public static void ladeNeuesSpiel(String dateiPfad) {
    gibSpiel().starteNeuesSpiel(dateiPfad);
    zeige("spiel");
  }

}
