package pl.oblivion.engine.camera;

import pl.oblivion.engine.Window;

import static pl.oblivion.common.utils.GetSystemProperty.getFloat;
import static pl.oblivion.engine.camera.CameraType.ORTHOGRAPHIC;

public class OrthographicCamera extends Camera {

  private final float left;
  private final float right;
  private final float bottom;
  private final float top;
  private final float nearPlane;
  private final float farPlane;

  public OrthographicCamera() {
    super(ORTHOGRAPHIC);
    this.left = getFloat("window.camera.left", -10.0f);
    this.right = getFloat("window.camera.right", 10.0f);
    this.bottom = getFloat("window.camera.bottom", -10.0f);
    this.top = getFloat("window.camera.top", 10.0f);
    this.nearPlane = getFloat("window.camera.nearPlane", -10.0f);
    this.farPlane = getFloat("window.camera.farPlane", 10.0f);
  }

  public OrthographicCamera(
      float left, float right, float bottom, float top, float nearPlane, float farPlane) {
    super(ORTHOGRAPHIC);
    this.left = left;
    this.right = right;
    this.bottom = bottom;
    this.top = top;
    this.nearPlane = nearPlane;
    this.farPlane = farPlane;
  }
    
    public void updateProjectionMatrix(Window window) {
        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        this.projectionMatrix.setOrtho(
                this.left * aspectRatio,
                this.right * aspectRatio,
                this.bottom,
                this.top,
                this.nearPlane,
                this.farPlane);
        window.setResized(false);
        logger.info("Orthographic camera updated");
    }
}
