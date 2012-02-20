package org.andengine.util.modifier;

import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:51:46 - 03.09.2010
 * @param <T>
 */
public abstract class BaseDoubleValueSpanModifier<T> extends BaseSingleValueSpanModifier<T> {
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
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         */
        public BaseDoubleValueSpanModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB) {
		this(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pEaseFunction
         */
        public BaseDoubleValueSpanModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pModifierListener
         */
        public BaseDoubleValueSpanModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IModifierListener<T> pModifierListener) {
		this(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pModifierListener
         * @param pEaseFunction
         */
        public BaseDoubleValueSpanModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IModifierListener<T> pModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValueA, pToValueA, pModifierListener, pEaseFunction);

		this.mFromValueB = pFromValueB;
		this.mValueSpanB = pToValueB - pFromValueB;
	}

        /**
         * 
         * @param pBaseDoubleValueSpanModifier
         */
        protected BaseDoubleValueSpanModifier(final BaseDoubleValueSpanModifier<T> pBaseDoubleValueSpanModifier) {
		super(pBaseDoubleValueSpanModifier);

		this.mFromValueB = pBaseDoubleValueSpanModifier.mFromValueB;
		this.mValueSpanB = pBaseDoubleValueSpanModifier.mValueSpanB;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         * @deprecated
         */
        @Deprecated
	public float getFromValue() {
		return super.getFromValue();
	}

	@Deprecated
	public float getToValue() {
		return super.getToValue();
	}

        /**
         * 
         * @return
         */
        public float getFromValueA() {
		return super.getFromValue();
	}

        /**
         * 
         * @return
         */
        public float getToValueA() {
		return super.getToValue();
	}

        /**
         * 
         * @return
         */
        public float getFromValueB() {
		return this.mFromValueB;
	}

        /**
         * 
         * @return
         */
        public float getToValueB() {
		return this.mFromValueB + this.mValueSpanB;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pItem
         * @param pValueA
         * @param pValueB
         */
        protected abstract void onSetInitialValues(final T pItem, final float pValueA, final float pValueB);
        /**
         * 
         * @param pItem
         * @param pPercentageDone
         * @param pValueA
         * @param pValueB
         */
        protected abstract void onSetValues(final T pItem, final float pPercentageDone, final float pValueA, final float pValueB);

        /**
         * 
         * @param pItem
         * @param pValueA
         */
        @Override
	protected void onSetInitialValue(final T pItem, final float pValueA) {
		this.onSetInitialValues(pItem, pValueA, this.mFromValueB);
	}

	@Override
	protected void onSetValue(final T pItem, final float pPercentageDone, final float pValueA) {
		this.onSetValues(pItem, pPercentageDone, pValueA, this.mFromValueB + pPercentageDone * this.mValueSpanB);
	}

        /**
         * 
         * @param pDuration
         * @param pFromValue
         * @param pToValue
         * @deprecated
         */
        @Override
	@Deprecated
	public void reset(final float pDuration, final float pFromValue, final float pToValue) {
		super.reset(pDuration, pFromValue, pToValue);
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
         */
        public void reset(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB) {
		super.reset(pDuration, pFromValueA, pToValueA);

		this.mFromValueB = pFromValueB;
		this.mValueSpanB = pToValueB - pFromValueB;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
