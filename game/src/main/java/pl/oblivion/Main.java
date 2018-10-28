package pl.oblivion;

import pl.oblivion.common.annotations.AppConfig;
import pl.oblivion.engine.Application;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import pl.oblivion.model.mesh.Mesh;
import pl.oblivion.model.model.Model;

@AppConfig("app.properties")
public class Main extends Application{
    
    private static final Logger logger = LogManager.getLogger(Main.class);
    
    public Main(){
        int[] indices ={
                // front
                0, 1, 2,
                2, 3, 0,
                // right
                1, 5, 6,
                6, 2, 1,
                // back
                7, 6, 5,
                5, 4, 7,
                // left
                4, 0, 3,
                3, 7, 4,
                // bottom
                4, 5, 1,
                1, 0, 4,
                // top
                3, 2, 6,
                6, 7, 3,
        };
        
        float[] vertices = {
                // front
                -0.5f, -0.5f,  0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                -0.5f,  0.5f,  0.5f,
                // back
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f,  0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f,
        };
        
        Mesh mesh = Mesh.create(2);
        mesh.bind();
        mesh.createIndexBuffer(indices);
        mesh.createAttribute(0,vertices,3);
        mesh.unbind();
        
        Model model = new Model();
        
        model.setMesh(mesh);
        this.getRendererHandler().addModel(model);
        
    }
    public static void main(String[] args) {
        new Main().run();
    }
    
    protected void update(float delta) {
    
    }
}


