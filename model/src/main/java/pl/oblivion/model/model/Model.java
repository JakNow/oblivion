package pl.oblivion.model.model;

import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.model.mesh.Mesh;

@Getter
@Setter
public class Model extends GameObject {
    
    private Mesh mesh;
    
}
