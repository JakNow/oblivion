/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.oblivion.core.utils.GetSystemProperty;


/**
 *
 * @author Piotr
 */

public class GetSystemPropertyTest
{
 
  @BeforeClass
  public static void setUp() {
    System.getProperties().setProperty("key", "1");
    System.getProperties().setProperty("key", "3.6");
    System.getProperties().setProperty("key", "2.0");
    System.getProperties().setProperty("key", "k");
    System.getProperties().setProperty("key", "key");
    
  }

  @Test
  public void getInt_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getInt("1"))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getInt("1"))
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
  public void getFloat_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getFloat("3.6f"))
        .withFailMessage("Couldn't read float property")
        .isNotNull();
    assertThat(GetSystemProperty.getFloat("3.6f"))
        .withFailMessage("Property value is not correct")
        .isEqualTo(3.6);
  }

  /*
  @Test
  public void getFloat_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getFloat("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }
  */

  @Test
  public void getDouble_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getDouble("2.0"))
        .withFailMessage("Couldn't read double property")
        .isNotNull();
    assertThat(GetSystemProperty.getDouble("2.0"))
        .withFailMessage("Property value is not correct")
        .isEqualTo(2.0);
  }

  /*
  @Test
  public void getDouble_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getDouble("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }
  */

  @Test
  public void getChar_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getChar("key"))
        .withFailMessage("Couldn't read char property")
        .isNotNull();
    assertThat(GetSystemProperty.getChar("key"))
        .withFailMessage("Property value is not correct")
        .isEqualTo('k');
  }

  /*
  @Test
  public void getChar_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getChar("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }
  */
  
  @Test
  public void getString_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getString("key"))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getString("key"))
        .withFailMessage("Property value is not correct")
        .isEqualTo("key");
  }

  /*
  @Test
  public void getString_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getString("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }
  */
}