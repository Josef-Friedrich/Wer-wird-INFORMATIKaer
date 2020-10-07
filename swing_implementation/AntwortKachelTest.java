package swing_implementation;

public class AntwortKachelTest {

  public static void main(String[] args) {
    SpielRahmen testRahmen = new SpielRahmen("Teste die Klasse „AntwortKachel“", true);

    AntwortKachel antwortA = new AntwortKachel();
    antwortA.setLocation(10, 100);
    testRahmen.add(antwortA);

    AntwortKachel antwortB = new AntwortKachel();
    antwortB.setLocation(10, 250);
    testRahmen.add(antwortB);

    AntwortKachel antwortC = new AntwortKachel();
    antwortC.setLocation(520, 100);
    testRahmen.add(antwortC);

    AntwortKachel antwortD = new AntwortKachel();
    antwortD.setLocation(520, 250);
    testRahmen.add(antwortD);

    AntwortKachel antwortRichtig = new AntwortKachel();
    antwortRichtig.setLocation(10, 400);
    antwortRichtig.setzeRichtig();
    testRahmen.add(antwortRichtig);

    AntwortKachel antwortFalsch = new AntwortKachel();
    antwortFalsch.setLocation(520, 400);
    antwortFalsch.setzeFalsch();
    testRahmen.add(antwortFalsch);

    AntwortKachel antwortAktionLauscher = new AntwortKachel();
    antwortAktionLauscher.setLocation(260, 550);
    antwortAktionLauscher.fügeAntwortLauscherHinzu(() -> {
      antwortAktionLauscher.setzeRichtig();
      System.out.println("beantwortet");
    });
    testRahmen.add(antwortAktionLauscher);

    testRahmen.setVisible(true);
    testRahmen.setBackground(Aussehen.FARBE_HINTERGRUND);

  }

}
