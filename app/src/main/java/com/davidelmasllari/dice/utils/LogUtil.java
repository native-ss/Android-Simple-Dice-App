package com.davidelmasllari.dice.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

public class LogUtil {

    public static void v(String tag, String msg) {
        log(Log.VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        log(Log.DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        log(Log.WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, msg);
    }

    public static void a(String tag, String msg) {
        log(Log.ASSERT, tag, msg);
    }

    private static void log(int priority, String tag, String msg) {
        Crashlytics.log(priority, tag, msg);
    }
}
