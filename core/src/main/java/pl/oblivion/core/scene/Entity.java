package pl.oblivion.core.scene;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.GameObjectType;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.mesh.MeshData;


public class Entity extends GameObject {

	private MeshData mesh;
	@Getter
	private ShaderType shaderType;
	@Getter
	private MeshOGL meshOGL;
	private AbstractRenderer abstractRenderer;

	public Entity(String name, GameObjectType gameObjectType, ShaderType shaderType, MeshData mesh) {
		super(name, gameObjectType);
		this.shaderType = shaderType;
		this.mesh = mesh;
	}

	void init() {
		this.meshOGL = new MeshOGL(mesh.getIndices(), new Attribute(3, mesh.getVertices()));
		this.abstractRenderer = RendererCache.getInstance().getRenderer(shaderType);
	}

	void render() {
		abstractRenderer.render(this.transform, this.meshOGL);
	}

}
