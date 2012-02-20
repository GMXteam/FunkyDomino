package org.andengine.entity.scene.background.modifier;

import org.andengine.entity.scene.background.IBackground;
import org.andengine.util.modifier.LoopModifier;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:03:53 - 03.09.2010
 */
public class LoopBackgroundModifier extends LoopModifier<IBackground> implements IBackgroundModifier {
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
     * @param pBackgroundModifier
     */
    public LoopBackgroundModifier(final IBackgroundModifier pBackgroundModifier) {
		super(pBackgroundModifier);
	}

        /**
         * 
         * @param pBackgroundModifier
         * @param pLoopCount
         */
        public LoopBackgroundModifier(final IBackgroundModifier pBackgroundModifier, final int pLoopCount) {
		super(pBackgroundModifier, pLoopCount);
	}

        /**
         * 
         * @param pBackgroundModifier
         * @param pLoopCount
         * @param pLoopModifierListener
         */
        public LoopBackgroundModifier(final IBackgroundModifier pBackgroundModifier, final int pLoopCount, final ILoopBackgroundModifierListener pLoopModifierListener) {
		super(pBackgroundModifier, pLoopCount, pLoopModifierListener, (IBackgroundModifierListener)null);
	}

        /**
         * 
         * @param pBackgroundModifier
         * @param pLoopCount
         * @param pBackgroundModifierListener
         */
        public LoopBackgroundModifier(final IBackgroundModifier pBackgroundModifier, final int pLoopCount, final IBackgroundModifierListener pBackgroundModifierListener) {
		super(pBackgroundModifier, pLoopCount, pBackgroundModifierListener);
	}

        /**
         * 
         * @param pBackgroundModifier
         * @param pLoopCount
         * @param pLoopModifierListener
         * @param pBackgroundModifierListener
         */
        public LoopBackgroundModifier(final IBackgroundModifier pBackgroundModifier, final int pLoopCount, final ILoopBackgroundModifierListener pLoopModifierListener, final IBackgroundModifierListener pBackgroundModifierListener) {
		super(pBackgroundModifier, pLoopCount, pLoopModifierListener, pBackgroundModifierListener);
	}

        /**
         * 
         * @param pLoopBackgroundModifier
         * @throws org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException
         */
        protected LoopBackgroundModifier(final LoopBackgroundModifier pLoopBackgroundModifier) throws DeepCopyNotSupportedException {
		super(pLoopBackgroundModifier);
	}

	@Override
	public LoopBackgroundModifier deepCopy() throws DeepCopyNotSupportedException {
		return new LoopBackgroundModifier(this);
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

        /**
         * 
         */
        public interface ILoopBackgroundModifierListener extends ILoopModifierListener<IBackground> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
}
