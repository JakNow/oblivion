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
  }

  @Test
  public void getInt_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getInt("1",123))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getInt("1",123))
        .withFailMessage("Property value is not correct")
        .isEqualTo(1);
  }
  @Test
  public void getInt_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getInt("NotPresent",123))
        .withFailMessage("Should return 123 value.")
        .isEqualTo(123);
  }  
   @Test
  public void getFloat_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getFloat("1",123.0f))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getFloat("1",123.0f))
        .withFailMessage("Property value is not correct")
        .isEqualTo(1.0f);
  }
  @Test
  public void getFloat_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getFloat("NotPresent",123.0f))
        .withFailMessage("Should return 123 value.")
        .isEqualTo(123.0f);
  }
  @Test
  public void getDouble_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getDouble("1",123.0))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getDouble("1",123.0))
        .withFailMessage("Property value is not correct")
        .isEqualTo(1.0);
  }
  @Test
  public void getDouble_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getDouble("NotPresent",123))
        .withFailMessage("Should return 123 value.")
        .isEqualTo(123.0);
  }  
   @Test
  public void getChar_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getChar("a",'a'))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getChar("a",'a'))
        .withFailMessage("Property value is not correct")
        .isEqualTo('a');
  }
  @Test
  public void getChar_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getChar("NotPresent",'a'))
        .withFailMessage("Should return 123 value.")
        .isEqualTo('a');
  }  
  @Test
  public void getString_propertyAvailable_Test() {
    assertThat(GetSystemProperty.getString("a","abc"))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(GetSystemProperty.getString("a","abc"))
        .withFailMessage("Property value is not correct")
        .isEqualTo("a");
  }
  @Test
  public void getString_propertyNotPresent_Test() {
    assertThat(GetSystemProperty.getString("NotPresent","abc"))
        .withFailMessage("Should return 123 value.")
        .isEqualTo("abc");
  }
}