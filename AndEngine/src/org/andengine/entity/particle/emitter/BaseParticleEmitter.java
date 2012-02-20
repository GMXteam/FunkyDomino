package org.andengine.entity.particle.emitter;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:58:12 - 01.10.2010
 */
public abstract class BaseParticleEmitter implements IParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected float mCenterX;
    /**
     * 
     */
    protected float mCenterY;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         */
        public BaseParticleEmitter(final float pCenterX, final float pCenterY) {
		this.mCenterX = pCenterX;
		this.mCenterY = pCenterY;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getCenterX() {
		return this.mCenterX;
	}

        /**
         * 
         * @return
         */
        public float getCenterY() {
		return this.mCenterY;
	}

        /**
         * 
         * @param pCenterX
         */
        public void setCenterX(final float pCenterX) {
		this.mCenterX = pCenterX;
	}

        /**
         * 
         * @param pCenterY
         */
        public void setCenterY(final float pCenterY) {
		this.mCenterY = pCenterY;
	}

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         */
        public void setCenter(final float pCenterX, final float pCenterY) {
		this.mCenterX = pCenterX;
		this.mCenterY = pCenterY;
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

	}

        /**
         * 
         */
        @Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
