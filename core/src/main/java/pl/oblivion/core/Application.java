package pl.oblivion.core;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.annotations.AppConfigRunner;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.Window;

import static pl.oblivion.common.utils.GetSystemProperty.getInt;

public abstract class Application {

  private static final Logger logger = LogManager.getLogger(Application.class);

  private final Window window;
  private final Timer timer;
  @Getter
  private final RendererHandler rendererHandler;

  private int fps;
  private int ups;

  protected Application() {
    logger.info("WELCOME TO OBLIVION ENGINE!");
    logger.info("Starting the Application");
    new AppConfigRunner();
    this.window = new Window();
    this.timer = new Timer();
    this.rendererHandler = RendererHandler.getInstance();

    this.ups = getInt("engine.ups", 30);
    this.fps = getInt("engine.fps", 60);

    init();
  }

  private void init() {
    this.window.init();
    this.timer.getTime();
  }

  public void run() {
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
      if (!window.isvSync()) {
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

  protected abstract void update(float delta);
  
}
