#version 400

in vec2 passTexture;

out vec4 out_color;

uniform sampler2D diffuseTexture;

void main(void){

    out_color = vec4(texture(diffuseTexture,passTexture));

}