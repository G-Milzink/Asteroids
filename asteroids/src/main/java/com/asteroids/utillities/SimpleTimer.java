package com.asteroids.utillities;

public class SimpleTimer {

    private double maxCount;
    private int currentCount;
    private boolean timedOut;

    public SimpleTimer(double maxCount) {
        this.maxCount = maxCount * 60;
        this.currentCount = 0;
        this.timedOut = true;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void increaseCount() {
        if (!this.timedOut) {
            this.currentCount++;
        }
        if (this.currentCount >= this.maxCount) {
            timedOut = true;
        }
    }

    public boolean hasTimedOut() {
        return this.timedOut;
    }

    public void reset() {
        this.currentCount = 0;
        timedOut = false;
    }

    public void debug() {
        System.out.println("timed out: " + timedOut + ", count: " +currentCount);
    }

}
