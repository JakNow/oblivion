package pl.oblivion;

import pl.oblivion.core.AppConfig;
import pl.oblivion.engine.Application;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@AppConfig("app.properties")
public class Main extends Application{
    
    public static void main(String[] args) {
        new Main();
    }
    
    protected void update(float delta) {
    }
    
    protected void render() {
    
    }
}
