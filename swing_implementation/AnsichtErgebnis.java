package swing_implementation;

import javax.swing.JLabel;

import spiel_logik.Spiel;

public class AnsichtErgebnis extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private JLabel textErgebnis;

  public AnsichtErgebnis() {
    setLayout(null);
    textErgebnis = Aussehen.macheText(20, 20, 500, 50);
    add(textErgebnis);
  }

  @Override
  public void zeige() {
    AnsichtSpiel ansichtSpiel = (AnsichtSpiel) AnsichtenVerwalter.gib("spiel");
    Spiel spiel = ansichtSpiel.gibAktuellesSpiel();
    if (spiel.istVerloren()) {
      textErgebnis.setText("Du hast leider verloren!");
    } else if (spiel.istBeendet()) {
      textErgebnis.setText("Du hast bewonnen!");
    } else {
      textErgebnis.setText("Du befindest dich noch im Spiel");
    }
  }

}
