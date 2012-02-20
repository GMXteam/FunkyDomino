package org.andengine.util.adt.list;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 11:14:45 - 27.01.2012
 */
public interface IFloatList {
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
    public boolean isEmpty();
    /**
     * 
     * @param pIndex
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public float get(final int pIndex) throws ArrayIndexOutOfBoundsException;
        /**
         * 
         * @param pItem
         */
        public void add(final float pItem);
        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws ArrayIndexOutOfBoundsException
         */
        public void add(final int pIndex, final float pItem) throws ArrayIndexOutOfBoundsException;
        /**
         * 
         * @param pIndex
         * @return
         * @throws ArrayIndexOutOfBoundsException
         */
        public float remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
        /**
         * 
         * @return
         */
        public int size();
        /**
         * 
         */
        public void clear();
}