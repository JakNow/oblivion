package pl.oblivion.engine.material;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.GL;

import java.nio.ByteBuffer;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class TextureOGLTest {

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
		TextureOGL textureOGL = new TextureOGL(textureBuffer, 4, 4);

		assertSoftly(
				soft -> {
					soft.assertThat(textureOGL.getId()).isEqualTo(1);
					soft.assertThat(textureOGL.getWidth()).isEqualTo(4);
					soft.assertThat(textureOGL.getHeight()).isEqualTo(4);
				}
		);
	}
}
