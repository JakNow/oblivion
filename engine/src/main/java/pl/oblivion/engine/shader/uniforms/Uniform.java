package pl.oblivion.engine.shader.uniforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL20;

public abstract class Uniform {

  private static final int NOT_FOUND = -1;
  private final Logger logger = LogManager.getLogger(this.getClass());
  private String name;
  private int location;

  public Uniform(String name) {
    this.name = name;
    this.logger.info("Creating {} with name={}",this.getClass().getSimpleName(),name);
  }
  
  public void storeUniformLocation(int programID) {
    location = GL20.glGetUniformLocation(programID, name);
    if (location == NOT_FOUND) {
      logger.error("Could not found {} for name={}", this.getClass().getSimpleName(), name);
    }
  }

  int getLocation() {
    return location;
  }
}
