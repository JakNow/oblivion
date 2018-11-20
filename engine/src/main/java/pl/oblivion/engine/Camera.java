package pl.oblivion.engine;

import org.joml.Matrix4f;

import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.Transformation;

public class Camera extends GameObject {

  private Matrix4f viewMatrix;

  public Camera(GameObject parent) {
    super(new Transformation(),parent);

    this.viewMatrix = new Matrix4f();
  }

  public Matrix4f getViewMatrix() {
    return viewMatrix
        .identity()
        .rotate(this.getTransformation().getRotation())
        .translate(this.getTransformation().getPosition().negate());
  }
}
