package pl.oblivion.common.transformation;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transformation {

  private final Vector3f position;
  private final Quaternionf rotation;
  private final Vector3f scale;
  private final Matrix4f transformationMatrix;

  public Transformation() {
    this.position = new Vector3f();
    this.rotation = new Quaternionf();
    this.scale = new Vector3f(1, 1, 1);
    this.transformationMatrix = new Matrix4f();
  }

  public Matrix4f getTransformationMatrix() {
    return transformationMatrix
        .identity()
        .translate(this.position)
        .rotate(this.rotation)
        .scale(this.scale);
  }

  public void rotate(float x, float y, float z) {
    this.rotation.rotateX((float) Math.toRadians(x));
    this.rotation.rotateY((float) Math.toRadians(y));
    this.rotation.rotateZ((float) Math.toRadians(z));
  }

  public void translate(float x, float y, float z) {
    this.position.x += x;
    this.position.y += y;
    this.position.z += z;
  }
}
