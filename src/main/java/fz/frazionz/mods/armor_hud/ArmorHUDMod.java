package fz.frazionz.mods.armor_hud;

import fz.frazionz.mods.mod_hud.ScreenPosition;
import fz.frazionz.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ArmorHUDMod extends ModDraggable
{
    public ArmorHUDMod() {
        super("armor_hud");
    }

    @Override
    public int getWidth() {
        return 20;
    }

    @Override
    public int getHeight() {
        if(!mc.player.getHeldItemMainhand().isEmpty())
            return 100;
        return 80;
    }

    @Override
    public void render(ScreenPosition pos) {

        GL11.glPushMatrix();

        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 150).getRGB());

        RenderHelper.enableGUIStandardItemLighting();
        int y = pos.getAbsoluteY();
        for(int i = 3; i >= 0; i--) {
            ItemStack stack = mc.player.inventory.armorInventory.get(i);
            mc.getRenderItem().renderItemAndEffectIntoGUI(stack, pos.getAbsoluteX()+2, y+2);
            mc.getRenderItem().renderItemOverlays(mc.fontRenderer, stack, pos.getAbsoluteX()+2, y+2);
            y += 20;
        }
        if(!mc.player.getHeldItemMainhand().isEmpty()) {
            mc.getRenderItem().renderItemAndEffectIntoGUI(mc.player.getHeldItemMainhand(), pos.getAbsoluteX() + 2, y + 2);
            mc.getRenderItem().renderItemOverlays(mc.fontRenderer, mc.player.getHeldItemMainhand(), pos.getAbsoluteX()+2, y+2);
        }
        RenderHelper.disableStandardItemLighting();

        GL11.glPopMatrix();
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        GL11.glPushMatrix();

        int width = 20;
        int height = 100;
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + width, pos.getAbsoluteY() + height, new Color(0, 0, 0, 150).getRGB());

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.IRON_HELMET), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.IRON_CHESTPLATE), pos.getAbsoluteX()+2, pos.getAbsoluteY()+22);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.IRON_LEGGINGS), pos.getAbsoluteX()+2, pos.getAbsoluteY()+42);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.IRON_BOOTS), pos.getAbsoluteX()+2, pos.getAbsoluteY()+62);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.IRON_SWORD), pos.getAbsoluteX()+2, pos.getAbsoluteY()+82);
        RenderHelper.disableStandardItemLighting();

        GL11.glPopMatrix();
    }
}