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
       
    public static int getInt(String key,int key2) {
        if(!key.isEmpty()){
            return Integer.parseInt(key);
        }else{
            return key2;
        } 
    }   
    public static float getFloat(String key,float key2) {
        if(!key.isEmpty()){
            return Integer.parseInt(key);
        }else{
            return key2;
        } 
    }    
    public static double getDouble(String key,double key2) {
        if(!key.isEmpty()){
            return Integer.parseInt(key);
        }else{
            return key2;
        } 
    }    
    public static char getChar(String key,char key2) {
        if(!key.isEmpty()){
            return key.charAt(0);
        }else{
            return key2;
        } 
    }   
    public static String getString(String key,String key2) {
        if(!key.isEmpty()){
            return key;
        }else{
            return key2;
        } 
    }
}