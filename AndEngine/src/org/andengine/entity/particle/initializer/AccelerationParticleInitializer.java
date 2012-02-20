package org.andengine.entity.particle.initializer;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 21:21:10 - 14.03.2010
 */
public class AccelerationParticleInitializer<T extends IEntity> extends BaseDoubleValueParticleInitializer<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     * @param pAcceleration
     */
    public AccelerationParticleInitializer(final float pAcceleration) {
		this(pAcceleration, pAcceleration);
	}

        /**
         * 
         * @param pAccelerationX
         * @param pAccelerationY
         */
        public AccelerationParticleInitializer(final float pAccelerationX, final float pAccelerationY) {
		this(pAccelerationX, pAccelerationX, pAccelerationY, pAccelerationY);
	}

        /**
         * 
         * @param pMinAccelerationX
         * @param pMaxAccelerationX
         * @param pMinAccelerationY
         * @param pMaxAccelerationY
         */
        public AccelerationParticleInitializer(final float pMinAccelerationX, final float pMaxAccelerationX, final float pMinAccelerationY, final float pMaxAccelerationY) {
		super(pMinAccelerationX, pMaxAccelerationX, pMinAccelerationY, pMaxAccelerationY);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getMinAccelerationX() {
		return this.mMinValue;
	}

        /**
         * 
         * @return
         */
        public float getMaxAccelerationX() {
		return this.mMaxValue;
	}

        /**
         * 
         * @return
         */
        public float getMinAccelerationY() {
		return this.mMinValueB;
	}

        /**
         * 
         * @return
         */
        public float getMaxAccelerationY() {
		return this.mMaxValueB;
	}

        /**
         * 
         * @param pAccelerationX
         */
        public void setAccelerationX(final float pAccelerationX) {
		this.mMinValue = pAccelerationX;
		this.mMaxValue = pAccelerationX;
	}

        /**
         * 
         * @param pAccelerationY
         */
        public void setAccelerationY(final float pAccelerationY) {
		this.mMinValueB = pAccelerationY;
		this.mMaxValueB = pAccelerationY;
	}

        /**
         * 
         * @param pMinAccelerationX
         * @param pMaxAccelerationX
         */
        public void setAccelerationX(final float pMinAccelerationX, final float pMaxAccelerationX) {
		this.mMinValue = pMinAccelerationX;
		this.mMaxValue = pMaxAccelerationX;
	}

        /**
         * 
         * @param pMinAccelerationY
         * @param pMaxAccelerationY
         */
        public void setAccelerationY(final float pMinAccelerationY, final float pMaxAccelerationY) {
		this.mMinValueB = pMinAccelerationY;
		this.mMaxValueB = pMaxAccelerationY;
	}

        /**
         * 
         * @param pMinAccelerationX
         * @param pMaxAccelerationX
         * @param pMinAccelerationY
         * @param pMaxAccelerationY
         */
        public void setAcceleration(final float pMinAccelerationX, final float pMaxAccelerationX, final float pMinAccelerationY, final float pMaxAccelerationY) {
		this.mMinValue = pMinAccelerationX;
		this.mMaxValue = pMaxAccelerationX;
		this.mMinValueB = pMinAccelerationY;
		this.mMaxValueB = pMaxAccelerationY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pParticle
         * @param pAccelerationX
         * @param pAccelerationY
         */
        @Override
	public void onInitializeParticle(final Particle<T> pParticle, final float pAccelerationX, final float pAccelerationY) {
		pParticle.getPhysicsHandler().accelerate(pAccelerationX, pAccelerationY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
