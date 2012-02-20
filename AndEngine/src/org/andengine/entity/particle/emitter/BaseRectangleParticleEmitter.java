package org.andengine.entity.particle.emitter;


/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 18:53:18 - 01.10.2010
 */
public abstract class BaseRectangleParticleEmitter extends BaseParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected float mWidth;
        /**
         * 
         */
        protected float mHeight;
        /**
         * 
         */
        protected float mWidthHalf;
        /**
         * 
         */
        protected float mHeightHalf;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         * @param pSize
         */
        public BaseRectangleParticleEmitter(final float pCenterX, final float pCenterY, final float pSize) {
		this(pCenterX, pCenterY, pSize, pSize);
	}

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         * @param pWidth
         * @param pHeight
         */
        public BaseRectangleParticleEmitter(final float pCenterX, final float pCenterY, final float pWidth, final float pHeight) {
		super(pCenterX, pCenterY);
		this.setWidth(pWidth);
		this.setHeight(pHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getWidth() {
		return this.mWidth;
	}

        /**
         * 
         * @param pWidth
         */
        public void setWidth(final float pWidth) {
		this.mWidth = pWidth;
		this.mWidthHalf = pWidth * 0.5f;
	}

        /**
         * 
         * @return
         */
        public float getHeight() {
		return this.mHeight;
	}

        /**
         * 
         * @param pHeight
         */
        public void setHeight(final float pHeight) {
		this.mHeight = pHeight;
		this.mHeightHalf = pHeight * 0.5f;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
