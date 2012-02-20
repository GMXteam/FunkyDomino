package org.andengine.engine.handler.timer;

import org.andengine.engine.handler.IUpdateHandler;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:23:58 - 12.03.2010
 */
public class TimerHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mTimerSeconds;
	private float mTimerSecondsElapsed;
	private boolean mTimerCallbackTriggered;
        /**
         * 
         */
        protected final ITimerCallback mTimerCallback;
	private boolean mAutoReset;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pTimerSeconds
         * @param pTimerCallback
         */
        public TimerHandler(final float pTimerSeconds, final ITimerCallback pTimerCallback) {
		this(pTimerSeconds, false, pTimerCallback);
	}

        /**
         * 
         * @param pTimerSeconds
         * @param pAutoReset
         * @param pTimerCallback
         */
        public TimerHandler(final float pTimerSeconds, final boolean pAutoReset, final ITimerCallback pTimerCallback) {
		this.mTimerSeconds = pTimerSeconds;
		this.mAutoReset = pAutoReset;
		this.mTimerCallback = pTimerCallback;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public boolean isAutoReset() {
		return this.mAutoReset;
	}

        /**
         * 
         * @param pAutoReset
         */
        public void setAutoReset(final boolean pAutoReset) {
		this.mAutoReset = pAutoReset;
	}

        /**
         * 
         * @param pTimerSeconds
         */
        public void setTimerSeconds(final float pTimerSeconds) {
		this.mTimerSeconds = pTimerSeconds;
	}

        /**
         * 
         * @return
         */
        public float getTimerSeconds() {
		return this.mTimerSeconds;
	}

        /**
         * 
         * @return
         */
        public float getTimerSecondsElapsed() {
		return this.mTimerSecondsElapsed;
	}
	
        /**
         * 
         * @return
         */
        public boolean isTimerCallbackTriggered() {
		return this.mTimerCallbackTriggered;
	}

        /**
         * 
         * @param pTimerCallbackTriggered
         */
        public void setTimerCallbackTriggered(boolean pTimerCallbackTriggered) {
		this.mTimerCallbackTriggered = pTimerCallbackTriggered;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	public void onUpdate(final float pSecondsElapsed) {
		if(this.mAutoReset) {
			this.mTimerSecondsElapsed += pSecondsElapsed;
			while(this.mTimerSecondsElapsed >= this.mTimerSeconds) {
				this.mTimerSecondsElapsed -= this.mTimerSeconds;
				this.mTimerCallback.onTimePassed(this);
			}
		} else {
			if(!this.mTimerCallbackTriggered) {
				this.mTimerSecondsElapsed += pSecondsElapsed;
				if(this.mTimerSecondsElapsed >= this.mTimerSeconds) {
					this.mTimerCallbackTriggered = true;
					this.mTimerCallback.onTimePassed(this);
				}
			}
		}
	}

        /**
         * 
         */
        @Override
	public void reset() {
		this.mTimerCallbackTriggered = false;
		this.mTimerSecondsElapsed = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
