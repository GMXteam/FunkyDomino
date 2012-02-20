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
public class EaseSineIn implements IEaseFunction, MathConstants {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseSineIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseSineIn() {

	}

        /**
         * 
         * @return
         */
        public static EaseSineIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseSineIn();
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
		return EaseSineIn.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pPercentage
         * @return
         */
        public static float getValue(final float pPercentage) {
		return -FloatMath.cos(pPercentage * PI_HALF) + 1;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
