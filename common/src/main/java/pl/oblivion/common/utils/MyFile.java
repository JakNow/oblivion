package pl.oblivion.common.utils;

import java.io.BufferedReader;
import static java.io.File.separator;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyFile {

  private static final Logger logger = LogManager.getLogger(MyFile.class);
  private static final String separator = "/";

  private String path;
  private String name;

  private BufferedReader bufferedReader;

  public MyFile(String path) {
    this.path = path.startsWith(separator) ? path : separator + path;
    String[] dirs = path.split(separator);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(String... paths) {
    StringBuilder sb = new StringBuilder();
    for (String path : paths) {
      if (path.startsWith(separator)) {
        sb.append(path);
      } else {
        sb.append(separator).append(path);
      }
      if (path.endsWith(separator)) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
    this.path = sb.toString();
    String[] dirs = path.split(separator);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(MyFile file, String subFile) {
    this.path =
        file.path + (subFile.startsWith(separator) ? subFile : separator + subFile);
    String[] dirs = path.split(separator);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(MyFile file, String... subFiles) {
    StringBuilder sb = new StringBuilder(file.path);
    for (String path : subFiles) {
      if (path.startsWith(separator)) {
        sb.append(path);
      } else {
        sb.append(separator).append(path);
      }
      if (path.endsWith(separator)) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
    this.path = sb.toString();
    String[] dirs = path.split(separator);
    this.name = dirs[dirs.length - 1];
  }

  @Override
  public String toString() {
    return getPath();
  }

  public String getPath() {
    return path;
  }

  public BufferedReader getReader() {
    bufferedReader =
        new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream(path)));
    return bufferedReader;
  }

  public void closeReader() {
    if (bufferedReader != null) {
      try {
        bufferedReader.close();
      } catch (IOException e) {
        logger.error("Couldn't close reader", e);
      }
    }
  }

  public String getName() {
    return name;
  }
}