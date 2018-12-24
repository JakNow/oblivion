package pl.oblivion.engine.texture;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.GL;

import java.nio.ByteBuffer;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OGLTextureTest {

	@BeforeClass
	public static void createGLFWContext() {
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		long window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();

	}

	@Test
	public void loadTextureFromConstructor() {
		ByteBuffer textureBuffer = ByteBuffer.allocateDirect(4 * 4 * 4);
		OGLTexture oglTexture = new OGLTexture(textureBuffer, 4, 4);

		assertSoftly(
				soft -> {
					soft.assertThat(oglTexture.getId()).isEqualTo(1);
					soft.assertThat(oglTexture.getWidth()).isEqualTo(4);
					soft.assertThat(oglTexture.getHeight()).isEqualTo(4);
				}
		);
	}
}
