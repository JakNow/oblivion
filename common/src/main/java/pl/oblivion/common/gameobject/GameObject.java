package pl.oblivion.common.gameobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.transformation.Transformation;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class GameObject {

  private final Transformation transformation;
  private GameObject parent;
  private List<GameObject> children;
  
  public GameObject(Transformation transformation, GameObject parent){
    this.transformation = transformation;
    this.parent = parent;
    this.children = new LinkedList<>();
  }
  
  public GameObject(){
    this.transformation = new Transformation();
    this.parent = null;
    this.children = new LinkedList<>();
  }
  
  public GameObject(GameObject parent){
    this.transformation = new Transformation();
    this.parent = parent;
    this.children = new LinkedList<>();
  }
  
  public boolean addChild(GameObject child){
   return false;
  }
  
  public boolean setParent(GameObject parent){
    return false;
  }
  
  public boolean removeChild(GameObject child){
    return false;
  }
  
  public boolean removeParent(){
    return true;
  }
  
  
}
