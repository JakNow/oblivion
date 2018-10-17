package pl.oblivion.engine;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

  @Getter @Setter private int width;
  @Getter @Setter private int height;
  @Setter(AccessLevel.PROTECTED) private String title;
  @Getter @Setter private long window;

  Window(int width, int height, String title) {
    this.width = width;
    this.height = height;
    this.title = title;
  }

  public void init() {
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
    glfwSwapInterval(1);

    glfwShowWindow(window);
  }

  public long getWindow() {
    return window;
  }
}
