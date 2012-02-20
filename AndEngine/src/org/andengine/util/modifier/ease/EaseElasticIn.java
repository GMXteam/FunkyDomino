package org.andengine.util.modifier.ease;

import org.andengine.util.math.MathConstants;

import android.util.FloatMath;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Gil
 * @author Nicolas Gramlich
 * @since 16:52:11 - 26.07.2010
 */
public class EaseElasticIn implements IEaseFunction, MathConstants {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseElasticIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseElasticIn() {

	}

        /**
         * 
         * @return
         */
        public static EaseElasticIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseElasticIn();
		}
		return INSTANCE;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         * @param pDuration
         * @return
         */
        @Override
	public float getPercentage(final float pSecondsElapsed, final float pDuration) {
		return EaseElasticIn.getValue(pSecondsElapsed, pDuration, pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         * @param pDuration
         * @param pPercentage
         * @return
         */
        public static float getValue(final float pSecondsElapsed, final float pDuration, final float pPercentage) {
		if(pSecondsElapsed == 0) {
			return 0;
		}
		if(pSecondsElapsed == pDuration) {
			return 1;
		}

		final float p = pDuration * 0.3f;
		final float s = p / 4;

		final float t = pPercentage - 1;
		return -(float)Math.pow(2, 10 * t) * FloatMath.sin((t * pDuration - s) * PI_TWICE / p);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
