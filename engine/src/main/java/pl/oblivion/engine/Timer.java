package pl.oblivion.engine;

import static pl.oblivion.common.utils.GetSystemProperty.getInt;

public class Timer {

	public static float deltaTime = 1f / getInt("engine.ups", 30);
	private static double lastLoopTime;

	public Timer() {
		this.init();
	}

	public static double getTime() {
		return System.nanoTime() / 1000_000_000.0;
	}

	public static float getElapsedTime() {
		double time = getTime();
		float elapsedTime = (float) (time - lastLoopTime);
		lastLoopTime = time;
		return elapsedTime;
	}

	public static double getLastLoopTime() {
		return lastLoopTime;
	}

	private void init() {
		lastLoopTime = getTime();
	}
}
