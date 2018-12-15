package pl.oblivion.model.mesh;

public interface MeshData {
    
    float[] getVertices();
    
    int[] getIndices();
    
    int getIndexCount();
    
    float[] getTextures();
    
    float[] getNormals();
}
