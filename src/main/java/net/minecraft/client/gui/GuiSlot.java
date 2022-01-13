package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fz.frazionz.forgemods.smoothscrollingeverywhere.RunSixtyTimesEverySec;

public abstract class GuiSlot
{
    protected final Minecraft mc;
    protected int width;
    protected int height;

    /** The top of the slot container. Affects the overlays and scrolling. */
    protected int top;

    /** The bottom of the slot container. Affects the overlays and scrolling. */
    protected int bottom;
    protected int right;
    protected int left;

    /** The height of a slot. */
    protected final int slotHeight;

    /** The buttonID of the button used to scroll up */
    private int scrollUpButtonID;

    /** The buttonID of the button used to scroll down */
    private int scrollDownButtonID;
    protected int mouseX;
    protected int mouseY;
    protected boolean centerListVertically = true;

    /** Where the mouse was in the window when you first clicked to scroll */
    protected int initialClickY = -2;

    /**
     * What to multiply the amount you moved your mouse by (used for slowing down scrolling when over the items and not
     * on the scroll bar)
     */
    protected float scrollMultiplier;

    /** How far down this slot has been scrolled */
    protected float amountScrolled;

    /** The element in the list that was selected */
    protected int selectedElement = -1;

    /** The time when this button was last clicked. */
    protected long lastClicked;
    protected boolean visible = true;

    /**
     * Set to true if a selected element in this gui will show an outline box
     */
    protected boolean showSelectionBox = true;
    protected boolean hasListHeader;
    protected int headerPadding;
    private boolean enabled = true;
    private boolean second;
    private int spaceBetween = 0;

    protected double scrollVelocity;
    protected RunSixtyTimesEverySec scroller;
    
    public GuiSlot(Minecraft mcIn, int width, int height, int topIn, int bottomIn, int slotHeightIn)
    {
    	this(mcIn, 0, width, topIn, bottomIn, slotHeightIn, false);
    }
    
    public GuiSlot(Minecraft mcIn, int left, int right, int topIn, int bottomIn, int slotHeightIn, boolean second)
    {
        this.mc = mcIn;
        this.width = right - left;
        this.height = bottomIn - topIn;
        this.top = topIn;
        this.bottom = bottomIn;
        this.slotHeight = slotHeightIn;
        this.left = left;
        this.right = right;
        this.second = second;
        this.setScroller();
    }
    
    public GuiSlot(Minecraft mcIn, int left, int right, int topIn, int bottomIn, int slotHeightIn, boolean second, int spaceBetween)
    {
        this.mc = mcIn;
        this.width = right - left;
        this.height = bottomIn - topIn;
        this.top = topIn;
        this.bottom = bottomIn;
        this.slotHeight = slotHeightIn;
        this.left = left;
        this.right = right;
        this.second = second;
        this.spaceBetween = spaceBetween;
        this.setScroller();
    }
    
    public void setScroller() {
        this.scroller = (() -> {
            final double change;
            if (this.scrollVelocity == 0.0 && this.amountScrolled >= 0.0 && this.amountScrolled <= this.getMaxScroll()) {
                this.scrollerUnregisterTick();
            }
            else {
                change = this.scrollVelocity * 0.65;
                if (this.scrollVelocity != 0.0) {
                    this.amountScrolled += (float)change;
                    this.scrollVelocity -= this.scrollVelocity * ((this.amountScrolled >= 0.0 && this.amountScrolled <= this.getMaxScroll()) ? 0.2 : 0.4);
                    if (Math.abs(this.scrollVelocity) < 0.1) {
                        this.scrollVelocity = 0.0;
                    }
                }
                if (this.amountScrolled < 0.0f && this.scrollVelocity == 0.0) {
                    this.amountScrolled = Math.min(this.amountScrolled + (0.0f - this.amountScrolled) * 0.2f, 0.0f);
                    if (this.amountScrolled > -0.1f && this.amountScrolled < 0.0f) {
                        this.amountScrolled = 0.0f;
                    }
                }
                else if (this.amountScrolled > this.getMaxScroll() && this.scrollVelocity == 0.0) {
                    this.amountScrolled = Math.max(this.amountScrolled - (this.amountScrolled - this.getMaxScroll()) * 0.2f, this.getMaxScroll());
                    if (this.amountScrolled > this.getMaxScroll() && this.amountScrolled < this.getMaxScroll() + 0.1) {
                        this.amountScrolled = this.getMaxScroll();
                    }
                }
            }
        });
    }
    
