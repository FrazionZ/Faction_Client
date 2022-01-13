package fz.frazionz.mods.sneak;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class SmoothSneakImpl {

    public static final List<SmoothSneakRunnable> TICKS_LIST;
    private static final ScheduledExecutorService EXECUTOR_SERVICE;
    
    static {
        TICKS_LIST = Lists.newCopyOnWriteArrayList();
        (EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor()).scheduleAtFixedRate(() -> {
        	SmoothSneakImpl.TICKS_LIST.removeIf(Objects::isNull);
        	SmoothSneakImpl.TICKS_LIST.iterator().forEachRemaining(Runnable::run);
        }, 0L, 6L, TimeUnit.MILLISECONDS);
    }
	
}
