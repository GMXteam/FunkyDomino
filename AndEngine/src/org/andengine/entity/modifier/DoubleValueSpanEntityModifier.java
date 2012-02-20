package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.BaseDoubleValueSpanModifier;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 23:29:22 - 19.03.2010
 */
public abstract class DoubleValueSpanEntityModifier extends BaseDoubleValueSpanModifier<IEntity> implements IEntityModifier {
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
     * @param pDuration
     * @param pFromValueA
     * @param pToValueA
     * @param pFromValueB
     * @param pToValueB
     */
    public DoubleValueSpanEntityModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB);
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
        public DoubleValueSpanEntityModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pEntityModifierListener
         */
        public DoubleValueSpanEntityModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pEntityModifierListener);
	}

        /**
         * 
         * @param pDuration
         * @param pFromValueA
         * @param pToValueA
         * @param pFromValueB
         * @param pToValueB
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public DoubleValueSpanEntityModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pDoubleValueSpanEntityModifier
         */
        protected DoubleValueSpanEntityModifier(final DoubleValueSpanEntityModifier pDoubleValueSpanEntityModifier) {
		super(pDoubleValueSpanEntityModifier);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
