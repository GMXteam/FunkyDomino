package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 10:46:10 - 05.10.2011
 */
public class SkewModifier extends DoubleValueSpanEntityModifier {
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
     * @param pFromSkewX
     * @param pToSkewX
     * @param pFromSkewY
     * @param pToSkewY
     */
    public SkewModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final float pFromSkewY, final float pToSkewY) {
		this(pDuration, pFromSkewX, pToSkewX, pFromSkewY, pToSkewY, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromSkewX
         * @param pToSkewX
         * @param pFromSkewY
         * @param pToSkewY
         * @param pEaseFunction
         */
        public SkewModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final float pFromSkewY, final float pToSkewY, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromSkewX, pToSkewX, pFromSkewY, pToSkewY, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromSkewX
         * @param pToSkewX
         * @param pFromSkewY
         * @param pToSkewY
         * @param pEntityModifierListener
         */
        public SkewModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final float pFromSkewY, final float pToSkewY, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromSkewX, pToSkewX, pFromSkewY, pToSkewY, pEntityModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromSkewX
         * @param pToSkewX
         * @param pFromSkewY
         * @param pToSkewY
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public SkewModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final float pFromSkewY, final float pToSkewY, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromSkewX, pToSkewX, pFromSkewY, pToSkewY, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pSkewXModifier
         */
        protected SkewModifier(final SkewModifier pSkewXModifier) {
		super(pSkewXModifier);
	}

	@Override
	public SkewModifier deepCopy(){
		return new SkewModifier(this);
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
         * @param pSkewX
         * @param pSkewY
         */
        @Override
	protected void onSetInitialValues(final IEntity pEntity, final float pSkewX, final float pSkewY) {
		pEntity.setSkew(pSkewX, pSkewY);
	}

        /**
         * 
         * @param pEntity
         * @param pPercentageDone
         * @param pSkewX
         * @param pSkewY
         */
        @Override
	protected void onSetValues(final IEntity pEntity, final float pPercentageDone, final float pSkewX, final float pSkewY) {
		pEntity.setSkew(pSkewX, pSkewY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
