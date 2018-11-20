package pl.oblivion.common.gameobject;

import org.joml.Vector3f;

public interface TransformationOperations {

  void translate(float x, float y, float z);

  void translate(Vector3f translationVector);

  Vector3f getPosition();
}
