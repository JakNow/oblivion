package pl.oblivion;

import pl.oblivion.core.AppConfig;
import pl.oblivion.engine.Application;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@AppConfig("app.properties")
public class Main extends Application{
    
    private static final Logger log = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        new Main().start();
    }
    
    protected void update(float delta) {
    }
    
    protected void render() {
    
    }
}
