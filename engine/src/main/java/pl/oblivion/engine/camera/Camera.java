package pl.oblivion.engine.camera;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.GameObjectType;
import pl.oblivion.engine.Window;

public abstract class Camera extends GameObject {

	static final Logger logger = LogManager.getLogger(Camera.class);
	final Matrix4f projectionMatrix;
	private final Matrix4f viewMatrix;
	private final Vector3f negatedPosition;
	@Getter
	private CameraType cameraType;

	Camera(CameraType cameraType) {
		super();

		this.setName(cameraType.getName());
		this.setGameObjectType(GameObjectType.CAMERA);
		this.viewMatrix = new Matrix4f();
		this.projectionMatrix = new Matrix4f();
		this.cameraType = cameraType;
		this.negatedPosition = new Vector3f();
	}

	public Matrix4f getViewMatrix() {
		this.getPosition().negate(negatedPosition);
		return viewMatrix.identity().translate(negatedPosition).rotate(this.getRotation());
	}

	public abstract void updateProjectionMatrix(Window window);

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
}
