/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion.model.texture;

import pl.oblivion.common.utils.MyFile;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {

	/**
	 * Stores the handle of the texture.
	 */
	private int id = 0;

	/**
	 * Width of the texture.
	 */
	private int width;
	/**
	 * Height of the texture.
	 */
	private int height;

	/**
	 * Creates a texture.
	 */
	public Texture(MyFile fileName) throws Exception {
		loadTexture(fileName);
	}

	public Texture(int id) {
		this.id = id;
	}

	public static ByteBuffer loadTexture(MyFile myfile) throws Exception { //String filename
		//InputStream in = new FileInputStream(new File(fileName.getName()));
                //MyFile inn = new MyFile(fileName);
                //InputStream in = new FileInputStream(new File(inn.getPath()));
		//PNGDecoder decoder = new PNGDecoder(in);
                
                PNGDecoder decoder = new PNGDecoder(myfile.getInputStream());

		ByteBuffer buf = ByteBuffer.allocateDirect(
				4 * decoder.getWidth() * decoder.getHeight());
		decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
		buf.flip();

                
		// Create a new OpenGL texture
		int textureId = glGenTextures();
		// Bind the texture
		glBindTexture(GL_TEXTURE_2D, textureId);

		// Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// Upload the texture data
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0,
				GL_RGBA, GL_UNSIGNED_BYTE, buf);
		// Generate Mip Map
		glGenerateMipmap(GL_TEXTURE_2D);
		//return textureId;
               return buf; 
	}

	public Texture(Texture texture) {
		this.id = texture.id;
	}

	/**
	 * Binds the texture.
	 */
	public void bind(int unit) {
		glActiveTexture(GL_TEXTURE0 + unit);
		glBindTexture(GL_TEXTURE_2D, id);
	}

	/**
	 * Delete the texture.
	 */
	public void delete() {
		glDeleteTextures(id);
	}

	public int getTextureId() {
		return id;
	}
}