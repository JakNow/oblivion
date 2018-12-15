package pl.oblivion.engine.renderer;

import lombok.Getter;
import org.lwjgl.opengl.GL11;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.shader.AbstractShader;

import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

@Getter
public abstract class AbstractRenderer implements Rendering {

  private AbstractShader shader;
  private Camera camera;
  
  public AbstractRenderer(AbstractShader shader, Camera camera) {
    this.shader = shader;
    this.camera = camera;
  }
  
  public void prepareScene() {
    GL11.glClearColor(1f, 0, 0, 1f);
    GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  }
  
  public void cleanUp() {
    this.shader.cleanUp();
  }
}
