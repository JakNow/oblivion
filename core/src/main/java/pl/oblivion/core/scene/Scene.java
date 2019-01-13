package pl.oblivion.core.scene;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.core.assets.Entity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Scene {

	private String name;
	private List<GameObject> rawObjects;
	private List<Entity> processedObjects;

	public Scene() {
		this.name = "New Scene";
		this.rawObjects = new ArrayList<>();
		this.processedObjects = new ArrayList<>();
		SceneManager.getInstance().addScene(this);
	}

	public Scene(String name) {
		this.name = name;
		this.rawObjects = new ArrayList<>();
		this.processedObjects = new ArrayList<>();
		SceneManager.getInstance().addScene(this);
	}

	public void update() {
		this.rawObjects.forEach(GameObject::update);
	}

	public Scene load() {
		rawObjects.forEach(rawGameObject -> {
			if (rawGameObject instanceof Entity) {
				Entity entity = (Entity) rawGameObject;
				this.processedObjects.add(entity.load());
			}
		});
		return this;
	}

	public void unload() {
		this.processedObjects.forEach(Entity::unload);
	}

	public void addToScene(GameObject gameObject) {
		rawObjects.add(gameObject);
		gameObject.getChildren().forEach(this::addToScene);
	}
}
