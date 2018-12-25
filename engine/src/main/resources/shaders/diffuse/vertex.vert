#version 400

layout(location = 0)in vec3 in_position;
layout(location = 1) in vec2 in_texture;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 transformationMatrix;

out vec2 passTexture;

void main(void){
	gl_Position = projectionMatrix*viewMatrix*transformationMatrix*vec4(in_position, 1.0);
    passTexture = in_texture;
}