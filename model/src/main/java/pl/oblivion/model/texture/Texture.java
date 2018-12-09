package pl.oblivion.model.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import lombok.Getter;
import lombok.Setter;
import pl.oblivion.common.utils.MyFile;

import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
@Setter
public class Texture {

  private int width;
  private int height;
  private ByteBuffer textureBuffer;

  public Texture(MyFile fileName) {
    this(loadTexture(fileName));
  }

  private Texture(int width, int height, ByteBuffer textureBuffer) {
  	this.width = width;
  	this.height = height;
    this.textureBuffer = textureBuffer;
  }
  
  private Texture(Texture texture){
  	this.width = texture.getWidth();
  	this.height = texture.getHeight();
  	this.textureBuffer = texture.getTextureBuffer();
  }

  private static Texture loadTexture(MyFile myfile) {
	  try {
		  PNGDecoder  decoder = new PNGDecoder(myfile.getInputStream());
		  ByteBuffer textureBuffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
		  decoder.decode(textureBuffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
		  textureBuffer.flip();
		  return new Texture(decoder.getWidth(), decoder.getHeight(), textureBuffer);
	  } catch (IOException e) {
		  e.printStackTrace();
		  throw new RuntimeException();
	  }
  }
}
