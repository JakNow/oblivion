package pl.oblivion.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.configuration.ConfigurationLoader;
import pl.oblivion.core.scene.SceneManager;
import pl.oblivion.engine.Window;

public class OblivionApplication {

	private static final Logger logger = LogManager.getLogger(OblivionApplication.class);
	private GameLoop gameLoop;

	private SceneManager sceneManager;
	private MasterRenderer masterRenderer;
	private Window window;

	private OblivionApplication(Class<?> primarySource, String... args) {
		logger.info("WELCOME TO OBLIVION ENGINE!");
		logger.info("Starting the Oblivion Application...");

		ConfigurationLoader.loadConfiguration(primarySource);

		this.window = new Window();

		this.masterRenderer = MasterRenderer.getInstance();
		this.sceneManager = SceneManager.getInstance();

		this.gameLoop = new GameLoop(window) {

			@Override
			void onStart() {
				sceneManager.onStart();
			}

			@Override
			void update() {
				sceneManager.update();
			}

			@Override
			void render() {
				masterRenderer.prepareScene();
				masterRenderer.render();
			}

			@Override
			void end() {
				sceneManager.cleanUp();
				masterRenderer.cleanUp();
			}
		};
	}

	public static void start(Class<?> primarySource, String... args) {
		OblivionApplication oblivionApplication = new OblivionApplication(primarySource, args);

		oblivionApplication.run();
	}

	private void run() {
		gameLoop.run();
	}

}
