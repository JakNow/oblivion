package pl.oblivion.common.gameobject.transform;

import lombok.Getter;
import lombok.Setter;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Getter
@Setter
public class Transform implements Transformation {

	private final Vector3f position;
	private final Quaternionf rotation;
	private final Vector3f scale;
	private final Matrix4f transformationMatrix;


	public Transform() {
		this.position = new Vector3f();
		this.rotation = new Quaternionf();
		this.scale = new Vector3f(1, 1, 1);
		this.transformationMatrix = new Matrix4f();
	}

	public Transform(Transform transform) {
		this.position = new Vector3f(transform.getPosition());
		this.rotation = new Quaternionf(transform.getRotation());
		this.scale = new Vector3f(transform.getScale());

		this.transformationMatrix = new Matrix4f(transform.getTransformationMatrix());
	}

	public Transform(
			Vector3f position, Quaternionf rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;

		this.transformationMatrix = new Matrix4f();
	}

	public Matrix4f getTransformationMatrix() {
		return transformationMatrix
				.identity()
				.translate(this.position)
				.rotate(this.rotation)
				.scale(this.scale);
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

	@Override
	public void translate(float xTranslation, float yTranslation, float zTranslation) {
		this.position.add(xTranslation, yTranslation, zTranslation);
	}

	@Override
	public void translate(Vector3f translationVector) {
		this.position.add(translationVector);
	}

	@Override
	public void scale(float xScale, float yScale, float zScale) {
		this.scale.mul(xScale, yScale, zScale);
	}

	@Override
	public void scale(Vector3f scaleVector) {
		this.scale.mul(scaleVector);
	}

	@Override
	public void rotate(Vector3f axis, float angle) {
		float angleInRadians = (float) Math.toRadians(angle);
		this.rotation.rotateAxis(angleInRadians, axis);
	}

	@Override
	public void rotate(float xAngle, float yAngle, float zAngle) {
		this.rotation.rotateXYZ(
				(float) Math.toRadians(xAngle),
				(float) Math.toRadians(yAngle),
				(float) Math.toRadians(zAngle));
	}

	@Override
	public void rotate(Quaternionf quaternionf) {
		this.rotation.mul(quaternionf);
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
