package pl.oblivion.engine.mesh;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import lombok.Getter;

@Getter
public abstract class Mesh implements MeshData {

  private static final int BYTES_PER_FLOAT = 4;
  private static final int BYTES_PER_INT = 4;
  private final List<VBO> vbos;
  private int id;
  private VBO indexVBO;

  private int indexCount;

  public Mesh(int[] indices, Attribute... attributes) {
    this.id = generateId();
    this.vbos = new ArrayList<>();
    this.indexCount = indices.length;

    this.bind();
    this.createIndexBuffer(indices);
    for (int i = 0; i < attributes.length; i++) {
      this.createAttribute(i, attributes[i].attributes, attributes[i].size);
    }
    this.unbind();
  }

  @Override
  public int generateId() {
    return GL30.glGenVertexArrays();
  }

  public void bind(int... attributes) {
    bind();
    for (int i : attributes) {
      GL20.glEnableVertexAttribArray(i);
    }
  }

  private void bind() {
    GL30.glBindVertexArray(this.id);
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

  private void createIndexBuffer(int[] indices) {
    indexVBO = VBO.create(GL15.GL_ELEMENT_ARRAY_BUFFER);
    indexVBO.bind();
    indexVBO.storeData(indices);

    this.indexCount = indices.length;
  }

  private void createAttribute(int attribute, float[] data, int attributeSize) {
    VBO vbo = VBO.create(GL15.GL_ARRAY_BUFFER);
    vbo.bind();
    vbo.storeData(data);
    GL20.glVertexAttribPointer(
        attribute, attributeSize, GL11.GL_FLOAT, false, attributeSize * BYTES_PER_FLOAT, 0);
    vbo.unbind();
    vbos.add(vbo);
  }

  private void createAttribute(int attribute, int[] data, int attributeSize) {
    VBO vbo = VBO.create(GL15.GL_ARRAY_BUFFER);
    vbo.bind();
    vbo.storeData(data);
    GL20.glVertexAttribPointer(
        attribute, attributeSize, GL11.GL_FLOAT, false, attributeSize * BYTES_PER_INT, 0);
    vbo.unbind();
    vbos.add(vbo);
  }

  public void delete() {
    GL30.glDeleteVertexArrays(this.id);
    for (VBO vbo : vbos) {
      vbo.delete();
    }
    indexVBO.delete();
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public List<VBO> getVBOS() {
    return this.vbos;
  }

  @Override
  public VBO getIndexVBO() {
    return indexVBO;
  }

  @Override
  public int getIndexCount() {
    return this.indexCount;
  }
}
