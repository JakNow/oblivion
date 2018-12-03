package pl.oblivion.common.gameobject;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public interface TransformOperations {
    
    void translate(float xTranslation, float yTranslation, float zTranslation);
    
    void translate(Vector3f translationVector);
    
    void translateLocal(float xTranslation, float yTranslation, float zTranslation);
    
    void translateLocal(Vector3f translationVector);
    
    Vector3f getPosition();
    
    
    void rotate(float xAngle, float yAngle, float zAngle);
    
    void rotate(Quaternionf quaternionf);
    
    void rotateLocal(float xAngle, float yAngle, float zAngle);
    
    void rotateLocal(Quaternionf quaternionf);
    
    Quaternionf getRotation();
    
    
    Vector3f getScale();
}
