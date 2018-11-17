package pl.oblivion.engine.shader;

import lombok.Getter;
import pl.oblivion.engine.renderer.RendererType;
import pl.oblivion.engine.shader.uniforms.UniformMatrix;

public class StaticShader extends AbstractShader {

  @Getter private UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
  @Getter private UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
  @Getter private UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");

  public StaticShader(RendererType rendererType) {
    super(rendererType, "in_position");
    storeAllUniformLocations(projectionMatrix, viewMatrix, transformationMatrix);
  }
}
