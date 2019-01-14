package pl.oblivion.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.engine.Timer;
import pl.oblivion.engine.Window;

import static pl.oblivion.common.utils.GetSystemProperty.getInt;

abstract class GameLoop {

	private static final Logger logger = LogManager.getLogger(GameLoop.class);

	private final Window window;
	private final int fps;

	GameLoop(Window window) {
		this.window = window;
		this.fps = getInt("engine.fps", 60);
	}

	void run() {
		logger.info("Running app");
		new Timer();
		float elapsedTime;
		float accumulator = 0f;
		int ups = 0, fps = 0;
		onStart();
		float startTime = Timer.getElapsedTime();

		while (!window.windowShouldClose()) {
			elapsedTime = Timer.getElapsedTime();
			accumulator += elapsedTime;
			startTime += elapsedTime;
			while (accumulator >= Timer.deltaTime) {
				update();
				accumulator -= Timer.deltaTime;
				ups++;
			}

			fps++;
			render();

			if (startTime > 1.0f) {
				System.out.println("FPS: " + fps);
				System.out.println("UPS: " + ups);
				fps = 0;
				ups = 0;
				startTime = 0;

			}

			window.updateAfterRendering();
			if (!window.isVSync()) {
				sync();
			}
		}
		end();
		window.destroy();
	}

	private void sync() {
		float loopSlot = 1f / fps;
		double endTime = Timer.getLastLoopTime() + loopSlot;
		while (Timer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				logger.warn("Thread sleep was interrupted. ", ie);
			}
		}
	}

	abstract void onStart();

	abstract void update();

	abstract void render();

	abstract void end();
}
