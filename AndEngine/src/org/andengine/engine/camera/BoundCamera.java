package org.andengine.engine.camera;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:55:54 - 27.07.2010
 */
public class BoundCamera extends Camera {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected boolean mBoundsEnabled;

        /**
         * 
         */
        protected float mBoundsXMin;
        /**
         * 
         */
        protected float mBoundsXMax;
        /**
         * 
         */
        protected float mBoundsYMin;
        /**
         * 
         */
        protected float mBoundsYMax;

        /**
         * 
         */
        protected float mBoundsCenterX;
        /**
         * 
         */
        protected float mBoundsCenterY;

        /**
         * 
         */
        protected float mBoundsWidth;
        /**
         * 
         */
        protected float mBoundsHeight;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         */
        public BoundCamera(final float pX, final float pY, final float pWidth, final float pHeight) {
		super(pX, pY, pWidth, pHeight);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pBoundMinX
         * @param pBoundMaxX
         * @param pBoundMinY
         * @param pBoundMaxY
         */
        public BoundCamera(final float pX, final float pY, final float pWidth, final float pHeight, final float pBoundMinX, final float pBoundMaxX, final float pBoundMinY, final float pBoundMaxY) {
		super(pX, pY, pWidth, pHeight);
		this.setBounds(pBoundMinX, pBoundMinY, pBoundMaxX, pBoundMaxY);
		this.mBoundsEnabled = true;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public boolean isBoundsEnabled() {
		return this.mBoundsEnabled;
	}

        /**
         * 
         * @param pBoundsEnabled
         */
        public void setBoundsEnabled(final boolean pBoundsEnabled) {
		this.mBoundsEnabled = pBoundsEnabled;
	}

        /**
         * 
         * @param pBoundsXMin
         * @param pBoundsYMin
         * @param pBoundsXMax
         * @param pBoundsYMax
         */
        public void setBounds(final float pBoundsXMin, final float pBoundsYMin, final float pBoundsXMax, final float pBoundsYMax) {
		this.mBoundsXMin = pBoundsXMin;
		this.mBoundsXMax = pBoundsXMax;
		this.mBoundsYMin = pBoundsYMin;
		this.mBoundsYMax = pBoundsYMax;

		this.mBoundsWidth = this.mBoundsXMax - this.mBoundsXMin;
		this.mBoundsHeight = this.mBoundsYMax - this.mBoundsYMin;

		this.mBoundsCenterX = this.mBoundsXMin + this.mBoundsWidth * 0.5f;
		this.mBoundsCenterY = this.mBoundsYMin + this.mBoundsHeight * 0.5f;
	}

        /**
         * 
         * @return
         */
        public float getBoundsXMin() {
		return this.mBoundsXMin;
	}

        /**
         * 
         * @return
         */
        public float getBoundsXMax() {
		return this.mBoundsXMax;
	}

        /**
         * 
         * @return
         */
        public float getBoundsYMin() {
		return this.mBoundsYMin;
	}

        /**
         * 
         * @return
         */
        public float getBoundsYMax() {
		return this.mBoundsYMax;
	}

        /**
         * 
         * @return
         */
        public float getBoundsWidth() {
		return this.mBoundsWidth;
	}

        /**
         * 
         * @return
         */
        public float getBoundsHeight() {
		return this.mBoundsHeight;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
        /**
         * 
         * @param pCenterX
         * @param pCenterY
         */
        @Override
	public void setCenter(final float pCenterX, final float pCenterY) {
		super.setCenter(pCenterX, pCenterY);
		
		if(this.mBoundsEnabled) {
			this.ensureInBounds();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         */
        protected void ensureInBounds() {
		final float centerX;
		if(this.mBoundsWidth < this.getWidth()) {
			centerX = this.mBoundsCenterX;
		} else {
			centerX = getBoundedX(this.getCenterX()); 
		}
		final float centerY;
		if(this.mBoundsHeight < this.getHeight()) {
			centerY = this.mBoundsCenterY;
		} else {
			centerY = getBoundedY(this.getCenterY()); 
		}
		super.setCenter(centerX, centerY);
	}

        /**
         * 
         * @param pX
         * @return
         */
        protected float getBoundedX(final float pX) {
		final float minXBoundExceededAmount = this.mBoundsXMin - this.getXMin();
		final boolean minXBoundExceeded = minXBoundExceededAmount > 0;

		final float maxXBoundExceededAmount = this.getXMax() - this.mBoundsXMax;
		final boolean maxXBoundExceeded = maxXBoundExceededAmount > 0;

		if(minXBoundExceeded) {
			if(maxXBoundExceeded) {
				/* Min and max X exceeded. */
				return pX - maxXBoundExceededAmount + minXBoundExceededAmount;
			} else {
				/* Only min X exceeded. */
				return pX + minXBoundExceededAmount;
			}
		} else {
			if(maxXBoundExceeded) {
				/* Only max X exceeded. */
				return pX - maxXBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pX;
			}
		}
	}

        /**
         * 
         * @param pY
         * @return
         */
        protected float getBoundedY(final float pY) {
		final float minYBoundExceededAmount = this.mBoundsYMin - this.getYMin();
		final boolean minYBoundExceeded = minYBoundExceededAmount > 0;

		final float maxYBoundExceededAmount = this.getYMax() - this.mBoundsYMax;
		final boolean maxYBoundExceeded = maxYBoundExceededAmount > 0;

		if(minYBoundExceeded) {
			if(maxYBoundExceeded) {
				/* Min and max Y exceeded. */
				return pY - maxYBoundExceededAmount + minYBoundExceededAmount;
			} else {
				/* Only min Y exceeded. */
				return pY + minYBoundExceededAmount;
			}
		} else {
			if(maxYBoundExceeded) {
				/* Only max Y exceeded. */
				return pY - maxYBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pY;
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
