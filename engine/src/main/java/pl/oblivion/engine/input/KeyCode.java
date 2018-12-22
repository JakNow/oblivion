package pl.oblivion.engine.input;

import lombok.Getter;

import static org.lwjgl.glfw.GLFW.*;

@Getter
public enum KeyCode {
	ESCAPE("Escape", GLFW_KEY_ESCAPE),
	SPACE_BAR("Space_bar", GLFW_KEY_SPACE),
	W("W", GLFW_KEY_W),
	S("S", GLFW_KEY_S),
	A("A", GLFW_KEY_A),
	D("D", GLFW_KEY_D),
	E("E", GLFW_KEY_E),
	Q("Q", GLFW_KEY_Q),
	HORIZONTAL("Horizontal", GLFW_KEY_A, GLFW_KEY_D),
	VERTICAL("Vertical", GLFW_KEY_W, GLFW_KEY_S);

	private String keyAlias;
	private int GLFW_KeyCode_Positive;
	private int GLFW_KeyCode_Negative;

	KeyCode(String keyAlias, int GLFW_KeyCode_Positive, int GLFW_KeyCode_Negative) {
		this.keyAlias = keyAlias;
		this.GLFW_KeyCode_Positive = GLFW_KeyCode_Positive;
		this.GLFW_KeyCode_Negative = GLFW_KeyCode_Negative;
	}

	KeyCode(String keyAlias, int GLFW_KeyCode_Positive) {
		this.keyAlias = keyAlias;
		this.GLFW_KeyCode_Positive = GLFW_KeyCode_Positive;
		this.GLFW_KeyCode_Negative = -1;
	}
}
