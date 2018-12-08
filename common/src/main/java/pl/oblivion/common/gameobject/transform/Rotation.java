package pl.oblivion.common.gameobject.transform;

import org.joml.Vector3f;

public class Rotation {
    
    public static final Vector3f RIGHT = new Vector3f(1,0,0);
    public static final Vector3f LEFT = new Vector3f(-1,0,0);
    
    public static final Vector3f UP = new Vector3f(0,1,0);
    public static final Vector3f DOWN = new Vector3f(0,-1,0);
    
    public static final Vector3f FORWARD = new Vector3f(0,0,1);
    public static final Vector3f BACKWARD = new Vector3f(0,0,-1);
}
