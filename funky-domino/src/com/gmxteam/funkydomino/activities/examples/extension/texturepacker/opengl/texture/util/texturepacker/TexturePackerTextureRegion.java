package org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker;

import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

/**
 * 
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 16:28:48 - 15.08.2011
 */
public class TexturePackerTextureRegion extends TextureRegion {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int mSourceX;
	private final int mSourceY;
	private final int mSourceWidth;
	private final int mSourceHeight;
	private final boolean mRotated;
	private final boolean mTrimmed;
	private final String mSource;
	private final int mID;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pTexture
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pID
         * @param pSource
         * @param pRotated
         * @param pTrimmed
         * @param pSourceX
         * @param pSourceY
         * @param pSourceWidth
         * @param pSourceHeight
         */
        public TexturePackerTextureRegion(final ITexture pTexture, final int pX, final int pY, final int pWidth, final int pHeight, final int pID, final String pSource, final boolean pRotated, final boolean pTrimmed, final int pSourceX, final int pSourceY, final int pSourceWidth, final int pSourceHeight) {
		super(pTexture, pX, pY, pWidth, pHeight);
		this.mID = pID;
		this.mSource = pSource;
		this.mRotated = pRotated;
		this.mTrimmed = pTrimmed;
		this.mSourceX = pSourceX;
		this.mSourceY = pSourceY;
		this.mSourceWidth = pSourceWidth;
		this.mSourceHeight = pSourceHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public int getID() {
		return this.mID;
	}

        /**
         * 
         * @return
         */
        public String getSource() {
		return this.mSource;
	}

        /**
         * 
         * @return
         */
        public boolean isRotated() {
		return this.mRotated;
	}

        /**
         * 
         * @return
         */
        public boolean isTrimmed() {
		return this.mTrimmed;
	}

        /**
         * 
         * @return
         */
        public int getSourceX() {
		return this.mSourceX;
	}

        /**
         * 
         * @return
         */
        public int getSourceY() {
		return this.mSourceY;
	}

        /**
         * 
         * @return
         */
        public int getSourceWidth() {
		return this.mSourceWidth;
	}

        /**
         * 
         * @return
         */
        public int getSourceHeight() {
		return this.mSourceHeight;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
