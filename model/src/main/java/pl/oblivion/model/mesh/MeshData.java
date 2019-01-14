package pl.oblivion.model.mesh;

public interface MeshData {

	String getName();

	float[] getVertices();

	int[] getIndices();

	float[] getTextures();

	float[] getNormals();

	String toString();
}
