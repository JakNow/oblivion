package pl.oblivion.core.assets.spaceobjects;

import pl.oblivion.core.assets.Entity;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.model.primitive.CubePrimitive;

public class Cube extends Entity {

	public Cube() {
		super(CubePrimitive.getInstance(), ShaderType.DIFFUSE_SHADER);
	}
}
