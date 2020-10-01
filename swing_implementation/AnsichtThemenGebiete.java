package swing_implementation;

import javax.swing.JLabel;

public class AnsichtThemenGebiete extends Ansicht {

  public AnsichtThemenGebiete() {
    JLabel überschrift = Konfiguration.erzeugeÜberschrift("Themengebiete");
    add(überschrift);
  }

}
