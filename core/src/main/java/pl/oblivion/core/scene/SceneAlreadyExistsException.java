package pl.oblivion.core.scene;

public class SceneAlreadyExistsException extends RuntimeException {

	public SceneAlreadyExistsException(String message) {
		super("Scene with name =[" + message + "] already exists.");
	}
}
