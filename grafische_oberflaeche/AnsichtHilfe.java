package grafische_oberflaeche;

import ch.aplu.jgamegrid.Location;
import ch.aplu.jgamegrid.TextActor;

public class AnsichtHilfe extends Ansicht {

  private TextActor überschrift;

  public AnsichtHilfe(Spielfeld spielfeld) {
    super(spielfeld);
    überschrift = new TextActor("Hilfe");
    überschrift.hide();
    spielfeld.addActor(überschrift, new Location(1, 1));
  }

  public void zeige() {
    überschrift.show();
  }

  public void entferne() {
    überschrift.hide();
    //überschrift.removeSelf();
    // spielfeld.removeActor(überschrift);
    // spielfeld.act();
    // spielfeld.refresh();
    System.out.println("Entferne Hilfe");
  }
}
