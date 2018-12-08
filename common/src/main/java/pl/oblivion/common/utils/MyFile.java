package pl.oblivion.common.utils;

import java.io.BufferedReader;
import static java.io.File.separator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyFile {

  private static final Logger logger = LogManager.getLogger(MyFile.class);
  private static final String FILE_SEPARATOR = "/";

  private String path;
  private String name;

  private BufferedReader bufferedReader;
  
  private InputStream inputstream;

  public MyFile(String path) {
    this.path = path.startsWith(FILE_SEPARATOR) ? path : FILE_SEPARATOR + path;
    String[] dirs = path.split(FILE_SEPARATOR);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(String... paths) {
    StringBuilder sb = new StringBuilder();
    for (String path : paths) {
      if (path.startsWith(FILE_SEPARATOR)) {
        sb.append(path);
      } else {
        sb.append(FILE_SEPARATOR).append(path);
      }
      if (path.endsWith(FILE_SEPARATOR)) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
    this.path = sb.toString();
    String[] dirs = path.split(FILE_SEPARATOR);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(MyFile file, String subFile) {
    this.path =
        file.path + (subFile.startsWith(FILE_SEPARATOR) ? subFile : FILE_SEPARATOR + subFile);
    String[] dirs = path.split(FILE_SEPARATOR);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(MyFile file, String... subFiles) {
    StringBuilder sb = new StringBuilder(file.path);
    for (String path : subFiles) {
      if (path.startsWith(FILE_SEPARATOR)) {
        sb.append(path);
      } else {
        sb.append(FILE_SEPARATOR).append(path);
      }
      if (path.endsWith(FILE_SEPARATOR)) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
    this.path = sb.toString();
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

  public BufferedReader getReader() {
    bufferedReader =
        new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream(path)));
    return bufferedReader;
  }
  

  public InputStream getInputStream() throws IOException {
      String path2 = read(new InputStreamReader(Class.class.getResourceAsStream(path)));
      inputstream = new FileInputStream(path2);
      return inputstream;
  }

  public static String read( Reader reader ) throws IOException {
      if (reader == null) return "";
      StringBuilder sb = new StringBuilder();
      boolean error = false;
      try {
          int numRead = 0;
          char[] buffer = new char[1024];
          while ((numRead = reader.read(buffer)) > -1) {
              sb.append(buffer, 0, numRead);
          }
      } catch (IOException e) {
          error = true; // this error should be thrown, even if there is an error closing reader
          throw e;
      } catch (RuntimeException e) {
          error = true; // this error should be thrown, even if there is an error closing reader
          throw e;
      } finally {
          try {
              reader.close();
          } catch (IOException e) {
              if (!error) throw e;
          }
      }
      return sb.toString();
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