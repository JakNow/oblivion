package pl.oblivion.core.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.engine.texture.TextureOGL;
import pl.oblivion.model.texture.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureOGLCache {

	private static final Logger logger = LogManager.getLogger(TextureOGLCache.class);

	private static TextureOGLCache instance;

	private Map<Texture, TextureOGL> textureOGLMap;

	private TextureOGLCache() {
		this.textureOGLMap = new HashMap<>();
	}

	public static synchronized TextureOGLCache getInstance() {
		if (instance == null) {
			instance = new TextureOGLCache();
		}
		return instance;
	}

	public TextureOGL getTextureOGL(Texture texture) {
		TextureOGL textureOGL = textureOGLMap.get(texture);
		if (textureOGL == null) {
			textureOGL = addTextureOGL(texture);
		}
		logger.info("Getting TextureOGL={}.", textureOGL.getId());
		return textureOGL;
	}

	private TextureOGL addTextureOGL(Texture texture) {
		logger.info("Creating TextureOGL from Texutre={}.", texture.getName());
		TextureOGL textureOGL = new TextureOGL(texture.getTextureBuffer(), texture.getWidth(), texture.getHeight());
		textureOGLMap.put(texture, textureOGL);
		return textureOGL;
	}

}
