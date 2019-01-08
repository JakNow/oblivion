package pl.oblivion.core;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.core.scene.Scene;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.input.InputManager;
import pl.oblivion.engine.renderer.DiffuseRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.shader.DiffuseShader;
import pl.oblivion.engine.shader.ShaderCache;

import static org.lwjgl.opengl.GL11.glViewport;
import static pl.oblivion.common.utils.GetSystemProperty.getBoolean;
import static pl.oblivion.common.utils.GetSystemProperty.getInt;

public class ApplicationOld {

	private static final Logger logger = LogManager.getLogger(ApplicationOld.class);

	private final Window window;
	private int fps;
	private Scene scene;
	@Getter
	private InputManager inputManager;
	private Camera camera;
	private boolean showPerformance;


	public ApplicationOld(Class mainClass, Scene scene) {
		logger.info("WELCOME TO OBLIVION ENGINE!");
		logger.info("Starting the ApplicationOld");
		this.window = new Window();
		new Timer();
		this.scene = scene;
		this.inputManager = window.getInputManager();
		this.showPerformance = getBoolean("engine.show-performance", false);
		this.fps = getInt("engine.fps", 60);

		initShaders();
	}


/*
	private void loadConfig(Class mainClass) {
		AppConfig appConfig = (AppConfig) mainClass.getAnnotation(AppConfig.class);
		if (appConfig == null) throw new MissingOblivionConfigurationAnnotationException();

		new AppConfigRunner(appConfig.value());
	}*/

	public void start() {
		this.run();
	}

	private void initShaders() {
		ShaderCache.getInstance().addShader(new DiffuseShader(ShaderType.DIFFUSE_SHADER));
		RendererCache.getInstance().addRenderer(new DiffuseRenderer(camera));
	}

	private void run() {
		float elapsedTime;
		float accumulator = 0f;
		double start = Timer.getElapsedTime();
		int fps = 0;
		int ups = 0;
		while (!window.windowShouldClose()) {
			if (window.isResized()) {
				glViewport(0, 0, window.getWidth(), window.getHeight());
				camera.updateProjectionMatrix(window);
			}
			elapsedTime = Timer.getElapsedTime();
			accumulator += elapsedTime;
			while (accumulator >= Timer.deltaTime) {
				update();
				accumulator -= Timer.deltaTime;
				ups++;
			}

			//scene.render();
			fps++;

			window.updateAfterRendering();
			if (!window.isVSync()) {
				sync();
			}
			if (showPerformance && (start += elapsedTime) >= 1.0f) {
				logger.info("UPS: {} | FPS: {}", ups, fps);
				ups = 0;
				fps = 0;
				start = 0;
			}
		}
		//scene.delete();
		window.destroy();
	}

	private void sync() {
		float loopSlot = 1f / fps;
		double endTime = Timer.getLastLoopTime() + loopSlot;
		while (Timer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				logger.warn("Thread sleep was interrupted. ", ie);
			}
		}
	}

	private void update() {
		//scene.update();
	}
}
