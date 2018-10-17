package pl.oblivion.engine;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Application {

  private final Window window;
  @Getter @Setter private int width = 300;
  @Getter @Setter private int height = width * 9 / 16;
  @Setter private String title = "Default Title";
  @Getter @Setter private int ups = 60;
  @Getter @Setter private int fps = 60;

  
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

    this.window = new Window(width, height, title);
  }

  public void run() {
    System.out.println("Hello LWJGL " + Version.getVersion() + "!");

    this.window.init();
    loop();

    glfwFreeCallbacks(this.window.getWindow());
    glfwDestroyWindow(this.window.getWindow());

    glfwTerminate();
    glfwSetErrorCallback(null).free();
  }

  private void loop() {
    GL.createCapabilities();
    glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

    while (!glfwWindowShouldClose(this.window.getWindow())) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
      glfwSwapBuffers(this.window.getWindow());
      glfwPollEvents();
    }
  }
}
