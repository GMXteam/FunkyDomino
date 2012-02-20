package org.andengine.util.adt.bounds;

import org.andengine.util.adt.spatial.bounds.util.FloatBoundsUtils;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 21:48:53 - 11.10.2011
 */
public class FloatBounds implements IFloatBounds {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mXMin;
	private float mYMin;
	private float mXMax;
	private float mYMax;

	// ===========================================================
	// Constructors
	// ===========================================================
	
        /**
         * 
         * @param pX
         * @param pY
         */
        public FloatBounds(final float pX, final float pY) {
		this.set(pX, pY);
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         */
        public FloatBounds(final float pXMin, final float pYMin, final float pXMax, final float pYMax) {
		this.set(pXMin, pYMin, pXMax, pYMax);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public float getXMin() {
		return this.mXMin;
	}
	
        /**
         * 
         * @return
         */
        @Override
	public float getYMin() {
		return this.mYMin;
	}

        /**
         * 
         * @return
         */
        @Override
	public float getXMax() {
		return this.mXMax;
	}

        /**
         * 
         * @return
         */
        @Override
	public float getYMax() {
		return this.mYMax;
	}

        /**
         * 
         * @param pX
         * @param pY
         */
        public void set(final float pX, final float pY) {
		this.set(pX, pY, pX, pY);
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         */
        public void set(final float pXMin, final float pYMin, final float pXMax, final float pYMax) {
		this.mXMin = pXMin;
		this.mYMin = pYMin;
		this.mXMax = pXMax;
		this.mYMax = pYMax;

		if(pXMin > pXMax) {
			throw new IllegalArgumentException("pXMin: '" + pXMin + "' must be smaller or equal to pXMax: '" + pXMax + "'.");
		}
		if(pYMin > pYMax) {
			throw new IllegalArgumentException("pYMin: '" + pYMin + "' must be smaller or equal to pYMax: '" + pYMax + "'.");
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pX
         * @param pY
         * @return
         */
        public boolean contains(final float pX, final float pY) {
		return FloatBoundsUtils.contains(this.mXMin, this.mYMin, this.mXMax, this.mYMax, pX, pY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
