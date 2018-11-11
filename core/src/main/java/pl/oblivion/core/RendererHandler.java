package pl.oblivion.core;

import static org.lwjgl.opengl.GL11C.*;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererType;
import pl.oblivion.engine.renderer.StaticRenderer;

class RendererHandler {

  private static RendererHandler instance;
  private final Map<RendererType, AbstractRenderer> rendererMap;
  private Window window;
  private float currentWidth;
  private float currentHeight;

  private RendererHandler() {
    rendererMap = new HashMap<>();
  }

  static synchronized RendererHandler getInstance() {
    if (instance == null) {
      instance = new RendererHandler();
    }
    return instance;
  }

  void initRenderers(Window window, Camera camera) {
    rendererMap.put(RendererType.STATIC_RENDERER, new StaticRenderer(window, camera));
    this.window = window;
    this.currentWidth = window.getWidth();
    this.currentHeight = window.getHeight();
  }

  void delete() {
    rendererMap.forEach(
        (k, v) -> {
          v.delete();
          v.cleanUp();
        });
  }

  void render() {
    if (window.isResized()) {
      glViewport(0, 0, window.getWidth(), window.getHeight());
      window.updateProjectionMatrix();
      currentWidth = window.getWidth();
      currentHeight = window.getHeight();
    }
    GL11.glClearColor(1f, 0, 0, 1f);
    GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    rendererMap.forEach(
        (k, v) -> {
          v.prepare();
          v.render();
          v.end();
        });
  }
}
