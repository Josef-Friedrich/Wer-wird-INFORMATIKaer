package swing_implementation;

public class AktiverTextTest {

  public static void main(String[] args) {
    SpielRahmen testRahmen = new SpielRahmen("Teste die Klasse „AktiverText“", true);

    AktiverText text1 = new AktiverText("Test 1");
    text1.setBounds(200, 200, 100, 50);
    testRahmen.add(text1);
    testRahmen.setVisible(true);
  }

}
