package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 23:13:01 - 19.03.2010
 */
public class AlphaModifier extends SingleValueSpanEntityModifier {
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
     * @param pFromAlpha
     * @param pToAlpha
     */
    public AlphaModifier(final float pDuration, final float pFromAlpha, final float pToAlpha) {
		this(pDuration, pFromAlpha, pToAlpha, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromAlpha
         * @param pToAlpha
         * @param pEaseFunction
         */
        public AlphaModifier(final float pDuration, final float pFromAlpha, final float pToAlpha, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromAlpha, pToAlpha, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromAlpha
         * @param pToAlpha
         * @param pEntityModifierListener
         */
        public AlphaModifier(final float pDuration, final float pFromAlpha, final float pToAlpha, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromAlpha, pToAlpha, pEntityModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromAlpha
         * @param pToAlpha
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public AlphaModifier(final float pDuration, final float pFromAlpha, final float pToAlpha, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromAlpha, pToAlpha, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pAlphaModifier
         */
        protected AlphaModifier(final AlphaModifier pAlphaModifier) {
		super(pAlphaModifier);
	}

        /**
         * 
         * @return
         */
        @Override
	public AlphaModifier deepCopy(){
		return new AlphaModifier(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pEntity
         * @param pAlpha
         */
        @Override
	protected void onSetInitialValue(final IEntity pEntity, final float pAlpha) {
		pEntity.setAlpha(pAlpha);
	}

        /**
         * 
         * @param pEntity
         * @param pPercentageDone
         * @param pAlpha
         */
        @Override
	protected void onSetValue(final IEntity pEntity, final float pPercentageDone, final float pAlpha) {
		pEntity.setAlpha(pAlpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
