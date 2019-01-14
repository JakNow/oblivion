package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.entity.EntityOGL;
import pl.oblivion.engine.shader.DiffuseShader;

public class DiffuseRenderer extends AbstractRenderer {

	private DiffuseShader diffuseShader;

	public DiffuseRenderer(Camera camera, DiffuseShader diffuseShader) {
		super(diffuseShader, camera);
		this.diffuseShader = diffuseShader;
		this.bindingAttributes = new int[]{0, 1};
		diffuseShader.start();
		diffuseShader.getProjectionMatrix().loadMatrix(camera.getProjectionMatrix());
		diffuseShader.stop();

		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public void render() {
		this.prepareShader();
		getMeshOGLListMap().forEach((entityOGL, gameObjects) -> {
			bindModel(entityOGL);
			gameObjects.forEach(gameObject -> {
				diffuseShader.getTransformationMatrix().loadMatrix(gameObject.getTransform().getTransformationMatrix());
				GL11.glDrawElements(GL11.GL_TRIANGLES, entityOGL.getMeshOGL().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
			});
			unbindModel(entityOGL);
		});
	}

	@Override
	public void prepareShader() {
		diffuseShader.start();
		diffuseShader.getProjectionMatrix().loadMatrix(this.getCamera().getProjectionMatrix());
		diffuseShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
	}

	@Override
	public void bindModel(EntityOGL entity) {
		entity.getMeshOGL().bind(bindingAttributes);
		//todo bind material/material
	}

	@Override
	public void unbindModel(EntityOGL entity) {
		entity.getMeshOGL().unbind(bindingAttributes);
		//todo unbind material/material
	}
}
