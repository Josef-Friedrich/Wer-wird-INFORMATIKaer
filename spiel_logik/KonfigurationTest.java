package spiel_logik;

import static org.junit.Assert.*;
import org.junit.Test;

public class KonfigurationTest {

  @Test
  public void methodeGib() {
    assertEquals(true, Konfiguration.gib("ko"));
    assertEquals(15, Konfiguration.gib("anzahlGeladenerFragen"));
  }

  @Test
  public void methodeSetze() {
    assertEquals(15, Konfiguration.anzahlGeladenerFragen);
    Konfiguration.setze("anzahlGeladenerFragen", 16);
    assertEquals(16, Konfiguration.anzahlGeladenerFragen);
  }
}
