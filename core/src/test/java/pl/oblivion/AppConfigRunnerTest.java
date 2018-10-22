package pl.oblivion;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.common.annotations.AppConfigRunner;
        
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
  

import static org.assertj.core.api.Java6Assertions.assertThat;

@AppConfig("app.properties")
public class AppConfigRunnerTest {
    
  //static final Logger logger = LogManager.getLogger(AppConfigRunnerTest.class.getName());


  @BeforeClass
  public static void  init() {
        new AppConfigRunner();
        //logger.entry();
  }

  //logger.error("Can't read property from file");
  
  @Test
  public void loadPropertiesFromFile_test() {
    assertThat(System.getProperty("testString"))
        .withFailMessage("Can't read property from file")
        .isEqualTo("String");
  }

}
