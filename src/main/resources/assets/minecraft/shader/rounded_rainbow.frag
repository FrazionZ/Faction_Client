void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    // Input info
    vec2 boxPos; // The position of the center of the box (in normalized coordinates)
    vec2 boxBnd; // The half-bounds (radii) of the box (in normalzied coordinates)
    float radius;// Radius
    
    
   	boxPos = vec2(0.5, 0.5);	// center of the screen
    boxBnd = vec2(0.25, 0.25);  // half of the area
    radius = 0.1;
    
    // Normalize the pixel coordinates (this is "passTexCoords" in your case)
    vec2 uv = gl_FragColor/iResolution.xy;
    
    vec2 aspectRatio = vec2(iResolution.x/iResolution.y, 1.0);
    
    // In order to make sure visual distances are preserved, we multiply everything by aspectRatio
    uv *= aspectRatio;
    boxPos *= aspectRatio;
    boxBnd *= aspectRatio;
    
    // Time varying pixel color
    vec3 col = 0.5 + 0.5*cos(iTime+uv.xyx+vec3(0,2,4));
    
    // Output to screen
    float alpha = length(max(abs(uv - boxPos) - boxBnd, 0.0)) - radius;
    
	// Shadertoy doesn't have an alpha in this case
    if(alpha <= 0.0){
    	gl_FragColor = vec4(col, 1.0);
    }else{
        gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
    }
}