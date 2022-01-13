package fz.frazionz.mods.sneak;

public interface SmoothSneakRunnable extends Runnable
{
    default boolean isRegistered() {
        return SmoothSneakImpl.TICKS_LIST.contains(this);
    }
    
    default void registerTick() {
    	SmoothSneakImpl.TICKS_LIST.removeIf(runSixtyTimesEverySec -> runSixtyTimesEverySec == this);
    	SmoothSneakImpl.TICKS_LIST.add(this);
    }
    
    default void unregisterTick() {
    	SmoothSneakImpl.TICKS_LIST.remove(this);
    }
}