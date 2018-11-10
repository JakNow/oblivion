package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.Mesh;
import pl.oblivion.engine.shader.StaticShader;

public class StaticRenderer extends AbstractRenderer {

  private static StaticShader staticShader = new StaticShader();

  private TestModel mesh;

  public StaticRenderer(Window window, Camera camera) {
    super(staticShader,window,camera);
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getProjectionMatrix());
    staticShader.stop();
    mesh = new TestModel();
  }

  @Override
  public void render() {
    GL30.glBindVertexArray(mesh.getId());
    mesh.bind(0);
    GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
  }

  @Override
  public void prepare() {
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getWindow().getProjectionMatrix());
    staticShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
  }

  @Override
  public void delete() {}

  @Override
  public void end() {
    staticShader.stop();
  }

  public class TestModel extends Mesh {
    public TestModel() {
      super(
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
              }));
    }
  }
}
