package pl.oblivion.core.assets;

import pl.oblivion.core.cache.MeshOGLCache;
import pl.oblivion.core.cache.TextureOGLCache;
import pl.oblivion.engine.entity.EntityOGL;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.mesh.MeshData;
import pl.oblivion.model.texture.Texture;

import java.util.Objects;

public class Entity extends EntityOGL {

	private MeshData meshData;
	private Texture texture;

	public Entity(MeshData meshData, Texture texture, ShaderType shaderType) {
		this.meshData = meshData;
		this.texture = texture;
		this.shaderType = shaderType;
	}

	public Entity load() {
		this.meshOGL = MeshOGLCache.getInstance().getMeshOGL(this.meshData);
		if (Objects.nonNull(texture)) {
			this.textureOGL = TextureOGLCache.getInstance().getTextureOGL(this.texture);
		}
		return this;
	}

	public void unload() {
		this.meshOGL.delete();
		if (Objects.nonNull(textureOGL)) {
			this.textureOGL.delete();
		}
	}
}
