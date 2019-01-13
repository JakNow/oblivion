package pl.oblivion.common.annotations;

import org.junit.Test;
import pl.oblivion.common.configuration.ConfigurationLoader;
import pl.oblivion.common.configuration.OblivionConfiguration;

import static org.assertj.core.api.Java6Assertions.assertThat;

@OblivionConfiguration("app.properties")
public class OblivionConfigurationLoaderTest {

	@Test
	public void loadPropertiesFromFile_test() {
		ConfigurationLoader.loadConfiguration(OblivionConfigurationLoaderTest.class);
		assertThat(System.getProperty("testString"))
				.withFailMessage("Can't read property from file")
				.isEqualTo("String");
	}
}
