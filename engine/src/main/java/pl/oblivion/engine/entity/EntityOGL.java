package pl.oblivion.engine.entity;

import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.engine.material.TextureOGL;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.ShaderType;

@Getter
@Setter
public abstract class EntityOGL extends GameObject {

	protected MeshOGL meshOGL;
	protected TextureOGL textureOGL;
	protected ShaderType shaderType;
}
