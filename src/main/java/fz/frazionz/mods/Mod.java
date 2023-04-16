package fz.frazionz.mods;

import fz.frazionz.event.EventManager;

public class Mod {

	protected String name;
	protected boolean isEnabled;
	
	public Mod(String name) {
		this.name = name;
		this.isEnabled = false;
	}

	public void setEnabled(boolean isEnabled) {
		
		this.isEnabled = isEnabled;
		
		/*if(isEnabled)
		{			
			EventManager.register(this);		
		}
		else
		{
			EventManager.unregister(this);
		}*/
		
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public String getName() {
		return name;
	}
}
