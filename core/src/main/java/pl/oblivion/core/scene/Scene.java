package pl.oblivion.core.scene;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.core.assets.spaceobjects.RenderableObjects;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.renderer.RendererCache;
import pl.oblivion.engine.renderer.ShaderType;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    
    private static final Logger logger = LogManager.getLogger(Scene.class);
    
    @Getter
    Camera camera;
    private List<RenderableObjects> gameObjectList;
    
    public Scene() {
        gameObjectList = new ArrayList<>();
    }
    
    public void addToScene(GameObject gameObject) {
        if (gameObject instanceof Camera) {
            logger.info("Setting up camera: {}", gameObject);
            this.camera = (Camera) gameObject;
        } else {
            logger.info("Adding to list: {}", gameObject);
            gameObjectList.add((RenderableObjects) gameObject);
        }
    }
    
    public void render() {
        RendererCache.getInstance().getRenderer(ShaderType.DIFFUSE_SHADER).prepareScene();
        RendererCache.getInstance().getRenderer(ShaderType.DIFFUSE_SHADER).prepareShader();
        gameObjectList.forEach(
                gameObject -> {
                    gameObject.prepare();
                    gameObject.render();
                });
        RendererCache.getInstance().getRenderer(ShaderType.DIFFUSE_SHADER).end();
    }
    
    public void delete() {
        gameObjectList.get(0).getAbstractRenderer().cleanUp();
    }
}
