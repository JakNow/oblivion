package pl.oblivion.engine.shader;

import lombok.Getter;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.shader.uniforms.UniformMatrix;

public class DiffuseShader extends AbstractShader {
    
    @Getter
    private UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
    @Getter
    private UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
    @Getter
    private UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
    
    public DiffuseShader(ShaderType shaderType) {
        super(shaderType, "in_position");
        storeAllUniformLocations(projectionMatrix, viewMatrix, transformationMatrix);
    }
}
