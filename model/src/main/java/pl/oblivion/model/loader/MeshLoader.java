package pl.oblivion.model.loader;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIVector3D;
import pl.oblivion.common.utils.MyFile;
import pl.oblivion.model.mesh.MeshData;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.assimp.Assimp.*;

public class MeshLoader {

	private static final Logger logger = LogManager.getLogger(MeshLoader.class);

	public MeshData[] loadMesh(MyFile file) {
		AIScene aiScene = aiImportFile(file.getClassPath(),
				aiProcess_JoinIdenticalVertices | aiProcess_Triangulate | aiProcess_FixInfacingNormals);

		if (aiScene == null) {
			throw new RuntimeException("Couldn't load model: " + file.getClassPath());
		}
		logger.info("Loading mesh from {}.", file.getClassPath());

		int numberOfMeshes = aiScene.mNumMeshes();
		PointerBuffer aiMeshes = aiScene.mMeshes();
		MeshData[] meshes = new MeshData[numberOfMeshes];
		for (int i = 0; i < numberOfMeshes; i++) {
			AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
			meshes[i] = processMesh(aiMesh);
		}
		return meshes;
	}

	private MeshData processMesh(AIMesh aiMesh) {
		float[] vertices = ArrayUtils.toPrimitive(processVertices(aiMesh).toArray(new Float[0]), 0.0F);
		float[] textures = ArrayUtils.toPrimitive(processTextures(aiMesh).toArray(new Float[0]), 0.0F);
		float[] normals = ArrayUtils.toPrimitive(processNormals(aiMesh).toArray(new Float[0]), 0.0F);
		int[] indices = processIndices(aiMesh).stream().mapToInt(Integer::intValue).toArray();

		return new MeshData() {
			@Override
			public float[] getVertices() {
				return vertices;
			}

			@Override
			public int[] getIndices() {
				return indices;
			}

			@Override
			public float[] getTextures() {
				return textures;
			}

			@Override
			public float[] getNormals() {
				return normals;
			}
		};
	}


	private List<Float> processVertices(AIMesh aiMesh) {
		AIVector3D.Buffer aiVertices = aiMesh.mVertices();
		List<Float> vertices = new ArrayList<>();
		while (aiVertices.remaining() > 0) {
			AIVector3D aiVertex = aiVertices.get();
			vertices.add(aiVertex.x());
			vertices.add(aiVertex.y());
			vertices.add(aiVertex.z());
		}
		return vertices;
	}

	private List<Float> processTextures(AIMesh aiMesh) {
		AIVector3D.Buffer aiTextures = aiMesh.mTextureCoords(0);
		List<Float> textures = new ArrayList<>();
		int numberOfTextures = aiTextures != null ? aiTextures.remaining() : 0;
		for (int i = 0; i < numberOfTextures; i++) {
			AIVector3D textCoord = aiTextures.get();
			textures.add(textCoord.x());
			textures.add(1 - textCoord.y());
		}
		return textures;
	}

	private List<Float> processNormals(AIMesh aiMesh) {
		AIVector3D.Buffer aiNormals = aiMesh.mNormals();
		List<Float> normals = new ArrayList<>();
		while (aiNormals != null && aiNormals.remaining() > 0) {
			AIVector3D aiNormal = aiNormals.get();
			normals.add(aiNormal.x());
			normals.add(aiNormal.y());
			normals.add(aiNormal.z());
		}
		return normals;
	}

	private List<Integer> processIndices(AIMesh aiMesh) {
		int numFaces = aiMesh.mNumFaces();
		List<Integer> indices = new ArrayList<>();
		AIFace.Buffer aiFaces = aiMesh.mFaces();
		for (int i = 0; i < numFaces; i++) {
			AIFace aiFace = aiFaces.get(i);
			IntBuffer buffer = aiFace.mIndices();
			while (buffer.remaining() > 0) {
				indices.add(buffer.get());
			}
		}
		return indices;
	}
}
