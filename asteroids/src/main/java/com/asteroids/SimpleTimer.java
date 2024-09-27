package com.asteroids;

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
        if (this.timedOut) {
            this.reset();
            return true;
        } else
            return false;
    }

    public void reset() {
        this.currentCount = 0;
        timedOut = true;
    }

}
