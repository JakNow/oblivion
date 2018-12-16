package pl.oblivion.core;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.common.annotations.AppConfigRunner;
import pl.oblivion.common.annotations.MissingAppConfigAnnotationException;
import pl.oblivion.core.scene.Scene;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.camera.PerspectiveCamera;
import pl.oblivion.engine.input.InputManager;
import pl.oblivion.engine.renderer.DiffuseRenderer;
import pl.oblivion.engine.renderer.RendererCache;

import static org.lwjgl.opengl.GL11.glViewport;
import static pl.oblivion.common.utils.GetSystemProperty.getInt;

public class Application {

  private static final Logger logger = LogManager.getLogger(Application.class);
  private static Application instance;

  private final Window window;
  private final Timer timer;
  @Getter private Camera camera;
  private int fps;
  private int ups;

  private Scene activeScene;
  @Getter private InputManager inputManager;

  private Application() {
    logger.info("WELCOME TO OBLIVION ENGINE!");
    logger.info("Starting the Application");
    this.window = new Window();
    this.timer = new Timer();
    this.camera = new PerspectiveCamera();
    this.inputManager = window.getInputManager();

    this.ups = getInt("engine.ups", 30);
    this.fps = getInt("engine.fps", 60);

    init();
  }

  public static void prepare(Class mainClass, String[] args) {
    AppConfig appConfig = (AppConfig) mainClass.getAnnotation(AppConfig.class);
    if (appConfig == null) throw new MissingAppConfigAnnotationException();

    new AppConfigRunner(appConfig.value());
    getInstance();
  }

  public static synchronized Application getInstance() {
    if (instance == null) {
      instance = new Application();
    }
    return instance;
  }

  public static void start() {
    getInstance().run();
  }

  public void setScene(Scene scene) {
    activeScene = scene;
  }

  private void init() {
    this.timer.getTime();
    RendererCache.getInstance().addRenderer(new DiffuseRenderer(camera));
  }

  private void run() {
    float elapsedTime;
    float accumulator = 0f;
    float interval = 1f / ups;

    while (!window.windowShouldClose()) {
      if (window.isResized()) {
        glViewport(0, 0, window.getWidth(), window.getHeight());
        camera.updateProjectionMatrix(window);
      }
      elapsedTime = timer.getElapsedTime();
      accumulator += elapsedTime;

      while (accumulator >= interval) {
        update(interval);
        accumulator -= interval;
      }

      activeScene.render();

      window.updateAfterRendering();
      if (!window.isVSync()) {
        sync();
      }
    }
    activeScene.delete();
    window.destroy();
  }

  private void sync() {
    float loopSlot = 1f / fps;
    double endTime = timer.getLastLoopTime() + loopSlot;
    while (timer.getTime() < endTime) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException ie) {
        logger.warn("Thread sleep was interrupted. ", ie);
      }
    }
  }

  private void update(float delta) {}
}
