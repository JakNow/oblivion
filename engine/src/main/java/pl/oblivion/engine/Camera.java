package pl.oblivion.engine;

import org.joml.Matrix4f;
import pl.oblivion.common.gameobject.GameObject;

public class Camera extends GameObject {

  private Matrix4f viewMatrix;

  public Camera(GameObject parent) {
    super("Camera",parent);

    this.viewMatrix = new Matrix4f();
  }

  public Matrix4f getViewMatrix() {
    return viewMatrix
        .identity()
            .rotate(this.transform.getRotation())
            .translate(this.transform.getPosition().negate());
  }
}
