package pl.oblivion.common.gameobject;

import org.assertj.core.api.SoftAssertions;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class TransformTest {
    
    @Test
    public void fromEulerToQuaternion() {
        Transform transform = new Transform(Collections.emptyList());
        Quaternionf rotation = transform.fromEulerAngle(30, 30, 30);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(rotation.x).isEqualTo(0.306f, within(0.005f));
        softAssertions.assertThat(rotation.y).isEqualTo(0.177f, within(0.005f));
        softAssertions.assertThat(rotation.z).isEqualTo(0.306f, within(0.005f));
        softAssertions.assertThat(rotation.w).isEqualTo(0.884f, within(0.005f));
        softAssertions.assertAll();
    }
    
    @Test
    public void translate_positionIsChanged() {
        Transform transform = new Transform(Collections.emptyList());
        transform.translate(0.5f, 123, 0);
        assertThat(transform.getPosition()).isEqualToComparingFieldByField(new Vector3f(0.5f, 123, 0));
        
        transform.translate(new Vector3f(13f, 0.3f, -0.5f));
        assertThat(transform.getPosition())
                .isEqualToComparingFieldByField(new Vector3f(13.5f, 123.3f, -0.5f));
    }
    
    @Test
    public void positionIsInheritedByChild() {
        GameObject parent =
                new GameObject(
                        new Transform(
                                new Vector3f(1, 2, 3),
                                new Quaternionf(),
                                new Vector3f(1, 1, 1),
                                Collections.emptyList())) {
                };
        
        GameObject child = new GameObject(parent) {
        };
        Vector3f positionFromTransformationMatrix = new Vector3f();
        child.transform.getTransformationMatrix().getTranslation(positionFromTransformationMatrix);
        assertThat(positionFromTransformationMatrix)
                .isEqualToComparingFieldByField(new Vector3f(1, 2, 3));
    }
    
    @Test
    public void whenParentMovesChildMoves() {
        GameObject parent =
                new GameObject(
                        new Transform(
                                new Vector3f(1, 2, 3),
                                new Quaternionf(),
                                new Vector3f(1, 1, 1),
                                Collections.emptyList())) {
                };
        
        GameObject child = new GameObject(parent) {
        };
        
        parent.transform.translate(new Vector3f(51, 123.5f, -5));
        Vector3f positionFromTransformationMatrix = new Vector3f();
        child.transform.getTransformationMatrix().getTranslation(positionFromTransformationMatrix);
        assertThat(positionFromTransformationMatrix)
                .isEqualToComparingFieldByField(new Vector3f(52, 125.5f, -2));
        assertThat(child.transform.getPosition()).isEqualTo(new Vector3f(51, 123.5f, -5f));
        
        parent.transform.translate(-23.5f, 12, -15f);
        child.transform.getTransformationMatrix().getTranslation(positionFromTransformationMatrix);
        assertThat(positionFromTransformationMatrix)
                .isEqualToComparingFieldByField(new Vector3f(28.5f, 137.5f, -17));
        assertThat(child.transform.getPosition()).isEqualTo(new Vector3f(27.5f, 135.5f, -20));
    }
}
