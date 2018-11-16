package pl.oblivion.common.transformation;

import lombok.ToString;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ToString
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
  
  public Transformation(Transformation transformation){
    this.position = new Vector3f(transformation.getPosition());
    this.rotation = new Quaternionf(transformation.getRotation());
    this.scale = new Vector3f(transformation.getScale());
    this.transformationMatrix = new Matrix4f(transformation.getTransformationMatrix());
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
  
  public void translate(Vector3f translation){
    this.position.x += translation.x;
    this.position.y += translation.y;
    this.position.z += translation.z;
  }
  
  public void set(Transformation transformation) {
    this.position.set(transformation.getPosition());
    this.rotation.set(transformation.getRotation());
    this.scale.set(transformation.getScale());
    this.transformationMatrix.set(transformation.getTransformationMatrix());
  
  }
}
