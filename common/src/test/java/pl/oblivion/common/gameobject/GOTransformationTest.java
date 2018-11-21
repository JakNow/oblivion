package pl.oblivion.common.gameobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GOTransformationTest {

  private static GameObject parent;
  private static GameObject child;

  private static Transformation defaultParentTransformation;
  private static Transformation defaultChildTransformation;

  @BeforeClass
  public static void setUp() {
    defaultParentTransformation = new Transformation();
    defaultChildTransformation =
        new Transformation(new Vector3f(2, 3, 4), new AxisAngle4f(), new Vector3f(1, 1, 1));
    parent = new GameObject(defaultParentTransformation) {};
    child = new GameObject(defaultChildTransformation, parent) {};
  }

  @Before
  public void resetTransformation() {
    parent.getTransformation().set(defaultParentTransformation);
    child.getTransformation().set(defaultChildTransformation);
  }

  @Test
  public void translateByFloatsGameObject_GameObjectIsTranslated_Test() {
    float xTranslation = 0.5f;
    float yTranslation = 123f;
    float zTranslation = 42.512f;

    parent.translateLocal(xTranslation, yTranslation, zTranslation);
    assertThat(parent.getPosition())
        .extracting("x", "y", "z")
        .contains(xTranslation, yTranslation, zTranslation);
  }

  @Test
  public void translateByVectorGameObject_GameObjectIsTranslated_Test() {
    Vector3f xyzTranslation = new Vector3f(0.5f, 123f, 42.512f);

    parent.translateLocal(xyzTranslation);
    assertThat(parent.getPosition()).isEqualTo(xyzTranslation);
  }

  @Test
  public void translateOverTimeGameObject_GameObjectIsTranslatedInSteps_Test() {
    float xSpeed = 0.5f;
    float ySpeed = 1.25f;
    float zSpeed = 1;

    int translationTimes = 100;
    SoftAssertions softAssertions = new SoftAssertions();
    for (int i = 0; i < translationTimes; i++) {
      parent.translateLocal(xSpeed, ySpeed, zSpeed);
      softAssertions.assertThat(parent.getPosition().x).isEqualTo(xSpeed * (i + 1));
      softAssertions.assertThat(parent.getPosition().y).isEqualTo(ySpeed * (i + 1));
      softAssertions.assertThat(parent.getPosition().z).isEqualTo(zSpeed * (i + 1));
    }
    softAssertions.assertAll();
    assertThat(parent.getPosition())
        .extracting("x", "y", "z")
        .contains(xSpeed * translationTimes, ySpeed * translationTimes, zSpeed * translationTimes);
  }

  @Test
  public void translateParentByFloats_parentAndChildAreTranslated_Test() {
    float xTranslation = 0.5f;
    float yTranslation = 123f;
    float zTranslation = 42.512f;

    parent.translate(xTranslation, yTranslation, zTranslation);

    assertThat(parent.getPosition())
        .extracting("x", "y", "z")
        .contains(xTranslation, yTranslation, zTranslation);
    assertThat(child.getPosition())
        .extracting("x", "y", "z")
        .contains((xTranslation + 2), (yTranslation + 3), (zTranslation + 4));
  }

  @Test
  public void translateParentByVector_parentAndChildAreTranslated_Test() {
    Vector3f xyzTranslation = new Vector3f(0.5f, 123f, 42.512f);

    parent.translate(xyzTranslation);

    assertThat(parent.getPosition()).isEqualTo(xyzTranslation);
    assertThat(child.getPosition())
        .extracting("x", "y", "z")
        .contains((xyzTranslation.x + 2), (xyzTranslation.y + 3), (xyzTranslation.z + 4));
  }

  @Test
  public void translateOverTimeParent_parentAndChildAreTranslatedInSteps_Test() {
    float xSpeed = 0.5f;
    float ySpeed = 1.25f;
    float zSpeed = 1;

    int translationTimes = 100;
    SoftAssertions softAssertions = new SoftAssertions();
    for (int i = 0; i < translationTimes; i++) {
      parent.translate(xSpeed, ySpeed, zSpeed);
      softAssertions.assertThat(parent.getPosition().x).isEqualTo(xSpeed * (i + 1));
      softAssertions.assertThat(parent.getPosition().y).isEqualTo(ySpeed * (i + 1));
      softAssertions.assertThat(parent.getPosition().z).isEqualTo(zSpeed * (i + 1));

      softAssertions.assertThat(child.getPosition().x).isEqualTo(xSpeed * (i + 1) + 2);
      softAssertions.assertThat(child.getPosition().y).isEqualTo(ySpeed * (i + 1) + 3);
      softAssertions.assertThat(child.getPosition().z).isEqualTo(zSpeed * (i + 1) + 4);
    }
    softAssertions.assertAll();
    assertThat(parent.getPosition())
        .extracting("x", "y", "z")
        .contains(xSpeed * translationTimes, ySpeed * translationTimes, zSpeed * translationTimes);

    assertThat(child.getPosition())
        .extracting("x", "y", "z")
        .contains(
            xSpeed * translationTimes + 2,
            ySpeed * translationTimes + 3,
            zSpeed * translationTimes + 4);
  }

  @Test
  public void translateParentLocally_thenTranslateGlobally_Test() {
    float xTranslation = 0.5f;
    float yTranslation = 123f;
    float zTranslation = 42.512f;

    parent.translateLocal(xTranslation, yTranslation, zTranslation);
    parent.translate(xTranslation, yTranslation, zTranslation);

    assertThat(parent.getPosition())
        .extracting("x", "y", "z")
        .contains(xTranslation * 2, yTranslation * 2, zTranslation * 2);

    assertThat(child.getPosition())
        .extracting("x", "y", "z")
        .contains(xTranslation + 2, yTranslation + 3, zTranslation + 4);
  }
}
