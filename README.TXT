# Wer wird Informatik-Millionär

Spiel mit der Spielmechanik ähnlich wie “Wer wird Millionär”

- 5 Stufen (nach dem Zufallsprinzip werden Fragen aus einem Fragenpool ausgewählt)
- Schwierigkeitsstufe aufsteigend
- erstmal für die Jahrgangstufen 6 und 7
- Optional 9 und 10.

Wir haben ein Repository angelegt und mit dem Ticket-System von Gitlab 
begonnen.

# Mettings

Freitag 26. Juni 9:30 (über Zoom oder Teams)

Fragen haben 5 Schwierigkeitsstufen (1 = leicht, 5 ist schwierig)
Bei einer Erweiterung können mehrmal hintereinander die gleich Schwierigkeitsstufen
dran kommen.

Spiel für Schüler
Fragen aus dem Lehrplan der jeweiligen Jahrgangsstufen

# Klasse Frage (Martin)
Attribute
- frage: String
- bild: (später)
- richtigAntwort: String
- falscheAntwort1: String
- falscheAntwort2: String
- falscheAntwort3: String
- schwierigkeit: int (1-5)
- jahrgangsstufe: int (6 oder 7)
- nächsteFrage
Methode
+ verteilteZufälligeAntworten()

# Klasse Jahrgangsstufe (Kompositum) (Michaela)
Attribute
- fragen: alle Fragen (20)
- beantworteteFragen
Methoden
- fügeNeueFrageHinzu(): 
- löscheFrage()
- gibFrage()

# Klasse Spiel (Steffi)
Attribute
- punktestand
Methoden
+ gibNeueFrage(): zufällig
+ zeigeNeueFrage(): mit den Antworten 
+ erhalteAntwort():
+ zeigeRichtigeAntwortAn()
+ werteAus(): Punktestand wird erhöht
+ main()

# Josef: Support (Grafische Oberfläche)

# Klasse Joker?

# Klasse GrafischeOberfläche?
Methoden
main()

Erweiterungsmöglichkeiten:
- Joker (50:50 Joker)
- Telefonjoker 
- zwei Spielmodi (noch Punkte oder falsche Frage aus)
- Anbindung an eine SQL-Datenbank
- Fragen-Editor (Formluare, wo Fragen eingegeben werden können)
- Stichwort-Suche (kann mit Themen aus AUD implementiert werden)
- Auswahl nach Lehrplan-Unterthemen


Präsentation des Projekts "Wer wird Millionär"
von Team Nbg.

1. IDEE (Martin)
- unterschiedliche Fächer unter einem Hut
- anwendbares Projekt für Schüler und Lernende
- bekanntes Spielprinzip 
- variantenreicher Aufbau (Inhalt, Fächer, Schwierigkeitsstufen)

2. EINTEILUNG (Steffi)
- Entscheiden für GitLab 
- Festlegen von Klassen, Attributnamen und Mehoden
- nahezu (!) gleichmäßige Aufgabenteilung
- Schreiben von drei verschiedenen Klassen
- Zusammenfassen dieser
- wöchentliche Treffen

3. CODE (Josef)
- Entscheiden für Liste mit Kompositum
- Vorführung des 1. Prototyps
- Zeigen von Code-Beispielen

4. SCHWIERIGKEITEN UND AUSBLICK (Michi) 
- coronabedingtes zeitliches Problem
- Abschätzung für den jeweiligen zeitlichen Arbeitsaufwand
- Zusammenführen verschiedener Vorstellungen 
- Reduktion von Ideen (Was ist umsetzbar?) 
- graphische Oberfläche
- Punkte-/Belohnungssystem
- evtl. Erweiterung für weitere Jahrgangsstufen


TREFFEN VOR DEN FERIEN: 
24.7., 14.00 Uhr in Zoom 
(Wer nicht kann, mailt an die anderen.)
Themen: 
- abstrakte Fabrik
- evtl. Programmierstücke 
- weiteres Vorgehen Richtung Prototyp 2
- Uhrzeit für das Treffen am 14.8. (?) 

TREFFEN IN DEN FERIEN: 
14.8. 9:30

TREFFEN NACH den FERIEN: 
11.9., 13.00 Uhr
- ABSTRAKTE FABRIK! 
- mehr Fragen zu 6 und 7
- GUI Swing


INHALTLICHE PLANUNG
1. PROTOTYP
- Anzeigen von zehn Fragen mit Zufall
- nicht spielbar

2. PROTOTYP
- spielbare Variante
- Benutzung von graphischer Oberfläche (Buttons)

3. PROTOTYP
- Hintergrundgraphik
- Musik