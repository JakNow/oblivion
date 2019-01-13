package pl.oblivion.core.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.model.mesh.MeshData;

import java.util.HashMap;
import java.util.Map;

public class MeshOGLCache {

	private static final Logger logger = LogManager.getLogger(MeshOGLCache.class);

	private static MeshOGLCache instance;

	private Map<MeshData, MeshOGL> meshOGLMap;

	private MeshOGLCache() {
		this.meshOGLMap = new HashMap<>();
	}

	public static synchronized MeshOGLCache getInstance() {
		if (instance == null) {
			instance = new MeshOGLCache();
		}
		return instance;
	}

	public MeshOGL getMeshOGL(MeshData meshData) {
		MeshOGL meshOGL = meshOGLMap.get(meshData);
		if (meshOGL == null) {
			meshOGL = addMeshOGL(meshData);
		}

		logger.info("Getting MeshOGL={}.", meshOGL.getId());
		return meshOGL;
	}

	private MeshOGL addMeshOGL(MeshData meshData) {
		logger.info("Creating MeshOgl from MeshData={}.", meshData);
		MeshOGL meshOGL = new MeshOGL(meshData.getIndices(),
				new Attribute(3, meshData.getVertices()),
				new Attribute(2, meshData.getTextures()));
		meshOGLMap.put(meshData, meshOGL);
		return meshOGL;
	}
}
