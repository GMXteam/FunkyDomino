package org.anddev.andengine.examples.game.snake.entity;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 17:44:59 - 09.07.2010
 */
public class SnakeTailPart extends CellEntity implements Cloneable {
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
     * @param pSnakeHead
     * @param pTextureRegion
     */
    public SnakeTailPart(final SnakeHead pSnakeHead, final TextureRegion pTextureRegion) {
		this(pSnakeHead.mCellX, pSnakeHead.mCellY, pTextureRegion);
	}

    /**
     * 
     * @param pCellX
     * @param pCellY
     * @param pTextureRegion
     */
    public SnakeTailPart(final int pCellX, final int pCellY, final TextureRegion pTextureRegion) {
		super(pCellX, pCellY, CELL_WIDTH, CELL_HEIGHT, pTextureRegion);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        protected SnakeTailPart deepCopy() {
		return new SnakeTailPart(this.mCellX, this.mCellY, this.getTextureRegion());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
