package spiel_logik;

import fragen_verwaltung.ThemenGebiet;

/**
 * Die Klasse {@link Spiel} repräsentiert ein aktuell laufendes Spiel.
 *
 * Die Klasse {@link Spiel} ist die Hauptklasse im paket {@link spiel_logik}.
 * Sie kann in den grafischen Implementierung verwendet werden um die Spiellogik
 * abzubilden.
 *
 * In ein Spiel werden zu Beginn Fragen in die einfach verkettete Liste
 * {@link unbeantworteteFragen} geladen. Mit der Methode {@link gibNächsteFrage}
 * wird eine Frage entnommen, die dann mit der Methode {@link beantworteFrage}
 * beantwortet werden kann. Ist die Frage beantwortet wird sie aus der Liste
 * {@link unbeantworteteFragen} entfernt und in die Liste
 * {@link beantworteteFragen} gesetzt.
 */
public class Spiel {

  /**
   * Die Gewinnsummen. Dieses Spiel zeigt für die ersten 15 Fragenn die
   * Gewinnsummen wie in der deutschen Fernsehsendung an. Die Gewinnsumme wird
   * nicht immer verdoppelt, sondern manchmal ein Betrag gewählt, sodass am Ende 1
   * Million gewonnen werden. Deshalb sind die Summen hier als Feld aufgelistet.
   */
  private int[] gewinnSummen = { 50, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 500000,
      1000000 };

  /**
   * Die aktuelle Fragenummer beginnend bei 1.
   */
  private int frageNummer = 0;

  /**
   * Die aktuelle Frage, die gerade beantwortet wird.
   */
  private Frage aktuelleFrage;

  /**
   * Die aktuelle Schwierigkeit der ausgewählten Fragen. Die Schwierigkeit der
   * Fragen steigt. 1 ist die leichteste Schwierigkeitsstufe. 5 ist schwer.
   */
  private int schwierigkeit = 1;

  /**
   * Eine Liste der zu unbeantwortenden Fragen. Wird eine Frage beantwortet wird
   * sind in die Liste {@link beantworteteFragen} hinzugefügt.
   */
  private FragenListe unbeantworteteFragen;

  /**
   * Eine Liste der beantworteten Fragen. Die Liste ist nützlich um eine Übersicht
   * über die bereits gegeben Antworten zu erzeugen.
   */
  private FragenListe beantworteteFragen;

  /**
   * Wenn eine Frage falsch beantwortet wird, gilt das Spiel als verloren.
   */
  private boolean verloren = false;

  /**
   * Man kann ein Spiel vorzeitig beenden und die bisherige Gewinnsumme mitnehmen.
   */
  private boolean beendet = false;

  /**
   * Die Anzahl der ins Spiel geladenen Fragen. Die Summe der beantworteten Fragen
   * und der unbeantworteten Fragen sollten diese Zahl ergeben.
   */
  private int anzahlFragen = 0;

  /**
   * Die Anzahl der richtig beantworteten Fragen.
   */
  private int anzahlRichtigerFragen = 0;

  /**
   * Der Dateipfad einer XML-Datei. Wir speichern ihn, damit wir nochmal dasselbe
   * Spiel starten können.
   */
  private String dateiPfad;

  /**
   * Der Themenbereich des aktuellen Spiels.
   */
  private String themenBereich;

  /**
   * Das Themengebiet des aktuellen Spiels.
   */
  private String themenGebiet;

  /**
   * Dieser Konstruktor startet ein neues Spiel ohne Fragen. Es müssen erst Fragen
   * ins Spiel geladen werden.
   */
  public Spiel() {
    unbeantworteteFragen = new FragenListe();
    beantworteteFragen = new FragenListe();
  }

  /**
   * Dieser Konstruktor lädt gleich zum Start des neuen Spiels Fragen.
   *
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public Spiel(String dateiPfad) {
    this();
    ladeThemenGebiet(dateiPfad);
  }

  /**
   * Gib die Anzahl der ins Spiel geladenen Fragen.
   *
   * @return Gib die Anzahl der ins Spiel geladenen Fragen.
   */
  public int gibAnzahlFragen() {
    return anzahlFragen;
  }

