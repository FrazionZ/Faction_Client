package fz.frazionz.gui.buttons;

import fz.frazionz.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiFzChoiceButton extends GuiFzButton
{   
	private String info;
	
    public GuiFzChoiceButton(int buttonId, int x, int y, String buttonText, String choiceInfo)
    {
        super(buttonId, x, y, 200, 20, buttonText);
        this.info = choiceInfo;
    }

    public GuiFzChoiceButton(int buttonId, int x, int y, int width, int height, String buttonText, String choiceInfo)
    {
        super(buttonId, x, y, width, height, buttonText);
        this.info = choiceInfo;
    }
    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            
            int i = 27 + (this.hovered ? 60 : 0) ;
            this.drawModalRectWithCustomSizedTexture(x, y, 0, i, 3*(width/5), height/2, 512.0F, 512.0F);
            this.drawModalRectWithCustomSizedTexture(x, y + height/2, 0, i+(30-height/2), 3*(width/5), height/2, 512.0F, 512.0F);

            this.drawModalRectWithCustomSizedTexture(x + 3*(width/5), y, 200 - 2*(width/5), i+30, 2*(width/5), height/2, 512.0F, 512.0F);
            this.drawModalRectWithCustomSizedTexture(x + 3*(width/5), y + height/2, 200 - 2*(width/5), i+30+(30-height/2), 2*(width/5), height/2, 512.0F, 512.0F);
            
            this.mouseDragged(mc, mouseX, mouseY);

            Client.getInstance().getTTFFontRenderers().get(16).drawCenteredString(displayString, x + this.width/2 - (width/5), y + height/2, 0xFFFFFFFF);
            Client.getInstance().getTTFFontRenderers().get(16).drawCenteredString(info, x + 4*(width/5), y + height/2, 0xFFFFFFFF);
        }
    }
    
    public String getInfo() {
		return info;
	}
    
    public void setInfo(String info) {
		this.info = info;
	}
}
