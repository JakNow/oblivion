/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.oblivion.core.utils.GetSystemProperty;

import static org.assertj.core.api.Assertions.assertThat;

/** @author Piotr */
public class GetSystemPropertyTest {

  @BeforeClass
  public static void setUp() {
    System.getProperties().setProperty("Int", "1");
  }

  @Test
  public void getInt_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getInt("Int"))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getInt("Int"))
        .withFailMessage("Property value is not correct")
        .isEqualTo(1);
  }

  @Test
  public void getInt_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getInt("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }

  @Test
  public void getFloat_test() {
    float x = 1;
    Assert.assertEquals(2, GetSystemProperty.getFloat(x));
  }

  @Test
  public void getDouble_test() {
    double x = 1.0;
    Assert.assertEquals(2.0, GetSystemProperty.getDouble(x));
  }

  @Test
  public void getChar_test() {
    char x = 'a';
    Assert.assertEquals('a', GetSystemProperty.getChar(x));
  }

  @Test
  public void getString_test() {
    String x = "test";
    Assert.assertEquals("test", GetSystemProperty.getString(x));
  }
}
