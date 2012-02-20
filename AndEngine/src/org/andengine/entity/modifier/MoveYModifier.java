package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 12:04:21 - 30.08.2010
 */
public class MoveYModifier extends SingleValueSpanEntityModifier {
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
     * @param pFromY
     * @param pToY
     */
    public MoveYModifier(final float pDuration, final float pFromY, final float pToY) {
		this(pDuration, pFromY, pToY, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromY
         * @param pToY
         * @param pEaseFunction
         */
        public MoveYModifier(final float pDuration, final float pFromY, final float pToY, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromY, pToY, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromY
         * @param pToY
         * @param pEntityModifierListener
         */
        public MoveYModifier(final float pDuration, final float pFromY, final float pToY, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromY, pToY, pEntityModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromY
         * @param pToY
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public MoveYModifier(final float pDuration, final float pFromY, final float pToY, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromY, pToY, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pMoveYModifier
         */
        protected MoveYModifier(final MoveYModifier pMoveYModifier) {
		super(pMoveYModifier);
	}

	@Override
	public MoveYModifier deepCopy(){
		return new MoveYModifier(this);
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
         * @param pY
         */
        @Override
	protected void onSetInitialValue(final IEntity pEntity, final float pY) {
		pEntity.setPosition(pEntity.getX(), pY);
	}

        /**
         * 
         * @param pEntity
         * @param pPercentageDone
         * @param pY
         */
        @Override
	protected void onSetValue(final IEntity pEntity, final float pPercentageDone, final float pY) {
		pEntity.setPosition(pEntity.getX(), pY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
