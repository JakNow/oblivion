package pl.oblivion.engine.camera;

import lombok.Getter;
import org.joml.Matrix4f;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.transform.GameObjectType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Camera extends GameObject {

  private Matrix4f viewMatrix;
  @Getter private CameraType cameraType;

  Camera(CameraType cameraType) {
    super(cameraType.getName());
    this.viewMatrix = new Matrix4f();
    this.cameraType = cameraType;
  }

  public Matrix4f getViewMatrix() {
    return viewMatrix
        .identity()
        .rotate(this.transform.getRotation())
        .translate(this.transform.getPosition().negate());
  }

  @Override
  public void addToScene(Map<GameObjectType, List<GameObject>> sceneHierarchy) {
    List<GameObject> gameObjects =
        sceneHierarchy.computeIfAbsent(GameObjectType.CAMERA, k -> new LinkedList<>());
    gameObjects.add(this);
  }
}
