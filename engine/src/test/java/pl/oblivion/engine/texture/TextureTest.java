package pl.oblivion.engine.texture;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import pl.oblivion.engine.mesh.Texture;

public class TextureTest {

  @Test
  public void Texture_Test() {
    Texture texture = null;
      try {
          texture = new Texture("textures/test_texture.png");
          texture.loadTexture("textures/test_texture.png");
      } catch (Exception ex) {
          Logger.getLogger(TextureTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    assertThat(texture.getTextureId())
        .withFailMessage("Output value is not correct. Should return id.")
        .isEqualTo(1);
  }
}
