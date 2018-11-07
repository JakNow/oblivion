package pl.oblivion.engine.renderer;

import org.joml.Matrix4f;

import lombok.Getter;
import pl.oblivion.engine.shader.AbstractShader;

@Getter
public abstract class AbstractRenderer {

  private AbstractShader shader;
  private Matrix4f projectionMatrix;

  public AbstractRenderer(AbstractShader shader) {
    this.shader = shader;
  }

  public abstract void render();

  public abstract void prepare();

  public abstract void delete();

  public abstract void end();

  public void cleanUp() {
    this.shader.cleanUp();
  }
}
