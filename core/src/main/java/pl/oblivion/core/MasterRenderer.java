package pl.oblivion.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import pl.oblivion.core.assets.spaceobjects.Entity;
import pl.oblivion.core.cache.RendererCache;
import pl.oblivion.core.scene.Scene;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.camera.Camera;
import pl.oblivion.engine.camera.PerspectiveCamera;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.engine.renderer.DiffuseRenderer;
import pl.oblivion.engine.renderer.ShaderType;
import pl.oblivion.engine.shader.DiffuseShader;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

public class MasterRenderer {

	private static final Logger logger = LogManager.getLogger(MasterRenderer.class);

	private static MasterRenderer instance;
	private Camera camera;
	private Map<ShaderType, AbstractRenderer> renderers;
	private Window window;

	private MasterRenderer() {
		logger.info("Initializing Master Renderer.");
		this.camera = new PerspectiveCamera();
		renderers = new HashMap<>();
		renderers.put(ShaderType.DIFFUSE_SHADER, new DiffuseRenderer(camera, new DiffuseShader()));
		this.window = Window.getInstance();
	}

	public static synchronized MasterRenderer getInstance() {
		if (instance == null) {
			instance = new MasterRenderer();
		}
		return instance;
	}

	public void prepareScene() {
		GL11.glClearColor(0f, 0f, 0f, 1f);
		GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		if (window.isResized()) {
			glViewport(0, 0, window.getWidth(), window.getHeight());
			camera.updateProjectionMatrix(window);
		}
	}

	public void render() {
		renderers.forEach((shaderType, abstractRenderer) -> abstractRenderer.render());
	}

	public void cleanUp() {
		renderers.forEach(((shaderType, abstractRenderer) -> abstractRenderer.cleanUp()));
	}

	public void loadScene(Scene scene) {
		scene.getProcessedObjects().forEach(entity -> getRenderer(entity).add(entity));
	}

	private AbstractRenderer getRenderer(Entity entity) {
		ShaderType entityShader = entity.getShaderType();
		AbstractRenderer renderer = renderers.get(entityShader);
		if (renderer == null) {
			renderer = RendererCache.getInstance().get(entityShader);
			renderers.put(entityShader, renderer);
		}

		return renderer;
	}
}
