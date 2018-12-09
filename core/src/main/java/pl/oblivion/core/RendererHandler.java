package pl.oblivion.core;

import org.lwjgl.opengl.GL11;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererType;
import pl.oblivion.engine.renderer.StaticRenderer;
import pl.oblivion.engine.scene.Scene;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11C.*;

class RendererHandler {

  private static RendererHandler instance;
  private final Map<RendererType, AbstractRenderer> rendererMap;
  private Window window;

  private Scene activeScene;

  private RendererHandler() {
    rendererMap = new HashMap<>();
  }

  static synchronized RendererHandler getInstance() {
    if (instance == null) {
      instance = new RendererHandler();
    }
    return instance;
  }

  void initRenderers(Window window, Scene activeScene) {
    rendererMap.put(
        RendererType.STATIC_RENDERER, new StaticRenderer(window, activeScene.getCamera()));
    this.window = window;
    this.activeScene = activeScene;
  }

  void delete() {
    rendererMap.forEach(
        (rendererType, renderer) -> {
          renderer.delete();
          renderer.cleanUp(activeScene);
        });
  }

  void render() {
    if (window.isResized()) {
      glViewport(0, 0, window.getWidth(), window.getHeight());
      window.updateProjectionMatrix(activeScene.getCamera());
    }
    GL11.glClearColor(1f, 0, 0, 1f);
    GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    rendererMap.forEach(
        (rendererType, renderer) -> {
          renderer.prepare();
          renderer.render(activeScene);
          renderer.end();
        });
  }
}
