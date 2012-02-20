package org.andengine.entity.particle.emitter;

import static org.andengine.util.Constants.VERTEX_INDEX_X;
import static org.andengine.util.Constants.VERTEX_INDEX_Y;

import org.andengine.util.math.MathUtils;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:48:00 - 01.10.2010
 */
public class RectangleOutlineParticleEmitter extends BaseRectangleParticleEmitter {
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
     * @param pCenterX
     * @param pCenterY
     * @param pWidth
     * @param pHeight
     */
    public RectangleOutlineParticleEmitter(final float pCenterX, final float pCenterY, final float pWidth, final float pHeight) {
		super(pCenterX, pCenterY, pWidth, pHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    /**
     * 
     * @param pOffset
     */
    @Override
	public void getPositionOffset(final float[] pOffset) {
		pOffset[VERTEX_INDEX_X] = this.mCenterX + MathUtils.randomSign() * this.mWidthHalf;
		pOffset[VERTEX_INDEX_Y] = this.mCenterY + MathUtils.randomSign() * this.mHeightHalf;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
