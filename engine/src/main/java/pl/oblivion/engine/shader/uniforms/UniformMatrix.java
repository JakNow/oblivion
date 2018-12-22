package pl.oblivion.engine.shader.uniforms;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

public class UniformMatrix extends Uniform {

	private FloatBuffer matrixBuffer;

	public UniformMatrix(String name) {
		super(name);
		this.matrixBuffer = BufferUtils.createFloatBuffer(16);
	}

	public void loadMatrix(Matrix4f matrix4f) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			this.matrixBuffer = stack.mallocFloat(16);
			matrix4f.get(matrixBuffer);
			GL20.glUniformMatrix4fv(super.getLocation(), false, this.matrixBuffer);
		}
	}
}
