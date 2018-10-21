/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion;

import org.junit.Assert;
import org.junit.Test;
import pl.oblivion.core.utils.GetSystemProperty;


/**
 *
 * @author Piotr
 */

public class GetSystemPropertyTest
{
    
 @Test
  public void getInt_test() {
    int x  = 1 ; 
    Assert.assertEquals(2, GetSystemProperty.getInt(x)); 
  }
  
  @Test
  public void getFloat_test() {
    float x  = 1 ; 
    Assert.assertEquals(2, GetSystemProperty.getFloat(x)); 
  }
  
  @Test
  public void getDouble_test() {
    double x  = 1.0 ; 
    Assert.assertEquals(2.0, GetSystemProperty.getDouble(x)); 
  }

  @Test
  public void getChar_test() {
    char x  = 'a' ; 
    Assert.assertEquals('a', GetSystemProperty.getChar(x)); 
  }
  
  @Test
  public void getString_test() {
    String x  = "test" ; 
    Assert.assertEquals("test", GetSystemProperty.getString(x)); 
  }
}