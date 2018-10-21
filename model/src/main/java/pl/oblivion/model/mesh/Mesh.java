package pl.oblivion.model.mesh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mesh {
    
    private int[] indices;
    private float[] vertices;
}
