package pl.oblivion.core.scene;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Scene {

	private static final Logger logger = LogManager.getLogger(Scene.class);

	private List<GameObject> rawObjects;
	private List<Entity> entities;

	public Scene() {
		this.rawObjects = new ArrayList<>();
		this.entities = new ArrayList<>();
	}

	void render() {
		entities.forEach(Entity::render);
	}

	void update() {
		entities.forEach(Entity::update);
	}

	void delete() {
		entities.forEach(Entity::delete);
	}

	Scene load() {
		logger.info("Loading Scene objects. RawObjects={}.", rawObjects.size());
		for (GameObject gameObject : rawObjects) {
			logger.info("Adding GameObject = {}.", gameObject);
			if (gameObject instanceof Entity) {
				logger.info("Added new Entity.");
				this.entities.add(((Entity) gameObject).init());
			}
		}
		logger.info("Scene was loaded.");
		return this;
	}

	void unload() {
		for (Entity entity : entities) {
			entity.delete();
		}
	}

	void transitionIn() {

	}

	void transitionOut() {

	}

	public void addToScene(GameObject gameObject) {
		rawObjects.add(gameObject);
		gameObject.getChildren().forEach(this::addToScene);
	}
}
