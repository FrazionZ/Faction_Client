#version 120

uniform vec3 startColor;
uniform vec3 endColor;
uniform float radius;

uniform vec2 position;
uniform vec2 size;

float rounding(vec2 centerPos, vec2 size, float radius) {
    return length(max(abs(centerPos) - size, 0.0)) - radius;
}

void main() {
    float r1 = (endColor.x - startColor.x) / size.x;
    float g1 = (endColor.y - startColor.y) / size.x;
    float b1 = (endColor.z - startColor.z) / size.x;

    float x = gl_FragCoord.x - position.x;

    float r = startColor.x + x * r1;
    float g = startColor.y + x * g1;
    float b = startColor.z + x * b1;

    vec2 rectHalf = size * .5;
    float smoothedAlpha =  (1.0-smoothstep(0.0, 1.65, rounding(rectHalf - (gl_TexCoord[0].st * size), rectHalf - radius - 1., radius))) * 1.0;
    gl_FragColor = vec4(r, g, b, smoothedAlpha);
}