package pl.oblivion.common.utils;

public class Clamp {

  public static int minmax(int target, int min, int max) {
    if (target < min) {
      return min;
    } else if (target > max) {
      return max;
    } else {
      return target;
    }
  }

  public static int min(int target, int min) {
    if (target < min) {
      return min;
    } else {
      return target;
    }
  }

  public static int max(int target, int max) {
    if (target > max) {
      return max;
    } else {
      return target;
    }
  }
}
