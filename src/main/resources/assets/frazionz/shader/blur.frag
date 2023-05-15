
uniform sampler2D DiffuseSampler;

uniform float Directions = 15.0;
uniform float Quality = 5.0;
uniform float Size = 10.0;

void main() {
    float Pi = 6.28318530718; // Pi*2
    
    // GAUSSIAN BLUR SETTINGS }}}
   
    vec2 Radius = Size/iResolution.xy;
    
    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = fragCoord/iResolution.xy;
    // Pixel colour
    vec4 Color = texture2D(DiffuseSampler, uv);
    
    // Blur calculations
    for( float d=0.0; d<Pi; d+=Pi/Directions)
    {
		for(float i=1.0/Quality; i<=1.001; i+=1.0/Quality)
        {
			Color += texture2D(DiffuseSampler, uv+vec2(cos(d),sin(d))*Radius*i);		
        }
    }
    
    // Output to screen
    Color /= Quality * Directions + 1.0;
    gl_FragColor =  Color;
}