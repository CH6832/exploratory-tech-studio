package com.system.utils;

import org.apache.jmeter.samplers.SampleResult; // Import SampleResult to log results


public class AppProfiler {

    // Start time of the profiling
    private long startTime;
    // End time of the profiling
    private long endTime;
    // SampleResult for JMeter logging
    private SampleResult sampleResult;

    /**
     * Starts the profiling by recording the current time and initializing the SampleResult.
     */
    public void start(String methodName) {
        startTime = System.nanoTime(); // Record the start time
        sampleResult = new SampleResult(); // Initialize SampleResult
        sampleResult.setSampleLabel(methodName); // Set the label of the method being profiled
        // sampleResult.getStartTime(System.currentTimeMillis()); // Set the start time in milliseconds
        sampleResult.getStartTime();
        sampleResult.setSuccessful(true); // Assume the execution is successful initially
    }

    /**
     * Stops the profiling and returns the elapsed time in milliseconds.
     *
     * @return Elapsed time in milliseconds.
     */
    public long stop() {
        endTime = System.nanoTime(); // Record the end time
        if (this.sampleResult != null) {
            this.sampleResult.setEndTime(System.currentTimeMillis());
        }
        assert sampleResult != null;
        sampleResult.setResponseCode(String.valueOf(getElapsedTimeInMilliseconds())); // Set response time
        sampleResult.setSuccessful(true); // Mark as successful
        return getElapsedTimeInMilliseconds(); // Return elapsed time
    }

    /**
     * Calculates the elapsed time in milliseconds.
     *
     * @return Elapsed time in milliseconds.
     */
    private long getElapsedTimeInMilliseconds() {
        return (endTime - startTime) / 1_000_000; // Convert nanoseconds to milliseconds
    }

    /**
     * Logs the profiling result.
     *
     * @param methodName The name of the method being profiled.
     */
    public void logProfilingResult(String methodName) {
        long elapsedTime = stop(); // Stop and get elapsed time
        sampleResult.setResponseMessage("Execution time of " + methodName + " : " + elapsedTime + " ms");
        AppLogger.info(sampleResult.getResponseMessage()); // Log the result using AppLogger
        // Here you can add additional logic to process the SampleResult
    }

    /**
     * Get SampleResult for further processing or assertions.
     *
     * @return The SampleResult instance.
     */
    public SampleResult getSampleResult() {
        return sampleResult;
    }
}
