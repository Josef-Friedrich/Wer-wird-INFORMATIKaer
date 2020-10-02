package jgamegrid_implementation;

import java.awt.Color;
import java.awt.Font;

import ch.aplu.jgamegrid.Location;
import fragen_verwaltung.ThemenKatalog;

public class AnsichtThemen extends Ansicht {

  private TextAkteur überschrift;

  public AnsichtThemen(Spielfeld spielfeld) {
    super(spielfeld);
  }

  public void zeige() {
    überschrift = new TextAkteur("Themen");
    spielfeld.addActor(überschrift, new Location(1, 1));

    ThemenKatalog katalog = new ThemenKatalog();

    for (int i = 0; i < katalog.gibAnzahlThemenGebiete(); i++) {
      ReaktiverText text = new ReaktiverText(katalog.gibGebietTitelDurchNummer(i), new Color(2, 2, 2),
          new Color(2, 255, 2), new Color(255, 255, 255, 0), new Font("Sans", 0, 40));
      text.fügeZumSpielfeldHinzu(spielfeld, new Location(6, (i * 2 ) + 10));
    }
  }
}
