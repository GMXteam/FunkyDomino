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
public abstract class BaseTripleValueSpanParticleModifier<T extends IEntity> extends BaseDoubleValueSpanParticleModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mFromValueC;
	private float mValueSpanC;

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
         * @param pFromValueC
         * @param pToValueC
         */
        public BaseTripleValueSpanParticleModifier(final float pFromTime, final float pToTime, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC) {
		this(pFromTime, pToTime, pFromValueA, pToValueA, pFromValueB, pToValueB, pFromValueC, pToValueC, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pFromTime
         * @param pToTime
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pFromValueC
         * @param pToValueC
         * @param pEaseFunction
         */
        public BaseTripleValueSpanParticleModifier(final float pFromTime, final float pToTime, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromValueA, pToValueA, pFromValueB, pToValueB, pEaseFunction);

		this.mFromValueC = pFromValueC;
		this.mValueSpanC = pToValueC - pFromValueC;
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
         * @param pValueC
         */
        protected abstract void onSetInitialValues(final Particle<T> pParticle, final float pValueA, final float pValueB, final float pValueC);
        /**
         * 
         * @param pParticle
         * @param pPercentageDone
         * @param pValueA
         * @param pValueB
         * @param pValueC
         */
        protected abstract void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pValueA, final float pValueB, final float pValueC);

        /**
         * 
         * @param pParticle
         * @param pValueA
         * @param pValueB
         */
        @Override
	public void onSetInitialValues(final Particle<T> pParticle, final float pValueA, final float pValueB) {
		this.onSetInitialValues(pParticle, pValueA, pValueB, this.mFromValueC);
	}

	@Override
	protected void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pValueA, final float pValueB) {
		this.onSetValues(pParticle, pPercentageDone, pValueA, pValueB, this.mFromValueC + pPercentageDone * this.mValueSpanC);
	}

        /**
         * 
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pFromTime
         * @param pToTime
         * @deprecated
         */
        @Override
	@Deprecated
	public void reset(float pFromValueA, float pToValueA, float pFromValueB, float pToValueB, float pFromTime, float pToTime) {
		super.reset(pFromValueA, pToValueA, pFromValueB, pToValueB, pFromTime, pToTime);
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
         * @param pFromValueC
         * @param pToValueC
         * @param pFromTime
         * @param pToTime
         */
        public void reset(final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final float pFromTime, final float pToTime) {
		super.reset(pFromValueA, pToValueA, pFromValueB, pToValueB, pFromTime, pToTime);

		this.mFromValueC = pFromValueC;
		this.mValueSpanC = pToValueC - pFromValueC;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
