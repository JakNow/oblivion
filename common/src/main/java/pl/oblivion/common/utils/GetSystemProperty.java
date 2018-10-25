package pl.oblivion.common.utils;

public class GetSystemProperty {

  public static int getInt(String key, int defaultValue) {
    return Integer.getInteger(key) != null ? Integer.getInteger(key) : defaultValue;
  }

  public static float getFloat(String key, float defaultValue) {
    String prop = System.getProperty(key);
    return prop != null ? Float.parseFloat(prop) : defaultValue;
  }

  public static double getDouble(String key, double defaultValue) {
    String prop = System.getProperty(key);
    return prop != null ? Double.parseDouble(prop) : defaultValue;
  }

  public static char getChar(String key, char defaultValue) {
    String prop = System.getProperty(key);
    return prop != null ? prop.charAt(0) : defaultValue;
  }

  public static String getString(String key, String defaultValue) {
    String prop = System.getProperty(key);
    return prop != null ? prop : defaultValue;
  }
  
  public static boolean getBoolean(String key, boolean defaultValue){
    String prop = System.getProperty(key);
    return prop != null ? Boolean.getBoolean(key) : defaultValue;
   }
}
