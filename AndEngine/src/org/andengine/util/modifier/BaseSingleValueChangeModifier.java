package org.andengine.util.modifier;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:49:51 - 03.09.2010
 * @param <T>
 */
public abstract class BaseSingleValueChangeModifier<T> extends BaseDurationModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final float mValueChangePerSecond;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pDuration
         * @param pValueChange
         */
        public BaseSingleValueChangeModifier(final float pDuration, final float pValueChange) {
		this(pDuration, pValueChange, null);
	}

        /**
         * 
         * @param pDuration
         * @param pValueChange
         * @param pModifierListener
         */
        public BaseSingleValueChangeModifier(final float pDuration, final float pValueChange, final IModifierListener<T> pModifierListener) {
		super(pDuration, pModifierListener);

		this.mValueChangePerSecond = pValueChange / pDuration;
	}

        /**
         * 
         * @param pBaseSingleValueChangeModifier
         */
        protected BaseSingleValueChangeModifier(final BaseSingleValueChangeModifier<T> pBaseSingleValueChangeModifier) {
		super(pBaseSingleValueChangeModifier);

		this.mValueChangePerSecond = pBaseSingleValueChangeModifier.mValueChangePerSecond;
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
         * @param pValue
         */
        protected abstract void onChangeValue(final float pSecondsElapsed, final T pItem, final float pValue);

	@Override
	protected void onManagedInitialize(final T pItem) {

	}

        /**
         * 
         * @param pSecondsElapsed
         * @param pItem
         */
        @Override
	protected void onManagedUpdate(final float pSecondsElapsed, final T pItem) {
		this.onChangeValue(pSecondsElapsed, pItem, this.mValueChangePerSecond * pSecondsElapsed);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
