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
    
    GetSystemProperty(){
        
    }
    
    public static int getInt(int a){
        
      Properties p = System.getProperties();
      
      if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return a;  
      }  
        return 0;
    }
    
    public static float getFloat(float b){
       
       Properties p = System.getProperties();
      
      if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return b;  
      }  
        return 0;        
    }
    
    public static double getDouble(double c){
        
      Properties p = System.getProperties();
      
      if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return c;  
      }  
        return 0;
    }
    
    public static char getChar(char d){
       
       Properties p = System.getProperties();
      
      if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return d;  
      }  
        return 0;        
    }
    
    public static String getString(String e){
        
        Properties p = System.getProperties();
      
      if(p.isEmpty()){
        p.list(System.out);  
      }else{
        return e;  
      }  
        return " ";
    }

    
    
}
