package com.java.ifrscalculator.profiling;

import java.util.HashMap;
import java.util.Map;

public class Profiler {
    private static final Map<String, Long> startTimes = new HashMap<>();

    public static void start(String label) {
        startTimes.put(label, System.currentTimeMillis());
    }

    public static void stop(String label) {
        Long startTime = startTimes.get(label);
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            System.out.println(label + " took " + duration + " milliseconds.");
            startTimes.remove(label);
        } else {
            System.out.println("Profiler error: No start time found for " + label);
        }
    }
}
