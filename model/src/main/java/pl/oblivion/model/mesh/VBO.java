package pl.oblivion.model.mesh;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/** Vertex Buffer Object */
public class VBO {

  private final int id;
  private final int type;

  private VBO(int id, int type) {
    this.id = id;
    this.type = type;
  }

  public static VBO create(int type) {
    int id = GL15.glGenBuffers();
    return new VBO(id, type);
  }

  public void bind() {
    GL15.glBindBuffer(type, id);
  }

  public void unbind() {
    GL15.glBindBuffer(type, 0);
  }

  public void storeData(float[] data) {
    FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
    buffer.put(data);
    buffer.flip();
    storeData(buffer);
  }

  public void storeData(FloatBuffer buffer) {
    GL15.glBufferData(type, buffer, GL15.GL_STATIC_DRAW);
  }
  
  public void storeData(int[] data){
      IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
      buffer.put(data);
      buffer.flip();
      storeData(buffer);
  }
    
    public void storeData(IntBuffer buffer) {
        GL15.glBufferData(type, buffer, GL15.GL_STATIC_DRAW);
    }
    
    public void delete(){
      GL15.glDeleteBuffers(id);
    }
}
