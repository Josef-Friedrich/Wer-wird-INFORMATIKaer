package swing_implementation;

/**
 * Die Ansicht, die zuerste gezeigt wird, wenn das Spiel gestartet wird.
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

  public AnsichtStartSeite() {
    setLayout(null);
    add(Aussehen.gibLogo());

    int kachelBreite = 500;
    int xKachel = Aussehen.zentriereX(kachelBreite);
    erzeugeKleineKachel("6. Jahrgangsstufe", "/FRAGEN/informatik/6_jahrgangsstufe.xml", xKachel, 500);
    erzeugeKleineKachel("7. Jahrgangsstufe", "/FRAGEN/informatik/7_jahrgangsstufe.xml", xKachel, 600);
  }

  /**
   *
   * @param text      Der Text der Taste erscheinen soll.
   * @param gridx
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   *
   * @return
   */
  private void erzeugeKleineKachel(String text, String dateiPfad, int x, int y) {
    KleineKachel kachel = new KleineKachel(text);

    kachel.fügeAktionenLauscherHinzu(() -> {
      AnsichtenVerwalter.ladeNeuesSpiel(dateiPfad);
    });
    kachel.setLocation(x, y);
    add(kachel);
  }

  public void zeige() {
    SpielSteuerung.musikSpieler.starteEndlos("haupt");
  }

}
