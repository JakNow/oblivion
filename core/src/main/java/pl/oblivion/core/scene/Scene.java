package pl.oblivion.core.scene;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.gameobject.Behavior;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;

import java.util.*;

//todo SCENE has to be refactored to be more flexible and with clear logic
public class Scene {

	private static final Logger logger = LogManager.getLogger(Scene.class);

	@Getter
	private Camera camera;
	@Getter
	private static Map<ShaderType, Map<MeshOGL, List<Entity>>> entitiesRendererMap;
	@Getter
	private List<Entity> rawEntities;
	private List<GameObject> gameObjects;

	public Scene() {
		rawEntities = new LinkedList<>();
		gameObjects = new LinkedList<>();
		entitiesRendererMap = new HashMap<>();
	}

	public static void spawn(Entity entity) {
		entity.init();
		entity.initialize();
		addEntityToObjectToMap(entity);
	}

	private static void addEntityToObjectToMap(Entity entity) {
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

	public void initObjects() {
		rawEntities.forEach(entity -> {
			entity.init();
			entity.initialize();
			addEntityToObjectToMap(entity);
		});
	}

	public static void remove(Entity entity) { //todo Refactor scene removal
		Map<MeshOGL, List<Entity>> entities = entitiesRendererMap.get(entity.getShaderType());
		entities.get(entity.getMeshOGL()).remove(entity);
	}

	public void addToScene(GameObject gameObject) {
		if (gameObject instanceof Camera) {
			logger.info("Setting up camera: {}", gameObject.getName());
			this.camera = (Camera) gameObject;
		} else if (gameObject instanceof Entity) {
			logger.info("Adding to list: {}", gameObject.getName());
			rawEntities.add((Entity) gameObject);
		} else {
			gameObjects.add(gameObject);
		}
		gameObject.getChildren().forEach(this::addToScene);
	}

	public void delete() {
		RendererCache.getInstance().getAvailableRenderers().forEach((key, value) ->
				value.cleanUp());
	}

	public void render() {
		AbstractRenderer.prepareScene();
		RendererCache.getInstance().getAvailableRenderers().forEach((shaderType, renderer) -> {
			renderer.prepareShader();
			Map<MeshOGL, List<Entity>> meshOGLListMap = entitiesRendererMap.get(shaderType);
			if (meshOGLListMap != null) {
				meshOGLListMap.forEach((meshOGL,
				                        entities) -> {
					meshOGL.bind(meshOGL.getAttributesBinding());
					entities.forEach(Entity::render);
					meshOGL.unbind(meshOGL.getAttributesBinding());
				});
			}
			renderer.end();
		});
	}

	public void update() {
		camera.update();
		entitiesRendererMap.forEach(((shaderType, meshOGLListMap) -> meshOGLListMap.forEach((meshOGL, entities) -> entities.forEach(Behavior::update))));
		gameObjects.forEach(Behavior::update);
	}
}
