package pl.oblivion;

import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.engine.Application;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import pl.oblivion.model.model.Model;

@AppConfig("app.properties")
public class Main extends Application{
    
    private static final Logger logger = LogManager.getLogger(Main.class);
    
    public Main(){
        Model model = new Model();
        this.getRendererHandler().addModel(model);
        
    }
    public static void main(String[] args) {
        new Main();
    }
    
    protected void update(float delta) {
    
    }
}


