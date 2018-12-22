package pl.oblivion.engine.renderer;

import lombok.Getter;

@Getter
public enum ShaderType {
	DIFFUSE_SHADER("diffuse");

	private final String location;

	ShaderType(String location) {
		this.location = location;
	}
}
