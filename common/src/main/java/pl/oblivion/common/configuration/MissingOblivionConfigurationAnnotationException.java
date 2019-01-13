package pl.oblivion.common.configuration;

public class MissingOblivionConfigurationAnnotationException extends RuntimeException {

	public MissingOblivionConfigurationAnnotationException() {
		super("Add @OblivionConfiguration annotation to main class");
	}
}
