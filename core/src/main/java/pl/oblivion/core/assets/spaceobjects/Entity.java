package pl.oblivion.core.assets.spaceobjects;

import pl.oblivion.engine.entity.EntityOGL;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.mesh.MeshData;

public class Entity extends EntityOGL {

	private MeshData meshData;

	public Entity(MeshData meshData, ShaderType shaderType) {
		this.meshData = meshData;
		this.shaderType = shaderType;
	}

	public Entity load() {
		this.meshOGL = new MeshOGL(meshData.getIndices(),
				new Attribute(3, meshData.getVertices()),
				new Attribute(2, meshData.getTextures()));

		return this;
	}

	public void unload() {
		this.meshOGL.delete();
		this.textureOGL.delete();
	}
}
