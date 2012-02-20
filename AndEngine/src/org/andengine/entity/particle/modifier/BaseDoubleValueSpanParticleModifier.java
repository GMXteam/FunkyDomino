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
 * @since 15:19:46 - 29.06.2010
 */
public abstract class BaseDoubleValueSpanParticleModifier<T extends IEntity> extends BaseSingleValueSpanParticleModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mFromValueB;
	private float mValueSpanB;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pFromTime
         * @param pToTime
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         */
        public BaseDoubleValueSpanParticleModifier(final float pFromTime, final float pToTime, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB) {
		this(pFromTime, pToTime, pFromValueA, pToValueA, pFromValueB, pToValueB, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pFromTime
         * @param pToTime
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pEaseFunction
         */
        public BaseDoubleValueSpanParticleModifier(final float pFromTime, final float pToTime, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromValueA, pToValueA, pEaseFunction);

		this.mFromValueB = pFromValueB;
		this.mValueSpanB = pToValueB - pFromValueB;
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
         * @param pValueA
         * @param pValueB
         */
        protected abstract void onSetInitialValues(final Particle<T> pParticle, final float pValueA, final float pValueB);
        /**
         * 
         * @param pParticle
         * @param pPercentageDone
         * @param pValueA
         * @param pValueB
         */
        protected abstract void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pValueA, final float pValueB);

        /**
         * 
         * @param pParticle
         * @param pValueA
         */
        @Override
	public void onSetInitialValue(final Particle<T> pParticle, final float pValueA) {
		this.onSetInitialValues(pParticle, pValueA, this.mFromValueB);
	}

        /**
         * 
         * @param pParticle
         * @param pPercentageDone
         * @param pValueA
         */
        @Override
	protected void onSetValue(final Particle<T> pParticle, final float pPercentageDone, final float pValueA) {
		this.onSetValues(pParticle, pPercentageDone, pValueA, this.mFromValueB + pPercentageDone * this.mValueSpanB);
	}

        /**
         * 
         * @param pFromValue
         * @param pToValue
         * @param pFromTime
         * @param pToTime
         * @deprecated
         */
        @Override
	@Deprecated
	public void reset(final float pFromValue, final float pToValue, final float pFromTime, final float pToTime) {
		super.reset(pFromValue, pToValue, pFromTime, pToTime);
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pFromTime
         * @param pToTime
         */
        public void reset(final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromTime, final float pToTime) {
		super.reset(pFromValueA, pToValueA, pFromTime, pToTime);

		this.mFromValueB = pFromValueB;
		this.mValueSpanB = pToValueB - pFromValueB;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
