/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion.common.utils;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Piotr
 */
public class ClampTest {
  
  @Test
  public void clamp_min_returnsCorrectValues_Test() {
    assertThat(Clamp.min(123, 100))
        .withFailMessage("Output value is not correct. Should be 123")
        .isEqualTo(123);
    assertThat(Clamp.min(90, 100))
        .withFailMessage("Output value is not correct. Should return min")
        .isEqualTo(100);
  }
  
  @Test
  public void clamp_max_returnsCorrectValues_Test() {
    assertThat(Clamp.max(123, 200))
        .withFailMessage("Output value is not correct. Should be 123")
        .isEqualTo(123);
    assertThat(Clamp.max(250, 100))
        .withFailMessage("Output value is not correct. Should return max")
        .isEqualTo(100);
    
  }

  @Test
  public void clamp_minmax_returnsCorrectValues_Test() {
    assertThat(Clamp.minmax(123,100,200))
        .withFailMessage("Output value is not correct. Should be 123")
        .isEqualTo(123);
	  
    assertThat(Clamp.minmax(90, 100, 200))
        .withFailMessage("Output value is not correct. Should return min")
        .isEqualTo(100);
	  
    assertThat(Clamp.minmax(250, 100, 200))
        .withFailMessage("Output value is not correct. Should return max")
        .isEqualTo(200);
  }  
}
