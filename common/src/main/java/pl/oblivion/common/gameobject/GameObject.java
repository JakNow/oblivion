package pl.oblivion.common.gameobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.common.gameobject.transform.Transformation;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public abstract class GameObject implements Transformation, Behavior {

	private Transform transform;

	private GameObjectType gameObjectType;

	private GameObject parent;

	private List<GameObject> children;

	private String name;

	public GameObject() {
		this.transform = new Transform();
		this.gameObjectType = GameObjectType.EMPTY;
		this.parent = null;
		this.children = new LinkedList<>();
		this.name = "No name";

		this.initialize();
	}

	private GameObject(GameObjectBuilder gameObjectBuilder) {
		this.transform = gameObjectBuilder.transform != null ? gameObjectBuilder.transform : new Transform();
		this.gameObjectType = gameObjectBuilder.gameObjectType != null ? gameObjectBuilder.gameObjectType : GameObjectType.EMPTY;
		this.children = gameObjectBuilder.children != null ? gameObjectBuilder.children : new LinkedList<>();
		this.parent = gameObjectBuilder.parent != null ? addParent(gameObjectBuilder.parent) : null;
		this.name = gameObjectBuilder.name != null ? gameObjectBuilder.name : "No name";

		this.initialize();
	}


	public GameObject addChild(GameObject child) {
		child.addParent(this);
		return child;
	}

	public GameObject addParent(GameObject parent) {
		if (Objects.nonNull(parent)) {
			this.parent = parent;
			this.parent.getChildren().add(this);
			return parent;
		}
		return null;
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

	@Override
	public Vector3f getPosition() {
		return this.transform.getPosition();
	}

	@Override
	public Vector3f getScale() {
		return this.transform.getScale();
	}

	@Override
	public Quaternionf getRotation() {
		return this.transform.getRotation();
	}

	@Override
	public void translate(float x, float y, float z) {
		this.transform.translate(x, y, z);
		this.children.forEach(child -> child.translate(x, y, z));
	}

	@Override
	public void translate(Vector3f direction) {
		this.transform.translate(direction);
		this.children.forEach(child -> child.translate(direction));
	}

	@Override
	public void scale(float x, float y, float z) {
		this.transform.scale(x, y, z);
		this.children.forEach(child -> child.scale(x, y, z));
	}

	@Override
	public void scale(Vector3f scale) {
		this.transform.scale(scale);
		this.children.forEach(child -> child.scale(scale));

	}

	@Override
	public void rotate(Vector3f axis, float angle) {
		this.transform.rotate(axis, angle);
		this.children.forEach(child -> child.rotate(axis, angle));
	}

	@Override
	public void rotate(float x, float y, float z) {
		this.transform.rotate(x, y, z);
		this.children.forEach(child -> child.rotate(x, y, z));

	}

	@Override
	public void rotate(Quaternionf quaternionf) {
		this.transform.rotate(quaternionf);
		this.children.forEach(child -> child.rotate(quaternionf));

	}

	@AllArgsConstructor
	@NoArgsConstructor
	public static class GameObjectBuilder {

		private Transform transform;
		private GameObjectType gameObjectType;
		private GameObject parent;
		private List<GameObject> children;
		private String name;


		public GameObjectBuilder setTransform(Transform transform) {
			this.transform = transform;
			return this;
		}

		public GameObjectBuilder setGameObjectType(GameObjectType gameObjectType) {
			this.gameObjectType = gameObjectType;
			return this;
		}

		public GameObjectBuilder setParent(GameObject parent) {
			this.parent = parent;
			return this;
		}

		public GameObjectBuilder setChildren(List<GameObject> children) {
			this.children = children;
			return this;
		}

		public GameObjectBuilder setName(String name) {
			this.name = name;
			return this;
		}

		GameObject build() {
			return new GameObject(this) {
			};
		}

	}
}
