package pl.oblivion.common.transformation;

import lombok.ToString;
import org.joml.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joml.Math;

@Getter
@AllArgsConstructor
@ToString
public class Transformation {

  private final Vector3f position;
  private final AxisAngle4f rotation;
  private final Vector3f scale;
  private final Matrix4f transformationMatrix;

  public Transformation() {
    this.position = new Vector3f();
    this.rotation = new AxisAngle4f();
    this.scale = new Vector3f(1, 1, 1);
    this.transformationMatrix = new Matrix4f().translate(this.position).rotate(this.rotation).scale(this.scale);
  }
  
  public Transformation(Transformation transformation){
    this.transformationMatrix = new Matrix4f(transformation.getTransformationMatrix());
    this.position = new Vector3f();
    this.rotation = new AxisAngle4f();
    this.scale = new Vector3f(1, 1, 1);

    this.transformationMatrix.getTranslation(this.position);
    this.transformationMatrix.getRotation(this.rotation);
    this.transformationMatrix.getScale(this.scale);
  }
  
  public Matrix4f getTransformationMatrix() {
    return transformationMatrix;
  }

  public void translate(float xAxis, float yAxis, float zAxis){
    this.transformationMatrix.translateLocal(xAxis,yAxis,zAxis);
  }
  
  public void translate(Vector3f translationVector){
    this.transformationMatrix.translateLocal(translationVector);
  }
  
  public void rotate(AxisAngle4f rotationAxisAngle){
    this.transformationMatrix.rotate(rotationAxisAngle);
  }
  
  public void scale(float xAxis, float yAxis, float zAxis){
    this.transformationMatrix.scaleLocal(xAxis,yAxis,zAxis);
  }
  
  public void set(Transformation transformation) {
    this.position.set(new Vector3f(transformation.getPosition()));
    this.rotation.set(new Quaternionf(transformation.getRotation()));
    this.scale.set(new Vector3f(transformation.getScale()));
    this.transformationMatrix.set(new Matrix4f(transformation.getTransformationMatrix()));
  }
  
  public Vector3f getPosition(){
    this.transformationMatrix.getTranslation(this.position);
    return this.position;
  }
  
  public AxisAngle4f getRotation(){
    this.transformationMatrix.getRotation(this.rotation);
    return this.rotation;
  }
  
  public Vector3f getScale(){
    this.transformationMatrix.getScale(this.scale);
    return this.scale;
  }
}
