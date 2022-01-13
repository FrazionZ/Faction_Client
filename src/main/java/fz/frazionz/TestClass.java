package fz.frazionz;

import fz.frazionz.event.EventTarget;
import fz.frazionz.event.impl.ClientTickEvent;

public class TestClass {

	@EventTarget
	public void onTick(ClientTickEvent e) {
		
		System.out.println("Client tick event is being allow !");
		
	}
	
}
