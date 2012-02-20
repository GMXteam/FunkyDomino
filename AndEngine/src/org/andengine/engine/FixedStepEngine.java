package org.andengine.engine;

import org.andengine.engine.options.EngineOptions;


/**
 * A subclass of {@link Engine} that tries to achieve a specific amount of updates per second.
 * When the time since the last update is bigger long the steplength, additional updates are executed.
 * 
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:17:47 - 02.08.2010
 */
public class FixedStepEngine extends Engine {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final long mStepLength;
	private long mSecondsElapsedAccumulator;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pEngineOptions
         * @param pStepsPerSecond
         */
        public FixedStepEngine(final EngineOptions pEngineOptions, final int pStepsPerSecond) {
		super(pEngineOptions);
		this.mStepLength = NANOSECONDS_PER_SECOND / pStepsPerSecond;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pNanosecondsElapsed
         * @throws InterruptedException
         */
        @Override
	public void onUpdate(final long pNanosecondsElapsed) throws InterruptedException {
		this.mSecondsElapsedAccumulator += pNanosecondsElapsed;

		final long stepLength = this.mStepLength;
		while(this.mSecondsElapsedAccumulator >= stepLength) {
			super.onUpdate(stepLength);
			this.mSecondsElapsedAccumulator -= stepLength;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
