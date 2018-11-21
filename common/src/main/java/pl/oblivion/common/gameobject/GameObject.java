package pl.oblivion.common.gameobject;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.joml.Vector3f;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public abstract class GameObject implements TransformationOperations {

  private final Transformation transformation;
  private GameObject parent;
  private List<GameObject> children;

  public GameObject(Transformation transformation, GameObject parent) {
    this.transformation = new Transformation(transformation);
    this.addParent(parent);
    this.children = new LinkedList<>();
  }

  public GameObject() {
    this.transformation = new Transformation();
    this.parent = null;
    this.children = new LinkedList<>();
  }

  public GameObject(Transformation transformation) {
    this.transformation = new Transformation(transformation);
    this.parent = null;
    this.children = new LinkedList<>();
  }

  public GameObject(GameObject parent) {
    this.transformation = new Transformation();
    this.addParent(parent);
    this.children = new LinkedList<>();
  }

  public boolean addChild(GameObject child) {
    this.children.add(child);
    child.addParent(this);
    return true;
  }

  public boolean addParent(GameObject parent) {
    this.parent = parent;
    this.parent.getChildren().add(this);
    return true;
  }

  public boolean removeChild(GameObject child) {
    child.setParent(null);
    this.getChildren().remove(child);
    return true;
  }

  public boolean removeParent() {
    this.parent.getChildren().remove(this);
    this.parent = null;
    return true;
  }

  @Override
  public void translate(float x, float y, float z) {
    this.translateLocal(x,y,z);
    children.stream().filter(Objects::nonNull).forEach(child -> child.translate(x,y,z));
  }

  @Override
  public void translate(Vector3f translationVector) {
    this.translateLocal(translationVector);
    children.stream().filter(Objects::nonNull).forEach(child -> child.translate(translationVector));
  }
  
  @Override
  public void translateLocal(float x, float y, float z) {
    this.transformation.translate(x,y,z);
  }
  
  @Override
  public void translateLocal(Vector3f translationVector) {
    this.transformation.translate(translationVector);
  }
  
  @Override
  public Vector3f getPosition() {
    return transformation.getPosition();
  }
}
