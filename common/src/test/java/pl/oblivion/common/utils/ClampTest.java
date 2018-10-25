/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion.common.utils;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Piotr
 */
public class ClampTest {
    
  @BeforeClass
  public static void setUp() {
    
  }
  
  @Test
  public void getminmax_propertyAvailable_Test() {
    assertThat(Clamp.minmax(123, 100, 200))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(Clamp.minmax(123,100,200))
        .withFailMessage("Property value is not correct")
        .isEqualTo(123);
  }

  @Test
  public void getminmax_propertyNotPresent_SetDefault_Test() {
    assertThat(Clamp.minmax(123,100,200))
        .withFailMessage("Should return 123 value.")
        .isEqualTo(123);
  }
  
  @Test
  public void getmin_propertyAvailable_Test() {
    assertThat(Clamp.min(123, 100))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(Clamp.min(123, 100))
        .withFailMessage("Property value is not correct")
        .isEqualTo(123);
  }

  @Test
  public void getmin_propertyNotPresent_SetDefault_Test() {
    assertThat(Clamp.min(123, 100))
        .withFailMessage("Should return 123 value.")
        .isEqualTo(123);
  }
  
  @Test
  public void getmax_propertyAvailable_Test() {
    assertThat(Clamp.max(123, 200))
        .withFailMessage("Couldn't read int property")
        .isNotNull();
    assertThat(Clamp.max(123, 200))
        .withFailMessage("Property value is not correct")
        .isEqualTo(123);
  }

  @Test
  public void getmax_propertyNotPresent_SetDefault_Test() {
    assertThat(Clamp.max(123, 200))
        .withFailMessage("Should return 123 value.")
        .isEqualTo(123);
  }
}
