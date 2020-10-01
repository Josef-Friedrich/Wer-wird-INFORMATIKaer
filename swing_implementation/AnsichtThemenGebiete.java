package swing_implementation;

import javax.swing.JLabel;

public class AnsichtThemenGebiete extends Ansicht {

  private static final long serialVersionUID = 1L;

  public AnsichtThemenGebiete() {
    JLabel überschrift = Konfiguration.erzeugeÜberschrift("Themengebiete");
    add(überschrift);
  }

}
