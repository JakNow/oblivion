package pl.oblivion.engine.renderer;

import pl.oblivion.common.gameobject.transform.Transform;
import pl.oblivion.engine.mesh.MeshOGL;

public interface Rendering {
    
    void render(MeshOGL meshOGL);
    
    void prepareShader();
    
    void prepareModel(Transform transform);
    
    void end();
}
