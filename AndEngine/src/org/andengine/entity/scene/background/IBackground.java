package org.andengine.entity.scene.background;

import org.andengine.engine.handler.IDrawHandler;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:47:41 - 19.07.2010
 */
public interface IBackground extends IDrawHandler, IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pBackgroundModifier
     */
    public void registerBackgroundModifier(final IModifier<IBackground> pBackgroundModifier);
    /**
     * 
     * @param pBackgroundModifier
     * @return
     */
    public boolean unregisterBackgroundModifier(final IModifier<IBackground> pBackgroundModifier);
        /**
         * 
         */
        public void clearBackgroundModifiers();

        /**
         * 
         * @return
         */
        public boolean isColorEnabled();
        /**
         * 
         * @param pColorEnabled
         */
        public void setColorEnabled(final boolean pColorEnabled);

        /**
         * 
         * @param pColor
         */
        public void setColor(final Color pColor);
        /**
         * 
         * @param pRed
         * @param pGreen
         * @param pBlue
         */
        public void setColor(final float pRed, final float pGreen, final float pBlue);
        /**
         * 
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha);
}
