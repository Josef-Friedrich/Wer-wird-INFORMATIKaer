package swing_implementation;

public class TasteTest {

  public static void main(String[] args) {
    SpielRahmen testRahmen = new SpielRahmen("Teste die Klasse „Taste“", true);

    Taste taste = new Taste("pfeil-blau.png", "pfeil-gelb.png", "pfeil-rot.png");
    taste.setLocation(200, 100);
    testRahmen.add(taste);

    taste.fügeLauscherHinzu(() -> {
      System.out.println("gedrückt");
    });

    testRahmen.setVisible(true);
    testRahmen.setBackground(Aussehen.FARBE_HINTERGRUND);
  }

}
