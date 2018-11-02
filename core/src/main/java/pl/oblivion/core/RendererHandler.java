package pl.oblivion.core;

import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.GL30;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererType;

class RendererHandler {

  private static RendererHandler instance;

  private final Map<RendererType, AbstractRenderer> rendererMap;

  private RendererHandler() {
    rendererMap = new HashMap<>();
  }

  public static synchronized RendererHandler getInstance() {
    if (instance == null) {
      instance = new RendererHandler();
    }
    return instance;
  }

  void delete() {
    rendererMap.forEach((k, v) -> v.delete());
  }

  void render() {
    GL11.glClearColor(1f, 0, 0, 1f);
    GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    rendererMap.forEach((k, v) -> v.render());
  }
  
 

  /*
  private void render(Model model) {
    GL30.glBindVertexArray(model.getMesh().id);
    model.getMesh().bind(0, 1);
    GL11.glDrawElements(
        GL11.GL_TRIANGLES, model.getMesh().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
  }
  */
}
