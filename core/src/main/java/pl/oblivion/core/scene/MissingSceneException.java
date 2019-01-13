package pl.oblivion.core.scene;

public class MissingSceneException extends RuntimeException {

	public MissingSceneException(String message) {
		super("Missing Scene =" + message);
	}
}
