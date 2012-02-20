package org.andengine.entity.shape;


/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 19:01:16 - 07.08.2011
 */
public interface IAreaShape extends IShape {
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
    public float getWidth();
        /**
         * 
         * @return
         */
        public float getBaseWidth();
        /**
         * 
         * @return
         */
        public float getHeight();
        /**
         * 
         * @return
         */
        public float getBaseHeight();

        /**
         * 
         * @return
         */
        public float getWidthScaled();
        /**
         * 
         * @return
         */
        public float getHeightScaled();

        /**
         * 
         * @param pHeight
         */
        public void setHeight(final float pHeight);
        /**
         * 
         * @param pWidth
         */
        public void setWidth(final float pWidth);
        /**
         * 
         * @param pWidth
         * @param pHeight
         */
        public void setSize(final float pWidth, final float pHeight);
}
