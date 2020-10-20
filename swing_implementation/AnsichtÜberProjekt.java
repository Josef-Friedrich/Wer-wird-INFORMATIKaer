package swing_implementation;

/**
 * Eine Ansicht, in der Hintergrundinformationen über das Spiel gezeigt werden.
 */
public class AnsichtÜberProjekt extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  public AnsichtÜberProjekt() {
    setLayout(null);
    add(Aussehen.macheZentrierteÜberschrift("Über das Projekt"));
  }

}
