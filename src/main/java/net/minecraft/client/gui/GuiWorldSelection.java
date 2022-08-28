package net.minecraft.client.gui;

import java.io.IOException;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.gui.buttons.GuiFzButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.resources.ResourceLocation;

public class GuiWorldSelection extends GuiScreen
{
    private static final Logger LOGGER = LogManager.getLogger();

    /** The screen to return to when this closes (always Main Menu). */
    protected GuiScreen prevScreen;
    protected String title = "Select world";

    /**
     * Tooltip displayed a world whose version is different from this client's
     */
    private String worldVersTooltip;
    private GuiButton deleteButton;
    private GuiButton selectButton;
    private GuiButton renameButton;
    private GuiButton copyButton;
    private GuiListWorldSelection selectionList;
    private static final ResourceLocation BACKGROUND_TEXTURE  = new ResourceLocation("textures/gui/title/background/fz_background.png");
    
    public GuiWorldSelection(GuiScreen screenIn)
    {
        this.prevScreen = screenIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.title = I18n.format("selectWorld.title");
        this.selectionList = new GuiListWorldSelection(this, this.mc, 0, this.width, this.height/8, this.height - this.height/8, 36);
        this.postInit();
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.selectionList.handleMouseInput();
    }

    public void postInit()
    {
        this.selectButton = this.addButton(new GuiFzButton(1, this.width / 2 - 154, this.height - 52, 150, 20, I18n.format("selectWorld.select")));
        this.addButton(new GuiFzButton(3, this.width / 2 + 4, this.height - 52, 150, 20, I18n.format("selectWorld.create")));
        this.renameButton = this.addButton(new GuiFzButton(4, this.width / 2 - 154, this.height - 28, 72, 20, I18n.format("selectWorld.edit")));
        this.deleteButton = this.addButton(new GuiFzButton(2, this.width / 2 - 76, this.height - 28, 72, 20, I18n.format("selectWorld.delete")));
        this.copyButton = this.addButton(new GuiFzButton(5, this.width / 2 + 4, this.height - 28, 72, 20, I18n.format("selectWorld.recreate")));
        this.addButton(new GuiFzButton(0, this.width / 2 + 82, this.height - 28, 72, 20, I18n.format("gui.cancel")));
        this.selectButton.enabled = false;
        this.deleteButton.enabled = false;
        this.renameButton.enabled = false;
        this.copyButton.enabled = false;
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
    {
        if (button.enabled)
        {
            GuiListWorldSelectionEntry guilistworldselectionentry = this.selectionList.getSelectedWorld();

            if (button.id == 2)
            {
                if (guilistworldselectionentry != null)
                {
                    guilistworldselectionentry.deleteWorld();
                }
            }
            else if (button.id == 1)
            {
                if (guilistworldselectionentry != null)
                {
                    guilistworldselectionentry.joinWorld();
                }
            }
            else if (button.id == 3)
            {
                this.mc.displayGuiScreen(new GuiCreateWorld(this));
            }
            else if (button.id == 4)
            {
                if (guilistworldselectionentry != null)
                {
                    guilistworldselectionentry.editWorld();
                }
            }
            else if (button.id == 0)
            {
                this.mc.displayGuiScreen(this.prevScreen);
            }
            else if (button.id == 5 && guilistworldselectionentry != null)
            {
                guilistworldselectionentry.recreateWorld();
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	this.drawRect(0, 0, width, height, BLACK_3);
        
        this.worldVersTooltip = null;
        this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
        
        this.drawRect(0, 0, this.width, this.height/8, this.BLACK_4);
	    this.drawRect(0, this.height - this.height/8, this.width, this.height, this.BLACK_4);
	    
        TTFFontRenderer titleRenderer = Client.getInstance().getTTFFontRenderers().get(24);
        int titleSize = titleRenderer.getWidth(this.title);
        titleRenderer.drawCenteredString(this.title, this.width / 2, this.height/16, 0xFFFFFFFF);
        
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.worldVersTooltip != null)
        {
            this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.worldVersTooltip)), mouseX, mouseY);
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Called when a mouse button is released.
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        this.selectionList.mouseReleased(mouseX, mouseY, state);
    }

    /**
     * Called back by selectionList when we call its drawScreen method, from ours.
     */
    public void setVersionTooltip(String p_184861_1_)
    {
        this.worldVersTooltip = p_184861_1_;
    }

    public void selectWorld(@Nullable GuiListWorldSelectionEntry entry)
    {
        boolean flag = entry != null;
        this.selectButton.enabled = flag;
        this.deleteButton.enabled = flag;
        this.renameButton.enabled = flag;
        this.copyButton.enabled = flag;
    }
}
