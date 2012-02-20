package org.andengine.opengl.texture.region;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 23:31:52 - 08.08.2011
 */
public interface ITiledTextureRegion extends ITextureRegion {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @return
     */
    public int getCurrentTileIndex();
    /**
     * 
     * @param pCurrentTileIndex
     */
    public void setCurrentTileIndex(final int pCurrentTileIndex);
        /**
         * 
         */
        public void nextTile();
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public ITextureRegion getTextureRegion(final int pTileIndex);
        /**
         * 
         * @return
         */
        public int getTileCount();

        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getTextureX(final int pTileIndex);
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getTextureY(final int pTileIndex);

        /**
         * 
         * @param pTileIndex
         * @param pTextureX
         */
        public void setTextureX(final int pTileIndex, final float pTextureX);
        /**
         * 
         * @param pTileIndex
         * @param pTextureY
         */
        public void setTextureY(final int pTileIndex, final float pTextureY);
        /**
         * 
         * @param pTileIndex
         * @param pTextureX
         * @param pTextureY
         */
        public void setTexturePosition(final int pTileIndex, final float pTextureX, final float pTextureY);

	/**
	 * Note: Takes {@link ITiledTextureRegion#getScale(int)} into account!
         * 
         * @param pTileIndex 
         * @return 
         */
	public float getWidth(final int pTileIndex);
	/**
	 * Note: Takes {@link ITiledTextureRegion#getScale(int)} into account!
         * 
         * @param pTileIndex
         * @return  
         */
	public float getHeight(final int pTileIndex);

        /**
         * 
         * @param pTileIndex
         * @param pWidth
         */
        public void setTextureWidth(final int pTileIndex, final float pWidth);
        /**
         * 
         * @param pTileIndex
         * @param pHeight
         */
        public void setTextureHeight(final int pTileIndex, final float pHeight);
        /**
         * 
         * @param pTileIndex
         * @param pWidth
         * @param pHeight
         */
        public void setTextureSize(final int pTileIndex, final float pWidth, final float pHeight);

        /**
         * 
         * @param pTileIndex
         * @param pTextureX
         * @param pTextureY
         * @param pTextureWidth
         * @param pTextureHeight
         */
        public void set(final int pTileIndex, final float pTextureX, final float pTextureY, final float pTextureWidth, final float pTextureHeight);

        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getU(final int pTileIndex);
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getU2(final int pTileIndex);
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getV(final int pTileIndex);
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getV2(final int pTileIndex);

        /**
         * 
         * @param pTileIndex
         * @return
         */
        public boolean isScaled(final int pTileIndex);
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public float getScale(final int pTileIndex);
        /**
         * 
         * @param pTileIndex
         * @return
         */
        public boolean isRotated(final int pTileIndex);

        /**
         * 
         * @return
         */
        @Override
	public ITiledTextureRegion deepCopy();
}
