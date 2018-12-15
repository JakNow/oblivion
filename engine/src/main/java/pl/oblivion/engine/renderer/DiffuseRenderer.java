package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.shader.DiffuseShader;

public class DiffuseRenderer extends AbstractRenderer {
    
    private static DiffuseShader diffuseShader = new DiffuseShader(ShaderType.DIFFUSE_SHADER);
    
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
    public void prepareModel(Transform transform) {
        diffuseShader.getTransformationMatrix().loadMatrix(transform.getTransformationMatrix());
    }
    
    @Override
    public void render(MeshOGL meshOGL) {
        GL30.glBindVertexArray(meshOGL.getId());
        meshOGL.bind(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, meshOGL.getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
        meshOGL.unbind(0);
        GL30.glBindVertexArray(0);
    }
    
    @Override
    public void end() {
        diffuseShader.stop();
    }
}
