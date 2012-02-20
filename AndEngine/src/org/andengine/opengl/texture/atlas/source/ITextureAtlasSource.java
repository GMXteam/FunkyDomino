package org.andengine.opengl.texture.atlas.source;


/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 11:46:56 - 12.07.2011
 */
public interface ITextureAtlasSource {
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
    public int getTextureX();
        /**
         * 
         * @return
         */
        public int getTextureY();
        /**
         * 
         * @param pTextureX
         */
        public void setTextureX(final int pTextureX);
        /**
         * 
         * @param pTextureY
         */
        public void setTextureY(final int pTextureY);

        /**
         * 
         * @return
         */
        public int getTextureWidth();
        /**
         * 
         * @return
         */
        public int getTextureHeight();
        /**
         * 
         * @param pTextureWidth
         */
        public void setTextureWidth(final int pTextureWidth);
        /**
         * 
         * @param pTextureHeight
         */
        public void setTextureHeight(final int pTextureHeight);

        /**
         * 
         * @return
         */
        public ITextureAtlasSource deepCopy();
}