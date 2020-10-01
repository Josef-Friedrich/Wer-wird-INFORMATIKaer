package litiengine_test;

// import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import java.awt.Graphics2D;
import java.awt.Color;

public class TestScreen extends Screen {
  public TestScreen() {
    super("TEST");
  }

  @Override
  public void render(final Graphics2D g) {
    super.render(g);
    g.setFont(Resources.fonts().get("SCHRIFTEN/Trebuchet_MS_Bold.ttf", 64f));
    g.setColor(Color.RED);
    TextRenderer.render(g, "Test text", 300, 100);

    // Menu menu = new Menu(500, 600, 200, 200, "eins", "zwei", "drei");
    // menu.setFont(Resources.fonts().get("SCHRIFTEN/Trebuchet_MS_Bold.ttf", 64f));
    // menu.getCellComponents().forEach(comp -> {
    //   comp.setFont(Resources.fonts().get("SCHRIFTEN/Trebuchet_MS_Bold.ttf", 64f));
    //   //comp.setSpriteSheet(Resources.spritesheets().get("button-background"));
    //   comp.getAppearance().setTextAntialiasing(true);
    //   comp.getAppearanceHovered().setTextAntialiasing(true);
    //   comp.getAppearance().setForeColor(Color.RED);
    //   comp.getAppearanceHovered().setForeColor(Color.BLUE);
    //   comp.setForwardMouseEvents(false);
    // });
    // menu.setEnabled(true);
    // menu.render(g);
    // this.getComponents().add(menu);
    System.out.println("lol");


  }
}
