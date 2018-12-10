package pl.oblivion.common.gameobject.transform;

import lombok.Getter;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import pl.oblivion.common.gameobject.GameObject;

import java.util.List;

@Getter
public class Transform {

  private final Vector3f position;
  private final Quaternionf rotation;
  private final Vector3f scale;
  private final Matrix4f transformationMatrix;
  private final Vector3f transformationPosition;
  private final Quaternionf transformationRotation;
  private final Vector3f transformationScale;

  private List<GameObject> children;

  public Transform(List<GameObject> children) {
    this.position = new Vector3f();
    this.rotation = new Quaternionf();
    this.scale = new Vector3f(1, 1, 1);
    this.children = children;

    this.transformationPosition = new Vector3f(this.position);
    this.transformationRotation = new Quaternionf(this.rotation);
    this.transformationScale = new Vector3f(this.scale);

    this.transformationMatrix = new Matrix4f();
  }

  public Transform(Transform transform, List<GameObject> children) {
    this.position = new Vector3f(transform.getPosition());
    this.rotation = new Quaternionf(transform.getRotation());
    this.scale = new Vector3f(transform.getScale());
    this.children = children;

    this.transformationPosition = new Vector3f(this.position);
    this.transformationRotation = new Quaternionf(this.rotation);
    this.transformationScale = new Vector3f(this.scale);

    this.transformationMatrix = new Matrix4f(transform.getTransformationMatrix());
  }

  public Transform(
      Vector3f position, Quaternionf rotation, Vector3f scale, List<GameObject> children) {
    this.position = position;
    this.rotation = rotation;
    this.scale = scale;
    this.children = children;

    this.transformationPosition = new Vector3f(this.position);
    this.transformationRotation = new Quaternionf(this.rotation);
    this.transformationScale = new Vector3f(this.scale);

    this.transformationMatrix = new Matrix4f();
  }

  public Matrix4f getTransformationMatrix() {
    return transformationMatrix
        .identity()
        .translate(this.transformationPosition)
        .rotate(this.transformationRotation)
        .scale(this.transformationScale);
  }

  public void setPosition(Vector3f position) {
    this.position.set(position);
  }

  public void setRotation(Quaternionf rotation) {
    this.rotation.set(rotation);
  }

  public void setScale(Vector3f scale) {
    this.scale.set(scale);
  }

  public void set(Transform transform) {
    this.position.set(transform.getPosition());
    this.rotation.set(transform.getRotation());
    this.scale.set(transform.getScale());
  }

  public Quaternionf fromEulerAngle(float xAngle, float yAngle, float zAngle) {
    SinCos xSinCos = new SinCos(xAngle * 0.5f);
    SinCos ySinCos = new SinCos(yAngle * 0.5f);
    SinCos zSinCos = new SinCos(zAngle * 0.5f);

    float cosYcosZ = ySinCos.getCos() * zSinCos.getCos();
    float sinYsinZ = ySinCos.getSin() * zSinCos.getSin();
    float cosYsinZ = ySinCos.getCos() * zSinCos.getSin();
    float sinYcosZ = ySinCos.getSin() * zSinCos.getCos();

    float w = (cosYcosZ * xSinCos.getCos() - sinYsinZ * xSinCos.getSin());
    float x = (cosYcosZ * xSinCos.getSin() + sinYsinZ * xSinCos.getCos());
    float y = (cosYsinZ * xSinCos.getCos() - sinYcosZ * xSinCos.getSin());
    float z = (sinYcosZ * xSinCos.getCos() + cosYsinZ * xSinCos.getSin());

    return new Quaternionf(x, y, z, w).normalize();
  }

  public void translate(float xTranslation, float yTranslation, float zTranslation) {
    this.position.add(xTranslation, yTranslation, zTranslation);
    this.transformationPosition.add(xTranslation, yTranslation, zTranslation);
    this.children.forEach(
        child -> child.transform.translate(xTranslation, yTranslation, zTranslation));
  }

  public void translate(Vector3f translationVector) {
    this.position.add(translationVector);
    this.transformationPosition.add(translationVector);
    this.children.forEach(child -> child.transform.translate(translationVector));
  }

  public void inheritTransformationFromParent(GameObject parent) {
    this.transformationPosition.add(parent.transform.position);
    this.transformationRotation.mul(parent.transform.rotation);
    this.transformationScale.mul(parent.transform.scale);
  }

  public void scale(float xScale, float yScale, float zScale) {
    this.scale.mul(xScale, yScale, zScale);
    this.transformationScale.mul(xScale, yScale, zScale);
    this.children.forEach(child -> child.transform.scale(xScale, yScale, zScale));
  }

  public void scale(Vector3f scaleVector) {
    this.scale.mul(scaleVector);
    this.transformationScale.mul(scaleVector);
    this.children.forEach(child -> child.transform.scale(scaleVector));
  }

  public void rotate(Vector3f axis, float angle) {
    float angleInRadians = (float) Math.toRadians(angle);
    this.rotation.rotateAxis(angleInRadians, axis);
    this.transformationRotation.rotateAxis(angleInRadians, axis);
    this.children.forEach(child -> child.transform.rotate(axis, angleInRadians));
  }

  public void rotate(float xAngle, float yAngle, float zAngle) {
    this.rotation.rotateXYZ(
        (float) Math.toRadians(xAngle),
        (float) Math.toRadians(yAngle),
        (float) Math.toRadians(zAngle));
    this.transformationRotation.rotateXYZ(
        (float) Math.toRadians(xAngle),
        (float) Math.toRadians(yAngle),
        (float) Math.toRadians(zAngle));
    this.children.forEach(child -> child.transform.rotate(xAngle, yAngle, zAngle));
  }

  public void rotate(Quaternionf quaternionf) {
    this.rotation.mul(quaternionf);
    this.transformationRotation.mul(quaternionf);
    this.children.forEach(child -> child.transform.rotate(quaternionf));
  }

  @Getter
  class SinCos {
    float sin;
    float cos;

    SinCos(float angle) {
      this.sin = (float) Math.sin(Math.toRadians(angle));
      this.cos = (float) Math.cos(Math.toRadians(angle));
    }
  }
}
