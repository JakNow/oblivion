package pl.oblivion.model.model;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.Transformation;
import pl.oblivion.model.mesh.Mesh;

@Getter
public class Model extends GameObject {

  private final Mesh mesh;

  public Model(Transformation transformation, GameObject parent, Mesh mesh) {
    super(transformation, parent);
    this.mesh = mesh;
  }
}
