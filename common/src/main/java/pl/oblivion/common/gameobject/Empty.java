package pl.oblivion.common.gameobject;

import pl.oblivion.common.gameobject.transform.GameObjectType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Empty extends GameObject {
    
    @Override
    public void addToScene(Map<GameObjectType,List<GameObject>> sceneHierarchy) {
        List<GameObject> gameObjects = sceneHierarchy.computeIfAbsent(GameObjectType.EMPTY, k -> new LinkedList<>());
        gameObjects.add(this);
    }
}
