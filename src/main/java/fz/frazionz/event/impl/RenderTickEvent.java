package fz.frazionz.event.impl;

import fz.frazionz.event.Event;

public class RenderTickEvent extends Event {

	private final Phase phase;
    private final float partialTicks;

    public RenderTickEvent(Phase phase, float partialTicks) {
        this.phase = phase;
        this.partialTicks = partialTicks;
    }

    public Phase getPhase() {
        return phase;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public enum Phase {
        START, END
    };
	
}
