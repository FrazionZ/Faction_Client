package fz.frazionz.mods.mod_hud;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import fz.frazionz.client.gui.hud.HUDConfigScreen;
import fz.frazionz.event.EventManager;
import fz.frazionz.event.EventHandler;
import fz.frazionz.event.impl.RenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

public class HUDManager {

	public HUDManager() {}
	
	private static HUDManager instance = null;
	
	public static HUDManager getInstance() {

		if(instance != null) {
			return instance;
		}
		
		instance = new HUDManager();
		EventManager.register(instance);
		
		return instance;

	}
	
	private static Set<IRenderer> registeredRenderers = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void register(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenderers.add(render);
		}
	}
	
	public void unregister(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenderers.remove(render);
		}
	}
	
	public Collection<IRenderer> getRegisteredRenderers() {
		return Sets.newHashSet(registeredRenderers);
	}
	
	public void openConfigScreen() {
		mc.displayGuiScreen(new HUDConfigScreen(this));
	}
	
	@EventHandler
	public void onRender(RenderEvent e) {
		if((!this.mc.gameSettings.hideGUI && mc.currentScreen == null) || mc.currentScreen instanceof GuiChat) {
			
			for(IRenderer renderer : registeredRenderers) {
				callRenderer(renderer);
			}
			
			
		}
	}

	private void callRenderer(IRenderer renderer) {
		if(!renderer.isEnabled()) {
			return;
		}
		
		ScreenPosition pos = renderer.load();
		
		if(pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
	
	public static void clear() {
		
		registeredRenderers.clear();
		
	}
	
	public static void rerun() {
			
		HUDManager.clear();
		HUDManager hudManager;
		hudManager = HUDManager.getInstance();
	}
	
}
