package pl.oblivion.engine.renderer;

import org.joml.Matrix4f;

import lombok.Getter;
import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.scene.Scene;
import pl.oblivion.engine.shader.AbstractShader;

@Getter
public abstract class AbstractRenderer {

  private AbstractShader shader;
  private Matrix4f projectionMatrix;
  private Window window;
  private Camera camera;

  public AbstractRenderer(AbstractShader shader, Window window, Camera camera) {
    this.shader = shader;
    this.window = window;
    this.projectionMatrix = window.getProjectionMatrix();
    this.camera = camera;
  }

  public abstract void render(Scene scene);

  public abstract void prepare();

  public abstract void delete();

  public abstract void end();

  public void cleanUp(Scene scene) {
    this.shader.cleanUp();
    scene.clear();
  }
}
