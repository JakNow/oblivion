package pl.oblivion.engine;

import lombok.Getter;
import lombok.Setter;
import pl.oblivion.core.AppConfigRunner;

public abstract class Application implements Runnable {

  private final Window window;
  private final Timer timer;
  private final Thread gameLoopThread;
  @Getter @Setter private int fps;
  @Getter @Setter private int ups;

  public Application() {
    this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
    new AppConfigRunner();
    this.window = new Window();
    this.timer = new Timer();
    this.ups = Integer.getInteger("engine.ups") != null ? Integer.getInteger("engine.ups") : 30;
    this.fps = Integer.getInteger("engine.fps") != null ? Integer.getInteger("engine.fps") : 60;
  }

  public void start() {
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
      elapsedTime = timer.getElapsedTime();
      accumulator += elapsedTime;

      while (accumulator >= interval) {
        update(interval);
        accumulator -= interval;
      }

      render();

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
        ie.printStackTrace();
      }
    }
  }

  protected abstract void update(float delta);

  protected abstract void render();
}
