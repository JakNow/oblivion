package pl.oblivion.common.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.oblivion.common.utils.MyFile;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationLoader {

	private static final Logger logger = LogManager.getLogger(ConfigurationLoader.class);

	public static void loadConfiguration(Class<?> primarySource) {
		OblivionConfiguration oblivionConfiguration =
				primarySource.getAnnotation(OblivionConfiguration.class);

		if (oblivionConfiguration == null) {
			throw new MissingOblivionConfigurationAnnotationException();
		}
		logger.info("Loading properties from {}.", oblivionConfiguration.value());
		loadProperties(oblivionConfiguration.value());
	}

	private static void loadProperties(String fileName) {
		try (InputStream is = new MyFile(fileName).getInputStream()) {
			System.getProperties().load(is);
		} catch (IOException e) {
			logger.info("Couldn't read {}.", fileName, e);
		}
	}
}