  /**
   * Erzeuge eine neue Frage anhand von mehreren String-Argumenten und füge diese
   * Frage zufällig ein. Die Argumente können direkt aus dem CSVLeser eingelesen
   * werden.
   *
   * @param fragenText      Der Text der Frage (Der eigentliche Fragensatz).
   * @param richtigeAntwort Die richtige Antwort.
   * @param falscheAntwort1 Die falsche Antwort Nr. 1.
   * @param falscheAntwort2 Die falsche Antwort Nr. 2.
   * @param falscheAntwort3 Die falsche Antwort Nr. 3.
   * @param schwierigkeit   Die Schwierigkeit (1-5).
   */
  public void erzeugeFrage(String fragenText, String richtigeAntwort, String falscheAntwort1, String falscheAntwort2,
      String falscheAntwort3, String schwierigkeit) {
    unbeantworteteFragen.erzeugeFrage(fragenText, richtigeAntwort, falscheAntwort1, falscheAntwort2, falscheAntwort3,
        schwierigkeit);

    anzahlFragen++;
  }

  /**
   * Füge mehrere Fragen zufällig als zweidimensionales Feld ein.
   *
   * Die Methode wird an die gleichnamige Methode der Klasse Jahrgangsstufe
   * weitergegeben.
   *
   * @param fragen Mehrere Fragen als zweidimensionales Feld.
   */
  public void fügeFragenAlsFeldEin(String[][] fragen) {
    unbeantworteteFragen.fügeFragenAlsFeldEin(fragen);
    anzahlFragen = unbeantworteteFragen.gibAnzahlFragen() + beantworteteFragen.gibAnzahlFragen();
  }

  /**
   * Entnimm eine Frage. Diese Methode wird an die Klasse {@link FragenListe}
   * weitergeleitet.
   *
   * @return Die nächste Frage
   */
  public Frage gibNächsteFrage() {
    aktuelleFrage = unbeantworteteFragen.entnimmFrage(schwierigkeit);
    schwierigkeit++;
    frageNummer++;
    return aktuelleFrage;
  }

  /**
   * Überprüfe, ob die Antwort zur aktuellen Frage richtig oder flasch ist.
   *
   * @param antwortNummer Eine Antwortnummer (0 = A, 1 = B, 2 = C, 3 = D).
   *
   * @return Wahr, wenn die Frage richtig beantwortete wurde.
   */
  public boolean istAntwortRichtig(int antwortNummer) {
    if (aktuelleFrage.gibRichtigeAntwort() == antwortNummer) {
      return true;
    }
    return false;
  }

  /**
   * Gib eine Liste der bereits beantworteten Fragen. Diese getter-Methode kann
   * beispielsweise dazu verwendet werden, um am Ende des Spiels eine Übersicht
   * über die bisherigen Fragen anzuzeigen.
   *
   * @return Eine Liste der bereits beantworteten Fragen.
   */
  public FragenListe gibBeantworteteFragen() {
    return beantworteteFragen;
  }

  /**
   * Gib die aktuelle Frage.
   *
   * @return Die aktuelle Frage.
   */
  public Frage gibAktuelleFrage() {
    return aktuelleFrage;
  }

  /**
   * Beantworte die aktuelle Frage.
   *
   * @param antwortNummer Eine Antwortnummer (0 = A, 1 = B, 2 = C, 3 = D).
   *
   * @return Wahr, wenn die Frage richtig beantwortet wurde.
   */
  public boolean beantworteFrage(int antwortNummer) {
    beantworteteFragen.fügeHintenEin(aktuelleFrage);
    boolean richtig = aktuelleFrage.beantworteFrage(antwortNummer);
    if (richtig)
      anzahlRichtigerFragen++;
    if (Konfiguration.ko)
      verloren = !richtig;
    if (verloren)
      beendet = true;

    if (gibFragenNummer() == gibAnzahlFragen()) {
      beendet = true;
    }
    return richtig;
  }

  /**
   * Gib die aktuelle Fragennummer zurück. 1 ist die erste Fragennummer und so
   * weiter.
   *
   * @return Die Fragennummer. 1 ist die erste Fragennummer.
   */
  public int gibFragenNummer() {
    return frageNummer;
  }

  /**
   * Gib die Anzahl der noch unbeantworteten Fragen, die ins Spiel geladen wurden
   * zurück.
   *
   * @return Die Anzahl der noch unbeantworteten Fragen.
   */
  public int gibAnzahlUnbeantworterFragen() {
    return unbeantworteteFragen.gibAnzahlFragen();
  }

  /**
   * Gib die Anzahl der beantworteten Fragen zurück.
   *
   * @return Die Anzahl der beantworteten Fragen.
   */
  public int gibAnzahlBeantworterFragen() {
    return beantworteteFragen.gibAnzahlFragen();
  }

