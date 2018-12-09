package pl.oblivion.model.texture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.oblivion.common.utils.MyFile;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@RunWith(Parameterized.class)
public class TextureTest {

  private int width;
  private int height;
  private int textureBufferCapacity;
  public TextureTest(int width, int height) {
    this.width = width;
    this.height = height;
    this.textureBufferCapacity = 4 * width * height;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {20, 20},
          {20, 30},
          {32, 32},
          {32, 64}
        });
  }

  @Test
  public void load_20x20_Texture() {
    Texture texture =
        new Texture(
            new MyFile(
                "/textures/test_texture_"
                    + String.valueOf(width)
                    + "x"
                    + String.valueOf(height)
                    + ".png"));

    assertSoftly(
        soft -> {
          soft.assertThat(texture.getWidth()).isEqualTo(width);
          soft.assertThat(texture.getHeight()).isEqualTo(height);
          soft.assertThat(texture.getTextureBuffer().capacity()).isEqualTo(textureBufferCapacity);
        });
  }
}
