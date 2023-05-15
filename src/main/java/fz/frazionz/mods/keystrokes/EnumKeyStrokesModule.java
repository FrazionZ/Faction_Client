package fz.frazionz.mods.keystrokes;

import net.minecraft.client.Minecraft;

public enum EnumKeyStrokesModule {

    WASD(KeyStrokes.UP, KeyStrokes.DOWN, KeyStrokes.LEFT, KeyStrokes.RIGHT),

    WASD_MOUSE(KeyStrokes.UP, KeyStrokes.DOWN, KeyStrokes.LEFT, KeyStrokes.RIGHT, KeyStrokes.LMB, KeyStrokes.RMB),

    WASD_SPACE(KeyStrokes.UP, KeyStrokes.DOWN, KeyStrokes.LEFT, KeyStrokes.RIGHT, new KeyStrokes("Space", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18)),

    WASD_SPACE_MOUSE(KeyStrokes.UP, KeyStrokes.DOWN, KeyStrokes.LEFT, KeyStrokes.RIGHT, KeyStrokes.LMB, KeyStrokes.RMB, new KeyStrokes("", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18)),
    ;

    private final KeyStrokes[] keys;
    private int width;
    private int height;

    private EnumKeyStrokesModule(KeyStrokes... keys) {

        this.keys = keys;

        for(KeyStrokes key : keys) {

            this.width = Math.max(this.width, key.getX() + key.getWidth());

            this.height = Math.max(this.height, key.getY() + key.getHeight());

        }

    }

    public KeyStrokes[] getKeys() {

        return keys;

    }

    public int getWidth() {

        return width;

    }

    public int getHeight() {

        return height;

    }
}