package pl.oblivion.engine.renderer;

import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.texture.TextureOGL;

public interface Rendering {

	void render(Transform transform, MeshOGL meshOGL, TextureOGL textureOGL);

	void prepareShader();

	void bindModel(MeshOGL meshOGL);

	void unbindModel(MeshOGL meshOGL);

	void end();
}
