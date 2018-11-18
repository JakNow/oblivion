package pl.oblivion.common.gameobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.joml.Vector3f;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.oblivion.common.transformation.Transformation;

public class TransformationTest {

  private static GameObject gameObject;
  private static Transformation gameObjectTransformation;

  @BeforeClass
  public static void setUp() {
    gameObject = new GameObject(gameObjectTransformation = new Transformation()) {};
  }

  @Before
  public void resetTransformation() {
    gameObjectTransformation.set(new Transformation());
  }

  @Test
  public void translateByFloatsGameObject_GameObjectIsTranslated_Test() {
    float xTranslation = 0.5f;
    float yTranslation = 123f;
    float zTranslation = 42.512f;

    gameObject.translate(xTranslation, yTranslation, zTranslation);
    assertThat(gameObject.getPosition())
        .extracting("x", "y", "z")
        .contains(xTranslation, yTranslation, zTranslation);
  }

  @Test
  public void translateByVectorGameObject_GameObjectIsTranslated_Test() {
    Vector3f xyzTranslation = new Vector3f(0.5f, 123f, 42.512f);

    gameObject.translate(xyzTranslation);
    assertThat(gameObject.getPosition()).isEqualTo(xyzTranslation);
  }
}
