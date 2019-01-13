package pl.oblivion.engine.renderer;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.entity.EntityOGL;
import pl.oblivion.engine.shader.AbstractShader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractRenderer implements Rendering {

	protected int[] bindingAttributes;
	private AbstractShader shader;
	private Camera camera;
	private Map<EntityOGL, List<GameObject>> meshOGLListMap;

	AbstractRenderer(AbstractShader shader, Camera camera) {
		this.shader = shader;
		this.camera = camera;
		this.meshOGLListMap = new HashMap<>();
	}


	@Override
	public void cleanUp() {
		shader.cleanUp();
	}

	public void add(EntityOGL entityOGL) {
		List<GameObject> batch = meshOGLListMap.get(entityOGL);
		if (batch == null) {
			batch = new LinkedList<>();
			batch.add(entityOGL);
			meshOGLListMap.put(entityOGL, batch);
		} else {
			batch.add(entityOGL);
		}

	}
}
