package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 12:03:22 - 30.08.2010
 */
public class MoveXModifier extends SingleValueSpanEntityModifier {
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
     */
    public MoveXModifier(final float pDuration, final float pFromX, final float pToX) {
		this(pDuration, pFromX, pToX, null, EaseLinear.getInstance());
	}

    /**
     * 
     * @param pDuration
     * @param pFromX
     * @param pToX
     * @param pEaseFunction
     */
    public MoveXModifier(final float pDuration, final float pFromX, final float pToX, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromX, pToX, null, pEaseFunction);
	}

    /**
     * 
     * @param pDuration
     * @param pFromX
     * @param pToX
     * @param pEntityModifierListener
     */
    public MoveXModifier(final float pDuration, final float pFromX, final float pToX, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromX, pToX, pEntityModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromX
         * @param pToX
         * @param pEntityModifierListener
         * @param pEaseFunction
         */
        public MoveXModifier(final float pDuration, final float pFromX, final float pToX, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromX, pToX, pEntityModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pMoveXModifier
         */
        protected MoveXModifier(final MoveXModifier pMoveXModifier) {
		super(pMoveXModifier);
	}

	@Override
	public MoveXModifier deepCopy(){
		return new MoveXModifier(this);
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
         */
        @Override
	protected void onSetInitialValue(final IEntity pEntity, final float pX) {
		pEntity.setPosition(pX, pEntity.getY());
	}

        /**
         * 
         * @param pEntity
         * @param pPercentageDone
         * @param pX
         */
        @Override
	protected void onSetValue(final IEntity pEntity, final float pPercentageDone, final float pX) {
		pEntity.setPosition(pX, pEntity.getY());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
