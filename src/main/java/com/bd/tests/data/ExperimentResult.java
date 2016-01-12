package com.bd.tests.data;

/**
 * @author majaschaefer
 */
public class ExperimentResult {

    private double recordsPerSecond;
    private double averageTime;
    private double minTime;
    private double maxTime;
    private double medTime;
    private int connections;

    public ExperimentResult(double recordsPerSecond, double averageTime, double minTime, double maxTime, double medTime, int connections) {
        this.recordsPerSecond = recordsPerSecond;
        this.averageTime = averageTime;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.medTime = medTime;
        this.connections = connections;
    }

    public double getRecordsPerSecond() {
        return recordsPerSecond;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public double getMinTime() {
        return minTime;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public double getMedTime() {
        return medTime;
    }

    public int getConnections() {
        return connections;
    }
}

