package pl.oblivion.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import static java.io.File.separator;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFile {

  private static final Logger logger = LogManager.getLogger(MyFile.class);
  private static final String separator = "/";

  private StringBuilder path;
  private String name;

  private BufferedReader bufferedReader;

  public MyFile(String path) {
      if(path.equals("/w+"))
          this.path.append(path);
      else
          this.path.append(separator).append(path);
    String[] dirs = path.split(separator);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(String... paths) {
    this.path.append("/");
    int length = paths.length;
    for (int i = 0; i < length - 1; i++) {
        if(paths.equals("/w+"))
            this.path.append(paths[i]); // todo replace with StringBuilder
        else
            this.path.append(paths[i]);
    }
    this.path.append(paths[length - 1]);
    String[] dirs = path.toString().split(separator);
    this.name = dirs[dirs.length - 1];
  }

  public MyFile(MyFile file, String subFile) {
    if(file.path.equals("/w+"))
        this.path.append(file.path).append(subFile);
    else
        this.path.append(file.path).append(subFile);
    this.name = subFile;
  }

  public MyFile(MyFile file, String... subFiles) {
    this.path = file.path;
    int length = subFiles.length;
    for (int i = 0; i < length - 1; i++) {
        if(file.path.equals("/w+"))
            this.path.append(subFiles[i]); // todo replace with StringBuilder
        else
            this.path.append(subFiles[i]);
    }
    this.path.append(subFiles[length - 1]);
    String[] dirs = path.toString().split(separator);
    this.name = dirs[dirs.length - 1];
  }

  @Override
  public String toString() {
    return getPath();
  }

  public String getPath() {
    return path.toString();
  }

  public BufferedReader getReader(){
    bufferedReader =
        new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream(path.toString())));
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
