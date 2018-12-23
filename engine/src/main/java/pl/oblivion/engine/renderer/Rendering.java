package pl.oblivion.engine.renderer;

import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.engine.mesh.MeshOGL;

public interface Rendering {

	void render(Transform transform, MeshOGL meshOGL);

	void prepareShader();

	void bindModel(MeshOGL meshOGL);

	void unbindModel(MeshOGL meshOGL);

	void end();
}
