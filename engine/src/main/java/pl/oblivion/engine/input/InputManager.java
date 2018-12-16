package pl.oblivion.engine.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class InputManager extends GLFWKeyCallback {

  private static final Logger logger = LogManager.getLogger(InputManager.class);
  private static boolean[] keys = new boolean[65536];
  private static Map<String, KeyCode> keyCodes;

  public InputManager() {
    keyCodes = new HashMap<>();
    logger.info("Adding binding to keys...");
    Arrays.asList(KeyCode.class.getEnumConstants()).forEach(this::putKeyCodeToMap);
    logger.info("Added binding to kes.");
  }

  public static int getKey(KeyCode keyCode) {
    logger.debug("Pressing {} key", keyCode.getKeyAlias());
    if (keys[keyCode.getGLFW_KeyCode()]) {
      return 1;
    }
    return 0;
  }

  public static int getKey(String name) {
    logger.debug("Pressing {} key", name);
    return keyCodes.get(name.toUpperCase()) != null ? getKey(keyCodes.get(name.toUpperCase())) : 0;
  }

  @Override
  public void invoke(long window, int key, int scancode, int action, int mods) {
    keys[key] = action != GLFW_RELEASE;
  }

  private void putKeyCodeToMap(KeyCode keyCode) {
    if (nonNull(keyCode)) {
      keyCodes.put(keyCode.getKeyAlias().toUpperCase(), keyCode);
      logger.info("Adding {} to InputManager.", keyCode);
    }
  }
}
