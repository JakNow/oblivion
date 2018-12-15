package pl.oblivion.engine.renderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RendererCache {
    
    private static final Logger logger = LogManager.getLogger(RendererCache.class);
    
    private static RendererCache instance;
    
    private Map<ShaderType, AbstractRenderer> rendererMap;
    
    private RendererCache() {
        rendererMap = new HashMap<>();
    }
    
    public static synchronized RendererCache getInstance() {
        if (instance == null) {
            instance = new RendererCache();
        }
        return instance;
    }
    
    public void addRenderer(AbstractRenderer abstractRenderer) {
        logger.info("Adding Shader {}", abstractRenderer.getShader().getShaderType());
        rendererMap.put(abstractRenderer.getShader().getShaderType(), abstractRenderer);
    }
    
    public AbstractRenderer getRenderer(ShaderType shaderType) {
        return rendererMap.get(shaderType);
    }
}
