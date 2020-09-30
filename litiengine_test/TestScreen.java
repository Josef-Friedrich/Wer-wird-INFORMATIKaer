package litiengine_test;

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
  }
}
