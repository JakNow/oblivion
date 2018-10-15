package pl.oblivion;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.oblivion.core.AppConfig;
import pl.oblivion.core.AppConfigRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@AppConfig("app.properties")
public class AppConfigRunnerTest {

  @BeforeClass
  public static void init() {
    AppConfigRunner appConfigRunner = new AppConfigRunner();
  }

  @Test
  public void loadPropertiesFromFile_test() {
    assertThat(System.getProperty("testString"))
        .withFailMessage("Can't read property from file")
        .isEqualTo("String");
  }
}
