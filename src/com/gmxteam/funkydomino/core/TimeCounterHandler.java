/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.TimerHandler;

/**
 *
 * @author Usager
 */
public class TimeCounterHandler implements IUpdateHandler {

    private float mElapsedTime;
    private boolean mIsPaused = true;

    /**
     *
     * @param initTime
     */
    public TimeCounterHandler(float initTime) {
        mElapsedTime = initTime;
    }

    /**
     *
     */
    public TimeCounterHandler() {
        mElapsedTime = 0l;
    }

    /**
     *
     */
    public void pause() {
        mIsPaused = true;
    }

    /**
     *
     */
    public void resume() {
        mIsPaused = false;
    }

    /**
     *
     */
    public void start() {

        reset();
        mIsPaused = false;
    }

    /**
     *
     * @return
     */
    public boolean isPaused() {
        return mIsPaused;
    }

    /**
     *
     * @return
     */
    public float getElapsedTime() {
        return mElapsedTime;
    }

    /**
     *
     * @param f
     */
    public void onUpdate(float f) {
        mElapsedTime += f;
    }

    /**
     *
     */
    public void reset() {
        mElapsedTime = 0.0f;
    }
}
