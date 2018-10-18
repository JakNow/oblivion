package pl.oblivion.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

  private int width;
  private int height;
  private String title;
  private long window;
  private boolean vSync;

  Window() {
    this.width =
        Integer.getInteger("window.width") != null ? Integer.getInteger("window.width") : 600;
    this.height =
        Integer.getInteger("window.height") != null
            ? Integer.getInteger("window.height")
            : 600 * 9 / 16;
    this.title =
        System.getProperty("window.title") != null
            ? System.getProperty("window.title")
            : "Default title";
    this.vSync = true;
  }

  protected void init() {
    GLFWErrorCallback.createPrint(System.err).set();

    if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

    glfwDefaultWindowHints();
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

    this.window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
    if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

    glfwSetKeyCallback(
        window,
        (window, key, scancode, action, mods) -> {
          if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            glfwSetWindowShouldClose(window, true);
        });

    try (MemoryStack stack = stackPush()) {
      IntBuffer pWidth = stack.mallocInt(1);
      IntBuffer pHeight = stack.mallocInt(1);

      glfwGetWindowSize(window, pWidth, pHeight);

      GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

      glfwSetWindowPos(
          window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
    }

    glfwMakeContextCurrent(window);
    if (isvSync()) {
      glfwSwapInterval(1);
    }

    glfwShowWindow(window);
    GL.createCapabilities();

    glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
  }

  public long getWindow() {
    return window;
  }

  public boolean windowShouldClose() {
    return glfwWindowShouldClose(window);
  }

  protected void updateAfterRendering() {
    glfwSwapBuffers(window); // swap the color buffers
    glfwPollEvents();
  }

  protected void destroy() {
    // Free the window callbacks and destroy the window
    glfwFreeCallbacks(window);
    glfwDestroyWindow(window);

    // Terminate GLFW and free the error callback
    glfwTerminate();
    glfwSetErrorCallback(null).free();
  }

  public boolean isvSync() {
    return vSync;
  }
}