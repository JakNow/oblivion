package pl.oblivion.core.cache;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.ShaderType;

import java.util.HashMap;
import java.util.Map;

public class RendererCache {

	private static final Logger logger = LogManager.getLogger(RendererCache.class);

	private static RendererCache instance;

	@Getter
	private Map<ShaderType, AbstractRenderer> renderers;

	private RendererCache() {
		renderers = new HashMap<>();
	}

	public static synchronized RendererCache getInstance() {
		if (instance == null) {
			instance = new RendererCache();
		}
		return instance;
	}

	public void addRenderer(AbstractRenderer abstractRenderer) {
		logger.info("Adding Renderer {}", abstractRenderer.getShader().getShaderType());
		renderers.put(abstractRenderer.getShader().getShaderType(), abstractRenderer);
	}

	public AbstractRenderer get(ShaderType shaderType) {
		return renderers.get(shaderType);
	}
}
