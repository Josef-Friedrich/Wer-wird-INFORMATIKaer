package swing_implementation;

/**
 * Die Ansicht, die zuerst gezeigt wird, wenn das Spiel gestartet wird.
 */
public class AnsichtStartSeite extends Ansicht {

  /**
   * Eine {@link serialVersionUID} wird als Versionsnummer bei der Serialisation
   * automatisch jeder Klasse hinzugefügt, die das Interface {@link Serializable}
   * implementiert. Fehlt dieses statische Attribut zeigt Visual Studio Code
   * beispielsweise diese Warnmeldung an: „The serializable class ... does not
   * declare a static final serialVersionUID field of type longJava(536871008)“
   */
  private static final long serialVersionUID = 1L;

  /**
   * Der Konstruktor initialisiert die grafischen Elemente der Ansicht.
   */
  public AnsichtStartSeite() {
    setLayout(null);
    add(Aussehen.gibLogo());

    int kachelBreite = 500;
    int xKachel = Aussehen.zentriereX(kachelBreite);
    erzeugeKleineKachel("6. Jahrgangsstufe", "/FRAGEN/informatik/6_jahrgangsstufe.xml", xKachel, 500);
    erzeugeKleineKachel("7. Jahrgangsstufe", "/FRAGEN/informatik/7_jahrgangsstufe.xml", xKachel, 600);
  }

  /**
   * Erzeuge eine kleine Kachel.
   *
   * @param text      Der Text der Taste erscheinen soll.
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   * @param x         Die X-Koordinate der nordwestlichen Ecke der kleinen Kachel.
   * @param y         Die y-Koordinate der nordwestlichen Ecke der kleinen Kachel.
   */
  private void erzeugeKleineKachel(String text, String dateiPfad, int x, int y) {
    KleineKachel kachel = new KleineKachel(text);

    kachel.fügeAktionenLauscherHinzu(() -> {
      AnsichtenVerwalter.ladeNeuesSpiel(dateiPfad);
    });
    kachel.setLocation(x, y);
    add(kachel);
  }

  /**
   * Spiele die Hauptmelodie, wenn die Ansicht „Startseite“ gezeigt wird.
   */
  public void zeige() {
    SpielSteuerung.musikSpieler.starteEndlos("haupt");
  }

}
