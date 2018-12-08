package pl.oblivion.common.gameobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.transform.Transform;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public abstract class GameObject {
    
    public final Transform transform;
    @Getter
    @Setter
    private GameObject parent;
    @Getter
    @Setter
    private List<GameObject> children;
    
    public GameObject(Transform transform, GameObject parent) {
    this.addParent(parent);
    this.children = new LinkedList<>();
        this.transform = new Transform(transform, children);
  }

  public GameObject() {
    this.parent = null;
    this.children = new LinkedList<>();
      this.transform = new Transform(children);
  }
    
    public GameObject(Transform transform) {
    this.parent = null;
    this.children = new LinkedList<>();
        this.transform = new Transform(transform, children);
  }

  public GameObject(GameObject parent) {
    this.children = new LinkedList<>();
      this.transform = new Transform(this.children);
      this.addParent(parent);
  }

  public boolean addChild(GameObject child) {
    this.children.add(child);
    child.addParent(this);
    return true;
  }

  public boolean addParent(GameObject parent) {
      if (Objects.nonNull(parent)) {
          this.parent = parent;
          this.parent.getChildren().add(this);
          this.transform.inheritTransformationFromParent(parent);
          return true;
      }
      return false;
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
}
