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
    
    public static int getInt(String a, int b) {
        Properties p = System.getProperties();
        if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return b;  
      }
       return 0;
    }

    public static int getInt(String a) {
        int liczba = getInt(a, 0); // here, 0 is a default value for b
      return liczba;
    }

            
    public static float getFloat(String a, float b) {
        Properties p = System.getProperties();
        if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return b;  
      }
       return 0;
    }

    public static float getFloat(String a) {
        float liczba = getFloat(a, 0); // here, 0 is a default value for b
      return liczba;
    }

    
    
        
    public static double getDouble(String a, double b) {
        Properties p = System.getProperties();
        if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return b;  
      }
       return 0;
    }

    public static double getDouble(String a) {
        double liczba = getDouble(a, 0); // here, 0 is a default value for b
      return liczba;
    }

    
    
        
    public static char getChar(String a, char b) {
        Properties p = System.getProperties();
        if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return b;  
      }
       return 0;
    }

    public static char getChar(String a) {
        char znak = getChar(a, ' '); // here, 0 is a default value for b
      return znak;
    }

    
    
 // public static Integer getInt2(String key) {
//     return Integer.getInteger(key);
//    }  
    
    
}