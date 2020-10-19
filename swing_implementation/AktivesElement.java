package swing_implementation;

import javax.swing.JLabel;

/**
 * Die Methode {@link AktivesElement.führeAus} aus diesem Interface wird
 * ausgeführt, wenn man auf die Antwortkachel klickt oder das entsprechende
 * Tastaturkürzel anklickt.
 */
interface Aktion {
  public void führeAus();
}

/**
 * Diese Klasse stellt eine eigene Aktionenverwaltung bereit (ähnlich den
 * ActionListeners). Klassen, die diese Klasse erben, können eine Aktion
 * registieren {@link fügeAktionenLauscherHinzu}. Diese kann dann
 * über die Methode {@link führeAktionAus} ausgeführt werden. Die
 * Klassen {@link AktiverText} und {@link Taste} mit allen Unterklassen erben
 * diese Klasse.
 */
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

  /**
   * Führe die registrierte Aktion aus. Zur Registrierung benutze
   * {@link fügeAktionenLauscherHinzu}.
   */
  public void führeAktionAus() {
    if (aktion != null)
      aktion.führeAus();
  }

  /**
   * Füge einen Lauschen hinzu, die nach Aktionen ausschaut hält (eigener
   * ActionListener).
   *
   * @param aktion Eine Instanz des Interfaces {@link Aktion}
   */
  public void fügeAktionenLauscherHinzu(Aktion aktion) {
    this.aktion = aktion;
  }

}
