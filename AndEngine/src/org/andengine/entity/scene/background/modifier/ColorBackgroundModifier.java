package org.andengine.entity.scene.background.modifier;

import org.andengine.entity.scene.background.IBackground;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.BaseTripleValueSpanModifier;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 14:51:03 - 03.09.2010
 */
public class ColorBackgroundModifier extends BaseTripleValueSpanModifier<IBackground> implements IBackgroundModifier {
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
     * @param pFromColor
     * @param pToColor
     */
    public ColorBackgroundModifier(final float pDuration, final Color pFromColor, final Color pToColor) {
		this(pDuration, pFromColor.getRed(), pToColor.getRed(), pFromColor.getGreen(), pToColor.getGreen(), pFromColor.getBlue(), pToColor.getBlue(), null, EaseLinear.getInstance());
	}

    /**
     * 
     * @param pDuration
     * @param pFromRed
     * @param pToRed
     * @param pFromGreen
     * @param pToGreen
     * @param pFromBlue
     * @param pToBlue
     */
    public ColorBackgroundModifier(final float pDuration, final float pFromRed, final float pToRed, final float pFromGreen, final float pToGreen, final float pFromBlue, final float pToBlue) {
		this(pDuration, pFromRed, pToRed, pFromGreen, pToGreen, pFromBlue, pToBlue, null, EaseLinear.getInstance());
	}

    /**
     * 
     * @param pDuration
     * @param pFromColor
     * @param pToColor
     * @param pEaseFunction
     */
    public ColorBackgroundModifier(final float pDuration, final Color pFromColor, final Color pToColor, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromColor.getRed(), pToColor.getRed(), pFromColor.getGreen(), pToColor.getGreen(), pFromColor.getBlue(), pToColor.getBlue(), null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromRed
         * @param pToRed
         * @param pFromGreen
         * @param pToGreen
         * @param pFromBlue
         * @param pToBlue
         * @param pEaseFunction
         */
        public ColorBackgroundModifier(final float pDuration, final float pFromRed, final float pToRed, final float pFromGreen, final float pToGreen, final float pFromBlue, final float pToBlue, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromRed, pToRed, pFromGreen, pToGreen, pFromBlue, pToBlue, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromColor
         * @param pToColor
         * @param pBackgroundModifierListener
         */
        public ColorBackgroundModifier(final float pDuration, final Color pFromColor, final Color pToColor, final IBackgroundModifierListener pBackgroundModifierListener) {
		super(pDuration, pFromColor.getRed(), pToColor.getRed(), pFromColor.getGreen(), pToColor.getGreen(), pFromColor.getBlue(), pToColor.getBlue(), pBackgroundModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromRed
         * @param pToRed
         * @param pFromGreen
         * @param pToGreen
         * @param pFromBlue
         * @param pToBlue
         * @param pBackgroundModifierListener
         */
        public ColorBackgroundModifier(final float pDuration, final float pFromRed, final float pToRed, final float pFromGreen, final float pToGreen, final float pFromBlue, final float pToBlue, final IBackgroundModifierListener pBackgroundModifierListener) {
		super(pDuration, pFromRed, pToRed, pFromGreen, pToGreen, pFromBlue, pToBlue, pBackgroundModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pFromColor
         * @param pToColor
         * @param pBackgroundModifierListener
         * @param pEaseFunction
         */
        public ColorBackgroundModifier(final float pDuration, final Color pFromColor, final Color pToColor, final IBackgroundModifierListener pBackgroundModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromColor.getRed(), pToColor.getRed(), pFromColor.getGreen(), pToColor.getGreen(), pFromColor.getBlue(), pToColor.getBlue(), pBackgroundModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pFromRed
         * @param pToRed
         * @param pFromGreen
         * @param pToGreen
         * @param pFromBlue
         * @param pToBlue
         * @param pBackgroundModifierListener
         * @param pEaseFunction
         */
        public ColorBackgroundModifier(final float pDuration, final float pFromRed, final float pToRed, final float pFromGreen, final float pToGreen, final float pFromBlue, final float pToBlue, final IBackgroundModifierListener pBackgroundModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromRed, pToRed, pFromGreen, pToGreen, pFromBlue, pToBlue, pBackgroundModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pColorBackgroundModifier
         */
        protected ColorBackgroundModifier(final ColorBackgroundModifier pColorBackgroundModifier) {
		super(pColorBackgroundModifier);
	}

	@Override
	public ColorBackgroundModifier deepCopy(){
		return new ColorBackgroundModifier(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pBackground
         * @param pRed
         * @param pGreen
         * @param pBlue
         */
        @Override
	protected void onSetInitialValues(final IBackground pBackground, final float pRed, final float pGreen, final float pBlue) {
		pBackground.setColor(pRed, pGreen, pBlue);
	}

        /**
         * 
         * @param pBackground
         * @param pPerctentageDone
         * @param pRed
         * @param pGreen
         * @param pBlue
         */
        @Override
	protected void onSetValues(final IBackground pBackground, final float pPerctentageDone, final float pRed, final float pGreen, final float pBlue) {
		pBackground.setColor(pRed, pGreen, pBlue);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
