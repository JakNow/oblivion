package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.scene.Scene;
import pl.oblivion.engine.shader.StaticShader;

public class StaticRenderer extends AbstractRenderer {

  private static StaticShader staticShader = new StaticShader(RendererType.STATIC_RENDERER);
 
  public StaticRenderer(Window window, Camera camera) {
    super(staticShader, window, camera);
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getProjectionMatrix());
    staticShader.stop();
  
    GL11.glEnable(GL11.GL_DEPTH_TEST);
  }
  
  @Override
  public void prepare() {
  
    staticShader.start();
    staticShader.getProjectionMatrix().loadMatrix(this.getWindow().getProjectionMatrix());
    staticShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
  }
  
  @Override
  public void render(Scene scene) {
  
  }

  @Override
  public void delete() {}

  @Override
  public void end() {
    staticShader.stop();
  }
}
