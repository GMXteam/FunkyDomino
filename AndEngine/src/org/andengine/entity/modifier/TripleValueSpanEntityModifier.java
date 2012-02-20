package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.BaseTripleValueSpanModifier;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:35:18 - 29.06.2010
 */
public abstract class TripleValueSpanEntityModifier extends BaseTripleValueSpanModifier<IEntity> implements IEntityModifier {
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
     * @param pFromValueC
     * @param pToValueC
     * @param pEaseFunction
     */
    public TripleValueSpanEntityModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pFromValueC, pToValueC, pEaseFunction);
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
     * @param pEntityModifierListener
     * @param pEaseFunction
     */
    public TripleValueSpanEntityModifier(final float pDuration, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValueA, pToValueA, pFromValueB, pToValueB, pFromValueC, pToValueC, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pTripleValueSpanEntityModifier
         */
        protected TripleValueSpanEntityModifier(final TripleValueSpanEntityModifier pTripleValueSpanEntityModifier) {
		super(pTripleValueSpanEntityModifier);
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
