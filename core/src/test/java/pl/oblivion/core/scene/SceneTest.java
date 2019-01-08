package pl.oblivion.core.scene;

import org.junit.Test;
import pl.oblivion.common.gameobject.GameObject;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SceneTest {

	@Test
	public void addObjectToScene() {
		Scene scene = new Scene();
		GameObject gameObject = new GameObject.GameObjectBuilder().build();
		scene.addToScene(gameObject);

		assertThat(scene.getRawObjects().size()).isEqualTo(1);
	}

	@Test
	public void addObjectWithChildrenToScene() {
		Scene scene = new Scene();
		GameObject gameObject = new GameObject.GameObjectBuilder().build();
		GameObject childOne = new GameObject.GameObjectBuilder().build();
		GameObject childTwo = new GameObject.GameObjectBuilder().build();
		GameObject childThree = new GameObject.GameObjectBuilder().build();
		GameObject childOfThird = new GameObject.GameObjectBuilder().build();

		childThree.addChild(childOfThird);

		gameObject.addChild(childOne);
		gameObject.addChild(childTwo);
		gameObject.addChild(childThree);

		scene.addToScene(gameObject);
		assertThat(scene.getRawObjects().size()).isEqualTo(5);
	}

	/*@Test
	public void loadScene(){
		Scene scene = new Scene();
		scene.addToScene(new Cube());

		scene.load();
		assertThat(scene.getEntities().size()).isEqualTo(1);
	}*/

	@Test
	public void unloadScene() {

	}
}
