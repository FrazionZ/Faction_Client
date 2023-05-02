// https://www.shadertoy.com/view/wsKGzW

#version 120

uniform vec3 startColor;
uniform vec3 endColor;
uniform vec2 resolution;
uniform vec2 size;
uniform float radius;

float rounding(vec2 centerPos, vec2 size, float radius) {
    return length(max(abs(centerPos) - size, 0.0)) - radius;
}

void main() {

	vec2 position = vec2(241.0/255.0, 107.0/255.0);

    vec2 rectHalf = size * .5;
    float smoothedAlpha =  (1.0-smoothstep(0.0, 1.65, rounding(rectHalf - (gl_TexCoord[0].st * size), rectHalf - radius - 1., radius))) * 1.0;
    gl_FragColor = vec4(position, 31.0/255.0, smoothedAlpha);
}