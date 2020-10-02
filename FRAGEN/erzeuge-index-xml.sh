#! /bin/bash

> index_neu.xml

for PFAD in $(find . -type f \( -iname "*.xml" ! -iname "*index_neu.xml" ! -iname "*index.xml" \) | sort); do
	PFAD="${PFAD:2}"
  # echo "$PFAD"
	THEMA="$(xmllint --xpath '//thema/text()' "$PFAD")"
	echo "<themenGebiet pfad=\"$PFAD\" titel=\"$THEMA\"/>" >> index_neu.xml
done
