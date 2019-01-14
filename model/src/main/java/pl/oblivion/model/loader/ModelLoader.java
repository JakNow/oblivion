package pl.oblivion.model.loader;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector4f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import pl.oblivion.common.utils.MyFile;
import pl.oblivion.model.Model;
import pl.oblivion.model.material.MaterialData;
import pl.oblivion.model.material.Texture;
import pl.oblivion.model.mesh.MeshData;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.assimp.Assimp.*;
import static pl.oblivion.model.material.MaterialData.DEFAULT_COLOR;

public class ModelLoader {

	private static final Logger logger = LogManager.getLogger(ModelLoader.class);

	public Model[] loadModel(MyFile file) {
		AIScene aiScene = aiImportFile(file.getClassPath(),
				aiProcess_JoinIdenticalVertices | aiProcess_Triangulate | aiProcess_FixInfacingNormals);

		if (aiScene == null) {
			logger.error("Couldn't load model: {}.", file.getClassPath());
			throw new RuntimeException();
		}

		int numberOfMaterials = aiScene.mNumMaterials();
		PointerBuffer aiMaterials = aiScene.mMaterials();
		MaterialData[] materialData = new MaterialData[numberOfMaterials];

		for (int i = 0; i < numberOfMaterials; i++) {
			AIMaterial aiMaterial = AIMaterial.create(aiMaterials.get(i));
			materialData[i] = getMaterials(aiMaterial);
		}

		int numberOfMeshes = aiScene.mNumMeshes();
		PointerBuffer aiMeshes = aiScene.mMeshes();
		Model[] models = new Model[numberOfMeshes];
		for (int i = 0; i < numberOfMeshes; i++) {
			AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
			models[i] = processModels(aiMesh, materialData);
		}

		return models;
	}

	private Model processModels(AIMesh aiMesh, MaterialData[] materialData) {
		MaterialData material;
		int materialIdx = aiMesh.mMaterialIndex();
		if (materialIdx >= 0 && materialIdx < materialData.length) {
			material = materialData[materialIdx];
		} else {
			material = new MaterialData() {
			};
		}
		return new Model(aiMesh.mName().dataString(), processMesh(aiMesh), material);
	}

	private String getMaterialName(AIMaterial aiMaterial) {
		AIString aiName = AIString.create();
		int result = aiGetMaterialString(aiMaterial, AI_MATKEY_NAME, aiTextureType_NONE, 0, aiName);

		return result == 0 ? aiName.dataString() : "Material";
	}

	private Vector4f getDiffuseColor(AIMaterial aiMaterial) {
		AIColor4D color = AIColor4D.create();
		int result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_DIFFUSE, aiTextureType_NONE, 0, color);
		return result == 0 ? new Vector4f(color.r(), color.g(), color.b(), color.a()) : DEFAULT_COLOR;
	}

	private Texture getDiffuseTexture(AIMaterial aiMaterial) {
		AIString path = AIString.calloc();
		aiGetMaterialTexture(aiMaterial, aiTextureType_DIFFUSE, 0, path, (IntBuffer) null, null, null, null, null,
				null);

		String texturePath = path.dataString();
		return texturePath.length() > 0 ? new Texture(new MyFile(path.dataString())) : null;
	}

	private MaterialData getMaterials(AIMaterial aiMaterial) {
		String name = getMaterialName(aiMaterial);
		Vector4f diffuseColor = getDiffuseColor(aiMaterial);
		Texture diffuseTexture = getDiffuseTexture(aiMaterial);

		return new MaterialData() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Vector4f getDiffuseColor() {
				return diffuseColor;
			}

			@Override
			public Texture getDiffuseTexture() {
				return diffuseTexture;
			}
		};
	}

	private MeshData processMesh(AIMesh aiMesh) {
		float[] vertices = ArrayUtils.toPrimitive(processVertices(aiMesh).toArray(new Float[0]), 0.0F);
		float[] textures = ArrayUtils.toPrimitive(processTextures(aiMesh).toArray(new Float[0]), 0.0F);
		float[] normals = ArrayUtils.toPrimitive(processNormals(aiMesh).toArray(new Float[0]), 0.0F);
		int[] indices = processIndices(aiMesh).stream().mapToInt(Integer::intValue).toArray();
		String name = aiMesh.mName().dataString();
		return new MeshData() {

			@Override
			public String getName() {
				return name;
			}

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
