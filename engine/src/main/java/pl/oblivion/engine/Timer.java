package pl.oblivion.engine;

import static pl.oblivion.common.utils.GetSystemProperty.getInt;

public class Timer {

  private double lastLoopTime;
    
    public Timer() {
    this.init();
  }
    
    public static float deltaTime = 1f / getInt("engine.ups", 30);
  
  private void init() {
    lastLoopTime = getTime();
  }

  public double getTime() {
    return System.nanoTime() / 1000_000_000.0;
  }

  public float getElapsedTime() {
    double time = getTime();
    float elapsedTime = (float) (time - lastLoopTime);
    lastLoopTime = time;
    return elapsedTime;
  }

  public double getLastLoopTime() {
    return lastLoopTime;
  }
}
