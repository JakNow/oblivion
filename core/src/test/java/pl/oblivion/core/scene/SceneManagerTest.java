package pl.oblivion.core.scene;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SceneManagerTest {

	@Test
	public void addSceneToSceneManager() {
		//given
		String sceneName = "New Scene 1";
		new Scene(sceneName);

		//when
		Scene scene = SceneManager.getInstance().getScene(sceneName);
		Map<String, Scene> scenes = null;//SceneManager.getInstance().getScenes();

		//then
		assertThat(scenes.size()).isEqualTo(1);
		assertThat(scene).isNotNull();
		assertThat(scene.getName()).isEqualTo(sceneName);
	}

	@Test(expected = MissingSceneException.class)
	public void getMissingScene_ThrowException() {
		assertThat(SceneManager.getInstance().getScene("MissingScene")).isNull();
	}

	@Test(expected = SceneAlreadyExistsException.class)
	public void addSceneWithSameName_ThrowException() {
		String sceneName = "New Scene 1";
		new Scene(sceneName);
		new Scene(sceneName);
	}
}
