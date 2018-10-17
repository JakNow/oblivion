package pl.oblivion.engine;

public class Timer {

  private double lastLoopTime;

  Timer() {
    this.init();
  }

  private void init() {
    lastLoopTime = getTime();
  }

  double getTime() {
    return System.nanoTime() / 1000_000_000.0;
  }

  float getElapsedTime() {
    double time = getTime();
    float elapsedTime = (float) (time - lastLoopTime);
    lastLoopTime = time;
    return elapsedTime;
  }

  double getLastLoopTime() {
    return lastLoopTime;
  }
}
