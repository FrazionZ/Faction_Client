uniform vec2 size;
// https://www.shadertoy.com/view/wsKGzW

uniform float radius;

uniform vec3      iResolution;           // viewport resolution (in pixels)
uniform float     iTime;                 // shader playback time (in seconds)

float rounding(vec2 centerPos, vec2 size, float radius) {
    return length(max(abs(centerPos) - size, 0.0)) - radius;
}


void main()
{    
    // Time varying pixel color
    vec3 color = 0.5 + 0.5 * cos(iTime+vec3(0,2,4));
    
    // Output to screen
    vec2 rectHalf = size * .5;
    float smoothedAlpha =  (1.0-smoothstep(0.0, 1.65, rounding(rectHalf - (gl_TexCoord[0].st * size), rectHalf - radius - 1., radius)));
    gl_FragColor = vec4(color, smoothedAlpha);
}