package fragen_verwaltung;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThemenKatalogTest {

  @Test
  public void methodeGibGebietDurchNummer() throws Exception {
    ThemenKatalog katalog = new ThemenKatalog();
    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(0);
    assertEquals("6. Jahrgangsstufe", gebiet.gibThema());
  }

  @Test
  public void methodeGibAnzahlThemenGebiete() throws Exception {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals(11, katalog.gibAnzahlThemenGebiete());
  }

  @Test
  public void methodeGibGebietTitelDurchNummer() throws Exception {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals("Informatik 6. Jahrgangsstufe", katalog.gibGebietTitelDurchNummer(0));
  }

  @Test
  public void methodeGibPfadDurchNummer() throws Exception {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals("informatik/6_jahrgangsstufe.xml", katalog.gibGebietPfadDurchNummer(0));
  }

}
