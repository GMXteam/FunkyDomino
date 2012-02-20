package org.andengine.extension.tmx;

import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:39:48 - 05.08.2010
 */
public class TMXTile {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	int mGlobalTileID;
	private final int mTileRow;
	private final int mTileColumn;
	private final int mTileWidth;
	private final int mTileHeight;
	ITextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pGlobalTileID
         * @param pTileColumn
         * @param pTileRow
         * @param pTileWidth
         * @param pTileHeight
         * @param pTextureRegion
         */
        public TMXTile(final int pGlobalTileID, final int pTileColumn, final int pTileRow, final int pTileWidth, final int pTileHeight, final ITextureRegion pTextureRegion) {
		this.mGlobalTileID = pGlobalTileID;
		this.mTileRow = pTileRow;
		this.mTileColumn = pTileColumn;
		this.mTileWidth = pTileWidth;
		this.mTileHeight = pTileHeight;
		this.mTextureRegion = pTextureRegion;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public int getGlobalTileID() {
		return this.mGlobalTileID;
	}

        /**
         * 
         * @return
         */
        public int getTileRow() {
		return this.mTileRow;
	}

        /**
         * 
         * @return
         */
        public int getTileColumn() {
		return this.mTileColumn;
	}

        /**
         * 
         * @return
         */
        public int getTileX() {
		return this.mTileColumn * this.mTileWidth;
	}

        /**
         * 
         * @return
         */
        public int getTileY() {
		return this.mTileRow * this.mTileHeight;
	}

        /**
         * 
         * @return
         */
        public int getTileWidth() {
		return this.mTileWidth;
	}

        /**
         * 
         * @return
         */
        public int getTileHeight() {
		return this.mTileHeight;
	}

        /**
         * 
         * @return
         */
        public ITextureRegion getTextureRegion() {
		return this.mTextureRegion;
	}

	/**
	 * Note this will also set the {@link ITextureRegion} with the associated pGlobalTileID of the {@link TMXTiledMap}.
	 * @param pTMXTiledMap
	 * @param pGlobalTileID
	 */
	public void setGlobalTileID(final TMXTiledMap pTMXTiledMap, final int pGlobalTileID) {
		this.mGlobalTileID = pGlobalTileID;
		this.mTextureRegion = pTMXTiledMap.getTextureRegionFromGlobalTileID(pGlobalTileID);
	}

	/**
	 * You'd probably want to call {@link TMXTile#setGlobalTileID(TMXTiledMap, int)} instead.
	 * @param pTextureRegion
	 */
	public void setTextureRegion(final ITextureRegion pTextureRegion) {
		this.mTextureRegion = pTextureRegion;
	}

        /**
         * 
         * @param pTMXTiledMap
         * @return
         */
        public TMXProperties<TMXTileProperty> getTMXTileProperties(final TMXTiledMap pTMXTiledMap) {
		return pTMXTiledMap.getTMXTileProperties(this.mGlobalTileID);
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
