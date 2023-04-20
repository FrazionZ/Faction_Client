package fz.frazionz.mods.keystrokes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;



public class KeyStrokes {

    public static final KeyStrokes UP = new KeyStrokes(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode()), Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
    public static final KeyStrokes DOWN = new KeyStrokes(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode()), Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
    public static final KeyStrokes LEFT = new KeyStrokes(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode()), Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
    public static final KeyStrokes RIGHT = new KeyStrokes(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode()), Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);

    public static final KeyStrokes LMB = new KeyStrokes("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18);
    public static final KeyStrokes RMB = new KeyStrokes("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18);

    private final String name;
    private final KeyBinding keyBinding;
    private final int x, y, width, height;

    public KeyStrokes(String name, KeyBinding keyBinding, int x, int y, int width, int height) {

        this.name = name;
        this.keyBinding = keyBinding;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public String getName() {

        return this.name;

    }

    public boolean isPressed() {

        return this.keyBinding.isKeyDown();

    }

    public int getX() {

        return x;

    }

    public int getY() {

        return y;

    }

    public int getHeight() {

        return height;

    }

    public int getWidth() {

        return width;

    }

}