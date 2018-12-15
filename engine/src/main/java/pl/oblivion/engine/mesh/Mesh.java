package pl.oblivion.engine.mesh;

import java.util.List;

public interface Mesh {
    
    int getId();
    
    int generateId();
    
    List<VBO> getVBOS();
    
    VBO getIndexVBO();
    
    int getIndexCount();
}
