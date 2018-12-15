package pl.oblivion.engine.camera;

import lombok.Getter;
import pl.oblivion.engine.Window;

import static pl.oblivion.common.utils.GetSystemProperty.getFloat;
import static pl.oblivion.engine.camera.CameraType.PERSPECTIVE;

@Getter
public class PerspectiveCamera extends Camera {

  private final float fieldOfView;
  private final float nearPlane;
  private final float farPlane;

  public PerspectiveCamera() {
    super(PERSPECTIVE);
    this.fieldOfView = getFloat("window.camera.fieldOfView", 70);
    this.nearPlane = getFloat("window.camera.nearPlane", 0.1f);
    this.farPlane = getFloat("window.camera.farPlane", 1000f);
  }

  public PerspectiveCamera(float fieldOfView, float nearPlane, float farPlane) {
    super(PERSPECTIVE);
    this.fieldOfView = fieldOfView;
    this.nearPlane = nearPlane;
    this.farPlane = farPlane;
  }
    
    public void updateProjectionMatrix(Window window) {
        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        this.projectionMatrix.setPerspective(
                this.fieldOfView, aspectRatio, this.nearPlane, this.farPlane);
        window.setResized(false);
        logger.info("Perspective camera updated");
    }
}
