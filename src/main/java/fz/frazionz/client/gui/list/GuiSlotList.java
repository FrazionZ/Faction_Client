package fz.frazionz.client.gui.list;

import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import fz.frazionz.forgemods.smoothscrollingeverywhere.RunSixtyTimesEverySec;
import fz.frazionz.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiSlotList
{
    protected final Minecraft mc;
    protected int width;
    protected int height;

    protected int x;
    protected int y;

    int x_right;
    int y_bottom;

    protected FzSlot[] slots;
    protected int contentHeight;

    protected int mouseX;
    protected int mouseY;

    /**
     * What to multiply the amount you moved your mouse by (used for slowing down scrolling when over the items and not
     * on the scroll bar)
     */
    protected float scrollMultiplier;

    /** How far down this slot has been scrolled */
    protected float amountScrolled;

    private int slotsGap;

    protected double scrollVelocity;
    protected RunSixtyTimesEverySec scroller;

    protected int firstIndexToDraw;
    protected int lastIndexToDraw;
    
    public GuiSlotList(Minecraft mcIn, FzSlot[] slots, int x, int y, int width, int height, int slotsGap)
    {
        this.mc = mcIn;
        this.slots = slots;
        this.x = x;
        this.y = y;
        this.x_right = x + width;
        this.y_bottom = y + height;
        this.width = width;
        this.height = height;
        this.slotsGap = slotsGap;
        this.setScroller();

        this.slots = new FzSlot[]{
                new FzSlotExample(1),
                new FzSlotExample(2),
                new FzSlotExample2(3),
                new FzSlotExample2(4),
                new FzSlotExample(5),
                new FzSlotExample(6),
                new FzSlotExample2(7),
                new FzSlotExample2(8),
                new FzSlotExample(9),
                new FzSlotExample(10),
                new FzSlotExample(11),
                new FzSlotExample2(12),
                new FzSlotExample2(13),
                new FzSlotExample(14),
                new FzSlotExample(15)
        };

        contentHeight = calcContentHeight();
        updateSlotsPosition();
        updateSlotsWidth();
    }

    private int calcContentHeight() {
        int contentHeight = 0;
        for(FzSlot slot : slots) {
            contentHeight += slot.getSlotHeight();
        }
        contentHeight += slotsGap * (slots.length - 1);
        return contentHeight;
    }

    private void updateSlotsWidth() {
        for(FzSlot slot : slots) {
            slot.setSlotWidth(width);
        }
    }

    private void updateSlotsPosition() {
        int y = this.y;
        for (FzSlot slot : slots) {
            slot.setSlotX(x);
            slot.setSlotY(y - (int)(amountScrolled));
            y += slot.getSlotHeight() + slotsGap;
        }

        firstIndexToDraw = 0;
        lastIndexToDraw = slots.length - 1;
    }

    public GuiSlotList(Minecraft mc, FzSlot[] slots, int x, int y, int width, int height)
    {
        this(mc, slots, x, y, width, height, 24);
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
            updateSlotsPosition();
        });
    }
    
    private void scrollerUnregisterTick() {
        this.scroller.unregisterTick();
    }

    public void setDimensions(int widthIn, int heightIn)
    {
        this.width = widthIn;
        this.height = heightIn;
    }

    protected int getSize() {
        return this.slots.length;
    }

    /**
     * The element in the slot that was clicked, boolean for whether it was double clicked or not
     */
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {

    }

    /**
     * Returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int slotIndex) {
        return false;
    }

    /**
     * Return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return contentHeight;
    }

    protected void drawBackground() {

    }

    protected void updateItemPos(int entryID, int insideLeft, int yPos, float partialTicks)
    {
    }

    protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {

    }

    protected void renderDecorations(int mouseXIn, int mouseYIn)
    {
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
        return Math.max(0, this.getContentHeight() - height);
    }

    /**
     * Returns the amountScrolled field as an integer.
     */
    public int getAmountScrolled()
    {
        return (int)this.amountScrolled;
    }

    public boolean isMouseYWithinSlotBounds(int y)
    {
        return y >= this.y && y <= this.y_bottom && this.mouseX >= this.x && this.mouseX <= this.x_right;
    }

    /**
     * Scrolls the slot by the given amount. A positive value scrolls down, and a negative value scrolls up.
     */
    public void scrollBy(int amount)
    {
        this.amountScrolled += (float)amount;
        this.bindAmountScrolled();
    }

    public void actionPerformed(GuiButton button, int keyCode)
    {
        /*if (button.enabled)
        {
            if (button.id == this.scrollUpButtonID)
            {
                this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
                this.bindAmountScrolled();
            }
            else if (button.id == this.scrollDownButtonID)
            {
                this.amountScrolled += (float)(this.slotHeight * 2 / 3);
                this.bindAmountScrolled();
            }
        }*/
    }

    protected void drawScrollBar() {
        if(contentHeight > height) {
            int scrollBarX = x_right + 24;
            int scrollBarWidth = 14;

            int scrollThumbHeight = (int) (height * ((float)height / (float)contentHeight));

            RoundedShaderRenderer.getInstance().drawRoundRect(scrollBarX, y, scrollBarWidth, height, 7, Gui.BLACK_2);

            int yPos = (int) ((height - scrollThumbHeight) * (amountScrolled / getMaxScroll()));
            RoundedShaderRenderer.getInstance().drawRoundRect(scrollBarX, y + yPos , scrollBarWidth, scrollThumbHeight, 7, Gui.COLOR_2);
        }
    }

    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks)
    {
        this.mouseX = mouseXIn;
        this.mouseY = mouseYIn;
        this.drawBackground();
        this.drawScrollBar();

        for(int i = 0; i < this.slots.length; ++i) {
            if(i >= firstIndexToDraw && i <= lastIndexToDraw
                slots[i].drawSlot(mouseXIn, mouseYIn, partialTicks);
        }
    }

    public void handleMouseInput()
    {
        if (Mouse.isButtonDown(0)) {
            bindAmountScrolled();
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

        /*if (this.isMouseYWithinSlotBounds(this.mouseY)) {
            if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.mouseY >= this.y && this.mouseY <= this.y_bottom) {

            }


            if (Mouse.isButtonDown(0)) {

            } else {

            }

            int i2 = Mouse.getEventDWheel();

            if (i2 != 0) {
                if (i2 > 0) {
                    i2 = -1;
                } else if (i2 < 0) {
                    i2 = 1;
                }

                //this.amountScrolled += (float)(i2 * this.slotHeight / 2);
            }
        }*/
    }

    /**
     * Gets the width of the list
     */
    public int getWidth()
    {
        return width;
    }
}
