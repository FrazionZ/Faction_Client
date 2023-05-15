package fz.frazionz.client.gui;

import java.io.IOException;

import fz.frazionz.client.gui.hud.HUDConfigScreen;
import fz.frazionz.mods.mod_hud.HUDManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiModToggle extends GuiScreen {

	private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {};

	private static HUDManager hudManager = new HUDManager();
	
	private GuiScreen prevScreen;
	
	public GuiModToggle(GuiScreen p_i51_1_) {
		this.prevScreen = p_i51_1_;
	}
	
	@Override
	public void initGui() {
		
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(10, this.width / 2 - 100, (this.height / 2 )- 60, I18n.format("Keystrokes Mods")));
		
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 60, I18n.format("Déplacer Mods")));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height - 30, I18n.format("gui.done")));
		
	}
	
	
    protected void actionPerformed(GuiButton button, int mouseButton)
    {
        if (button.enabled)
        {
            
            if (button.id == 1)
            {
                this.mc.gameSettings.saveOptions();
                hudManager = HUDManager.getInstance();
            	this.mc.displayGuiScreen(new HUDConfigScreen(hudManager));
            }
            
            if (button.id == 2)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.refreshFontRenderer();
                this.mc.displayGuiScreen(this.prevScreen);
            }
            
            if (button.id == 10)
            {
                this.mc.gameSettings.saveOptions();
                HUDManager.rerun();
            }
        }
    }
    

	

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Mod Options", this.width / 2, 8, 16777215);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}