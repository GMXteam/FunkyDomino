package org.andengine.util.modifier;

import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:52:31 - 03.09.2010
 * @param <T>
 */
public abstract class BaseTripleValueSpanModifier<T> extends BaseDoubleValueSpanModifier<T> {
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
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pFromValueC
         * @param pToValueC
         * @param pEaseFunction
         */
        public BaseTripleValueSpanModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pFromValueC, pToValueC, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pFromValueC
         * @param pToValueC
         * @param pModifierListener
         * @param pEaseFunction
         */
        public BaseTripleValueSpanModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final IModifierListener<T> pModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pModifierListener, pEaseFunction);

		this.mFromValueC = pFromValueC;
		this.mValueSpanC = pToValueC - pFromValueC;
	}

        /**
         * 
         * @param pBaseTripleValueSpanModifier
         */
        protected BaseTripleValueSpanModifier(final BaseTripleValueSpanModifier<T> pBaseTripleValueSpanModifier) {
		super(pBaseTripleValueSpanModifier);

		this.mFromValueC = pBaseTripleValueSpanModifier.mFromValueC;
		this.mValueSpanC = pBaseTripleValueSpanModifier.mValueSpanC;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getFromValueC() {
		return this.mFromValueC;
	}

        /**
         * 
         * @return
         */
        public float getToValueC() {
		return this.mFromValueC + this.mValueSpanC;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pItem
         * @param pValueA
         * @param pValueB
         * @param pValueC
         */
        protected abstract void onSetInitialValues(final T pItem, final float pValueA, final float pValueB, final float pValueC);
        /**
         * 
         * @param pItem
         * @param pPerctentageDone
         * @param pValueA
         * @param pValueB
         * @param pValueC
         */
        protected abstract void onSetValues(final T pItem, final float pPerctentageDone, final float pValueA, final float pValueB, final float pValueC);

        /**
         * 
         * @param pItem
         * @param pValueA
         * @param pValueB
         */
        @Override
	protected void onSetInitialValues(final T pItem, final float pValueA, final float pValueB) {
		this.onSetInitialValues(pItem, pValueA, pValueB, this.mFromValueC);
	}

        /**
         * 
         * @param pItem
         * @param pPercentageDone
         * @param pValueA
         * @param pValueB
         */
        @Override
	protected void onSetValues(final T pItem, final float pPercentageDone, final float pValueA, final float pValueB) {
		this.onSetValues(pItem, pPercentageDone, pValueA, pValueB, this.mFromValueC + pPercentageDone * this.mValueSpanC);
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @deprecated
         */
        @Override
	@Deprecated
	public void reset(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB) {
		super.reset(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB);
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pFromValueC
         * @param pToValueC
         */
        public void reset(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC) {
		super.reset(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB);

		this.mFromValueC = pFromValueC;
		this.mValueSpanC = pToValueC - pFromValueC;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
