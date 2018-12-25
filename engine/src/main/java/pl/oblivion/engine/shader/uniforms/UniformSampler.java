package pl.oblivion.engine.shader.uniforms;

import org.lwjgl.opengl.GL20;

public class UniformSampler extends Uniform {

	private int currentValue;
	private boolean used = false;

	public UniformSampler(String name) {
		super(name);
	}

	public void loadTextureUnit(int textureUnit) {
		if (!used || currentValue != textureUnit) {
			GL20.glUniform1i(super.getLocation(), textureUnit);
			used = true;
			currentValue = textureUnit;
		}
	}
}
