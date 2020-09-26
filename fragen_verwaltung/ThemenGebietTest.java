package fragen_verwaltung;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThemenGebietTest {

  @Test
  public void konstruktor() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/musik/musik01.xml");
    assertEquals("Musik", gebiet.gibFach());
  }

  @Test
  public void methodeGibFach() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/informatik/6_jahrgangsstufe.xml");
    assertEquals("Informatik", gebiet.gibFach());
  }

  @Test
  public void methodeGibAutor() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/informatik/6_jahrgangsstufe.xml");
    assertEquals("Team Nürnberg", gebiet.gibAutor());
  }

  @Test
  public void methodeGibFragenAnzahl() throws Exception {
    ThemenGebiet gebiet = new ThemenGebiet("/FRAGEN/informatik/6_jahrgangsstufe.xml");
    assertEquals(30, gebiet.gibAnzahlFragen());
  }

}
