package pl.oblivion.engine.scene;

import pl.oblivion.common.gameobject.GameObject;

public class Scene extends GameObject {
    
    public Scene(String name) {
        super(name);
    }
    
    public void clear(){
        //todo remove objects before closing
    }
}