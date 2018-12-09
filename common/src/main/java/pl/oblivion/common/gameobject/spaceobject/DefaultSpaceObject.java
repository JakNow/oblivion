package pl.oblivion.common.gameobject.spaceobject;

import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.transform.GameObjectType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultSpaceObject extends GameObject {
    
    @Override
    public void addToScene(Map<GameObjectType,List<GameObject>> sceneHierarchy) {
        List<GameObject> gameObjects = sceneHierarchy.computeIfAbsent(GameObjectType.SPACE_OBJECT, k -> new LinkedList<>());
        gameObjects.add(this);
    }
}
