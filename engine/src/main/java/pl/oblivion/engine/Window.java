package pl.oblivion.engine;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.camera.OrthographicCamera;
import pl.oblivion.engine.camera.PerspectiveCamera;

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
  private final Matrix4f projectionMatrix;
  @Getter private int width;
  @Getter private int height;
  private String title;
  private long window;
  private boolean vSync;
  private boolean resized;

  public Window() {
    this.width = getInt("window.width", 600);
    this.height = getInt("window.height", (600 * 9 / 16));
    this.title = getString("window.title", "Default title");
    this.vSync = true;
    this.projectionMatrix = new Matrix4f();
    this.resized = false;
  }

  public void init() {
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

  public void updateProjectionMatrix(Camera camera) {
    logger.info("Updating projection matrix due to resize.");
    switch (camera.getCameraType()) {
      case PERSPECTIVE:
        updatePerspectiveProjection((PerspectiveCamera) camera);
        break;
      case ORTHOGRAPHIC:
        updateOrthographicProjection((OrthographicCamera) camera);
        break;
    }
  }

  private void updatePerspectiveProjection(PerspectiveCamera camera) {
    float aspectRatio = (float) width / (float) height;
    this.projectionMatrix.setPerspective(
        camera.getFieldOfView(), aspectRatio, camera.getNearPlane(), camera.getFarPlane());
    this.setResized(false);
    logger.info("Perspective camera updated");
  }

  private void updateOrthographicProjection(OrthographicCamera camera) {
    float aspectRatio = (float) width / (float) height;
    this.projectionMatrix.setOrtho(
        camera.getLeft(),
        camera.getRight(),
        camera.getBottom() * aspectRatio,
        camera.getTop() * aspectRatio,
        camera.getNearPlane(),
        camera.getFarPlane());
    this.setResized(false);
    logger.info("Orthographic camera updated");
  }

  public boolean isVSync() {
    return this.vSync;
  }

  public Matrix4f getProjectionMatrix() {
    return this.projectionMatrix;
  }

  public boolean isResized() {
    return this.resized;
  }

  public void setResized(boolean resized) {
    this.resized = resized;
  }
}
