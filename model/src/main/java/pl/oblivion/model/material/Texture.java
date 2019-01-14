package pl.oblivion.model.material;

import de.matthiasmann.twl.utils.PNGDecoder;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.utils.MyFile;

import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
@Setter
public class Texture {

	private static final Logger logger = LogManager.getLogger(Texture.class);

	private int width;
	private int height;
	private String name;
	private ByteBuffer textureBuffer;

	public Texture(MyFile fileName) {
		this(loadTexture(fileName));
		logger.info("Loaded material {}.", fileName.getPath());

	}

	private Texture(String name, int width, int height, ByteBuffer textureBuffer) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.textureBuffer = textureBuffer;
	}

	private Texture(Texture texture) {
		this.width = texture.getWidth();
		this.height = texture.getHeight();
		this.textureBuffer = texture.getTextureBuffer();
	}

	private static Texture loadTexture(MyFile myfile) {
		try {
			PNGDecoder decoder = new PNGDecoder(myfile.getInputStream());
			ByteBuffer textureBuffer =
					ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(textureBuffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
			textureBuffer.flip();
			return new Texture(myfile.getName(), decoder.getWidth(), decoder.getHeight(), textureBuffer);
		} catch (IOException e) {
			logger.error("Couldn't load file at {}.", myfile.getPath(), e);
			throw new RuntimeException();
		}
	}
}
