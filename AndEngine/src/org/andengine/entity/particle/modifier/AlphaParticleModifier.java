package org.andengine.entity.particle.modifier;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 21:21:10 - 14.03.2010
 */
public class AlphaParticleModifier<T extends IEntity> extends BaseSingleValueSpanParticleModifier<T> {
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
     * @param pFromTime
     * @param pToTime
     * @param pFromAlpha
     * @param pToAlpha
     */
    public AlphaParticleModifier(final float pFromTime, final float pToTime, final float pFromAlpha, final float pToAlpha) {
		this(pFromTime, pToTime, pFromAlpha, pToAlpha, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pFromTime
         * @param pToTime
         * @param pFromAlpha
         * @param pToAlpha
         * @param pEaseFunction
         */
        public AlphaParticleModifier(final float pFromTime, final float pToTime, final float pFromAlpha, final float pToAlpha, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromAlpha, pToAlpha, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pParticle
         * @param pAlpha
         */
        @Override
	protected void onSetInitialValue(final Particle<T> pParticle, final float pAlpha) {
		pParticle.getEntity().setAlpha(pAlpha);
	}

        /**
         * 
         * @param pParticle
         * @param pPercentageDone
         * @param pAlpha
         */
        @Override
	protected void onSetValue(final Particle<T> pParticle, final float pPercentageDone, final float pAlpha) {
		pParticle.getEntity().setAlpha(pAlpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
