package pl.oblivion.core.scene;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.input.InputManager;
import pl.oblivion.engine.input.KeyCode;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;

import java.util.*;

public class Scene {

	private static final Logger logger = LogManager.getLogger(Scene.class);

	@Getter
	private Camera camera;
	@Getter
	private Map<ShaderType, Map<MeshOGL, List<Entity>>> entitiesRendererMap;
	@Getter
	private List<Entity> rawEntities;

	public Scene() {
		rawEntities = new LinkedList<>();
		entitiesRendererMap = new HashMap<>();
	}

	public void addToScene(GameObject gameObject) {
		gameObject.getChildren().forEach(this::addToScene);
		if (gameObject instanceof Camera) {
			logger.info("Setting up camera: {}", gameObject);
			this.camera = (Camera) gameObject;
		} else if (gameObject instanceof Entity) {
			logger.info("Adding to list: {}", gameObject);
			rawEntities.add((Entity) gameObject);
		}
	}

	public void initObjects() {
		rawEntities.forEach(entity -> {
			entity.init();
			addEntityToObjectToMap(entity);
		});
	}

	public void render() {
		AbstractRenderer.prepareScene();
		RendererCache.getInstance().getAvailableRenderers().forEach((shaderType, renderer) -> {
			renderer.prepareShader();
			entitiesRendererMap.get(shaderType).forEach(((meshOGL, entities) -> {
				meshOGL.bind(meshOGL.getAttributesBinding());
				entities.forEach(Entity::render);
				meshOGL.unbind(meshOGL.getAttributesBinding());
			}));
			renderer.end();
		});
	}

	public void update() {
		// for testing purpose
		float x = InputManager.getKey(KeyCode.HORIZONTAL) * 2 * Timer.deltaTime;
		float y = InputManager.getKey(KeyCode.VERTICAL) * 2 * Timer.deltaTime;

		camera.transform.translate(x, y, 0);
	}

	public void delete() {
		RendererCache.getInstance().getAvailableRenderers().forEach((key, value) ->
				value.cleanUp());
	}

	private void addEntityToObjectToMap(Entity entity) {
		Map<MeshOGL, List<Entity>> entities = entitiesRendererMap.get(entity.getShaderType());
		if (entities != null) {
			List<Entity> batch = entities.get(entity.getMeshOGL());
			if (batch != null) {
				batch.add(entity);
			} else {
				batch = new ArrayList<>();
				batch.add(entity);
				entities.put(entity.getMeshOGL(), batch);
			}
		} else {
			entities = new HashMap<>();
			List<Entity> batch = new ArrayList<>();
			batch.add(entity);
			entities.put(entity.getMeshOGL(), batch);
			entitiesRendererMap.put(entity.getShaderType(), entities);
		}

	}
}
