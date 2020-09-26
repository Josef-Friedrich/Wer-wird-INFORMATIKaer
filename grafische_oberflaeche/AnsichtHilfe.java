package grafische_oberflaeche;

import ch.aplu.jgamegrid.Location;

@SuppressWarnings("serial")
public class AnsichtHilfe extends Ansicht {
  public AnsichtHilfe(Spielfeld spielfeld) {
    super(spielfeld);
  }

  public void zeige() {
    spielfeld.addActor(new TextAkteur("Hilfe"), new Location(1, 1));
  }
}
