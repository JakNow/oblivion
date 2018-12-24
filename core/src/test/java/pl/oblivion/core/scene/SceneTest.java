package pl.oblivion.core.scene;

import org.junit.Test;
import pl.oblivion.core.assets.spaceobjects.Cube;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SceneTest {

	@Test
	public void addObjectToScene() {
		Scene scene = new Scene();
		scene.addToScene(new Cube());

		assertThat(scene.getRawEntities().size()).isEqualTo(1);
	}

	@Test
	public void addObjectWithChildrenToScene() {
		Scene scene = new Scene();
		Cube cube = new Cube();
		cube.addChild(new Cube());

		scene.addToScene(cube);
		assertThat(scene.getRawEntities().size()).isEqualTo(2);
	}

	@Test
	public void addObjectHierarchyToScene() {
		Scene scene = new Scene();
		Cube parent = new Cube();
		Cube child = new Cube();
		Cube grandChild = new Cube();
		child.addChild(grandChild);
		parent.addChild(child);

		scene.addToScene(parent);
		assertThat(scene.getRawEntities().size()).isEqualTo(3);
	}
}
