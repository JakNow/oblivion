package pl.oblivion.core;

import static pl.oblivion.common.utils.GetSystemProperty.getInt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;
import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.common.annotations.AppConfigRunner;
import pl.oblivion.engine.Camera;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.Window;

public class Application {

  private static final Logger logger = LogManager.getLogger(Application.class);

  private final Window window;
  private final Timer timer;
  private final Camera camera;
  @Getter private final RendererHandler rendererHandler;

  private int fps;
  private int ups;

  private Application() {
    logger.info("WELCOME TO OBLIVION ENGINE!");
    logger.info("Starting the Application");
    this.window = new Window();
    this.timer = new Timer();
    this.camera = new Camera(null);
    this.rendererHandler = RendererHandler.getInstance();

    this.ups = getInt("engine.ups", 30);
    this.fps = getInt("engine.fps", 60);

    init();
  }

  public static void start(Class mainClass, String[] args) {
    AppConfig appConfig = (AppConfig) mainClass.getAnnotation(AppConfig.class);
    new AppConfigRunner(appConfig.value());
    new Application().run();
  }

  private void init() {
    this.window.init();
    this.timer.getTime();
    this.rendererHandler.initRenderers(this.window, this.camera);
  }

  private void run() {
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
