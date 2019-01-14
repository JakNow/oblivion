package pl.oblivion.model.material;

import org.joml.Vector4f;

public interface MaterialData {

	Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);

	default String getName() {
		return "Material";
	}

	default Vector4f getDiffuseColor() {
		return DEFAULT_COLOR;
	}

	default Texture getDiffuseTexture() {
		return null;
	}
}
