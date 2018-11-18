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

  private GameObject parent;
  private TestModel testModel;
  private TestModel testModel2;

  public StaticRenderer(Window window, Camera camera) {
    super(staticShader, window, camera);
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getProjectionMatrix());
    staticShader.stop();
    parent = new GameObject(new Transformation(),null) {
    };
    testModel = new TestModel(parent);
    testModel2 = new TestModel(parent);
    testModel.getTransformation().translate(-2, 0, -6);
    testModel2.getTransformation().translate(2, 0, -6);
    GL11.glEnable(GL11.GL_DEPTH_TEST);
  }

  @Override
  public void render() {
  
  }

  @Override
  public void prepare() {
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getWindow().getProjectionMatrix());
    staticShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
    staticShader
        .getTransformationMatrix()
        .loadMatrix(testModel.getTransformation().getTransformationMatrix());
  
    testModel.funnyAnimation(-2,2);
    GL30.glBindVertexArray(testModel.getMesh().getId());
    testModel.getMesh().bind(0);
    GL11.glDrawElements(
            GL11.GL_TRIANGLES, testModel.getMesh().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
    testModel.getMesh().unbind(0);
    
    staticShader
            .getTransformationMatrix()
            .loadMatrix(testModel2.getTransformation().getTransformationMatrix());
    
    testModel2.funnyAnimation(2,2);
    GL30.glBindVertexArray(testModel2.getMesh().getId());
    testModel2.getMesh().bind(0);
    GL11.glDrawElements(
            GL11.GL_TRIANGLES, testModel2.getMesh().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
    testModel2.getMesh().unbind(0);
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
    float speed = 0.015f;

    public TestModel(GameObject parent) {
      super(new Transformation(),parent);
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

    void funnyAnimation(float min, float max) {
      //this.getTransformation().rotate(1, 1, 1);

      xDirection =
          setDirection(this.getTransformation().getPosition().x, min, max, xDirection);
      yDirection = 0;
          //setDirection(this.getTransformation().getPosition().y, min, max, yDirection);
      zDirection = 0;
              //setDirection(this.getTransformation().getPosition().z, -6,-5, zDirection);

      float xSpeed = speed * xDirection;
      float ySpeed = speed * yDirection;
      float zSpeed = speed * zDirection;

      this.getTransformation().translate(xSpeed, ySpeed, zSpeed);
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
