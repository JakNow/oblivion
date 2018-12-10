package pl.oblivion.common.gameobject;

import org.junit.Test;
import pl.oblivion.common.gameobject.transform.GameObjectType;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameObjectTest {

  @Test
  public void addObjectWithNoParent_Test() {
    GameObject gameObject = defaultParent();

    assertThat(gameObject.getParent()).isNull();
  }

  @Test
  public void addParentByConstructor_Test() {
    GameObject parent = defaultParent();
    GameObject child = defaultChild(parent);

    assertThat(child.getParent()).isEqualTo(parent);
    assertThat(parent.getChildren()).hasSize(1).contains(child);
  }

  @Test
  public void addObjectToParent_Test() {
    GameObject parent = defaultParent();
    GameObject child = defaultChild(parent);

    assertThat(parent.addChild(child)).isTrue();
    assertThat(child.getParent()).isEqualTo(parent);
    assertThat(parent.getChildren().get(0)).isEqualTo(child);
  }

  @Test
  public void addParentToObject_Test() {
    GameObject parent = defaultParent();
    GameObject child = defaultChild(parent);

    assertThat(child.addParent(parent)).isTrue();
    assertThat(child.getParent()).isEqualTo(parent);
    assertThat(parent.getChildren().get(0)).isEqualTo(child);
  }

  @Test
  public void removeObjectFromParent_Test() {
    GameObject parent = defaultParent();
    GameObject child = defaultChild(parent);

    assertThat(parent.removeChild(child)).isTrue();
    assertThat(parent.getChildren().isEmpty()).isTrue();
    assertThat(child.getParent()).isNull();
  }

  @Test
  public void removeParentFromObject_Test() {
    GameObject parent = defaultParent();
    GameObject child = defaultChild(parent);

    assertThat(child.removeParent()).isTrue();
    assertThat(parent.getChildren().isEmpty()).isTrue();
    assertThat(child.getParent()).isNull();
  }

  private GameObject defaultParent() {
    return new GameObject("parent") {
      @Override
      public void addToScene(Map<GameObjectType, List<GameObject>> sceneHierarchy) {
    
      }
    };
  }

  private GameObject defaultChild(GameObject parent) {
    return new GameObject("child", parent) {
      @Override
      public void addToScene(Map<GameObjectType, List<GameObject>> sceneHierarchy) {
    
      }
    };
  }
}
