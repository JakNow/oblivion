package pl.oblivion.engine.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import pl.oblivion.model.model.Model;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;

public class RendererHandler {

  private static RendererHandler instance;

  private List<Model> modelList = new ArrayList<>();

  private RendererHandler() {}

  public static synchronized RendererHandler getInstance() {
    if (instance == null) {
      instance = new RendererHandler();
    }
    return instance;
  }

  public void delete() {
    modelList.forEach(model -> model.getMesh().delete());
  }

  public void render() {
    prepare();
    modelList.forEach(model -> render(model));
  }

  public void addModel(Model model) {
    modelList.add(model);
  }

  private void prepare() {
    GL11.glClearColor(1f, 0, 0, 1f);
    GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  }

  private void render(Model model) {
    GL30.glBindVertexArray(model.getMesh().id);
    model.getMesh().bind(0, 1);
    GL11.glDrawElements(
        GL11.GL_TRIANGLES, model.getMesh().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
  }
}
