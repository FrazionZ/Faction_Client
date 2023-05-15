package fz.frazionz.mods.mod_hud;

public interface IRenderer extends IRenderConfig {

	int getWidth();
	int getHeight();

	int getDummyWidth();
	int getDummyHeight();
	
	void render(ScreenPosition pos);
	
	default void renderDummy(ScreenPosition pos) {
		
		render(pos);
		
	}
	
	
	
	public default boolean isEnabled() {
		return true;
	}
	
}
