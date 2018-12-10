package pl.oblivion.model.model;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.transform.GameObjectType;
import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.model.mesh.Mesh;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class Model extends GameObject {

  private final Mesh mesh;

  public Model(String name, Transform transformation, GameObject parent, Mesh mesh) {
    super(name, transformation, parent);
    this.mesh = mesh;
  }
  
  @Override
  public void addToScene(Map<GameObjectType, List<GameObject>> sceneHierarchy) {
    List<GameObject> gameObjects = sceneHierarchy.computeIfAbsent(GameObjectType.SPACE_OBJECT, k -> new LinkedList<>());
    gameObjects.add(this);
  }
}
