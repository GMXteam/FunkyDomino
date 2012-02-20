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
 * @since 20:37:27 - 04.05.2010
 */
public class ScaleParticleModifier<T extends IEntity> extends BaseDoubleValueSpanParticleModifier<T> {
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
     * @param pFromScale
     * @param pToScale
     */
    public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScale, final float pToScale) {
		this(pFromTime, pToTime, pFromScale, pToScale, EaseLinear.getInstance());
	}

    /**
     * 
     * @param pFromTime
     * @param pToTime
     * @param pFromScale
     * @param pToScale
     * @param pEaseFunction
     */
    public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScale, final float pToScale, final IEaseFunction pEaseFunction) {
		this(pFromTime, pToTime, pFromScale, pToScale, pFromScale, pToScale, pEaseFunction);
	}

        /**
         * 
         * @param pFromTime
         * @param pToTime
         * @param pFromScaleX
         * @param pToScaleX
         * @param pFromScaleY
         * @param pToScaleY
         */
        public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScaleX, final float pToScaleX, final float pFromScaleY, final float pToScaleY) {
		this(pFromTime, pToTime, pFromScaleX, pToScaleX, pFromScaleY, pToScaleY, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pFromTime
         * @param pToTime
         * @param pFromScaleX
         * @param pToScaleX
         * @param pFromScaleY
         * @param pToScaleY
         * @param pEaseFunction
         */
        public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScaleX, final float pToScaleX, final float pFromScaleY, final float pToScaleY, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromScaleX, pToScaleX, pFromScaleY, pToScaleY, pEaseFunction);
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
         * @param pScaleX
         * @param pScaleY
         */
        @Override
	protected void onSetInitialValues(final Particle<T> pParticle, final float pScaleX, final float pScaleY) {
		pParticle.getEntity().setScale(pScaleX, pScaleY);
	}

        /**
         * 
         * @param pParticle
         * @param pPercentageDone
         * @param pScaleX
         * @param pScaleY
         */
        @Override
	protected void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pScaleX, final float pScaleY) {
		pParticle.getEntity().setScale(pScaleX, pScaleY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
