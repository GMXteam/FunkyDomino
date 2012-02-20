package org.andengine.util.modifier;


/**
 * (c) Zynga 2011
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:17:30 - 10.08.2011
 */
public abstract class BaseDoubleValueChangeModifier<T> extends BaseSingleValueChangeModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mValueChangeBPerSecond;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pDuration
         * @param pValueChangeA
         * @param pValueChangeB
         */
        public BaseDoubleValueChangeModifier(final float pDuration, final float pValueChangeA, final float pValueChangeB) {
		this(pDuration, pValueChangeA, pValueChangeB, null);
	}

        /**
         * 
         * @param pDuration
         * @param pValueChangeA
         * @param pValueChangeB
         * @param pModifierListener
         */
        public BaseDoubleValueChangeModifier(final float pDuration, final float pValueChangeA, final float pValueChangeB, final IModifierListener<T> pModifierListener) {
		super(pDuration, pValueChangeA, pModifierListener);

		this.mValueChangeBPerSecond = pValueChangeB / pDuration;
	}

        /**
         * 
         * @param pBaseDoubleValueChangeModifier
         */
        protected BaseDoubleValueChangeModifier(final BaseDoubleValueChangeModifier<T> pBaseDoubleValueChangeModifier) {
		super(pBaseDoubleValueChangeModifier);

		this.mValueChangeBPerSecond = pBaseDoubleValueChangeModifier.mValueChangeBPerSecond;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         * @param pItem
         * @param pValueA
         */
        @Override
	protected void onChangeValue(final float pSecondsElapsed, final T pItem, final float pValueA) {
		this.onChangeValues(pSecondsElapsed, pItem, pValueA, pSecondsElapsed * this.mValueChangeBPerSecond);
	}

        /**
         * 
         * @param pSecondsElapsed
         * @param pItem
         * @param pValueA
         * @param pValueB
         */
        protected abstract void onChangeValues(float pSecondsElapsed, T pItem, float pValueA, float pValueB);

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
