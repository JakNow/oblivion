package pl.oblivion.engine.renderer;

import lombok.Getter;
import org.joml.Matrix4f;
import pl.oblivion.engine.Window;
import pl.oblivion.engine.shader.AbstractShader;

@Getter
public abstract class AbstractRenderer {

    private AbstractShader shader;
    private RendererHandler handler;
    private Matrix4f projectionMatrix;
    
    public AbstractRenderer(AbstractShader shader){
        this.shader = shader;
        this.handler = RendererHandler.getInstance();
    }
    
    public abstract void render(Window window);
    
    public abstract void prepare(Window window);
    
    public abstract void delete();
    
    public abstract void end();
    
    private void cleanUp(){
        this.shader.cleanUp();
        this.handler.delete();
    }
}
