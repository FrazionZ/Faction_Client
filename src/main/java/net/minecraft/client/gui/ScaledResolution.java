package net.minecraft.client.gui;

import java.util.Arrays;
import java.util.List;

import fz.frazionz.gui.GuiClassement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.util.math.MathHelper;
import optifine.GuiAnimationSettingsOF;
import optifine.GuiDetailSettingsOF;
import optifine.GuiModsConfig;
import optifine.GuiOtherSettingsOF;
import optifine.GuiPerformanceSettingsOF;
import optifine.GuiQualitySettingsOF;

public class ScaledResolution
{
    private final double scaledWidthD;
    private final double scaledHeightD;
    private int scaledWidth;
    private int scaledHeight;
    private int scaleFactor;
    
    private List<Class<?>> screens = Arrays.<Class<?>>asList(
    		GuiOptions.class,
    		GuiScreenOptionsSounds.class,
    		GuiCustomizeSkin.class,
    		GuiVideoSettings.class,
    		GuiControls.class,
    		GuiLanguage.class,
    		ScreenChatOptions.class,
    		GuiSnooper.class,
    		GuiScreenResourcePacks.class,
    		GuiMacro.class,
    		GuiDetailSettingsOF.class,
    		GuiQualitySettingsOF.class,
    		GuiAnimationSettingsOF.class,
    		GuiPerformanceSettingsOF.class,
    		GuiOtherSettingsOF.class,
    		GuiScreenAdvancements.class,
    		GuiStats.class,
    		GuiModsConfig.class,
    		GuiClassement.class
    		);

    public ScaledResolution(Minecraft minecraftClient)
    {
        this.scaledWidth = minecraftClient.displayWidth;
        this.scaledHeight = minecraftClient.displayHeight;
        this.scaleFactor = 1;
        boolean flag = minecraftClient.isUnicode();
        int i = minecraftClient.gameSettings.guiScale;

        if (i == 0)
        {
            i = 1000;
        }
        
        if(minecraftClient.currentScreen != null && minecraftClient.gameSettings.frazionz_ui) {
            for(Class classe : this.screens) {
            	if(minecraftClient.currentScreen.getClass() == classe) {
            		i = 2;
            	}
            }
        }
        
        if(minecraftClient.currentScreen instanceof GuiMainMenu || minecraftClient.currentScreen instanceof GuiIngameMenu)
        	i = 2;

        while (this.scaleFactor < i && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240)
        {
            ++this.scaleFactor;
        }

        if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1)
        {
            --this.scaleFactor;
        }

        this.scaledWidthD = (double)this.scaledWidth / (double)this.scaleFactor;
        this.scaledHeightD = (double)this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(this.scaledWidthD);
        this.scaledHeight = MathHelper.ceil(this.scaledHeightD);
    }

    public int getScaledWidth()
    {
        return this.scaledWidth;
    }

    public int getScaledHeight()
    {
        return this.scaledHeight;
    }

    public double getScaledWidth_double()
    {
        return this.scaledWidthD;
    }

    public double getScaledHeight_double()
    {
        return this.scaledHeightD;
    }

    public int getScaleFactor()
    {
        return this.scaleFactor;
    }
}
