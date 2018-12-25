package pl.oblivion.core.scene;

import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.GameObjectType;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.texture.TextureOGL;
import pl.oblivion.model.mesh.MeshData;
import pl.oblivion.model.texture.Texture;


public class Entity extends GameObject {

	@Getter
	private ShaderType shaderType;
	private AbstractRenderer abstractRenderer;

	@Getter
	private MeshOGL meshOGL;
	private MeshData mesh;

	@Getter
	private TextureOGL textureOGL;
	@Setter
	private Texture texture;

	public Entity(String name, GameObjectType gameObjectType, ShaderType shaderType, MeshData mesh) {
		super(name, gameObjectType);
		this.shaderType = shaderType;
		this.mesh = mesh;
	}

	void init() {
		this.meshOGL = new MeshOGL(mesh.getIndices(), new Attribute(3, mesh.getVertices()), new Attribute(2, mesh.getTextures()));
		if (texture != null) {
			this.textureOGL = new TextureOGL(texture.getTextureBuffer(), texture.getWidth(), texture.getHeight());
		}
		this.abstractRenderer = RendererCache.getInstance().getRenderer(shaderType);
	}

	void render() {
		if (this.textureOGL != null) {
			textureOGL.bind(0);
		}
		abstractRenderer.render(this.transform, this.meshOGL, this.textureOGL);
	}

}
