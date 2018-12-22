package pl.oblivion.core.assets.spaceobjects;

import pl.oblivion.common.gameobject.GameObjectType;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.primitive.CubePrimitive;

public class Cube extends RenderableObjects {

	public Cube() {
		super("Cube", GameObjectType.SPACE_OBJECT, ShaderType.DIFFUSE_SHADER, new CubePrimitive());
	}
}
