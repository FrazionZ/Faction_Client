package fz.frazionz.mods;

import org.apache.commons.lang3.StringUtils;

import fz.frazionz.gui.GuiCheckBox;
import fz.frazionz.gui.GuiModToggle;
import fz.frazionz.mods.impl.ModKeystrokes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.settings.GameSettings;

public class ModEntry implements GuiListExtended.IGuiListEntry, Comparable<ModEntry> {

	private final GuiCheckBox checkbox;
	private final String name;
	private final Mod mod;
	private final GuiModToggle gui;
	
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	private GameSettings settings = this.mc.gameSettings;
	
	public ModEntry(GuiModToggle inGui, Mod mod) {
		this.mod = mod;
		name = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(mod.getClass().getSimpleName().replace("Mod", "").replaceAll("\\d+", "") + " Mod"), " ");
		
		if(mod instanceof ModKeystrokes) {
			checkbox = new GuiCheckBox(0, 0, 0, this.mc.gameSettings.keystrokesMod);
		}
		else {
			checkbox = new GuiCheckBox(0, 0, 0, mod.isEnabled());
		}
		
		this.gui = inGui;
	}

	@Override
	public boolean mousePressed(int slotIndex, int x, int y, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
		return this.checkbox.mousePressed(Minecraft.getMinecraft(), x, y);
	}

	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
		this.checkbox.mouseReleased(x, y);
	}

	@Override
	public int compareTo(ModEntry o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_) {
		
	}

	@Override
	public void func_192634_a(int slotIndex, int x, int y, int p_192634_4_, int p_192634_5_, int mouseX, int mouseY, boolean isSelected, float p_192634_9_)	
	{
		
		this.checkbox.xPosition = x + 100;
		this.checkbox.yPosition = y;
		this.checkbox.func_191745_a(Minecraft.getMinecraft(), mouseX, mouseY, mouseY);
		this.mod.setEnabled(this.checkbox.isChecked());
    	this.mc.gameSettings.saveOptions();
        this.mc.gameSettings.keystrokesMod = this.checkbox.isChecked();
		gui.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, name, x, y + 4, -1);
		
	}

}