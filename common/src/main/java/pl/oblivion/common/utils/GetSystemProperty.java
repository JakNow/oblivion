package pl.oblivion.common.utils;

import static java.util.Objects.nonNull;

public class GetSystemProperty {

  public static int getInt(String key, int defaultValue) {
    return nonNull(Integer.getInteger(key)) ? Integer.getInteger(key) : defaultValue;
  }

  public static float getFloat(String key, float defaultValue) {
    String prop = System.getProperty(key);
    return nonNull(prop) ? Float.parseFloat(prop) : defaultValue;
  }

  public static double getDouble(String key, double defaultValue) {
    String prop = System.getProperty(key);
    return nonNull(prop) ? Double.parseDouble(prop) : defaultValue;
  }

  public static char getChar(String key, char defaultValue) {
    String prop = System.getProperty(key);
    return nonNull(prop) ? prop.charAt(0) : defaultValue;
  }

  public static String getString(String key, String defaultValue) {
    String prop = System.getProperty(key);
    return nonNull(prop) ? prop : defaultValue;
  }

  public static boolean getBoolean(String key, boolean defaultValue) {
    String prop = System.getProperty(key);
    return nonNull(prop) ? Boolean.getBoolean(key) : defaultValue;
  }
}
