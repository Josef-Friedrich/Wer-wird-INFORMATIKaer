package jgamegrid_implementation;

import ch.aplu.jgamegrid.Location;

public class AnsichtHilfe extends Ansicht {

  private TextAkteur überschrift;

  public AnsichtHilfe(Spielfeld spielfeld) {
    super(spielfeld);
  }

  public void zeige() {
    überschrift = new TextAkteur("Hilfe");
    spielfeld.addActor(überschrift, new Location(1, 1));
  }

}
