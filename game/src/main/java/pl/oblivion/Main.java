package pl.oblivion;

import pl.oblivion.core.AppConfig;
import pl.oblivion.engine.Application;

@AppConfig("app.properties")
public class Main extends Application{
    
    public static void main(String[] args) {
        new Main().start();
    }
    
    protected void update(float delta) {
    }
    
    protected void render() {
    
    }
}
