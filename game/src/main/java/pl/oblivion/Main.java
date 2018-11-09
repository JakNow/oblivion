package pl.oblivion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.core.Application;

@AppConfig("app.properties")
public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);

  public Main() {
  
  }

  public static void main(String[] args) {
    Application.start(Main.class, args);
  }
}
