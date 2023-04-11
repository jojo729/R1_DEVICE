package com.unisound.vui.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = (CPU_COUNT + 1);
    private static final int KEEP_ALIVE = 30;
    private static final int MAXIMUM_POOL_SIZE = ((CPU_COUNT * 2) + 1);
    private static ExecutorService sSingleThreadExecutor = Executors.newSingleThreadExecutor();
    private static ExecutorService sThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(CORE_POOL_SIZE * 5));

    public static void execute(Runnable r) {
        executeInMultiThread(r);
    }

    public static void executeInMultiThread(Runnable runnable) {
        sThreadPoolExecutor.execute(runnable);
    }

    public static void executeInSingle(Runnable r) {
        sSingleThreadExecutor.execute(r);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
