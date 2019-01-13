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
		onStart();

		while (!window.windowShouldClose()) {
			elapsedTime = Timer.getElapsedTime();
			accumulator += elapsedTime;
			while (accumulator >= Timer.deltaTime) {
				update();
				accumulator -= Timer.deltaTime;
			}

			render();

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
