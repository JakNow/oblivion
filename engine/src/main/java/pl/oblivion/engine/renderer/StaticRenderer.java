package pl.oblivion.engine.renderer;

import lombok.Getter;
import org.joml.Quaternionf;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.Mesh;
import pl.oblivion.engine.shader.StaticShader;

public class StaticRenderer extends AbstractRenderer {

  private static StaticShader staticShader = new StaticShader(RendererType.STATIC_RENDERER);

  private GameObject parent;
  private TestModel testModel;
  private TestModel testModel2;
  
  Quaternionf rot = new Quaternionf(0, 1, 0, (float) Math.toRadians(0.2));
  
  @Override
  public void render() {
  
  }
  
  public StaticRenderer(Window window, Camera camera) {
    super(staticShader, window, camera);
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getProjectionMatrix());
    staticShader.stop();
    parent = new GameObject() {
    };
    testModel = new TestModel(parent);
    testModel2 = new TestModel(parent);
    // testModel.rotate(new Quaternionf(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
    testModel.transform.translate(2, 0, -4);
    testModel2.transform.translate(-2, 0, -4);
    //  testModel2.rotate(new Quaternionf(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
    GL11.glEnable(GL11.GL_DEPTH_TEST);
  }

  @Override
  public void prepare() {
    //testModel.transform.rotate(rot);
    //  testModel.translate(0.015f,0,0);
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getWindow().getProjectionMatrix());
    staticShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
    staticShader
        .getTransformationMatrix()
            .loadMatrix(testModel.transform.getTransformationMatrix());
  
    GL30.glBindVertexArray(testModel.getMesh().getId());
    testModel.getMesh().bind(0);
    GL11.glDrawElements(
            GL11.GL_TRIANGLES, testModel.getMesh().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
    testModel.getMesh().unbind(0);
    
    staticShader
            .getTransformationMatrix()
            .loadMatrix(testModel2.transform.getTransformationMatrix());
    
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
      super(parent);
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
      //this.getTransform().rotate(1, 1, 1);

      xDirection =
              setDirection(this.transform.getPosition().x, min, max, xDirection);
      yDirection = 0;
      //setDirection(this.getTransform().getPosition().y, min, max, yDirection);
      zDirection = 0;
      //setDirection(this.getTransform().getPosition().z, -6,-5, zDirection);

      float xSpeed = speed * xDirection;
      float ySpeed = speed * yDirection;
      float zSpeed = speed * zDirection;
  
      this.transform.translate(xSpeed, ySpeed, zSpeed);
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
