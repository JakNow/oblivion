package pl.oblivion.engine;

public class Application {

  private static Window window;
  private static Timer timer;

  private int width = 300;
  private int height = width * 9 / 16;
  private String title = "Default Title";
  private int ups = 60;
  private int fps = 60;

  public Application() {
    if (System.getProperty("window.width") != null) {
      width = Integer.parseInt(System.getProperty("window.width"));
    }
    if (System.getProperty("window.height") != null) {
      height = Integer.parseInt(System.getProperty("window.height"));
    }
    if (System.getProperty("window.title") != null) {
      title = System.getProperty("window.title");
    }
    if (System.getProperty("engine.ups") != null) {
      ups = Integer.parseInt(System.getProperty("engine.ups"));
    }
    if (System.getProperty("engine.fps") != null) {
      fps = Integer.parseInt(System.getProperty("engine.fps"));
    }

    window = new Window(width, height, title);
    timer = new Timer();
  }

  public void run() {
    float elapsedTimed;
    float accumulator = 0f;
    float interval = 1f / ups;

    while (!this.window.windowShouldClose()) {
      elapsedTimed = timer.getElapsedTime();
      accumulator += elapsedTimed;

      while (accumulator >= interval) {
        fixedUpdated(interval);
        accumulator -= interval;
      }

      update();
      if (!window.isvSync()) {
        sync();
      }

      window.updateAfterRendering();
    }

    window.destroy();
    cleanApplicationForClosure();
  }

  public void fixedUpdated(float delta) {
    // logic update for 60ups
  }

  public void update() {}

  public void cleanApplicationForClosure() {}

  private void sync() {
    float loopSlot = 1f / fps;
    double endTime = timer.getLastLoopTime() + loopSlot;
    while (timer.getTime() < endTime) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException ie) {
        // todo logger info
      }
    }
  }
}
