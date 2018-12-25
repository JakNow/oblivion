package pl.oblivion.engine.texture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TextureOGLCache {

	private static final Logger logger = LogManager.getLogger(TextureOGLCache.class);

	private static TextureOGLCache instance;

	private Map<Integer, TextureOGL> textureOGLMap;

	private TextureOGLCache() {
		this.textureOGLMap = new HashMap<>();
	}

	public static synchronized TextureOGLCache getInstance() {
		if (instance == null) {
			instance = new TextureOGLCache();
		}
		return instance;
	}

	public void addTextureOGL(TextureOGL textureOGL) {
		logger.info("Adding TextureOGL {}.", textureOGL);
		if (textureOGLMap.get(textureOGL.getId()) != null) {
			logger.info("TextureOGL with id={} already in a map.", textureOGL.getId());
		} else {
			textureOGLMap.put(textureOGL.getId(), textureOGL);
		}
	}

	public TextureOGL getTextureOGL(int id) {
		return textureOGLMap.get(id);
	}

	public Map<Integer, TextureOGL> getAvailableTextureOGL() {
		return textureOGLMap;
	}
}
