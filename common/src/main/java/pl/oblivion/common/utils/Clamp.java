/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.oblivion.common.utils;

/**
 *
 * @author Piotr
 */
public class Clamp {
    
    public static int met1(int target,int min,int max){
      
        if(target<min)
            return min;
        else if(target>max)
            return max;
        else
            return target;
        
    }
    
    public static int met2(int target,int min){
        
        if(target<min)
            return min;
        else
            return target;
        
    }
    
    public static int met3(int target, int max){
        
        if(target>max)
            return max;
        else 
            return target;
    }
    
}
