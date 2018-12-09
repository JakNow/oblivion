package pl.oblivion.core;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.common.annotations.AppConfigRunner;
import pl.oblivion.common.annotations.MissingAppConfigAnnotationException;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.scene.Scene;

import static pl.oblivion.common.utils.GetSystemProperty.getInt;

public class Application {

  private static final Logger logger = LogManager.getLogger(Application.class);
  private static Application instance;

  private final Window window;
  private final Timer timer;
  @Getter private final RendererHandler rendererHandler;
  @Getter @Setter private Scene activeScene;
  private int fps;
  private int ups;

  private Application() {
    logger.info("WELCOME TO OBLIVION ENGINE!");
    logger.info("Starting the Application");
    this.window = new Window();
    this.timer = new Timer();
    this.rendererHandler = RendererHandler.getInstance();

    this.ups = getInt("engine.ups", 30);
    this.fps = getInt("engine.fps", 60);

    init();
  }

  public static void prepare(Class mainClass, String[] args) {
    AppConfig appConfig = (AppConfig) mainClass.getAnnotation(AppConfig.class);
    if (appConfig == null) throw new MissingAppConfigAnnotationException();

    new AppConfigRunner(appConfig.value());
  }

  public static synchronized Application getInstance() {
    if (instance == null) {
      instance = new Application();
    }
    return instance;
  }

  public static void start() {
    instance.run();
  }

  private void init() {
    this.window.init();
    this.timer.getTime();
  }

  private void run() {
    this.rendererHandler.initRenderers(this.window, this.activeScene);

    float elapsedTime;
    float accumulator = 0f;
    float interval = 1f / ups;

    while (!window.windowShouldClose()) {
      elapsedTime = timer.getElapsedTime();
      accumulator += elapsedTime;

      while (accumulator >= interval) {
        update(interval);
        accumulator -= interval;
      }

      rendererHandler.render();

      window.updateAfterRendering();
      if (!window.isVSync()) {
        sync();
      }
    }
    rendererHandler.delete();
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
