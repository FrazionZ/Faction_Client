package fz.frazionz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LaunchClient {

    static List<String> arguments = new ArrayList<>();

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.util.Debug", "true");
        arguments.addAll(Arrays.asList(args));
        arguments.add("--tweakClass");
        arguments.add("me.djtheredstoner.lwjgl.Tweaker");
        net.minecraft.launchwrapper.Launch.main(arguments.toArray(new String[0]));
        //net.minecraft.client.main.Main.main(args);
    }

}
