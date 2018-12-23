package pl.oblivion.common.gameobject;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.junit.Test;
import pl.oblivion.common.gameobject.transform.Rotation;
import pl.oblivion.common.gameobject.transform.Transform;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class TransformTest {

	@Test
	public void fromEulerToQuaternion() {
		Transform transform = new Transform(Collections.emptyList());
		Quaternionf rotation = transform.fromEulerAngle(30, 30, 30);

		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(rotation.x).isEqualTo(0.306f, within(0.005f));
					softAssertions.assertThat(rotation.y).isEqualTo(0.177f, within(0.005f));
					softAssertions.assertThat(rotation.z).isEqualTo(0.306f, within(0.005f));
					softAssertions.assertThat(rotation.w).isEqualTo(0.884f, within(0.005f));
				});
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
		GameObject parent = prepareDefaultGameObject();

		GameObject child = new GameObject("child", parent, null) {
		};
		Vector3f positionFromTransformationMatrix = new Vector3f();
		child.transform.getTransformationMatrix().getTranslation(positionFromTransformationMatrix);
		assertThat(positionFromTransformationMatrix)
				.isEqualToComparingFieldByField(new Vector3f(1, 2, 3));
	}

	@Test
	public void whenParentMovesChildMoves() {
		GameObject parent = prepareDefaultGameObject();

		GameObject child = new GameObject("child", parent, null) {
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

	@Test
	public void scaleIsInheritedByChild() {
		GameObject parent = prepareScaledGameObject();

		GameObject child = new GameObject("child", parent, null) {
		};
		Vector3f scaleFromTransformationMatrix = new Vector3f();
		child.transform.getTransformationMatrix().getScale(scaleFromTransformationMatrix);
		assertThat(scaleFromTransformationMatrix)
				.isEqualToComparingFieldByField(new Vector3f(2.5f, 0.3f, 10));
		assertThat(child.transform.getScale()).isEqualTo(new Vector3f(1, 1, 1));
	}

	@Test
	public void whenParentScalesChildScales() {
		GameObject parent = prepareScaledGameObject();

		GameObject child = new GameObject("child", parent, null) {
		};

		parent.transform.scale(new Vector3f(2, 10, 0.5f));
		Vector3f scaleFromTransformationMatrix = new Vector3f();
		child.transform.getTransformationMatrix().getScale(scaleFromTransformationMatrix);
		assertThat(scaleFromTransformationMatrix).isEqualToComparingFieldByField(new Vector3f(5, 3, 5));
		assertThat(child.transform.getScale()).isEqualTo(new Vector3f(2, 10, 0.5f));

		parent.transform.scale(0.2f, 2, 0.4f);
		child.transform.getTransformationMatrix().getScale(scaleFromTransformationMatrix);
		assertThat(scaleFromTransformationMatrix)
				.isEqualToComparingFieldByField(new Vector3f(1, 6, 2f));
		assertThat(child.transform.getScale()).isEqualTo(new Vector3f(0.4f, 20f, 0.2f));
	}

	@Test
	public void rotateByVectorRightAndLeft() {
		Transform transform = new Transform(Collections.emptyList());

		transform.rotate(Rotation.RIGHT, 30);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0.2588f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(0.9659f, within(0.001f));
				});

		transform.rotate(Rotation.LEFT, 30);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(1f, within(0.001f));
				});
	}

	@Test
	public void rotateByVectorUpAndDown() {
		Transform transform = new Transform(Collections.emptyList());

		transform.rotate(Rotation.UP, 30);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0.2588f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(0.9659f, within(0.001f));
				});

		transform.rotate(Rotation.DOWN, 30);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(1f, within(0.001f));
				});
	}

	@Test
	public void rotateByVectorForwardAndBackward() {
		Transform transform = new Transform(Collections.emptyList());

		transform.rotate(Rotation.FORWARD, 30);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0.2588f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(0.9659f, within(0.001f));
				});

		transform.rotate(Rotation.BACKWARD, 30);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0f);
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(1f, within(0.001f));
				});
	}

	@Test
	public void rotateByAngles() {
		Transform transform = new Transform(Collections.emptyList());

		transform.rotate(45, 45, 45);
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0.462f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0.191f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0.462f, within(0.001f));
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(0.733f, within(0.001f));
				});
	}

	@Test
	public void rotateByQuaternion() {
		Transform transform = new Transform(Collections.emptyList());

		transform.rotate(new Quaternionf(0.5f, 0.5f, 0.5f, 0.5f));
		assertSoftly(
				softAssertions -> {
					softAssertions.assertThat(transform.getRotation().x).isEqualTo(0.5f);
					softAssertions.assertThat(transform.getRotation().y).isEqualTo(0.5f);
					softAssertions.assertThat(transform.getRotation().z).isEqualTo(0.5f);
					softAssertions.assertThat(transform.getRotation().w).isEqualTo(0.5f);
				});
	}

	private GameObject prepareDefaultGameObject() {
		return new GameObject(
				"Default GameObject",
				new Transform(
						new Vector3f(1, 2, 3),
						new Quaternionf(),
						new Vector3f(1, 1, 1),
						Collections.emptyList()),
				null) {
		};
	}

	private GameObject prepareScaledGameObject() {
		return new GameObject(
				"Scaled GameObject",
				new Transform(
						new Vector3f(1, 2, 3),
						new Quaternionf(),
						new Vector3f(2.5f, 0.3f, 10),
						Collections.emptyList()),
				null) {
		};
	}

	private GameObject prepareRotatedGameObject() {
		return new GameObject(
				"Rotated GameObject",
				new Transform(
						new Vector3f(),
						new Quaternionf(0.3f, 0.5f, 0.7f, 0.9f),
						new Vector3f(1, 1, 1),
						Collections.emptyList()),
				null) {
		};
	}
}
