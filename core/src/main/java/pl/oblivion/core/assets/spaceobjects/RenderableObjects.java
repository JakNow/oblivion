package pl.oblivion.core.assets.spaceobjects;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.GameObjectType;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.mesh.MeshData;

@Getter
public abstract class RenderableObjects extends GameObject {

	MeshData meshData;
	AbstractRenderer abstractRenderer;
	ShaderType shaderType;
	MeshOGL meshOGL;

	RenderableObjects(
			String name, GameObjectType gameObjectType, ShaderType shaderType, MeshData meshData) {
		super(name, gameObjectType);
		this.shaderType = shaderType;
		this.meshData = meshData;
	}

	public void initObject() {
		this.abstractRenderer = RendererCache.getInstance().getRenderer(shaderType);
		this.meshOGL = new MeshOGL(meshData.getIndices(), new Attribute(3, meshData.getVertices()));
	}

	public void bind() {
		this.abstractRenderer.bindModel(meshOGL);
	}

	public void render() {
		this.abstractRenderer.render(transform, meshOGL);
	}

	public void unbind() {
		this.abstractRenderer.unbindModel(meshOGL);
	}

	public void end() {
		this.abstractRenderer.end();
	}
}
