package pl.oblivion.engine.input;

import lombok.Getter;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

@Getter
public enum KeyCode {
  ESCAPE("Escape", GLFW_KEY_ESCAPE),
  SPACE_BAR("Space_bar", GLFW_KEY_SPACE);

  private String keyAlias;
  private int GLFW_KeyCode;

  KeyCode(String keyAlias, int GLFW_KeyCode) {
    this.keyAlias = keyAlias;
    this.GLFW_KeyCode = GLFW_KeyCode;
  }
}
