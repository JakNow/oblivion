package pl.oblivion.engine.renderer;

import pl.oblivion.model.model.Model;

import java.util.ArrayList;
import java.util.List;

public class RendererHandler {

    private static RendererHandler instance;
    
    private List<Model> modelList = new ArrayList<>();
    
    private RendererHandler(){
    }
    
    
    public static synchronized RendererHandler getInstance(){
        if(instance == null){
            instance = new RendererHandler();
        }
        return instance;
    }
    
    public void delete(){
    
    }
    
    public void render(){
        modelList.forEach(model -> {
            //render();
        });
    }
    
    public void addModel(Model model) {
        modelList.add(model);
    }
}
