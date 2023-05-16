#version 120

uniform sampler2D DiffuseSampler;

varying vec2 texCoord;
varying vec2 oneTexel;

uniform vec2 InSize;

uniform vec2 BlurDir;
uniform float Radius;
uniform float Progress;

void main() {
    vec4 blurred = vec4(0.0);
    float totalStrength = 0.0;
    float totalAlpha = 0.0;
    float totalSamples = 0.0;
    int progRadius = int(floor(Radius * Progress));
    float radiusFactor = float(progRadius * 2 + 1);

    for (int r = -progRadius; r <= progRadius; r += 2) {
        vec4 smple = texture2D(DiffuseSampler, texCoord + oneTexel * float(r) * BlurDir);

        // Accumulate average alpha
        totalAlpha += smple.a;
        totalSamples += 1.0;

        // Accumulate smoothed blur
        float strength = 1.0 - abs(float(r) / float(progRadius));
        totalStrength += strength;
        blurred += smple;
    }

    gl_FragColor = vec4(blurred.rgb / radiusFactor, totalAlpha / totalSamples);
}