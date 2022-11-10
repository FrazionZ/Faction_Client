package fz.frazionz.client.launchwrapper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogWrapper
{
    public static LogWrapper log = new LogWrapper();
    private Logger myLog;
    private static boolean configured;

    private static void configureLogging()
    {
        log.myLog = LogManager.getLogger("LaunchWrapper");
        configured = true;
    }

    public static void retarget(Logger p_retarget_0_)
    {
        log.myLog = p_retarget_0_;
    }

    public static void log(String p_log_0_, Level p_log_1_, String p_log_2_, Object... p_log_3_)
    {
        makeLog(p_log_0_);
        LogManager.getLogger(p_log_0_).log(p_log_1_, String.format(p_log_2_, p_log_3_));
    }

    public static void log(Level p_log_0_, String p_log_1_, Object... p_log_2_)
    {
        if (!configured)
        {
            configureLogging();
        }

        log.myLog.log(p_log_0_, String.format(p_log_1_, p_log_2_));
    }

    public static void log(String p_log_0_, Level p_log_1_, Throwable p_log_2_, String p_log_3_, Object... p_log_4_)
    {
        makeLog(p_log_0_);
        LogManager.getLogger(p_log_0_).log(p_log_1_, String.format(p_log_3_, p_log_4_), p_log_2_);
    }

    public static void log(Level p_log_0_, Throwable p_log_1_, String p_log_2_, Object... p_log_3_)
    {
        if (!configured)
        {
            configureLogging();
        }

        log.myLog.log(p_log_0_, String.format(p_log_2_, p_log_3_), p_log_1_);
    }

    public static void severe(String p_severe_0_, Object... p_severe_1_)
    {
        log(Level.ERROR, p_severe_0_, p_severe_1_);
    }

    public static void warning(String p_warning_0_, Object... p_warning_1_)
    {
        log(Level.WARN, p_warning_0_, p_warning_1_);
    }

    public static void info(String p_info_0_, Object... p_info_1_)
    {
        log(Level.INFO, p_info_0_, p_info_1_);
    }

    public static void fine(String p_fine_0_, Object... p_fine_1_)
    {
        log(Level.DEBUG, p_fine_0_, p_fine_1_);
    }

    public static void finer(String p_finer_0_, Object... p_finer_1_)
    {
        log(Level.TRACE, p_finer_0_, p_finer_1_);
    }

    public static void finest(String p_finest_0_, Object... p_finest_1_)
    {
        log(Level.TRACE, p_finest_0_, p_finest_1_);
    }

    public static void makeLog(String p_makeLog_0_)
    {
        LogManager.getLogger(p_makeLog_0_);
    }
}
