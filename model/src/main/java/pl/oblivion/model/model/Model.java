package pl.oblivion.model.model;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.model.mesh.Mesh;

@Getter
public class Model extends GameObject {

  private final Mesh mesh;
    
    public Model(String name, Transform transformation, GameObject parent, Mesh mesh) {
    super(name,transformation, parent);
    this.mesh = mesh;
  }
}
