package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.shader.DiffuseShader;
import pl.oblivion.engine.shader.ShaderCache;

public class DiffuseRenderer extends AbstractRenderer {

	private static DiffuseShader diffuseShader =
			(DiffuseShader) ShaderCache.getInstance().getShader(ShaderType.DIFFUSE_SHADER);

	public DiffuseRenderer(Camera camera) {
		super(diffuseShader, camera);
		diffuseShader.start();
		diffuseShader.getProjectionMatrix().loadMatrix(camera.getProjectionMatrix());
		diffuseShader.stop();

		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public void prepareShader() {
		diffuseShader.start();
		diffuseShader.getProjectionMatrix().loadMatrix(this.getCamera().getProjectionMatrix());
		diffuseShader.getViewMatrix().loadMatrix(this.getCamera().getViewMatrix());
	}

	@Override
	public void bindModel(MeshOGL meshOGL) {
		meshOGL.bind(meshOGL.getAttributesBinding());
	}

	@Override
	public void render(Transform transform, MeshOGL meshOGL) {
		diffuseShader.getTransformationMatrix().loadMatrix(transform.getTransformationMatrix());
		GL11.glDrawElements(GL11.GL_TRIANGLES, meshOGL.getIndexCount(), GL11.GL_UNSIGNED_INT, 0);

		GL30.glBindVertexArray(0);
	}

	@Override
	public void unbindModel(MeshOGL meshOGL) {
		meshOGL.unbind(meshOGL.getAttributesBinding());
	}

	@Override
	public void end() {
		diffuseShader.stop();
	}
}
