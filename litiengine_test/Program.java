package litiengine_test;

import de.gurkenlabs.litiengine.Game;

public class Program {

  public static void main(String[] args) {
    Game.init(args);
    Game.screens().add(new TestScreen());
    Game.start();
  }
}
