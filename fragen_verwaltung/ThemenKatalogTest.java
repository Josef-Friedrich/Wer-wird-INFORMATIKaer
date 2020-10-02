package fragen_verwaltung;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThemenKatalogTest {

  @Test
  public void methodeGibAnzahlGebiete() {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals(149, katalog.gibAnzahlGebiete());
  }

  @Test
  public void methodeGibGebietTitelDurchNummer() {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals("Allgemeinwissen", katalog.gibGebietTitelDurchNummer(0));
  }

  @Test
  public void methodeGibPfadDurchNummer() {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals("allgemein/allg02.xml", katalog.gibGebietPfadDurchNummer(0));
  }

  @Test
  public void methodeGibGebietDurchNummer() {
    ThemenKatalog katalog = new ThemenKatalog();
    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(0);
    assertEquals("Allgemeinwissen", gebiet.gibThema());
  }

}
