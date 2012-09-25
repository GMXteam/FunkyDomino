/*
 *   This file is part of Funky Domino.
 *
 *   Funky Domino is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Funky Domino is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Funky Domino.  If not, see <http://www.gnu.org/licenses/>.
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
