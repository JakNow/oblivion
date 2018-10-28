package pl.oblivion.model.mesh;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

@Getter
@Setter
public class Mesh {

  private static final int BYTES_PER_FLOAT = 4;
  private static final int BYTES_PER_INT = 4;

  public final int id;
  private final VBO[] vbos;
  
  private int[] indices;
  private float[] vertices;
    private int indexCount;
    
  private Mesh(int id, int vbosSize) {
    this.id = id;
    this.vbos = new VBO[vbosSize];
  }

  public static Mesh create(int vbosSize) {
    int id = GL30.glGenVertexArrays();
    return new Mesh(id, vbosSize);
  }

  public void bind(int... attributes) {
    bind();
    for (int i : attributes) {
      GL20.glEnableVertexAttribArray(i);
    }
  }

  private void bind() {
    GL30.glBindVertexArray(id);
  }

  public void unbind(int... attributes) {
    for (int i : attributes) {
      GL20.glDisableVertexAttribArray(i);
    }
    unbind();
  }

  private void unbind() {
    GL30.glBindVertexArray(0);
  }

  public void createIndexBuffer(int[] indices) {
    vbos[0] = VBO.create(GL15.GL_ELEMENT_ARRAY_BUFFER);
    vbos[0].bind();
    vbos[0].storeData(indices);
    this.indexCount = indices.length;
  }

  public void createAttribute(int attribute, float[] data, int attributeSize) {
    vbos[attribute + 1] = VBO.create(GL15.GL_ARRAY_BUFFER);
    vbos[attribute + 1].bind();
    vbos[attribute + 1].storeData(data);
    GL20.glVertexAttribPointer(
        attribute, attributeSize, GL11.GL_FLOAT, false, attributeSize * BYTES_PER_FLOAT, 0);
    vbos[attribute + 1].unbind();
  }
    
    public void createAttribute(int attribute, int[] data, int attributeSize) {
        vbos[attribute + 1] = VBO.create(GL15.GL_ARRAY_BUFFER);
        vbos[attribute + 1].bind();
        vbos[attribute + 1].storeData(data);
        GL20.glVertexAttribPointer(
                attribute, attributeSize, GL11.GL_FLOAT, false, attributeSize * BYTES_PER_FLOAT, 0);
        vbos[attribute + 1].unbind();
    }
    
    public void delete(){
      GL30.glDeleteVertexArrays(id);
      for (VBO vbo : vbos){
          vbo.delete();
      }
    }
    
}
