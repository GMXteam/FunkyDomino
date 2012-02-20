package org.andengine.opengl.texture.region;

import org.andengine.opengl.texture.ITexture;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 20:07:17 - 07.08.2011
 */
public interface ITextureRegion {
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
    public float getTextureX();
    /**
     * 
     * @return
     */
    public float getTextureY();

        /**
         * 
         * @param pTextureX
         */
        public void setTextureX(final float pTextureX);
        /**
         * 
         * @param pTextureY
         */
        public void setTextureY(final float pTextureY);
        /**
         * 
         * @param pTextureX
         * @param pTextureY
         */
        public void setTexturePosition(final float pTextureX, final float pTextureY);

	/**
	 * Note: Takes {@link ITextureRegion#getScale()} into account!
         * 
         * @return 
         */
	public float getWidth();
	/**
	 * Note: Takes {@link ITextureRegion#getScale()} into account!
         * 
         * @return 
         */
	public float getHeight();

        /**
         * 
         * @param pTextureWidth
         */
        public void setTextureWidth(final float pTextureWidth);
        /**
         * 
         * @param pTextureHeight
         */
        public void setTextureHeight(final float pTextureHeight);
        /**
         * 
         * @param pTextureWidth
         * @param pTextureHeight
         */
        public void setTextureSize(final float pTextureWidth, final float pTextureHeight);

        /**
         * 
         * @param pTextureX
         * @param pTextureY
         * @param pTextureWidth
         * @param pTextureHeight
         */
        public void set(final float pTextureX, final float pTextureY, final float pTextureWidth, final float pTextureHeight);

        /**
         * 
         * @return
         */
        public float getU();
        /**
         * 
         * @return
         */
        public float getU2();
        /**
         * 
         * @return
         */
        public float getV();
        /**
         * 
         * @return
         */
        public float getV2();

        /**
         * 
         * @return
         */
        public boolean isScaled();
        /**
         * 
         * @return
         */
        public float getScale();
        /**
         * 
         * @return
         */
        public boolean isRotated();

        /**
         * 
         * @return
         */
        public ITexture getTexture();

        /**
         * 
         * @return
         */
        public ITextureRegion deepCopy();
}