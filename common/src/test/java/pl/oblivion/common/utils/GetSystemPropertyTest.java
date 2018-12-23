package pl.oblivion.common.utils;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GetSystemPropertyTest {

	@BeforeClass
	public static void setUp() {
		System.getProperties().setProperty("GetInt", "1");
		System.getProperties().setProperty("GetFloat", "1.23512f");
		System.getProperties().setProperty("GetDouble", "1.23512d");
		System.getProperties().setProperty("GetString", "String");
		System.getProperties().setProperty("GetChar", "a");
		System.getProperties().setProperty("GetBoolean", "true");
	}

	@Test
	public void getInt_propertyAvailable_Test() {
		assertThat(GetSystemProperty.getInt("GetInt", 123))
				.withFailMessage("Couldn't read int property")
				.isNotNull();
		assertThat(GetSystemProperty.getInt("GetInt", 123))
				.withFailMessage("Property value is not correct")
				.isEqualTo(1);
	}

	@Test
	public void getInt_propertyNotPresent_SetDefault_Test() {
		assertThat(GetSystemProperty.getInt("NotPresent", 123))
				.withFailMessage("Should return 123 value.")
				.isEqualTo(123);
	}

	@Test
	public void getFloat_propertyAvailable_Test() {
		assertThat(GetSystemProperty.getFloat("GetFloat", 123.0f))
				.withFailMessage("Couldn't read float property")
				.isNotNull();
		assertThat(GetSystemProperty.getFloat("GetFloat", 123.0f))
				.withFailMessage("Property value is not correct")
				.isEqualTo(1.23512f);
	}

	@Test
	public void getFloat_propertyNotPresent_SetDefault_Test() {
		assertThat(GetSystemProperty.getFloat("NotPresent", 1.491f))
				.withFailMessage("Should return 123.0f value.")
				.isEqualTo(1.491f);
	}

	@Test
	public void getDouble_propertyAvailable_Test() {
		assertThat(GetSystemProperty.getDouble("GetDouble", 123.0))
				.withFailMessage("Couldn't read double property")
				.isNotNull();
		assertThat(GetSystemProperty.getDouble("GetDouble", 123.0))
				.withFailMessage("Property value is not correct")
				.isEqualTo(1.23512d);
	}

	@Test
	public void getDouble_propertyNotPresent_SetDefault_Test() {
		assertThat(GetSystemProperty.getDouble("NotPresent", 12345678))
				.withFailMessage("Should return 123 value.")
				.isEqualTo(1.2345678E7);
	}

	@Test
	public void getChar_propertyAvailable_Test() {
		assertThat(GetSystemProperty.getChar("GetChar", 'a'))
				.withFailMessage("Couldn't read char property")
				.isNotNull();
		assertThat(GetSystemProperty.getChar("GetChar", 'a'))
				.withFailMessage("Property value is not correct")
				.isEqualTo('a');
	}

	@Test
	public void getChar_propertyNotPresent_SetDefault_Test() {
		assertThat(GetSystemProperty.getChar("NotPresent", 'a'))
				.withFailMessage("Should return 'a' value.")
				.isEqualTo('a');
	}

	@Test
	public void getString_propertyAvailable_Test() {
		assertThat(GetSystemProperty.getString("GetString", "abc"))
				.withFailMessage("Couldn't read String property")
				.isNotNull();
		assertThat(GetSystemProperty.getString("GetString", "abc"))
				.withFailMessage("Property value is not correct")
				.isEqualTo("String");
	}

	@Test
	public void getString_propertyNotPresent_SetDefault_Test() {
		assertThat(GetSystemProperty.getString("NotPresent", "abc"))
				.withFailMessage("Should return abc value.")
				.isEqualTo("abc");
	}

	@Test
	public void getBoolean_propertyAvailable_Test() {
		assertThat(GetSystemProperty.getBoolean("GetBoolean", true))
				.withFailMessage("Couldn't read String property")
				.isNotNull();
		assertThat(GetSystemProperty.getBoolean("GetBoolean", true))
				.withFailMessage("Property value is not correct")
				.isEqualTo(true);
	}

	@Test
	public void getBoolean_propertyNotPresent_SetDefault_Test() {
		assertThat(GetSystemProperty.getBoolean("NotPresent", true))
				.withFailMessage("Should return abc value.")
				.isEqualTo(true);
	}
}
