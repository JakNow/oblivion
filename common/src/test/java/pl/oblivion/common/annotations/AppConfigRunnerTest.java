package pl.oblivion.common.annotations;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

@AppConfig("app.properties")
public class AppConfigRunnerTest {


  @BeforeClass
  public static void  init() {
        new AppConfigRunner();
  }

  @Test
  public void loadPropertiesFromFile_test() {
    assertThat(System.getProperty("testString"))
        .withFailMessage("Can't read property from file")
        .isEqualTo("String");
  }
}