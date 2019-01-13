package pl.oblivion.engine.entity;

import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.texture.TextureOGL;

@Getter
@Setter
public abstract class EntityOGL extends GameObject {

	protected MeshOGL meshOGL;
	protected TextureOGL textureOGL;
	protected ShaderType shaderType;
}
