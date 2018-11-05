package pl.oblivion.engine.mesh;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@Getter
//@AllArgsConstructor
class Attribute {
    
    final int size;
    final float[] attributes;
    
    public Attribute(int size, float[] attributes) {
        this.size = size;
        this.attributes = attributes;
    }
    
    public int getSize() {
        return size;
    }
    
    public float[] getAttributes() {
        return attributes;
    }
}
