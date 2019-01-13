package pl.oblivion.engine.renderer;

import pl.oblivion.engine.entity.EntityOGL;

public interface Rendering {

	void render();

	void prepareShader();

	void bindModel(EntityOGL entity);

	void unbindModel(EntityOGL entity);

	void cleanUp();
}
