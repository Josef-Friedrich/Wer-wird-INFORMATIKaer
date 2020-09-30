package kommando_zeile;

import spiel_logik.Spiel;
import spiel_logik.Frage;
import spiel_logik.FragenListe;
import spiel_logik.ListenElement;

import fragen_verwaltung.ThemenGebiet;
import fragen_verwaltung.ThemenKatalog;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Enthält die main-Methode für das Kommandozeilen-Interface (cli = command line
 * interface) sowie einige statische Methoden um den Code etwas zu
 * strukturieren.
 */
public class KommandoZeile {

  /**
   * Zeigt eine Antwort in der Kommandozeile.
   *
   * @param frage     Eine Instanz der aktuellen Frage.
   * @param antworten Ein Feld mit den zufällig gemischten Antworttexten.
   * @param antwortNr Die Indexnummer der Antwort, die gezeigt werden soll (0, 1,
   *                  2 oder 3).
   */
  private void zeigeAntwort(Frage frage, String[] antworten, int antwortNr) {
    System.out.println(String.format("  %s: %s", Frage.konvertiereAntwortNummer(antwortNr), antworten[antwortNr]));
  }

  /**
   *
   * @param spiel Eine Instanz des aktuellen Spiels.
   * @param frage Eine Instanz der aktuellen Frage.
   */
  private void stelleFrageAlsTextausgabe(Spiel spiel, Frage frage) {
    frage.mischeAntworten();
    String[] antworten = frage.gibAntworten();
    System.out
        .println(Farbe.gelb(String.format("\n\nFrage Nr. %s: %s\n", spiel.gibFragenNummer(), frage.gibFragenText())));
    for (int i = 0; i < antworten.length; i++) {
      zeigeAntwort(frage, antworten, i);
    }
  }

  /**
   * @param scanner
   *
   * @return
   */
  private int frageNachAntwort(Scanner scanner) {
    System.out.print("\nDeine Antwort (a, b, c, d): ");
    String auswahl = scanner.next();
    return Frage.konvertiereAntwortBuchstabe(auswahl);
  }

  /**
   * Frage in der Kommandozeile, ob weitergespielt werden soll oder nicht. Es
   * können sowohl Klein- als auch Großbuchstaben eingegeben werden. Wird nichts
   * eingegeben, dann bedeutet das ja.
   *
   * @param scanner
   *
   * @return
   */
  private boolean frageNachWeiterspielen() throws IOException {
    System.out.print("Weiter spielen, ja oder nein? (J, n): ");
    // https://stackoverflow.com/a/1323799/10193818
    // Hier kann nicht der Scanner verwendet werden, weil wir die
    // Möglichkeite haben wollen eine Standardeingabe zu bekommen, d. h.
    // gibt der Benutzer nichts ein und drückt Return, dann soll das für
    // Ja stehen.
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String eingabe = input.readLine();
    eingabe = eingabe.trim();
    eingabe = eingabe.toLowerCase();
    if (eingabe.equals("") || eingabe.equals("j")) {
      return true;
    }
    return false;
  }

  /**
   * @param frage
   *
   * @return
   */
  private void zeigeFragenErgebnis(Spiel spiel, Frage frage) throws Exception {
    String buchstabeRichtig = frage.konvertiereAntwortNummer(frage.gibRichtigeAntwort());
    String buchstabeAntwort = frage.konvertiereAntwortNummer(frage.gibGegebeneAntwort());
    if (frage.istRichtigBeantwortet()) {
      System.out.println(Farbe.grün(String.format("Die Antwort %s war richtig!", buchstabeAntwort)));
      System.out.println(String.format("Deine momentane Gewinnsumme: %s", Farbe.blau(spiel.gibGewinnSumme() + " €")));
    } else {
      System.out.print(Farbe.rot(String.format("Die Antwort %s war falsch! ", buchstabeAntwort)));
      System.out.println(String.format("Richtig wäre Antwort %s gewesen: %s", Farbe.grün(buchstabeRichtig),
          Farbe.grün(frage.gibRichtigeAntwortText())));
    }
  }

