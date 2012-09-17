/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.util;

import org.andengine.engine.handler.IUpdateHandler;

/**
 *
 * @author Usager
 */
public class TimeCounterHandler implements IUpdateHandler {

    private final float CALL_AFTER;
    /**
     * Last time onTimeChange was called.
     */
    private float mLastTime = 0.0f;

    /**
     *
     */
    public interface IOnTimeChangeListener {

        /**
         *
         * @param newTime
         */
        public void onTimeChange(float newTime);
    }
    private float mElapsedTime;
    private boolean mIsPaused = true;
    private IOnTimeChangeListener mOnTimeChangeListener;

    /**
     *
     * @param initTime
     * @param pOnTimeChangeListener
     * @param pCallAfter  
     */
    public TimeCounterHandler(float initTime, IOnTimeChangeListener pOnTimeChangeListener, float pCallAfter) {
        mElapsedTime = initTime;
        CALL_AFTER = pCallAfter;
        mOnTimeChangeListener = pOnTimeChangeListener;
    }

    /**
     *
     * @param pOnTimeChangeListener 
     */
    public TimeCounterHandler(IOnTimeChangeListener pOnTimeChangeListener) {
        this(0.0f, pOnTimeChangeListener, 0.5f);
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
        if (Math.abs(mElapsedTime - mLastTime) >= CALL_AFTER) {
            mOnTimeChangeListener.onTimeChange(mElapsedTime);
            mLastTime = mElapsedTime;
        }
    }

    /**
     *
     */
    public void reset() {
        mElapsedTime = 0.0f;
        mLastTime = 0.0f;
    }
}
