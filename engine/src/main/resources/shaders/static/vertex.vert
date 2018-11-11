#version 400

layout(location = 0)in vec3 in_position;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 transformationMatrix;

out vec3 pass_color;

void main(void){
	gl_Position = projectionMatrix*viewMatrix*transformationMatrix*vec4(in_position, 1.0);
    pass_color = vec3(in_position.x+0.3,in_position.y+0.5,in_position.z+0.7);
}