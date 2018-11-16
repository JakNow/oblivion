package pl.oblivion.common.gameobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.oblivion.common.transformation.Transformation;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public abstract class GameObject {

  private final Transformation transformation;
  private GameObject parent;
  private List<GameObject> children;
  
  public GameObject(Transformation transformation, GameObject parent){
    this.transformation = new Transformation(transformation);
    this.parent = parent;
    this.children = new LinkedList<>();
  }
  
  public GameObject(){
    this.transformation = new Transformation();
    this.parent = null;
    this.children = new LinkedList<>();
  }
  
  public GameObject(Transformation transformation){
    this.transformation = new Transformation(transformation);
    this.parent = null;
    this.children = new LinkedList<>();
  }
  
  public GameObject(GameObject parent){
    this.transformation = new Transformation();
    this.addParent(parent);
    this.children = new LinkedList<>();
    
  }
  
  public boolean addChild(GameObject child){
    this.children.add(child);
    child.addParent(this);
    return true;
  }
  
  public boolean addParent(GameObject parent){
    this.parent = parent;
    this.parent.getChildren().add(this);
    return true;
  }
  
  public boolean removeChild(GameObject child){
    child.setParent(null);
    this.getChildren().remove(child);
    return true;
  }
  
  public boolean removeParent(){
    this.parent.getChildren().remove(this);
    this.parent = null;
    return true;
  }
  
  public void setTransformation(Transformation transformation){
    this.transformation.set(transformation);
  }
  
}
