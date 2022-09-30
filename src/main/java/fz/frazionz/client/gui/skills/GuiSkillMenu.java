package fz.frazionz.client.gui.skills;

import java.io.IOException;

import fz.frazionz.client.gui.GuiFrazionZInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiSkillMenu extends GuiFrazionZInterface {

	public GuiSkillMenu(GuiScreen lastScreen, Minecraft mc) {
		super("Skills", lastScreen, mc);
		this.hasBackButton = true;
		this.drawButtonLater = true;
	}
	
	public void initGui()
	{
        super.initGui();
	}

    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
    }
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {	
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);	
	}
    
    @Override
    public void drawTitle() {
    }

}
