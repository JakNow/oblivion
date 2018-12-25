package pl.oblivion.engine.mesh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class MeshOGLCache {

	private static final Logger logger = LogManager.getLogger(MeshOGLCache.class);

	private static MeshOGLCache instance;

	private Map<Integer, MeshOGL> meshOGLMap;

	private MeshOGLCache() {
		this.meshOGLMap = new HashMap<>();
	}

	public static synchronized MeshOGLCache getInstance() {
		if (instance == null) {
			instance = new MeshOGLCache();
		}
		return instance;
	}

	public void addMeshOGL(MeshOGL meshOGL) {
		logger.info("Adding MeshOGL {}.", meshOGL);
		if (meshOGLMap.get(meshOGL.getId()) != null) {
			logger.info("MeshOGL with id={} already in a map.", meshOGL.getId());
		} else {
			meshOGLMap.put(meshOGL.getId(), meshOGL);
		}
	}

	public MeshOGL getMeshOGL(int id) {
		return meshOGLMap.get(id);
	}

	public Map<Integer, MeshOGL> getAvailableMeshOGL() {
		return meshOGLMap;
	}
}
