package org.andengine.entity.particle.initializer;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 10:17:42 - 29.06.2010
 */
public class RotationParticleInitializer<T extends IEntity> extends BaseSingleValueParticleInitializer<T> {
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
     * @param pRotation
     */
    public RotationParticleInitializer(final float pRotation) {
		this(pRotation, pRotation);
	}

    /**
     * 
     * @param pMinRotation
     * @param pMaxRotation
     */
    public RotationParticleInitializer(final float pMinRotation, final float pMaxRotation) {
		super(pMinRotation, pMaxRotation);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

    /**
     * 
     * @return
     */
    public float getMinRotation() {
		return this.mMinValue;
	}

    /**
     * 
     * @return
     */
    public float getMaxRotation() {
		return this.mMaxValue;
	}

        /**
         * 
         * @param pRotation
         */
        public void setRotation(final float pRotation) {
		this.mMinValue = pRotation;
		this.mMaxValue = pRotation;
	}

        /**
         * 
         * @param pMinRotation
         * @param pMaxRotation
         */
        public void setRotation(final float pMinRotation, final float pMaxRotation) {
		this.mMinValue = pMinRotation;
		this.mMaxValue = pMaxRotation;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pParticle
         * @param pRotation
         */
        @Override
	public void onInitializeParticle(final Particle<T> pParticle, final float pRotation) {
		pParticle.getEntity().setRotation(pRotation);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
