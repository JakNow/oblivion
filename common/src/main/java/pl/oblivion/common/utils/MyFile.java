package pl.oblivion.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFile {

  private static final Logger logger = LogManager.getLogger(MyFile.class);
  private static final String FILE_SEPARATOR = "/";

  private String path;
  private String name;

  private BufferedReader bufferedReader;

  public MyFile(String path) {
    this.path = path;
    String[] dirs = path.split(FILE_SEPARATOR);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(String... paths) {
    this.path = "";
    int length = paths.length;
    for (int i = 0; i < length - 1; i++) {
      this.path += paths[i] + FILE_SEPARATOR; // todo replace with StringBuilder
    }
    this.path += paths[length - 1];
    String[] dirs = path.split(FILE_SEPARATOR);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(MyFile file, String subFile) {
    this.path = file.path + FILE_SEPARATOR + subFile;
    this.name = subFile;
  }

  public MyFile(MyFile file, String... subFiles) {
    this.path = file.path;
    int length = subFiles.length;
    for (int i = 0; i < length - 1; i++) {
      this.path += subFiles[i] + FILE_SEPARATOR; // todo replace with StringBuilder
    }
    this.path += subFiles[length - 1];
    String[] dirs = path.split(FILE_SEPARATOR);
    this.name = dirs[dirs.length - 1];
  }

  @Override
  public String toString() {
    return getPath();
  }

  public String getPath() {
    return path;
  }

  public BufferedReader getReader(){
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
