package org.andengine.util.adt.bounds;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 7:42:02 AM - Oct 10, 2011
 */
public interface IIntBounds extends IBounds {
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
    public int getXMin();
        /**
         * 
         * @return
         */
        public int getYMin();
        /**
         * 
         * @return
         */
        public int getXMax();
        /**
         * 
         * @return
         */
        public int getYMax();
}
