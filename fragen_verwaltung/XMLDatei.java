package fragen_verwaltung;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Liefert einen vereinfachten Zugriff auf eine XML-Datei.
 */
public class XMLDatei {

  protected Element wurzel;

  protected Document dokument;

  /**
   *
   * @param pfad Eine relativer Pfad (relative zum Ordner src/main/resources)
   */
  public XMLDatei(String pfad) {

    try {
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        dokument = documentBuilder.parse(getClass().getResourceAsStream(pfad));
        wurzel = dokument.getDocumentElement();
        wurzel.normalize();

    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }

  }

  public Element gibWurzel() {
    return wurzel;
  }

  public Document gibDokument() {
    return dokument;
  }

  /**
   * Gib den Textinhalt eines XML-Kind-Knoten zurück.
   *
   * @param elternKnoten Der übergeordnete Elternknoten: ein „fragen“-Knoten.
   * @param name         Der Name des Kindknoten z. B. „fragenText“ oder
   *                     „richtigeAntwort“.
   *
   * @return Der Textinhalt des Kind-Knotens.
   */
  public String gibTextVonKind(Node elternKnoten, String name) {
    Element element = (Element) elternKnoten;
    return element.getElementsByTagName(name).item(0).getTextContent();
  }

  /**
   * Lese den Text eines XML-Elements.
   *
   * @param elementName Der Name des XML-Elements z. b. thema, autor
   *
   * @return Der Textinhalt des Elements.
   */
  protected String leseTextInhalt(String elementName) {
    NodeList nodeList = dokument.getElementsByTagName(elementName);
    Node node = nodeList.item(0);
    if (node == null) {
      return "";
    } else {
      return node.getTextContent();
    }
  }

}
