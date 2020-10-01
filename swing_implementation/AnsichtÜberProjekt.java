package swing_implementation;

import javax.swing.JLabel;

public class AnsichtÜberProjekt extends Ansicht {

  private static final long serialVersionUID = 1L;

  public AnsichtÜberProjekt() {
    JLabel überschrift = Konfiguration.erzeugeÜberschrift("Über das Projekt");
    add(überschrift);
  }

}