  /**
   * Gib die aktuelle Gewinnsumme aus.
   *
   * @return Die aktuelle Gewinnsumme.
   */
  public long gibGewinnSumme() {
    if (anzahlRichtigerFragen == 0) {
      return 0;
    } else if (anzahlRichtigerFragen <= 15) {
      return gewinnSummen[anzahlRichtigerFragen - 1];
    } else {
      // Ist eine Million erreicht (bei der 15 Frage) verdoppeln wir
      // bei jeder neuen Frage:
      // 16. Frage 2.000.000
      // 17. Frage 4.000.000
      int potenz = anzahlRichtigerFragen - 15;
      return Math.round(1000000 * Math.pow(2, potenz));
    }
  }

  /**
   * Gib die formatiere Gewinnsumme mit Währungs-Zeichen und Tausenderpunkt und im
   * konfigurierten Zahlenformat.
   *
   * @return Die formatierte Gewinnsumme.
   */
  public String gibFormatierteGewinnSumme() {
    long gewinnSumme = gibGewinnSumme();
    String formatiert = "";

    switch (Konfiguration.zahlenFormat) {
      case DEZIMAL:
        formatiert = String.format("%,d", gewinnSumme);
        break;

      case BINÄR:
        formatiert = "0b" + Long.toBinaryString(gewinnSumme);
        break;

      case HEXALDEZIMAL:
        formatiert = "0x" + Long.toHexString(gewinnSumme).toUpperCase();
        break;
    }

    return String.format("%s €", formatiert);
  }

  /**
   * Zeige an, ob das Spiel gewonnen oder verloren wurde.
   *
   * @return Wahr, wenn das Spiel verloren wurde.
   */
  public boolean istVerloren() {
    return verloren;
  }

  /**
   * Zeige an, ob das Spiel beendet wurde. Wenn das Spiel verloren wurde, ist das
   * Spiel immer beendet. Man kann ein Spiel vorzeitig beenden und die Gewinnsumme
   * mitnehmen.
   *
   * @return Wahr, wenn das Spiel beendet wurde.
   */
  public boolean istBeendet() {
    return beendet;
  }

  /**
   * Zeige an, ob die aktuelle Frage beantwortet (egal ob falsch oder richtig)
   * wurde.
   *
   * Diese Methode wird gebraucht, um zu überprüfen, ob eine neue Frage angezeigt
   * werden kann.
   *
   * @return Wahr, wenn die Frage beantwortete wurde.
   */
  public boolean istAktuelleFrageBeantwortet() {
    if (aktuelleFrage != null && aktuelleFrage.istBeantwortet())
      return true;
    return false;
  }

  /**
   * Beende ein Spiel.
   */
  public void beende() {
    beendet = true;
  }

  /**
   * Lade ein Themengebiet ins Spiel.
   *
   * @param dateiPfad Ein Pfad zu einer Themengebiets-XML-Datei. Relativer Pfad
   *                  zum Projektverzeichnis, beispielsweise
   *                  <code>"/FRAGEN/informatik/6_jahrgangsstufe.xml"</code>.
   */
  public void ladeThemenGebiet(String dateiPfad) {
    this.dateiPfad = dateiPfad;
    ThemenGebiet gebiet = new ThemenGebiet(dateiPfad);
    setzeThemenBereich(gebiet.gibFach());
    setzeThemenGebiet(gebiet.gibThema());
    gebiet.leseFragenInsSpiel(this, Konfiguration.anzahlGeladenerFragen);
  }

  /**
   * Setze den Namen des aktuellen Themenbereichs (z. B. Unterrichtsfach
   * „Informatik“)
   *
   * @param name Der Namen des aktuellen Themenbereichs.
   */
  public void setzeThemenBereich(String name) {
    themenBereich = name;
  }

  /**
   * Gib den Namen des aktuellen Themenbereichs (z. B. Unterrichtsfach
   * „Informatik“)
   */
  public String gibThemenBereich() {
    return themenBereich;
  }

  /**
   * Setze das Thema des aktuellen Themengebiets (z. B. Jahrgangsstufe („6.
   * Jahrgangsstufe“)).
   *
   * @param thema
   */
  public void setzeThemenGebiet(String thema) {
    themenGebiet = thema;
  }

  /**
   * Gib das Thema des aktuellen Themengebiets (z. B. Jahrgangsstufe („6.
   * Jahrgangsstufe“)).
   */
  public String gibThemenGebiet() {
    return themenGebiet;
  }

  /**
   * Gib den Dateipfad der XML-Datei, der das aktuelle Spiel gestartet hat. Die
   * getter-Methode kann dazu benutzt werden, das gleiche Spiel nochmal zu
   * starten.
   */
  public String gibDateiPfad() {
    return dateiPfad;
  }

}
