package pl.oblivion.common.annotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

public class AppConfigRunner {

  private static final Logger logger = LogManager.getLogger(AppConfigRunner.class);

  public AppConfigRunner(String path) {
    loadProperties(path);
  }

  private void loadProperties(String fileName) {
    try (InputStream is = AppConfigRunner.class.getClassLoader().getResourceAsStream(fileName)) {
      System.getProperties().load(is);
    } catch (IOException e) {
      logger.info("Couldn't read %.", fileName, e);
    }
  }
}
