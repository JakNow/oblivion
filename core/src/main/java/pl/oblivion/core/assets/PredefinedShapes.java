package pl.oblivion.core.assets;

import lombok.Getter;

@Getter
public enum PredefinedShapes {

	CUBE(1);

	private int id;

	PredefinedShapes(int id) {
		this.id = id;
	}
}
