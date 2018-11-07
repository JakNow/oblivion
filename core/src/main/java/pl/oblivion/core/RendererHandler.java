package pl.oblivion.core;

import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererType;
import pl.oblivion.engine.renderer.StaticRenderer;

class RendererHandler {

  private static RendererHandler instance;

  private final Map<RendererType, AbstractRenderer> rendererMap;

  private StaticRenderer staticRenderer;

  private RendererHandler() {
    rendererMap = new HashMap<>();
  }

  public void initRenderers() {
    staticRenderer = new StaticRenderer();
    rendererMap.put(RendererType.STATIC_RENDERER, staticRenderer);
  }

  public static synchronized RendererHandler getInstance() {
    if (instance == null) {
      instance = new RendererHandler();
    }
    return instance;
  }

  void delete() {
    rendererMap.forEach(
        (k, v) -> {
          v.delete();
          v.cleanUp();
        });
  }

  void render() {
    GL11.glClearColor(1f, 0, 0, 1f);
    GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    rendererMap.forEach((k, v) -> v.render());
  }
}
