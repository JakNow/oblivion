package pl.oblivion.engine.shader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.engine.renderer.ShaderType;

import java.util.HashMap;
import java.util.Map;

public class ShaderCache {
    
    private static final Logger logger = LogManager.getLogger(ShaderCache.class);
    
    private static ShaderCache instance;
    
    private Map<ShaderType, AbstractShader> rendererMap;
    
    private ShaderCache() {
        rendererMap = new HashMap<>();
    }
    
    public static synchronized ShaderCache getInstance() {
        if (instance == null) {
            instance = new ShaderCache();
        }
        return instance;
    }
    
    public void addShader(AbstractShader abstractShader) {
        logger.info("Adding Shader {}", abstractShader.getShaderType());
        rendererMap.put(abstractShader.getShaderType(), abstractShader);
    }
    
    public AbstractShader getShader(ShaderType shaderType) {
        return rendererMap.get(shaderType);
    }
}
