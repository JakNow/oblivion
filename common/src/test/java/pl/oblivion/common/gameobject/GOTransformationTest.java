package pl.oblivion.common.gameobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GOTransformationTest {

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

  @Test
  public void translateOverTimeGameObject_GameObjectIsTranslatedInSteps_Test() {
    float xSpeed = 0.5f;
    float ySpeed = 1.25f;
    float zSpeed = 1;

    int translationTimes = 100;
    SoftAssertions softAssertions = new SoftAssertions();
    for (int i = 0; i < translationTimes; i++) {
      gameObject.translate(xSpeed, ySpeed, zSpeed);
      softAssertions.assertThat(gameObject.getPosition().x).isEqualTo(xSpeed * (i + 1));
      softAssertions.assertThat(gameObject.getPosition().y).isEqualTo(ySpeed * (i + 1));
      softAssertions.assertThat(gameObject.getPosition().z).isEqualTo(zSpeed * (i + 1));
    }
    softAssertions.assertAll();
    assertThat(gameObject.getPosition())
        .extracting("x", "y", "z")
        .contains(xSpeed * translationTimes, ySpeed * translationTimes, zSpeed * translationTimes);
  }
}
