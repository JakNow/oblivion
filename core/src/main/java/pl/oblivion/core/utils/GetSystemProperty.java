/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion.core.utils;

import java.util.Properties;

/**
 *
 * @author Piotr
 */
public class GetSystemProperty {
       
    public static Integer getInt(String key) {
      Properties p = System.getProperties();
      p.list(System.out);  
        return Integer.getInteger(key);
    }  
    
    public static Float getFloat(String key) {
      Properties p = System.getProperties();
      p.list(System.out);  
        return Float.parseFloat(key);
    }
    
    public static Double getDouble(String key) {
      Properties p = System.getProperties();
      p.list(System.out);  
        return Double.parseDouble(key);
    }
    
    public static char getChar(String key) {
      Properties p = System.getProperties();
      p.list(System.out);  
        return key.charAt(0);
    }
    
    public static String getString(String key) {
      Properties p = System.getProperties();
      p.list(System.out);  
        return key;
    }
}