  /**
   * Zeige ein Logo in ASCII Kunst.
   */
  private static void zeigeASCIILogo() {
    String[] zeilen = { "                         ", "        R    W   I       ", "    E               R    ",
        "  W                   D  ", "                         ", "                         ",
        " I n f o r m a t i k Ä R ", "                         ", "                         ",
        "  W                   D  ", "    E                R   ", "        R    W    I      ",
        "                         ", };
    System.out.println(String.join("\n", zeilen));
  }

  /**
   * Zeige eine Meldung in der Kommando-Zeile am Spiel-Ende. Zeige die
   * Gewinnsumme.
   */
  private void zeigeSpielEnde(Spiel spiel) {
    if (spiel.istVerloren()) {
      System.out.println("Du hast leider verloren!");
    } else {
      System.out.println(String.format("Gratulation! Du hast %s gewonnen!", Farbe.blau(spiel.gibGewinnSumme() + " €")));
    }
  }

  /**
   * Liste die Themengebiete auf.
   *
   * @param katalog Der Themenkatalog.
   */
  private void zeigeThemenGebiete(ThemenKatalog katalog) {
    System.out.println("Wähle ein Themengebiet aus:\n");

    for (int i = 0; i < katalog.gibAnzahlThemenGebiete(); i++) {
      System.out.println(String.format(" %3d. %s", i + 1, katalog.gibGebietTitelDurchNummer(i)));
    }

    System.out.print("\nGib die Nummer des Themen-Gebiets ein: ");
  }

  /**
   * Wähle ein Themengebiet aus.
   *
   * @param scanner Eine Instanz der Scanner-Klasse.
   * @param katalog Der Themenkatalog.
   *
   * @return Die Indexnummer des Themengebiets beginnend bei 0.
   */
  private int wähleThemenGebietAus(Scanner scanner, ThemenKatalog katalog) {
    int gebietsNummer = scanner.nextInt() - 1;

    System.out.println(String.format("Du hast das Themengebiet „%s“ ausgewählt. Das ist eine sehr gute Wahl!",
        Farbe.grün(katalog.gibGebietTitelDurchNummer(gebietsNummer))));
    return gebietsNummer;
  }

  private void zeigeBeantworteteFragen(FragenListe fragen) {
    System.out.println("\nDeine beantworteten Fragen:\n");
    ListenElement datenKnoten = fragen.gibKopf();
    while (datenKnoten != null) {
      Frage frage = datenKnoten.gibFrage();
      if (frage != null) {
        System.out.println(frage.gibFragenText());
      }
      datenKnoten = datenKnoten.gibNächstes();
    }
  }

  public KommandoZeile() throws Exception, IOException {
    System.out.println("Willkommen bei „Wer wird INFORMATIKär (INFORMATIK-Millionär)");
    zeigeASCIILogo();
    ThemenKatalog katalog = new ThemenKatalog();
    zeigeThemenGebiete(katalog);
    Scanner scanner = new Scanner(System.in);

    int gebietsNummer = wähleThemenGebietAus(scanner, katalog);

    Spiel spiel = new Spiel();

    ThemenGebiet gebiet = katalog.gibGebietDurchNummer(gebietsNummer);
    gebiet.leseFragenInsSpiel(spiel);

    boolean nochImSpiel = true;

    while (nochImSpiel) {
      Frage frage = spiel.gibNächsteFrage();
      stelleFrageAlsTextausgabe(spiel, frage);
      int antwort = frageNachAntwort(scanner);
      boolean richtig = spiel.beantworteFrage(antwort);
      zeigeFragenErgebnis(spiel, frage);
      if (spiel.gibAnzahlUnbeantworterFragen() == 0) {
        nochImSpiel = false;
      } else if (richtig) {
        nochImSpiel = frageNachWeiterspielen();
      } else {
        nochImSpiel = richtig;
      }
    }

    zeigeSpielEnde(spiel);
    zeigeBeantworteteFragen(spiel.gibBeantworteteFragen());

    scanner.close();
  }
}
