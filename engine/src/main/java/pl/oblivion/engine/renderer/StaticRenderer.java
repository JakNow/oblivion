package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.transformation.Transformation;
import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.Mesh;
import pl.oblivion.engine.shader.StaticShader;

public class StaticRenderer extends AbstractRenderer {

  private static StaticShader staticShader = new StaticShader();

  private TestModel testModel;

  public StaticRenderer(Window window, Camera camera) {
    super(staticShader, window, camera);
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getProjectionMatrix());
    staticShader.stop();
    testModel = new TestModel();
    testModel.getTransformation().getPosition().set(0, 0, -4);
    GL11.glEnable(GL11.GL_DEPTH_TEST);
  }

  @Override
  public void render() {
    testModel.funnyAnimation();
    GL30.glBindVertexArray(testModel.getMesh().getId());
    testModel.getMesh().bind(0);
    GL11.glDrawElements(
        GL11.GL_TRIANGLES, testModel.getMesh().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
  }

  @Override
  public void prepare() {
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getWindow().getProjectionMatrix());
    staticShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
    staticShader
        .getTransformationMatrix()
        .loadMatrix(testModel.getTransformation().getTransformationMatrix());
  }

  @Override
  public void delete() {}

  @Override
  public void end() {
    staticShader.stop();
  }

  public class TestModel extends GameObject {

    @Getter Mesh mesh;
    // just for testing purpose
    int xDirection = 1;
    int yDirection = 1;
    int zDirection = 1;
    float speed = 0.005f;

    public TestModel() {
      super(new Transformation());
      mesh =
          new Mesh(
              new int[] {
                // front
                0, 1, 2,
                2, 3, 0,
                // right
                1, 5, 6,
                6, 2, 1,
                // back
                7, 6, 5,
                5, 4, 7,
                // left
                4, 0, 3,
                3, 7, 4,
                // bottom
                4, 5, 1,
                1, 0, 4,
                // top
                3, 2, 6,
                6, 7, 3,
              },
              new Attribute(
                  3,
                  new float[] {
                    // front
                    -0.5f,
                    -0.5f,
                    0.5f,
                    0.5f,
                    -0.5f,
                    0.5f,
                    0.5f,
                    0.5f,
                    0.5f,
                    -0.5f,
                    0.5f,
                    0.5f,
                    // back
                    -0.5f,
                    -0.5f,
                    -0.5f,
                    0.5f,
                    -0.5f,
                    -0.5f,
                    0.5f,
                    0.5f,
                    -0.5f,
                    -0.5f,
                    0.5f,
                    -0.5f,
                  })) {};
    }

    void funnyAnimation() {
      testModel.getTransformation().rotate(1, 1, 1);

      xDirection =
          setDirection(testModel.getTransformation().getPosition().x, -0.5f, 0.5f, xDirection);
      yDirection =
          setDirection(testModel.getTransformation().getPosition().y, -0.5f, 0.5f, yDirection);
      zDirection = setDirection(testModel.getTransformation().getPosition().z, -4, -3f, zDirection);

      float xSpeed = speed * xDirection;
      float ySpeed = speed * yDirection;
      float zSpeed = speed * zDirection;

      testModel.getTransformation().translate(xSpeed, ySpeed, zSpeed);
    }

    private int setDirection(float variable, float min, float max, int current) {
      if (variable < min) {
        return 1;
      } else if (variable > min && variable < max) {
        return current;
      } else {
        return -1;
      }
    }
  }
}
