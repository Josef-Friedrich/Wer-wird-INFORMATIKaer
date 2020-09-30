package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;
import spiel_logik.Spiel;
import spiel_logik.Frage;

import java.awt.event.KeyEvent;

public class AnsichtThemen extends Ansicht {
  public AnsichtThemen(Spielfeld spielfeld) {
    super(spielfeld);
  }

  public void zeige() {
    spielfeld.addActor(new TextAkteur("Hilfe"), new Location(1, 1));
  }
}
