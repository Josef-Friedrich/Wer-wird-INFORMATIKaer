package swing_implementation;

import javax.swing.JLabel;

public class AnsichtÜberProjekt extends Ansicht {

  public AnsichtÜberProjekt() {
    JLabel überschrift = Konfiguration.erzeugeÜberschrift("Über das Projekt");
    add(überschrift);
  }

}
