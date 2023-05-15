package fz.frazionz;

import fz.frazionz.event.EventHandler;
import fz.frazionz.event.impl.RenderTickEvent;

public class TestClass {

	@EventHandler
	public void onTick(RenderTickEvent e) {
		
		System.out.println("Client tick event is being allow !");
		
	}
	
}
