package pl.oblivion.engine.shader;

import java.io.BufferedReader;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import pl.oblivion.common.utils.MyFile;
import pl.oblivion.engine.shader.uniforms.Uniform;

public abstract class AbstractShader {

  private final Logger logger = LogManager.getLogger(this.getClass());
  private int programID;

  private MyFile file;

  public AbstractShader(String type, String... inVariables) {
    int vertexShaderID = loadShader(type, "vertex.vert", GL20.GL_VERTEX_SHADER);
    int fragmentShaderID = loadShader(type, "fragment.frag", GL20.GL_FRAGMENT_SHADER);
    programID = GL20.glCreateProgram();
    GL20.glAttachShader(programID, vertexShaderID);
    GL20.glAttachShader(programID, fragmentShaderID);
    bindAttributes(inVariables);
    GL20.glLinkProgram(programID);
    GL20.glDetachShader(programID, vertexShaderID);
    GL20.glDetachShader(programID, fragmentShaderID);
    GL20.glDeleteShader(vertexShaderID);
    GL20.glDeleteShader(fragmentShaderID);
  }

  private int loadShader(String shaderType, String shader, int type) {
    StringBuilder shaderSource = new StringBuilder();
    file = new MyFile("shaders/" + shaderType + "/" + shader);
    try (BufferedReader br = file.getReader()) {
      shaderSource.append(br.lines().collect(Collectors.joining("\n")));
    } catch (Exception e) {
      logger.error("Could not read file.");
      e.printStackTrace();
      System.exit(-1);
    } finally {
      file.closeReader();
      logger.info("Closing reader.");
    }
    int shaderID = GL20.glCreateShader(type);
    GL20.glShaderSource(shaderID, shaderSource);
    GL20.glCompileShader(shaderID);
    if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
      System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
      logger.error("Could not compile shader " + file);
      System.exit(-1);
    }
    logger.info("Compiled shader " + file + " was successful.");
    return shaderID;
  }

  private void bindAttributes(String[] inVariables) {
    for (int i = 0; i < inVariables.length; i++) {
      GL20.glBindAttribLocation(programID, i, inVariables[i]);
    }
  }

  protected void storeAllUniformLocations(Uniform... uniforms) {
    for (Uniform uniform : uniforms) {
      uniform.storeUniformLocation(programID);
    }
    GL20.glValidateProgram(programID);
  }

  protected void storeAllComplexUniformLocation(Uniform[]... uniforms) {
    for (Uniform[] uniform : uniforms) {
      storeAllUniformLocations(uniform);
    }
  }

  public void start() {
    GL20.glUseProgram(programID);
  }

  public void cleanUp() {
    stop();
    GL20.glDeleteProgram(programID);
  }

  public void stop() {
    GL20.glUseProgram(0);
  }
}
