package pl.oblivion.core.scene;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.core.assets.spaceobjects.RenderableObjects;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;

import java.util.*;

public class Scene {

  private static final Logger logger = LogManager.getLogger(Scene.class);

  @Getter private Camera camera;

  private Map<ShaderType, Map<RenderableObjects, List<RenderableObjects>>> renderersObjectsMap;

  public Scene() {
    renderersObjectsMap = new HashMap<>();
  }

  public void addToScene(GameObject gameObject) {
    if (gameObject instanceof Camera) {
      logger.info("Setting up camera: {}", gameObject);
      this.camera = (Camera) gameObject;
    } else if (gameObject instanceof RenderableObjects) {
      logger.info("Adding to list: {}", gameObject);
      addRenderableObjectToMap((RenderableObjects) gameObject);
    }
  }

  public void render() {
    AbstractRenderer.prepareScene();
    renderersObjectsMap.forEach(
        (shaderType, renderableObjectsListMap) -> {
          RendererCache.getInstance().getRenderer(shaderType).prepareShader();
          renderableObjectsListMap.forEach(
              (renderableObjects, renderableObjectsList) -> {
                renderableObjects.bind();
                renderableObjectsList.forEach(
                        RenderableObjects::render);
                renderableObjects.unbind();
              });

          RendererCache.getInstance().getRenderer(shaderType).end();
        });
  }

  public void delete() {
    renderersObjectsMap.forEach((k, v) -> RendererCache.getInstance().getRenderer(k).cleanUp());
  }

  private void addRenderableObjectToMap(RenderableObjects renderableObjects) {
    Map<RenderableObjects, List<RenderableObjects>> mapOfShadersObjects =
        renderersObjectsMap.get(renderableObjects.getShaderType());

    if (mapOfShadersObjects != null) {
      // process
      List<RenderableObjects> batch = mapOfShadersObjects.get(renderableObjects);
      if (batch != null) {
        batch.add(renderableObjects);
      } else {
        batch = new ArrayList<>();
        batch.add(renderableObjects);
        mapOfShadersObjects.put(renderableObjects, batch);
      }
    } else {
      mapOfShadersObjects = new HashMap<>();
      List<RenderableObjects> batch = new LinkedList<>();
      batch.add(renderableObjects);
      mapOfShadersObjects.put(renderableObjects, batch);
      renderersObjectsMap.put(renderableObjects.getShaderType(), mapOfShadersObjects);
    }
  }
}
