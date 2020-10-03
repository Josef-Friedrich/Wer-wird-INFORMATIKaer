package swing_implementation;

import java.awt.CardLayout;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Verwaltet alle Ansichten, in dem die Klasse statischen Zugriff auf sie
 * erlaubt.
 *
 * Ansichten sind Spezialisierungen der Swing Klasse {@link javax.swing.JPanel}.
 * Mit Hilfe eines CardLayouts können zwischen den einzelnen Ansichten
 * umgeschaltet werden.
 */
public class AnsichtenVerwalter {

  public static final CardLayout ansichtenWechsler = new CardLayout();

  public static final JPanel hauptAnsicht = new JPanel();

  public static final Map<String, Ansicht> ansichten = new HashMap<String, Ansicht>();

  /**
   * Initialisiere eine Ansicht. Verhindere, dass eine Ansicht mehrmals
   * initialisiert wird.
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
    initialisiereAnsicht("themen", new AnsichtThemenGebiete());
    initialisiereAnsicht("spiel", new AnsichtSpiel());
    initialisiereAnsicht("über", new AnsichtÜberProjekt());
  }

  public static void zeige(String ansichtenName) {
    ansichtenWechsler.show(hauptAnsicht, ansichtenName);
  }

  public static Ansicht gib(String ansichtenName) {
    return ansichten.get(ansichtenName);
  }

  /**
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public static void ladeNeuesSpiel(String dateiPfad) {
    AnsichtSpiel ansichtSpiel = (AnsichtSpiel) gib("spiel");
    ansichtSpiel.starteNeuesSpiel(dateiPfad);
    zeige("spiel");
  }

}
