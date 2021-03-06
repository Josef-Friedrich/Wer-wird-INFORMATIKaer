package spiel_logik;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Field;

public class SpielTest {

  private Spiel spiel;

  @Before
  public void erzeugeFrischesTestSpiel() {
    spiel = new Spiel();

    String[][] fragen = {
      {"Frage1", "richtig", "falsch1", "falsch2", "falsch3", "1"},
      {"Frage2", "richtig", "falsch1", "falsch2", "falsch3", "2"},
      {"Frage3", "richtig", "falsch1", "falsch2", "falsch3", "3"},
      {"Frage4", "richtig", "falsch1", "falsch2", "falsch3", "4"},
      {"Frage5", "richtig", "falsch1", "falsch2", "falsch3", "5"},
      {"Frage6", "richtig", "falsch1", "falsch2", "falsch3", "1"},
      {"Frage7", "richtig", "falsch1", "falsch2", "falsch3", "2"},
      {"Frage8", "richtig", "falsch1", "falsch2", "falsch3", "3"},
      {"Frage9", "richtig", "falsch1", "falsch2", "falsch3", "4"},
      {"Frage10", "richtig", "falsch1", "falsch2", "falsch3", "5"},
    };

    spiel.fügeFragenAlsFeldEin(fragen);
  }

  @Test
  public void methodeIstAntwortRichtig() {
    Frage frage = spiel.gibNächsteFrage();
    assertTrue(spiel.istAntwortRichtig(frage.gibRichtigeAntwort()));
  }

  @Test
  public void methodeGibFragenNummer() {
    assertEquals(spiel.gibFragenNummer(), 0);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibFragenNummer(), 1);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibFragenNummer(), 2);
  }

  @Test
  public void methodeGibAnzahlUnbeantworterFragen() {
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 10);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 9);
    spiel.gibNächsteFrage();
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 8);
  }

  @Test
  public void methodeBeantworteFrage() {
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 10);
    assertEquals(spiel.gibAnzahlBeantworterFragen(), 0);

    spiel.gibNächsteFrage();
    spiel.beantworteFrage(0);
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 9);
    assertEquals(spiel.gibAnzahlBeantworterFragen(), 1);

    spiel.gibNächsteFrage();
    spiel.beantworteFrage(0);
    assertEquals(spiel.gibAnzahlUnbeantworterFragen(), 8);
    assertEquals(spiel.gibAnzahlBeantworterFragen(), 2);
  }

  @Test
  public void methodeGibGewinnSumme() throws NoSuchFieldException, IllegalAccessException {
    assertEquals(spiel.gibGewinnSumme(), 0, 0);
    Field anzahl = Spiel.class.getDeclaredField("anzahlRichtigerFragen");
    anzahl.setAccessible(true);

    anzahl.set(spiel, 1);
    assertEquals(spiel.gibGewinnSumme(), 50, 0);

    anzahl.set(spiel, 2);
    assertEquals(spiel.gibGewinnSumme(), 100, 0);

    anzahl.set(spiel, 15);
    assertEquals(spiel.gibGewinnSumme(), 1000000, 0);

    anzahl.set(spiel, 16);
    assertEquals(spiel.gibGewinnSumme(), 2000000, 0);
  }

  @Test
  public void methodeLadeThemenGebiet() {
    Spiel spiel = new Spiel();
    spiel.ladeThemenGebiet("/FRAGEN/test/03_fragen.xml");
    assertEquals(3, spiel.gibAnzahlUnbeantworterFragen());
    assertEquals("Test", spiel.gibThemenBereich());
    assertEquals("3 Test-Fragen", spiel.gibThemenGebiet());
  }

  @Test
  public void methodeGibAnzahlFragen() {
    assertEquals(10, spiel.gibAnzahlFragen());
  }

  @Test
  public void getterSetterThemenBereich() {
    Spiel spiel = new Spiel();
    spiel.setzeThemenBereich("test");
    assertEquals("test", spiel.gibThemenBereich());
  }

  @Test
  public void getterSetterThemenGebiet() {
    Spiel spiel = new Spiel();
    spiel.setzeThemenGebiet("test");
    assertEquals("test", spiel.gibThemenGebiet());
  }

  @Test
  public void methodeIstVerloren() {
    Spiel spiel = new Spiel();
    assertEquals(false, spiel.istVerloren());
  }

  @Test
  public void methodeIstBeendet() {
    Spiel spiel = new Spiel();
    assertEquals(false, spiel.istBeendet());
    spiel.beende();
    assertEquals(true, spiel.istBeendet());
  }

  @Test
  public void methodeIstAktuelleFrageBeantwortet() {
    spiel.gibNächsteFrage();
    assertEquals(false, spiel.istAktuelleFrageBeantwortet());
    spiel.beantworteFrage(0);
    assertEquals(true, spiel.istAktuelleFrageBeantwortet());
  }

}
