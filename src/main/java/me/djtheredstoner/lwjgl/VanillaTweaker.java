package me.djtheredstoner.lwjgl;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VanillaTweaker implements ITweaker {
    private List<String> args;
    private final ArrayList<String> arguments = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String version) {
        this.arguments.addAll(args);
        if (gameDir != null) {
            this.arguments.add("--gameDir");
            this.arguments.add(".");
        }
        if (assetsDir != null) {
            this.arguments.add("--assetsDir");
            this.arguments.add("assets");
        }
        if (version != null) {
            this.arguments.add("--version");
            this.arguments.add(version);
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        classLoader.registerTransformer("net.minecraft.launchwrapper.injector.VanillaTweakInjector");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return arguments.toArray(new String[0]);
    }
}