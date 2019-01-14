package pl.oblivion.common.utils;

public class MissingFileException extends RuntimeException {

	public MissingFileException(String file) {
		super("Couldn't find file at: " + file + ".");
	}
}
