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

Klasse Frage

Attribute
- frage: String
- bild: (später)
- richtigAntwort: String
- falscheAntwort1: String
- falscheAntwort2: String
- falscheAntwort3: String
- schwierigkeit: int (1-5)
- jahrgangsstufe: int (6 oder 7)

Erweiterungsmöglichkeiten:
- Joker (50:50 Joker)

