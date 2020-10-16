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

  private AktiverText textNeuesSpiel;

  private AktiverText textSpielWiederholen;

  public AnsichtErgebnis() {
    setLayout(null);
    textErgebnis = Aussehen.macheText(400, 300, 500, 50);
    add(textErgebnis);

    textNeuesSpiel = new AktiverText("neues Spiel");
    textNeuesSpiel.setBounds(400, 400, 500, 50);
    textNeuesSpiel.fügeAktionenLauscherHinzu(() -> {
      AnsichtenVerwalter.zeige("start");
    });
    add(textNeuesSpiel);

    textSpielWiederholen = new AktiverText("Spiel wiederholen");
    textSpielWiederholen.setBounds(400, 500, 500, 50);
    textSpielWiederholen.fügeAktionenLauscherHinzu(() -> {
      Spiel spiel = SpielSteuerung.gibSpiel();
      if (spiel != null && spiel.gibDateiPfad() != null) {
        AnsichtenVerwalter.ladeNeuesSpiel(spiel.gibDateiPfad());
      }
    });
    add(textSpielWiederholen);
  }

  @Override
  public void zeige() {
    Spiel spiel = SpielSteuerung.gibSpiel();
    if (spiel == null) {
      textErgebnis.setText("");
    } else if (spiel.istVerloren()) {
      textErgebnis.setText("Du hast leider verloren");
    } else if (spiel.istBeendet()) {
      textErgebnis.setText(String.format("Du hast %s gewonnen!", spiel.gibFormatierteGewinnSumme()));
    } else {
      textErgebnis.setText("Du befindest dich noch im Spiel");
    }

    if (spiel != null) {
      textSpielWiederholen.setVisible(true);
    } else {
      textSpielWiederholen.setVisible(false);
    }
  }

}
