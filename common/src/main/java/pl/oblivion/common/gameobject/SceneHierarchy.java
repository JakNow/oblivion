package pl.oblivion.common.gameobject;

import pl.oblivion.common.gameobject.transform.GameObjectType;

import java.util.List;
import java.util.Map;

public interface SceneHierarchy {
    
    void addToScene(Map<GameObjectType, List<GameObject>> sceneHierarchy);
}
