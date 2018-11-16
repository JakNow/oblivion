package pl.oblivion.common.transformation;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.oblivion.common.gameobject.GameObject;

public class TranslateTest {

  private static GameObject parent;
  private static GameObject child;

  private static Transformation defaultParentTransformation;
  private static Transformation defaultChildTransformation;

  @BeforeClass
  public static void initGameObjects() {
    defaultParentTransformation = new Transformation();
    defaultChildTransformation = new Transformation();
    defaultChildTransformation.getPosition().set(2, 2, 2);

    parent = new GameObject(defaultParentTransformation) {};
    child = new GameObject(new Transformation(defaultChildTransformation), parent) {};
  }

  @Before
  public void resetTransformation() {
    parent.getTransformation().set(defaultParentTransformation);
    child.getTransformation().set(defaultChildTransformation);
  }

  @Test
  public void constructTransformationWithTransformation_Test() {
    Transformation createdTransformation = new Transformation(defaultParentTransformation);
    assertThat(createdTransformation)
        .usingDefaultComparator()
        .isEqualToComparingFieldByField(defaultParentTransformation);
  }

  @Test
  public void constructTransformation_newInstance_Test() {
    Transformation createdTransformation = new Transformation(defaultParentTransformation);
    createdTransformation.getPosition().set(2, 3, 4);
    createdTransformation.getRotation().set(30, 45, 60, 90);
    createdTransformation.getScale().set(2, 3, 4);

    SoftAssertions softly = new SoftAssertions();
    softly
        .assertThat(defaultParentTransformation.getPosition())
        .extracting("x", "y", "z")
        .contains(0.0f);
    softly
        .assertThat(defaultParentTransformation.getRotation())
        .extracting("x", "y", "z", "w")
        .contains(0.0f);
    softly
        .assertThat(defaultParentTransformation.getScale())
        .extracting("x", "y", "z")
        .contains(1.0f);
    softly
        .assertThat(createdTransformation.getPosition())
        .extracting("x", "y", "z")
        .contains(2.0f, 3.0f, 4.0f);
    softly
        .assertThat(createdTransformation.getRotation())
        .extracting("x", "y", "z", "w")
        .contains(30.0f, 45.0f, 60.0f, 90.0f);
    softly
        .assertThat(createdTransformation.getScale())
        .extracting("x", "y", "z")
        .contains(2.0f, 3.0f, 4.0f);
    softly.assertAll();
  }

  @Test
  public void translateXYZ_GameObjectIsTranslated_Test() {
    parent.getTransformation().translate(0.5f, 52.5f, 100);
    assertThat(parent.getTransformation().getPosition())
        .extracting("x", "y", "z")
        .contains(0.5f, 52.5f, 100.0f);
  }

  @Test
  public void translateVector_GameObjectIsTranslated_Test() {
    parent.getTransformation().translate(new Vector3f(0.5f, 52.5f, 100));
    assertThat(parent.getTransformation().getPosition())
        .extracting("x", "y", "z")
        .contains(0.5f, 52.5f, 100.0f);
  }

  @Test
  public void rotate_GameObjectIsRotated_Test() {}

  @Test
  public void scale_GameObjectIsScaled_Test() {}

  @Test
  public void translateParent_ChildIsTranslated_Test() {}

  @Test
  public void rotateParent_ChildIsRotated_Test() {}

  @Test
  public void scaleParent_ChildIsScaled_Test() {}
}