    private void scrollerUnregisterTick() {
        this.scroller.unregisterTick();
    }

    public void setDimensions(int widthIn, int heightIn, int topIn, int bottomIn)
    {
        this.width = widthIn;
        this.height = heightIn;
        this.top = topIn;
        this.bottom = bottomIn;
        this.left = 0;
        this.right = widthIn;
    }

    public void func_193651_b(boolean p_193651_1_)
    {
        this.showSelectionBox = p_193651_1_;
    }

    /**
     * Sets hasListHeader and headerHeight. Params: hasListHeader, headerHeight. If hasListHeader is false headerHeight
     * is set to 0.
     */
    protected void setHasListHeader(boolean hasListHeaderIn, int headerPaddingIn)
    {
        this.hasListHeader = hasListHeaderIn;
        this.headerPadding = headerPaddingIn;

        if (!hasListHeaderIn)
        {
            this.headerPadding = 0;
        }
    }

    protected abstract int getSize();

    /**
     * The element in the slot that was clicked, boolean for whether it was double clicked or not
     */
    protected abstract void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY);

    /**
     * Returns true if the element passed in is currently selected
     */
    protected abstract boolean isSelected(int slotIndex);

    /**
     * Return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return this.getSize() * this.slotHeight + this.headerPadding;
    }

    protected abstract void drawBackground();

    protected void func_192639_a(int p_192639_1_, int p_192639_2_, int p_192639_3_, float p_192639_4_)
    {
    }

    protected abstract void func_192637_a(int p_192637_1_, int p_192637_2_, int p_192637_3_, int p_192637_4_, int p_192637_5_, int p_192637_6_, float p_192637_7_);

    /**
     * Handles drawing a list's header row.
     */
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn)
    {
    }

    protected void clickedHeader(int p_148132_1_, int p_148132_2_)
    {
    }

    protected void renderDecorations(int mouseXIn, int mouseYIn)
    {
    }

    public int getSlotIndexFromScreenCoords(int posX, int posY)
    {
        int i = this.left + this.width / 2 - this.getListWidth() / 2;
        int j = this.left + this.width / 2 + this.getListWidth() / 2;
        int k = posY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
        int l = k / this.slotHeight;
        return posX < this.getScrollBarX() && posX >= i && posX <= j && l >= 0 && k >= 0 && l < this.getSize() ? l : -1;
    }

    /**
     * Registers the IDs that can be used for the scrollbar's up/down buttons.
     */
    public void registerScrollButtons(int scrollUpButtonIDIn, int scrollDownButtonIDIn)
    {
        this.scrollUpButtonID = scrollUpButtonIDIn;
        this.scrollDownButtonID = scrollDownButtonIDIn;
    }

    /**
     * Stop the thing from scrolling out of bounds
     */
    protected void bindAmountScrolled()
    {
        this.amountScrolled = MathHelper.clamp(this.amountScrolled, 0.0F, (float)this.getMaxScroll());
    }

    public int getMaxScroll()
    {
        return Math.max(0, this.getContentHeight() - (this.bottom - this.top - 4));
    }

    /**
     * Returns the amountScrolled field as an integer.
     */
    public int getAmountScrolled()
    {
        return (int)this.amountScrolled;
    }

    public boolean isMouseYWithinSlotBounds(int p_148141_1_)
    {
        return p_148141_1_ >= this.top && p_148141_1_ <= this.bottom && this.mouseX >= this.left && this.mouseX <= this.right;
    }

    /**
     * Scrolls the slot by the given amount. A positive value scrolls down, and a negative value scrolls up.
     */
    public void scrollBy(int amount)
    {
        this.amountScrolled += (float)amount;
        this.bindAmountScrolled();
        this.initialClickY = -2;
    }

    public void actionPerformed(GuiButton button, int mouseButton)
    {
        if (button.enabled)
        {
            if (button.id == this.scrollUpButtonID)
            {
                this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
                this.initialClickY = -2;
                this.bindAmountScrolled();
            }
            else if (button.id == this.scrollDownButtonID)
            {
                this.amountScrolled += (float)(this.slotHeight * 2 / 3);
                this.initialClickY = -2;
                this.bindAmountScrolled();
            }
        }
    }

    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks)
    {

        if (this.visible)
        {
        	int height = (int)((this.height / 14) * 1.5);
        	int ok = this.width/26;
        	int y = this.height / 22;
        	
            this.mouseX = mouseXIn;
            this.mouseY = mouseYIn;
            this.drawBackground();
            int i = this.getScrollBarX();
            int j = i + 6;
            this.bindAmountScrolled();
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            this.drawContainerBackground(tessellator);
            int k = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int l = this.top + 4 - (int)this.amountScrolled;

            if (this.hasListHeader)
            {
                this.drawListHeader(k, l, tessellator);
            }

            this.func_192638_a(k, l, mouseXIn, mouseYIn, partialTicks);
            GlStateManager.disableDepth();
            this.overlayBackground(0, this.top, 255, 255);
            this.overlayBackground(this.bottom, this.height, 255, 255);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);
            GlStateManager.disableTexture2D();
            int i1 = 4;
            
            // A SUPPRIMER ? //
            
            /*bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            bufferbuilder.pos((double)this.left, (double)(this.top + 4), 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos((double)this.right, (double)(this.top + 4), 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos((double)this.right, (double)this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos((double)this.left, (double)this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            bufferbuilder.pos((double)this.left, (double)this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos((double)this.right, (double)this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos((double)this.right, (double)(this.bottom - 4), 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos((double)this.left, (double)(this.bottom - 4), 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 0).endVertex();
            tessellator.draw();*/
            
            int j1 = this.getMaxScroll();

            if (j1 > 0)
            {
                int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                k1 = MathHelper.clamp(k1, 32, this.bottom - this.top - 8);
                int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;

                if (l1 < this.top)
                {
                    l1 = this.top;
                }
                
                if(this.mc.gameSettings.frazionz_ui) {
                	if(this.second) {
                        i = this.right - 30;
                        j = i + 6;
                    	this.drawRoundedSliderButton(i, this.top, j, this.bottom, 0xFF101010);
                    	this.drawRoundedSliderButton(i, l1, j, (l1 + k1), 0xFFFEA801);
                	}
                	else {
                    	this.drawRoundedSliderButton(i, this.top, j, this.bottom, 0xFF101010);
                    	this.drawRoundedSliderButton(i, l1, j, (l1 + k1), 0xFFFEA801);
                	}
                }
                else { 	
	                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
	                bufferbuilder.pos((double)i, (double)this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
	                bufferbuilder.pos((double)j, (double)this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
	                bufferbuilder.pos((double)j, (double)this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
	                bufferbuilder.pos((double)i, (double)this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
	                tessellator.draw();
	                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
	                bufferbuilder.pos((double)i, (double)(l1 + k1), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
	                bufferbuilder.pos((double)j, (double)(l1 + k1), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
	                bufferbuilder.pos((double)j, (double)l1, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
	                bufferbuilder.pos((double)i, (double)l1, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
	                tessellator.draw();
	                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
	                bufferbuilder.pos((double)i, (double)(l1 + k1 - 1), 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 255).endVertex();
	                bufferbuilder.pos((double)(j - 1), (double)(l1 + k1 - 1), 0.0D).tex(1.0D, 1.0D).color(192, 192, 192, 255).endVertex();
	                bufferbuilder.pos((double)(j - 1), (double)l1, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 255).endVertex();
	                bufferbuilder.pos((double)i, (double)l1, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 255).endVertex();
	                tessellator.draw();
                }

            }

            //this.renderDecorations(mouseXIn, mouseYIn);
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }
    
    public static void drawRoundedSliderButton(int left, int top, int right, int bottom, int color)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	int radiusF = right - left;
    	if(radiusF%2==1) {
    		radiusF+=1;
    		right+=1;
    	}
    	radiusF /= 2;
    	
    	int radius = (int) radiusF;
    	
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top+radius, (double)0.0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top+radius, (double)0.0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom-radius, (double)0.0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom-radius, (double)0.0).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public void handleMouseInput()
    {	
        if (Mouse.isButtonDown(0) && this.getEnabled()) {
            this.bindAmountScrolled();
        }
        else {
            int i2 = Mouse.getEventDWheel();
            if (i2 != 0) {
                if (i2 > 0) {
                    i2 = 1;
                }
                else if (i2 < 0) {
                    i2 = -1;
                }
                if (this.amountScrolled <= this.getMaxScroll() && i2 < 0.0) {
                    this.scrollVelocity += 16.0;
                }
                if (this.amountScrolled >= 0.0 && i2 > 0.0) {
                    this.scrollVelocity -= 16.0;
                }
                if (!this.scroller.isRegistered()) {
                    this.scroller.registerTick();
                }
            }
        }
    	
        if (this.isMouseYWithinSlotBounds(this.mouseY))
        {

            if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.mouseY >= this.top && this.mouseY <= this.bottom)
            {
                /*int i = (this.width - this.getListWidth()) / 2;
                int j = (this.width + this.getListWidth()) / 2;*/
            	int i = this.left + (this.width - this.getListWidth()) / 2;
            	int j = this.right - (this.width - this.getListWidth()) / 2;
            	
                //int k = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
            	int k = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled;
                int l = k / this.slotHeight;
                if (l < this.getSize() && this.mouseX >= i && this.mouseX <= j && l >= 0 && k >= 0)
                {
                    this.elementClicked(l, false, this.mouseX, this.mouseY);
                    this.selectedElement = l;
                }
                else if (this.mouseX >= i && this.mouseX <= j && k < 0)
                {
                    this.clickedHeader(this.mouseX - i, this.mouseY - this.top + (int)this.amountScrolled - 4);
                }
            }

            if (Mouse.isButtonDown(0) && this.getEnabled())
            {
                if (this.initialClickY != -1)
                {
                    if (this.initialClickY >= 0)
                    {
                        this.amountScrolled -= (float)(this.mouseY - this.initialClickY) * this.scrollMultiplier;
                        this.initialClickY = this.mouseY;
                    }
                }
                else
                {
                    boolean flag1 = true;

                    if (this.mouseY >= this.top && this.mouseY <= this.bottom)
                    {
                    	int j2 = this.left + (this.width - this.getListWidth())/2;
                    	int k2 = this.right - (this.width - this.getListWidth())/2;
                        /*int j2 = (this.width - this.getListWidth()) / 2;
                        int k2 = (this.width + this.getListWidth()) / 2; */
                        //int l2 = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
                        int l2 = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled;
                        int i1 = l2 / this.slotHeight;
                        
                        if (i1 < this.getSize() && this.mouseX >= j2 && this.mouseX <= k2 && i1 >= 0 && l2 >= 0)
                        {
                            boolean flag = i1 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                            this.elementClicked(i1, flag, this.mouseX, this.mouseY);
                            this.selectedElement = i1;
                            this.lastClicked = Minecraft.getSystemTime();
                        }
                        else if (this.mouseX >= j2 && this.mouseX <= k2 && l2 < 0)
                        {
                            this.clickedHeader(this.mouseX - j2, this.mouseY - this.top + (int)this.amountScrolled - 4);
                            flag1 = false;
                        }

                        
                        /*if (i1 < this.getSize() && this.mouseX >= j2 && this.mouseX <= k2 && i1 >= 0 && l2 >= 0)
                        {
                            boolean flag = i1 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                            this.elementClicked(i1, flag, this.mouseX, this.mouseY);
                            this.selectedElement = i1;
                            this.lastClicked = Minecraft.getSystemTime();
                        }
                        else if (this.mouseX >= j2 && this.mouseX <= k2 && l2 < 0)
                        {
                            this.clickedHeader(this.mouseX - j2, this.mouseY - this.top + (int)this.amountScrolled - 4);
                            flag1 = false;
                        }*/

                        int i3 = this.getScrollBarX();
                        int j1 = i3 + 6;

                        /*if(this.mc.gameSettings.frazionz_ui) {
                        	if(this.second) {
                                i3 = this.right - 30;
                                j1 = i3 + 6;
                        	}
                        }*/
                        
                        if (this.mouseX >= i3 && this.mouseX <= j1)
                        {
                            this.scrollMultiplier = -1.0F;
                            int k1 = this.getMaxScroll();

                            if (k1 < 1)
                            {
                                k1 = 1;
                            }

                            int l1 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
                            l1 = MathHelper.clamp(l1, 32, this.bottom - this.top - 8);
                            this.scrollMultiplier /= (float)(this.bottom - this.top - l1) / (float)k1;
                        }
                        else
                        {
                            this.scrollMultiplier = 1.0F;
                        }

                        if (flag1)
                        {
                            this.initialClickY = this.mouseY;
                        }
                        else
                        {
                            this.initialClickY = -1;
                        }
                    }
                    else
                    {
                        this.initialClickY = -1;
                    }
                }
            }
            else
            {
                this.initialClickY = -1;
            }

            int i2 = Mouse.getEventDWheel();

            
            if (i2 != 0)
            {
                if (i2 > 0)
                {
                    i2 = -1;
                }
                else if (i2 < 0)
                {
                    i2 = 1;
                }
                
            }
        }
    }

    public void setEnabled(boolean enabledIn)
    {
        this.enabled = enabledIn;
    }

    public boolean getEnabled()
    {
        return this.enabled;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
    	/*if(this.mc.gameSettings.frazionz_ui && this.second) {
    		return this.width;
    	}*/
        return 220;
    }

    protected void func_192638_a(int p_192638_1_, int p_192638_2_, int p_192638_3_, int p_192638_4_, float p_192638_5_)
    {
        int i = this.getSize();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        for (int j = 0; j < i; ++j)
        {
            int k = p_192638_2_ + j * this.slotHeight + this.headerPadding;
            int l = this.slotHeight;

            if (k > this.bottom || k + l < this.top)
            {
                this.func_192639_a(j, p_192638_1_, k, p_192638_5_);
            }

            if (k >= this.top - this.slotHeight && k <= this.bottom)
            {
                if (this.showSelectionBox && this.isSelected(j))
                {
                    int i1 = this.left + (this.width / 2 - this.getListWidth() / 2);
                    int j1 = this.left + this.width / 2 + this.getListWidth() / 2;
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    GlStateManager.disableTexture2D();
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                    bufferbuilder.pos((double)i1, (double)(k + l + 2), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                    bufferbuilder.pos((double)j1, (double)(k + l + 2), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                    bufferbuilder.pos((double)j1, (double)(k - 2), 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                    bufferbuilder.pos((double)i1, (double)(k - 2), 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                    bufferbuilder.pos((double)(i1 + 1), (double)(k + l + 1), 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                    bufferbuilder.pos((double)(j1 - 1), (double)(k + l + 1), 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                    bufferbuilder.pos((double)(j1 - 1), (double)(k - 1), 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                    bufferbuilder.pos((double)(i1 + 1), (double)(k - 1), 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                }
            	
                this.func_192637_a(j, p_192638_1_, k, l, p_192638_3_, p_192638_4_, p_192638_5_);
            }
        }
    }

    protected int getScrollBarX()
    {
    	if(this.mc.gameSettings.frazionz_ui && this.second) {
    		return this.right - 30;
    	}
        return this.width / 2 + 124;
    }

    /**
     * Overlays the background to hide scrolled items
     */
    protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha)
    {
        /*Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        this.mc.getTextureManager().bindTexture(Gui.FZ_OPTIONS_BACKGROUND);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double)this.left, (double)endY, 0.0D).tex(0.0D, (double)((float)endY / 32.0F)).color(64, 64, 64, endAlpha).endVertex();
        bufferbuilder.pos((double)(this.left + this.width), (double)endY, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)endY / 32.0F)).color(64, 64, 64, endAlpha).endVertex();
        bufferbuilder.pos((double)(this.left + this.width), (double)startY, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)startY / 32.0F)).color(64, 64, 64, startAlpha).endVertex();
        bufferbuilder.pos((double)this.left, (double)startY, 0.0D).tex(0.0D, (double)((float)startY / 32.0F)).color(64, 64, 64, startAlpha).endVertex();
        tessellator.draw();*/
    }

    /**
     * Sets the left and right bounds of the slot. Param is the left bound, right is calculated as left + width.
     */
    public void setSlotXBoundsFromLeft(int leftIn)
    {
        this.left = leftIn;
        this.right = leftIn + this.width;
    }

    public int getSlotHeight()
    {
        return this.slotHeight;
    }

    protected void drawContainerBackground(Tessellator p_drawContainerBackground_1_)
    {
        /*BufferBuilder bufferbuilder = p_drawContainerBackground_1_.getBuffer();
        float f = 32.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double)this.left, (double)this.bottom, 0.0D).tex((double)((float)this.left / 32.0F), (double)((float)(this.bottom + (int)this.amountScrolled) / 32.0F)).color(32, 32, 32, 255).endVertex();
        bufferbuilder.pos((double)this.right, (double)this.bottom, 0.0D).tex((double)((float)this.right / 32.0F), (double)((float)(this.bottom + (int)this.amountScrolled) / 32.0F)).color(32, 32, 32, 255).endVertex();
        bufferbuilder.pos((double)this.right, (double)this.top, 0.0D).tex((double)((float)this.right / 32.0F), (double)((float)(this.top + (int)this.amountScrolled) / 32.0F)).color(32, 32, 32, 255).endVertex();
        bufferbuilder.pos((double)this.left, (double)this.top, 0.0D).tex((double)((float)this.left / 32.0F), (double)((float)(this.top + (int)this.amountScrolled) / 32.0F)).color(32, 32, 32, 255).endVertex();
        p_drawContainerBackground_1_.draw();*/
    }
}
