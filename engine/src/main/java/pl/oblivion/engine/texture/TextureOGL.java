package pl.oblivion.engine.texture;

import lombok.Getter;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11C.GL_RGBA;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11C.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11C.glBindTexture;
import static org.lwjgl.opengl.GL11C.glDeleteTextures;
import static org.lwjgl.opengl.GL11C.glPixelStorei;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

@Getter
public class TextureOGL {

	private final int id;
	private final int width;
	private final int height;

	public TextureOGL(ByteBuffer textureBuffer, int width, int height) {
		this.width = width;
		this.height = height;
		this.id = loadTexture(textureBuffer, width, height);
	}


	private static int loadTexture(ByteBuffer textureBuffer, int width, int height) {
		int textureId = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureId);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
				GL_RGBA, GL_UNSIGNED_BYTE, textureBuffer);
		glGenerateMipmap(GL_TEXTURE_2D);
		return textureId;
	}

	public void bind(int unit) {
		glActiveTexture(GL_TEXTURE0 + unit);
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void delete() {
		glDeleteTextures(id);
	}
}
