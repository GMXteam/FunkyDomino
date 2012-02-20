package org.andengine.util.adt.trie;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 12:18:44 - 30.01.2012
 */
public interface ITrie {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pCharSequence
     */
    public void add(final CharSequence pCharSequence);
    /**
     * 
     * @param pCharSequence
     * @param pStart
     * @param pEnd
     */
    public void add(final CharSequence pCharSequence, final int pStart, final int pEnd);
        /**
         * 
         * @param pCharSequence
         * @return
         */
        public boolean contains(final CharSequence pCharSequence);
        /**
         * 
         * @param pCharSequence
         * @param pStart
         * @param pEnd
         * @return
         */
        public boolean contains(final CharSequence pCharSequence, final int pStart, final int pEnd);
	/* TODO public void clear(); */
	/* TODO public boolean remove(final CharSequence pCharSequence); */
	/* TODO public boolean remove(final CharSequence pCharSequence, final int pStart, final int pEnd); */
}
