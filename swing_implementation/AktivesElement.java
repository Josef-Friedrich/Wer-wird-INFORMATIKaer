package swing_implementation;

import javax.swing.JLabel;

/**
 * Die Methode {@link AktivesElement.führeAus} aus diesem Interface wird
 * ausgeführt, wenn man auf die Antwortkachel klickt oder das
 * entsprechende Tastaturkürzel anklickt.
 */
interface Aktion {
  public void führeAus();
}

public class AktivesElement extends JLabel {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  private Aktion aktion;

  public void führeAktionAus() {
    if (aktion != null)
      aktion.führeAus();
  }

  public void fügeAktionenLauscherHinzu(Aktion aktion) {
    this.aktion = aktion;
  }

}
