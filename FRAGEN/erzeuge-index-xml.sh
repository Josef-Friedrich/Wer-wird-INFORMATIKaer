#! /bin/sh

> index_neu.xml

for PFAD in $(find . -iname "*.xml" | sort); do
	echo $PFAD
	THEMA="$(xmllint --xpath '//thema/text()' "$PFAD")"

	echo "<themenGebiet pfad=\"$PFAD\" titel=\"$THEMA\"/>" >> index_neu.xml
done
