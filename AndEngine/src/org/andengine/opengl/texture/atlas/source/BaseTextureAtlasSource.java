package org.andengine.opengl.texture.atlas.source;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:55:12 - 12.07.2011
 */
public abstract class BaseTextureAtlasSource implements ITextureAtlasSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected int mTextureX;
        /**
         * 
         */
        protected int mTextureY;
        /**
         * 
         */
        protected int mTextureWidth;
        /**
         * 
         */
        protected int mTextureHeight;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pTextureX
         * @param pTextureY
         * @param pTextureWidth
         * @param pTextureHeight
         */
        public BaseTextureAtlasSource(final int pTextureX, final int pTextureY, final int pTextureWidth, final int pTextureHeight) {
		this.mTextureX = pTextureX;
		this.mTextureY = pTextureY;
		this.mTextureWidth = pTextureWidth;
		this.mTextureHeight = pTextureHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public int getTextureX() {
		return this.mTextureX;
	}

        /**
         * 
         * @return
         */
        @Override
	public int getTextureY() {
		return this.mTextureY;
	}

        /**
         * 
         * @param pTextureX
         */
        @Override
	public void setTextureX(final int pTextureX) {
		this.mTextureX = pTextureX;
	}

        /**
         * 
         * @param pTextureY
         */
        @Override
	public void setTextureY(final int pTextureY) {
		this.mTextureY = pTextureY;
	}

        /**
         * 
         * @return
         */
        @Override
	public int getTextureWidth() {
		return this.mTextureWidth;
	}

        /**
         * 
         * @return
         */
        @Override
	public int getTextureHeight() {
		return this.mTextureHeight;
	}

        /**
         * 
         * @param pTextureWidth
         */
        @Override
	public void setTextureWidth(final int pTextureWidth) {
		this.mTextureWidth = pTextureWidth;
	}

        /**
         * 
         * @param pTextureHeight
         */
        @Override
	public void setTextureHeight(final int pTextureHeight) {
		this.mTextureHeight = pTextureHeight;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public String toString() {
		return this.getClass().getSimpleName() + "( " + this.getTextureWidth() + "x" + this.getTextureHeight() + " @ "+ this.mTextureX + "/" + this.mTextureY + " )";
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}