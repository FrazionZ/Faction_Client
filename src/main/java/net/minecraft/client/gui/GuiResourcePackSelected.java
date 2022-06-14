package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;

public class GuiResourcePackSelected extends GuiResourcePackList
{
    public GuiResourcePackSelected(Minecraft mcIn, int width, int height, List<ResourcePackListEntry> p_i45056_4_)
    {
        super(mcIn, width, height, p_i45056_4_);
    }
    
    public GuiResourcePackSelected(Minecraft mcIn, int left, int right, int topIn, int bottomIn, int slotHeightIn, List<ResourcePackListEntry> packEntries)
    {
    	super(mcIn, left, right, topIn, bottomIn, slotHeightIn, packEntries);
    }


    protected String getListHeader()
    {
        return I18n.format("resourcePack.selected.title");
    }
}
