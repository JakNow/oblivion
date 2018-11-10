package pl.oblivion.common.transformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Getter
@AllArgsConstructor
public class Transformation {
    
    private final Vector3f position;
    private final Quaternionf rotation;
    private final Vector3f scale;
}
