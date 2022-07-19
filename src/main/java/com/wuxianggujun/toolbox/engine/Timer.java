package com.wuxianggujun.toolbox.engine;

public class Timer {
    private double lastLoopTime;

    public void init() {
        lastLoopTime = getTime();
    }

    public float getELapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }

    public double getTime() {
        return System.nanoTime();
    }
}
