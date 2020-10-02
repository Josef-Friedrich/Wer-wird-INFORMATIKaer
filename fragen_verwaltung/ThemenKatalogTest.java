package fragen_verwaltung;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ThemenKatalogTest {

  @Test
  public void methodeGibAnzahlBereiche() {
    ThemenKatalog katalog = new ThemenKatalog();
    assertEquals(16, katalog.gibAnzahlBereiche());
  }

  @Test
  public void methodeGibBereichDurchNummer() {
    ThemenKatalog katalog = new ThemenKatalog();
    List<Map<String, String>> liste = katalog.gibBereichDurchNummer(0);
    assertEquals(34, liste.size());
    Map<String, String> kontainer = liste.get(0);
    assertEquals("Allgemeinwissen", kontainer.get("titel"));
    assertEquals("allgemein/allg02.xml", kontainer.get("pfad"));
  }

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
