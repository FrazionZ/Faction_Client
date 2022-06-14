package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;

public class GuiResourcePackAvailable extends GuiResourcePackList
{
    public GuiResourcePackAvailable(Minecraft mcIn, int width, int height, List<ResourcePackListEntry> p_i45054_4_)
    {
        super(mcIn, width, height, p_i45054_4_);
    }
    
    public GuiResourcePackAvailable(Minecraft mcIn, int left, int right, int topIn, int bottomIn, int slotHeightIn, List<ResourcePackListEntry> packEntries)
    {
    	super(mcIn, left, right, topIn, bottomIn, slotHeightIn, packEntries);
    }

    protected String getListHeader()
    {
        return I18n.format("resourcePack.available.title");
    }
}
