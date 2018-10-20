package pl.oblivion;

import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.engine.Application;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@AppConfig("app.properties")
public class Main extends Application{
    
    private static final Logger logger = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        logger.info("WELCOME TO OBLIVION ENGINE!");
        new Main();
    }
    
    protected void update(float delta) {
    }
    
    protected void render() {
    
    }
}
