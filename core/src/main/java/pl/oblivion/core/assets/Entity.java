package pl.oblivion.core.assets;

import pl.oblivion.core.cache.MeshOGLCache;
import pl.oblivion.engine.entity.EntityOGL;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.mesh.MeshData;

public class Entity extends EntityOGL {

	private MeshData meshData;

	public Entity(MeshData meshData, ShaderType shaderType) {
		this.meshData = meshData;
		this.shaderType = shaderType;
	}

	public Entity load() {
		this.meshOGL = MeshOGLCache.getInstance().getMeshOGL(this.meshData);
		return this;
	}

	public void unload() {
		this.meshOGL.delete();
		this.textureOGL.delete();
	}
}
