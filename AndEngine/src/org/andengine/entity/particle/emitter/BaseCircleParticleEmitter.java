package org.andengine.entity.particle.emitter;


/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 20:14:43 - 01.10.2010
 */
public abstract class BaseCircleParticleEmitter extends BaseParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected float mRadiusX;
    /**
     * 
     */
    protected float mRadiusY;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         * @param pRadius
         */
        public BaseCircleParticleEmitter(final float pCenterX, final float pCenterY, final float pRadius) {
		this(pCenterX, pCenterY, pRadius, pRadius);
	}

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         * @param pRadiusX
         * @param pRadiusY
         */
        public BaseCircleParticleEmitter(final float pCenterX, final float pCenterY, final float pRadiusX, final float pRadiusY) {
		super(pCenterX, pCenterY);
		this.setRadiusX(pRadiusX);
		this.setRadiusY(pRadiusY);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getRadiusX() {
		return this.mRadiusX;
	}

        /**
         * 
         * @param pRadiusX
         */
        public void setRadiusX(final float pRadiusX) {
		this.mRadiusX = pRadiusX;
	}

        /**
         * 
         * @return
         */
        public float getRadiusY() {
		return this.mRadiusY;
	}

        /**
         * 
         * @param pRadiusY
         */
        public void setRadiusY(final float pRadiusY) {
		this.mRadiusY = pRadiusY;
	}

        /**
         * 
         * @param pRadius
         */
        public void setRadius(final float pRadius) {
		this.mRadiusX = pRadius;
		this.mRadiusY = pRadius;
	}

        /**
         * 
         * @param pRadiusX
         * @param pRadiusY
         */
        public void setRadius(final float pRadiusX, final float pRadiusY) {
		this.mRadiusX = pRadiusX;
		this.mRadiusY = pRadiusY;
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
