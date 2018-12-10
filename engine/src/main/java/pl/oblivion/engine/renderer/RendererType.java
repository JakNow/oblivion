package pl.oblivion.engine.renderer;

import lombok.Getter;

@Getter
public enum RendererType {
  STATIC_RENDERER("static");

  private final String location;

  RendererType(String location) {
    this.location = location;
  }
}
