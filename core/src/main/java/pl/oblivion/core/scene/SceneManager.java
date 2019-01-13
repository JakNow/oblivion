package pl.oblivion.core.scene;

import lombok.Getter;
import pl.oblivion.core.MasterRenderer;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class SceneManager {

	private static SceneManager instance;

	private Scene activeScene;
	private Map<String, Scene> scenes;

	private SceneManager() {
		scenes = new LinkedHashMap<>();

	}

	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}

	public static void changeScene(Scene scene) {
		if (instance.activeScene != null) {
			instance.unloadScene();
		}
		instance.loadScene(scene);
	}

	private void loadScene(Scene scene) {
		instance.activeScene = scene.load();
	}

	private void unloadScene() {
		instance.activeScene.unload();
	}

	public void update() {
		instance.activeScene.update();
	}

	public void cleanUp() {
		unloadScene();
	}

	public void onStart() {
		instance.activeScene.load();
		MasterRenderer.getInstance().loadScene(instance.activeScene);
	}

	public void addScene(Scene scene) {
		if (scenes.isEmpty()) {
			this.activeScene = scene;
		}
		scenes.put(scene.getName(), scene);
	}

	public Scene getScene(String sceneName) {
		return scenes.get(sceneName);
	}

	public Scene getScene(Scene scene) {
		return getScene(scene.getName());
	}
}

