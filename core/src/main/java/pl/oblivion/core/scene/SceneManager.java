package pl.oblivion.core.scene;

public class SceneManager {

	private static SceneManager instance;

	private Scene activeScene;

	private boolean changeScene = false;

	private SceneManager() {
	}

	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}

	public static void setActiveScene(Scene scene) {
		if (SceneManager.getInstance().activeScene != null) {
			instance.activeScene.unload();
		}
		instance.changeScene = true;
		instance.activeScene = scene;
	}

	public void render() {
		if (activeScene != null && !changeScene) {
			activeScene.render();
		}
	}

	public void update() {
		if (activeScene != null) {
			activeScene.update();
		}
		if (changeScene && activeScene != null) {
			activeScene.load();
			changeScene = false;
		}
	}

	public void delete() {
		if (activeScene != null) {
			activeScene.delete();
		}
	}
}

