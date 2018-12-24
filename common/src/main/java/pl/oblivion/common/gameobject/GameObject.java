package pl.oblivion.common.gameobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.transform.Transform;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public abstract class GameObject {

	public final Transform transform;
	@Getter
	private final GameObjectType gameObjectType;
	@Getter
	@Setter
	private GameObject parent;
	@Getter
	@Setter
	private List<GameObject> children;
	@Getter
	@Setter
	private String name;

	public GameObject(
			String name, Transform transform, GameObject parent, GameObjectType gameObjectType) {
		this.name = name;
		this.addParent(parent);
		this.children = new LinkedList<>();
		this.transform = new Transform(transform, children);
		this.gameObjectType = gameObjectType;
	}

	public GameObject(String name, GameObjectType gameObjectType) {
		this.name = name;
		this.parent = null;
		this.children = new LinkedList<>();
		this.transform = new Transform(children);
		this.gameObjectType = gameObjectType;
	}

	public GameObject(GameObjectType gameObjectType) {
		this.name = "Game Object";
		this.parent = null;
		this.children = new LinkedList<>();
		this.transform = new Transform(children);
		this.gameObjectType = gameObjectType;
	}

	public GameObject(String name, Transform transform, GameObjectType gameObjectType) {
		this.name = name;
		this.parent = null;
		this.children = new LinkedList<>();
		this.transform = new Transform(transform, children);
		this.gameObjectType = gameObjectType;
	}

	public GameObject(String name, GameObject parent, GameObjectType gameObjectType) {
		this.name = name;
		this.children = new LinkedList<>();
		this.transform = new Transform(this.children);
		this.addParent(parent);
		this.gameObjectType = gameObjectType;
	}

	public boolean addChild(GameObject child) {
		child.addParent(this);
		return true;
	}

	public boolean addParent(GameObject parent) {
		if (Objects.nonNull(parent)) {
			this.parent = parent;
			this.parent.getChildren().add(this);
			this.transform.inheritTransformationFromParent(parent);
			return true;
		}
		return false;
	}

	public boolean removeChild(GameObject child) {
		child.setParent(null);
		this.getChildren().remove(child);
		return true;
	}

	public boolean removeParent() {
		this.parent.getChildren().remove(this);
		this.parent = null;
		return true;
	}
}
