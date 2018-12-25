package pl.oblivion.engine.shader;

import lombok.Getter;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.shader.uniforms.UniformMatrix;
import pl.oblivion.engine.shader.uniforms.UniformSampler;

public class DiffuseShader extends AbstractShader {

	@Getter
	private UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
	@Getter
	private UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
	@Getter
	private UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");

	@Getter
	private UniformSampler diffuseTexture = new UniformSampler("diffuseTexture");

	public DiffuseShader(ShaderType shaderType) {
		super(shaderType, "in_position", "in_texture");
		storeAllUniformLocations(projectionMatrix, viewMatrix, transformationMatrix, diffuseTexture);
		connectTextureUnits(diffuseTexture);
	}

}
