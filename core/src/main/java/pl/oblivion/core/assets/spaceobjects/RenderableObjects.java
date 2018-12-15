package pl.oblivion.core.assets.spaceobjects;

import lombok.Getter;
import pl.oblivion.common.gameobject.GameObject;
import pl.oblivion.common.gameobject.GameObjectType;
import pl.oblivion.engine.mesh.Attribute;
import pl.oblivion.engine.mesh.MeshOGL;
import pl.oblivion.engine.renderer.AbstractRenderer;
import pl.oblivion.model.mesh.MeshData;

@Getter
public abstract class RenderableObjects extends GameObject {
    
    MeshData meshData;
    AbstractRenderer abstractRenderer;
    MeshOGL meshOGL;
    
    RenderableObjects(
            String name,
            GameObjectType gameObjectType,
            AbstractRenderer abstractRenderer,
            MeshData meshData) {
        super(name, gameObjectType);
        this.abstractRenderer = abstractRenderer;
        this.meshData = meshData;
        this.meshOGL = new MeshOGL(meshData.getIndices(), new Attribute(3, meshData.getVertices()));
    }
    
    public void prepare() {
        this.abstractRenderer.prepareModel(transform);
    }
    
    public void render() {
        this.abstractRenderer.render(meshOGL);
    }
    
    public void end() {
        this.abstractRenderer.end();
    }
}
