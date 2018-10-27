package pl.oblivion.engine;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.annotations.AppConfigRunner;
import pl.oblivion.engine.renderer.RendererHandler;

import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClear;
import static pl.oblivion.common.utils.GetSystemProperty.*;

public abstract class Application implements Runnable {

  private static final Logger logger = LogManager.getLogger(Application.class);

  private final Window window;
  private final Timer timer;
  private final Thread gameLoopThread;
  @Getter
  private final RendererHandler rendererHandler;

  private int fps;
  private int ups;

  public Application() {
    logger.info("WELCOME TO OBLIVION ENGINE!");
    logger.info("Starting Application");
    this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
    new AppConfigRunner();
    this.window = new Window();
    this.timer = new Timer();
    this.rendererHandler = RendererHandler.getInstance();

    this.ups = getInt("engine.ups", 30);
    this.fps = getInt("engine.fps", 60);

    this.start();
  }

  private void start() {
    gameLoopThread.start();
  }

  @Override
  public void run() {
    init();
    gameloop();
  }

  private void init() {
    this.window.init();
    this.timer.getTime();
  }

  private void gameloop() {
    float elapsedTime;
    float accumulator = 0f;
    float interval = 1f / ups;

    while (!window.windowShouldClose()) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
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
