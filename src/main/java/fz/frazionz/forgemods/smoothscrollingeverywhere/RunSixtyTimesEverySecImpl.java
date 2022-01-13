package fz.frazionz.forgemods.smoothscrollingeverywhere;

import java.util.*;
import java.util.function.*;
import com.google.common.collect.*;
import java.util.concurrent.*;

public class RunSixtyTimesEverySecImpl
{
    public static final List<RunSixtyTimesEverySec> TICKS_LIST;
    private static final ScheduledExecutorService EXECUTOR_SERVICE;
    
    static {
        TICKS_LIST = Lists.newCopyOnWriteArrayList();
        (EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor()).scheduleAtFixedRate(() -> {
        	RunSixtyTimesEverySecImpl.TICKS_LIST.removeIf(Objects::isNull);
        	RunSixtyTimesEverySecImpl.TICKS_LIST.iterator().forEachRemaining(Runnable::run);
        }, 0L, 16L, TimeUnit.MILLISECONDS);
    }
}
