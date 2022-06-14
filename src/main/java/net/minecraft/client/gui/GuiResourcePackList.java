package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.util.text.TextFormatting;

public abstract class GuiResourcePackList extends GuiListExtended
{
    protected final Minecraft mc;
    protected final List<ResourcePackListEntry> resourcePackEntries;

    public GuiResourcePackList(Minecraft mcIn, int left, int right, List<ResourcePackListEntry> p_i45055_4_)
    {
        super(mcIn, left, right, 32, 200 - 55 + 4, 36);
        this.mc = mcIn;
        this.resourcePackEntries = p_i45055_4_;
        this.centerListVertically = false;
        this.setHasListHeader(true, (int)((float)mcIn.fontRenderer.FONT_HEIGHT * 1.5F));
    }
    
    public GuiResourcePackList(Minecraft mcIn, int left, int right, int topIn, int bottomIn, int slotHeightIn, List<ResourcePackListEntry> packEntries)
    {
        super(mcIn, left, right, topIn, bottomIn, slotHeightIn);
        this.mc = mcIn;
        this.resourcePackEntries = packEntries;
        this.centerListVertically = false;
        this.setHasListHeader(true, (int)((float)mcIn.fontRenderer.FONT_HEIGHT * 1.5F));
    }

    /**
     * Handles drawing a list's header row.
     */
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn)
    {
    	if (insideTop >= this.top - this.slotHeight && insideTop <= this.bottom) {
            String s = TextFormatting.UNDERLINE + "" + TextFormatting.BOLD + this.getListHeader();
            this.mc.fontRenderer.drawString(s, insideLeft + this.width / 2 - this.mc.fontRenderer.getStringWidth(s) / 2, Math.min(this.top + 3, insideTop), 16777215, true);
    	} 
    }

    protected abstract String getListHeader();

    public List<ResourcePackListEntry> getList()
    {
        return this.resourcePackEntries;
    }

    protected int getSize()
    {
        return this.getList().size();
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public ResourcePackListEntry getListEntry(int index)
    {
        return (ResourcePackListEntry)this.getList().get(index);
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return this.width;
    }

    protected int getScrollBarX()
    {
        return this.right - 6;
    }
}
