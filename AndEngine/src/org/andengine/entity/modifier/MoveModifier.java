package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:12:52 - 19.03.2010
 */
public class MoveModifier extends DoubleValueSpanEntityModifier {
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
     * @param pFromX
     * @param pToX
     * @param pFromY
     * @param pToY
     */
    public MoveModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY) {
		this(pDuration, pFromX, pToX, pFromY, pToY, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromX
         * @param pToX
         * @param pFromY
         * @param pToY
         * @param pEaseFunction
         */
        public MoveModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromX, pToX, pFromY, pToY, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromX
         * @param pToX
         * @param pFromY
         * @param pToY
         * @param pEntityModifierListener
         */
        public MoveModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromX, pToX, pFromY, pToY, pEntityModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromX
         * @param pToX
         * @param pFromY
         * @param pToY
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public MoveModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromX, pToX, pFromY, pToY, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pMoveModifier
         */
        protected MoveModifier(final MoveModifier pMoveModifier) {
		super(pMoveModifier);
	}

	@Override
	public MoveModifier deepCopy(){
		return new MoveModifier(this);
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
         * @param pX
         * @param pY
         */
        @Override
	protected void onSetInitialValues(final IEntity pEntity, final float pX, final float pY) {
		pEntity.setPosition(pX, pY);
	}

        /**
         * 
         * @param pEntity
         * @param pPercentageDone
         * @param pX
         * @param pY
         */
        @Override
	protected void onSetValues(final IEntity pEntity, final float pPercentageDone, final float pX, final float pY) {
		pEntity.setPosition(pX, pY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
