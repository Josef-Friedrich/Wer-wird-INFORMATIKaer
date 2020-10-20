doc:
	-mkdir .tmp
	-mv fragen_verwaltung/ThemenGebietTest.java .tmp
	-mv fragen_verwaltung/ThemenKatalogTest.java .tmp
	-mv fragen_verwaltung/XMLDateiTest.java .tmp

	-mv spiel_logik/SpielTest.java .tmp
	-mv spiel_logik/FragenListeTest.java .tmp
	-mv spiel_logik/KonfigurationTest.java .tmp
	-mv spiel_logik/FrageTest.java .tmp

	javadoc -d docs swing_implementation kommandozeilen_implementation fragen_verwaltung spiel_logik

	-mv .tmp/ThemenGebietTest.java  fragen_verwaltung
	-mv .tmp/ThemenKatalogTest.java  fragen_verwaltung
	-mv .tmp/XMLDateiTest.java  fragen_verwaltung

	-mv .tmp/SpielTest.java spiel_logik
	-mv .tmp/FragenListeTest.java spiel_logik
	-mv .tmp/KonfigurationTest.java spiel_logik
	-mv .tmp/FrageTest.java spiel_logik
