package pl.oblivion.common.gameobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.oblivion.common.transformation.Transformation;

@Getter
@AllArgsConstructor
public abstract class GameObject {

  private final Transformation transformation;
}
