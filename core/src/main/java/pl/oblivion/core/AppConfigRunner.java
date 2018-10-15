package pl.oblivion.core;

import org.reflections.Reflections;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class AppConfigRunner {

  public AppConfigRunner() {
    importProperties();
  }

  private static void loadProperties(String fileName) {
    try (InputStream is = AppConfigRunner.class.getClassLoader().getResourceAsStream(fileName)) {
      System.getProperties().load(is);
    } catch (IOException e) {

    }
  }

  private void importProperties() {
    Reflections reflections = new Reflections();
    Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(AppConfig.class);
    for (Class<?> main : annotated) {
      loadProperties(main.getAnnotation(AppConfig.class).value());
    }
  }
}
