    package pl.oblivion;

import pl.oblivion.core.AppConfig;
import pl.oblivion.core.AppConfigRunner;
import pl.oblivion.engine.Application;

@AppConfig("app.properties")
public class Main {

  public static void main(String[] args) {
  	new AppConfigRunner();
    new Application().run();
  }
}
