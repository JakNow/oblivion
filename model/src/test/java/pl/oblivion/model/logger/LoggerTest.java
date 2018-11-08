package pl.oblivion.model.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class LoggerTest {

  private static final Logger logger = LogManager.getLogger(LoggerTest.class);

  @Test
  public void loggerShouldWork_Test() {
    logger.fatal("FATAL");
    logger.error("ERROR");
    logger.warn("WARN");
    logger.debug("DEBUG");
    logger.trace("TRACE");
    logger.info("INFO");
  }
}
