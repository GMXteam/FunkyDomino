package org.andengine.util.adt.bounds;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 7:42:02 AM - Oct 10, 2011
 */
public interface IFloatBounds extends IBounds {
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
    public float getXMin();
        /**
         * 
         * @return
         */
        public float getYMin();
        /**
         * 
         * @return
         */
        public float getXMax();
        /**
         * 
         * @return
         */
        public float getYMax();
}
