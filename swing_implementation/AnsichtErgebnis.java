package swing_implementation;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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

  /**
   * Der Konstrutor zeigt drei Texte horizontal zentiert und vertikal über die
   * ganze Fensterhöhe verteilt.
   */
  public AnsichtErgebnis() {
    setLayout(null);
    Box rahmen = Box.createVerticalBox();
    rahmen.setBounds(0, 0, Aussehen.FENSTER_BREITE, Aussehen.FENSTER_HÖHE);

    rahmen.add(Box.createVerticalGlue());

    // Text Ergebnis
    textErgebnis = Aussehen.macheText(400, 300, 500, 50);
    rahmen.add(zentriereHorizontal(textErgebnis));

    rahmen.add(Box.createVerticalGlue());

    // Text „neues Spiel“
    textNeuesSpiel = new AktiverText("neues Spiel");
    textNeuesSpiel.fügeAktionenLauscherHinzu(() -> {
      AnsichtenVerwalter.zeige("start");
    });
    textNeuesSpiel.setHorizontalAlignment(SwingConstants.CENTER);
    rahmen.add(zentriereHorizontal(textNeuesSpiel));

    rahmen.add(Box.createVerticalGlue());

    // Text „Spiel wiederholen“
    textSpielWiederholen = new AktiverText("Spiel wiederholen");
    textSpielWiederholen.fügeAktionenLauscherHinzu(() -> {
      Spiel spiel = SpielSteuerung.gibSpiel();
      if (spiel != null && spiel.gibDateiPfad() != null) {
        AnsichtenVerwalter.ladeNeuesSpiel(spiel.gibDateiPfad());
      }
    });
    rahmen.add(zentriereHorizontal(textSpielWiederholen));

    rahmen.add(Box.createVerticalGlue());

    add(rahmen, BorderLayout.SOUTH);
  }

  /**
   * Zentiere die Komponente (z. B. Text) horizontal.
   *
   * @param komponente Eine Swing-Komponente.
   *
   * @return Ein Rahmen der die Komponente enthält und horizontal zentriert.
   */
  private Box zentriereHorizontal(JComponent komponente) {
    Box rahmen = Box.createHorizontalBox();
    rahmen.add(Box.createHorizontalGlue());
    rahmen.add(komponente);
    rahmen.add(Box.createHorizontalGlue());
    return rahmen;
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
