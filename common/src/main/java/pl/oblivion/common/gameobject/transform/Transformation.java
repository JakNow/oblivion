package pl.oblivion.common.gameobject.transform;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public interface Transformation {

	Vector3f getPosition();

	void setPosition(Vector3f position);

	Vector3f getScale();

	void setScale(Vector3f scale);

	Quaternionf getRotation();

	void setRotation(Quaternionf rotation);

	void translate(float x, float y, float z);

	void translate(Vector3f direction);

	void scale(float x, float y, float z);

	void scale(Vector3f scale);

	void rotate(Vector3f axis, float angle);

	void rotate(float x, float y, float z);

	void rotate(Quaternionf quaternionf);
}
