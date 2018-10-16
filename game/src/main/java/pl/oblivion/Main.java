package pl.oblivion;

import pl.oblivion.core.AppConfig;
import pl.oblivion.core.AppConfigRunner;
import pl.oblivion.engine.Application;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@AppConfig("app.properties")
public class Main {
    
  private static final Logger log = LogManager.getLogger(Main.class);
  
  public static void main(String[] args) {
	
		try{
                    new AppConfigRunner();
			new Application().run();
		}catch(ArithmeticException ex){
			log.error("Sorry, something wrong!", ex);
		}
		
		
	}
}
