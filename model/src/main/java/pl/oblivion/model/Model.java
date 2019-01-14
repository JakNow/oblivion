package pl.oblivion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.oblivion.model.material.MaterialData;
import pl.oblivion.model.mesh.MeshData;

@Getter
@Setter
@AllArgsConstructor
public class Model {

	private String name;
	private MeshData meshData;
	private MaterialData materialData;

}
