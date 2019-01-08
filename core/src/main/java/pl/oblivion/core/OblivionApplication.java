package pl.oblivion.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.configuration.ConfigurationLoader;
import pl.oblivion.core.scene.SceneManager;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.shader.DiffuseShader;
import pl.oblivion.engine.shader.ShaderCache;

public class OblivionApplication {

	private static final Logger logger = LogManager.getLogger(OblivionApplication.class);
	private GameLoop gameLoop;

	private SceneManager sceneManager;

	private OblivionApplication(Class<?> primarySource, String... args) {
		logger.info("WELCOME TO OBLIVION ENGINE!");
		logger.info("Starting the Oblivion Application...");

		ConfigurationLoader.loadConfiguration(primarySource);

		this.sceneManager = SceneManager.getInstance();

		this.gameLoop = new GameLoop() {

			@Override
			void update() {
				sceneManager.update();
			}

			@Override
			void render() {
				sceneManager.render();
			}

			@Override
			void destroy() {
				ShaderCache.getInstance().cleanShaders();
			}
		};
	}

	public static void start(Class<?> primarySource, String... args) {
		OblivionApplication oblivionApplication = new OblivionApplication(primarySource, args);

		ShaderCache.getInstance().addShader(new DiffuseShader(ShaderType.DIFFUSE_SHADER));

		oblivionApplication.run();
	}

	private void run() {
		gameLoop.run();
	}

}
