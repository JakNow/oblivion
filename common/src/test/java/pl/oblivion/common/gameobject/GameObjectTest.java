package pl.oblivion.common.gameobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GameObjectTest {

  @Test
  public void addObjectWithNoParent_Test() {
    GameObject gameObject = new GameObject() {};
    assertThat(gameObject.getParent()).isNull();
  }

  @Test
  public void addParentByConstructor_Test() {
    GameObject parent = new GameObject() {};
    GameObject child = new GameObject(parent) {};

    assertThat(child.getParent()).isEqualTo(parent);
    assertThat(parent.getChildren()).hasSize(1).contains(child);
  }

  @Test
  public void addObjectToParent_Test() {
    GameObject child = new GameObject() {};
    GameObject parent = new GameObject() {};
    assertThat(parent.addChild(child)).isTrue();
    assertThat(child.getParent()).isEqualTo(parent);
    assertThat(parent.getChildren().get(0)).isEqualTo(child);
  }

  @Test
  public void addParentToObject_Test() {
    GameObject child = new GameObject() {};
    GameObject parent = new GameObject() {};
    assertThat(child.setParent(parent)).isTrue();
    assertThat(child.getParent()).isEqualTo(parent);
    assertThat(parent.getChildren().get(0)).isEqualTo(child);
  }

  @Test
  public void removeObjectFromParent_Test() {
    GameObject parent = new GameObject() {};
    GameObject child = new GameObject(parent) {};

    assertThat(parent.removeChild(child)).isTrue();
    assertThat(parent.getChildren().isEmpty()).isTrue();
    assertThat(child.getParent()).isNull();
  }

  @Test
  public void removeParentFromObject() {
    GameObject parent = new GameObject() {};
    GameObject child = new GameObject(parent) {};

    assertThat(child.removeParent()).isTrue();
    assertThat(parent.getChildren().isEmpty()).isTrue();
    assertThat(child.getParent()).isNull();
  }
}
