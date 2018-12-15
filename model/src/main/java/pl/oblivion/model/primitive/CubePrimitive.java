package pl.oblivion.model.primitive;

import pl.oblivion.model.mesh.MeshData;

public class CubePrimitive implements MeshData {
    
    private final float[] vertices = {
            // front
            -0.5f,
            -0.5f,
            0.5f,
            0.5f,
            -0.5f,
            0.5f,
            0.5f,
            0.5f,
            0.5f,
            -0.5f,
            0.5f,
            0.5f,
            // back
            -0.5f,
            -0.5f,
            -0.5f,
            0.5f,
            -0.5f,
            -0.5f,
            0.5f,
            0.5f,
            -0.5f,
            -0.5f,
            0.5f,
            -0.5f,
    };
    
    private final float[] textures = {};
    private final float[] normals = {};
    
    private final int[] indices = {
            // front
            0, 1, 2,
            2, 3, 0,
            // right
            1, 5, 6,
            6, 2, 1,
            // back
            7, 6, 5,
            5, 4, 7,
            // left
            4, 0, 3,
            3, 7, 4,
            // bottom
            4, 5, 1,
            1, 0, 4,
            // top
            3, 2, 6,
            6, 7, 3,
    };
    
    private final int indexCount = indices.length;
    
    @Override
    public float[] getVertices() {
        return vertices;
    }
    
    @Override
    public int[] getIndices() {
        return indices;
    }
    
    @Override
    public int getIndexCount() {
        return indexCount;
    }
    
    @Override
    public float[] getTextures() {
        return new float[0];
    }
    
    @Override
    public float[] getNormals() {
        return new float[0];
    }
}
