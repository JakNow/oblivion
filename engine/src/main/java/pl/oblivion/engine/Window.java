package pl.oblivion.engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.awt.*;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window{

  int width = 360;
  If(System.getProperty("window.width") != null){
    width =Integer.parseInt(System. getProperty("window.width"));
  }
  int heigh = 500;
  If(System.getProperty("window.heigh") != null){
    heigh =Integer.parseInt(System. getProperty("window.heigh"));
  }
  String title = "Oblivion";
  If(System.getProperty("window.title") != null){
    title = System. getProperty("window.heigh");
  }
	
	public Window(int width,int heigh,String title){
		this.width=width;
		this.heigh=heigh;
		this.title=title;
	}
        
        public Window(){
            
        }

  private long window;
	
  public void init() {
    GLFWErrorCallback.createPrint(System.err).set();

    if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

    glfwDefaultWindowHints();
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

    window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
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

  
}	
