package grafische_oberflaeche;

import ch.aplu.jgamegrid.*;

public class AnsichtThemen extends Ansicht {

  private TextActor überschrift;

  public AnsichtThemen(Spielfeld spielfeld) {
    super(spielfeld);
    überschrift = new TextActor("Themen");
    überschrift.hide();
    spielfeld.addActor(überschrift, new Location(1, 1));
  }

  public void zeige() {
    überschrift.show();
  }

  public void entferne() {
    überschrift.hide();
    //überschrift.removeSelf();
    //spielfeld.refresh();
    System.out.println("Entferne Themen");
  }
}
