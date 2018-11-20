package pl.oblivion.common.gameobject.transformation;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.oblivion.common.gameobject.Transformation;

public class TransformationTest {

  private static Transformation transformation;

  @BeforeClass
  public static void initGameObjects() {
    transformation = new Transformation();
  }

  @Before
  public void resetTransformation() {
    transformation.set(new Transformation());
  }

  @Test
  public void receivedTranslation_isEqualToMatrixTranslation() {
    Vector3f receivedTranslation = new Vector3f();
    transformation.getTransformationMatrix().getTranslation(receivedTranslation);
    assertThat(transformation.getPosition()).isEqualTo(receivedTranslation);
  }

  @Test
  public void receivedRotation_isEqualToMatrixRotation() {
    AxisAngle4f receivedRotation = new AxisAngle4f();
    transformation.getTransformationMatrix().getRotation(receivedRotation);
    assertThat(transformation.getRotation()).isEqualTo(receivedRotation);
  }

  @Test
  public void receivedScale_isEqualToMatrixScale() {
    Vector3f receivedScale = new Vector3f();
    transformation.getTransformationMatrix().getScale(receivedScale);
    assertThat(transformation.getScale()).isEqualTo(receivedScale);
  }

  @Test
  public void translateXYZ_GameObjectIsTranslated_Test()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method translation =
        Transformation.class.getDeclaredMethod("translate", float.class, float.class, float.class);
    translation.setAccessible(true);
    translation.invoke(transformation, 0.5f, 52.5f, 100);

    assertThat(transformation.getPosition())
        .extracting("x", "y", "z")
        .contains(0.5f, 52.5f, 100.0f);
  }

  @Test
  public void translateVector_GameObjectIsTranslated_Test()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method translation = Transformation.class.getDeclaredMethod("translate", Vector3f.class);
    translation.setAccessible(true);
    translation.invoke(transformation, new Vector3f(0.5f, 52.5f, 100));

    assertThat(transformation.getPosition())
        .extracting("x", "y", "z")
        .contains(0.5f, 52.5f, 100.0f);
  }

  @Test
  public void rotate_GameObjectIsRotated_Test()
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method rotation = Transformation.class.getDeclaredMethod("rotate", AxisAngle4f.class);
    rotation.setAccessible(true);
    rotation.invoke(transformation, new AxisAngle4f((float) (Math.PI / 2), 0.3f, 0.5f, 0.7f));

    assertThat(transformation.getRotation())
        .extracting("x", "y", "z", "angle")
        .contains(0.3291863f, 0.5577188f, 0.761962f, 1.5965585f);
  }

  @Test
  public void scale_GameObjectIsScaled_Test()
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method translation =
        Transformation.class.getDeclaredMethod("scale", float.class, float.class, float.class);
    translation.setAccessible(true);
    translation.invoke(transformation, 1.5f, 4.32f, 2);

    assertThat(transformation.getScale()).extracting("x", "y", "z").contains(1.5f, 4.32f, 2.0f);
  }
}
