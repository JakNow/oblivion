package pl.oblivion.engine;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import pl.oblivion.engine.input.InputManager;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import static pl.oblivion.common.utils.GetSystemProperty.getInt;
import static pl.oblivion.common.utils.GetSystemProperty.getString;

public class Window {

	private static final Logger logger = LogManager.getLogger(Window.class);
	@Getter
	private int width;
	@Getter
	private int height;
	private String title;
	private long window;
	private boolean vSync;
	private boolean resized;

	@Getter
	private InputManager inputManager;

	public Window() {
		logger.info("Creating window...");
		this.width = getInt("window.width", 600);
		this.height = getInt("window.height", this.width * 9 / 16);
		this.title = getString("window.title", "Default title");
		this.vSync = true;
		this.inputManager = new InputManager();
		this.resized = false;
		this.init();
		logger.info("Window created {}", this);
	}

	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit()) {
			logger.error("Unable to initialize GLFW");
			throw new IllegalStateException();
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		this.window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

		if (window == NULL) {
			logger.error("Failed to create the GLFW window");
			throw new RuntimeException();
		}

		glfwSetFramebufferSizeCallback(
				window,
				(window, width, height) -> {
					this.width = width;
					this.height = height;
					this.setResized(true);
				});

		glfwSetKeyCallback(window, inputManager);

		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(
					window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}

		glfwMakeContextCurrent(window);
		if (this.isVSync()) {
			glfwSwapInterval(1);
		}

		glfwShowWindow(window);
		GL.createCapabilities();

		glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
	}

	public boolean windowShouldClose() {
		return glfwWindowShouldClose(window);
	}

	public void updateAfterRendering() {
		glfwSwapBuffers(window); // swap the color buffers
		glfwPollEvents();
	}

	public void destroy() {
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();

		logger.info("Terminating GLFW");
	}

	public boolean isVSync() {
		return this.vSync;
	}

	public boolean isResized() {
		return this.resized;
	}

	public void setResized(boolean resized) {
		this.resized = resized;
	}

	@Override
	public String toString() {
		return "Window{"
				+ "width="
				+ width
				+ ", height="
				+ height
				+ ", title='"
				+ title
				+ '\''
				+ ", window="
				+ window
				+ '}';
	}
}
