package pl.oblivion.engine.camera;

import lombok.Getter;

@Getter
public enum CameraType {
  ORTHOGRAPHIC("Orthographic"),
  PERSPECTIVE("Perspective");

  private final String name;

  CameraType(String name) {
    this.name = name;
  }
}
