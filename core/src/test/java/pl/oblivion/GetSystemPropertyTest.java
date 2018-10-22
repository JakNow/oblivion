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
    System.getProperties().setProperty("Int", "1");
    System.getProperties().setProperty("float", "3.6");
    System.getProperties().setProperty("double", "2.0");
    System.getProperties().setProperty("char", "a");
    
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
  public void getFloat_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getFloat("float"))
        .withFailMessage("Couldn't read float property")
        .isNotNull();
    assertThat(GetSystemProperty.getFloat("float"))
        .withFailMessage("Property value is not correct")
        .isEqualTo(3.6);
  }

  @Test
  public void getFloat_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getFloat("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }

  @Test
  public void getDouble_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getDouble("double"))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getDouble("Int"))
        .withFailMessage("Property value is not correct")
        .isEqualTo(2.0);
  }

  @Test
  public void getDouble_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getDouble("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }

  @Test
  public void getChar_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getChar("char"))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getChar("char"))
        .withFailMessage("Property value is not correct")
        .isEqualTo('a');
  }

  @Test
  public void getChar_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getChar("NotPresent"))
        .withFailMessage("Should return null value.")
        .isNull();
  }
  
 
}