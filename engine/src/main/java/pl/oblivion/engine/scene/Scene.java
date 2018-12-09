package pl.oblivion.engine.scene;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.transform.GameObjectType;
import pl.oblivion.engine.camera.Camera;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class Scene {

  private String name;
  
  private Map<GameObjectType,List<GameObject>> sceneHierarchy;
  
  public Scene(String name) {
    this.name = name;
    this.sceneHierarchy = new HashMap<>();
  }

  public Scene() {
    this("Default name");
  }

  public void clear() {
    // todo remove objects before closing
  }

  public void addGameObject(GameObject gameObject){
    gameObject.addToScene(sceneHierarchy);
  }
  
  
  public Camera getCamera() {
    return (Camera) sceneHierarchy.get(GameObjectType.CAMERA).get(0);
  }
}
