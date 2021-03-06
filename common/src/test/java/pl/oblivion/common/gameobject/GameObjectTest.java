package pl.oblivion.common.gameobject;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameObjectTest {

	@Test
	public void addObjectWithNoParent_Test() {
		GameObject gameObject = defaultParent();

		assertThat(gameObject.getParent()).isNull();
	}

	@Test
	public void addParentByConstructor_Test() {
		GameObject parent = defaultParent();
		GameObject child = defaultChild(parent);

		assertThat(child.getParent()).isEqualTo(parent);
		assertThat(parent.getChildren()).hasSize(1).contains(child);
	}

	@Test
	public void addObjectToParent_Test() {
		GameObject parent = defaultParent();
		GameObject child = defaultChild(parent);

		assertThat(child.getParent()).isEqualTo(parent);
		assertThat(parent.getChildren().get(0)).isEqualTo(child);
	}

	@Test
	public void addParentToObject_Test() {
		GameObject parent = defaultParent();
		GameObject child = defaultChild(parent);

		assertThat(child.getParent()).isEqualTo(parent);
		assertThat(parent.getChildren().get(0)).isEqualTo(child);
	}

	@Test
	public void removeObjectFromParent_Test() {
		GameObject parent = defaultParent();
		GameObject child = defaultChild(parent);

		assertThat(parent.removeChild(child)).isTrue();
		assertThat(parent.getChildren().isEmpty()).isTrue();
		assertThat(child.getParent()).isNull();
	}

	@Test
	public void removeParentFromObject_Test() {
		GameObject parent = defaultParent();
		GameObject child = defaultChild(parent);

		assertThat(child.removeParent()).isTrue();
		assertThat(parent.getChildren().isEmpty()).isTrue();
		assertThat(child.getParent()).isNull();
	}

	private GameObject defaultParent() {
		return new GameObject.GameObjectBuilder().setName("parent").build();
	}

	private GameObject defaultChild(GameObject parent) {
		return new GameObject.GameObjectBuilder().setName("child").setParent(parent).build();

	}
}
