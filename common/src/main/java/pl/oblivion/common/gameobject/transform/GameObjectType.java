package pl.oblivion.common.gameobject.transform;

import lombok.Getter;

@Getter
public enum GameObjectType {
    EMPTY("Empty"),
    SPACE_OBJECT("3D"),
    FLAT_OBJECT("2D"),
    CAMERA("Camera");
    
    private final String name;
    
    GameObjectType(String name){
        this.name = name;
    }
}
