package net.minecraft.launchwrapper;

import java.util.*;
import java.io.*;

public interface ITweaker
{
    void acceptOptions(final List<String> p0, final File p1, final File p2, final String p3);
    
    void injectIntoClassLoader(final LaunchClassLoader p0);
    
    String getLaunchTarget();
    
    String[] getLaunchArguments();
}
