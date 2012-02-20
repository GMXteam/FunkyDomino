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
public class SkewXModifier extends SingleValueSpanEntityModifier {
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
     */
    public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX) {
		this(pDuration, pFromSkewX, pToSkewX, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromSkewX
         * @param pToSkewX
         * @param pEaseFunction
         */
        public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromSkewX, pToSkewX, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromSkewX
         * @param pToSkewX
         * @param pEntityModifierListener
         */
        public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromSkewX, pToSkewX, pEntityModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromSkewX
         * @param pToSkewX
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromSkewX, pToSkewX, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pSkewXModifier
         */
        protected SkewXModifier(final SkewXModifier pSkewXModifier) {
		super(pSkewXModifier);
	}

	@Override
	public SkewXModifier deepCopy(){
		return new SkewXModifier(this);
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
         */
        @Override
	protected void onSetInitialValue(final IEntity pEntity, final float pSkewX) {
		pEntity.setSkewX(pSkewX);
	}

	@Override
	protected void onSetValue(final IEntity pEntity, final float pPercentageDone, final float pSkewX) {
		pEntity.setSkewX(pSkewX);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
