package org.andengine.util.adt.list;

/**
 * (c) Zynga 2012
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 15:27:16 - 01.02.2012
 */
public interface IList<T> {
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
     * @throws IndexOutOfBoundsException
     */
    public T get(final int pIndex) throws IndexOutOfBoundsException;
        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        public void set(final int pIndex, final T pItem) throws IndexOutOfBoundsException;
        /**
         * 
         * @param pItem
         * @return
         */
        public int indexOf(final T pItem);
        /**
         * 
         * @param pItem
         */
        public void add(final T pItem);
        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        public void add(final int pIndex, final T pItem) throws IndexOutOfBoundsException;
        /**
         * 
         * @param pItem
         * @return
         */
        public boolean remove(final T pItem);
        /**
         * 
         * @return
         */
        public T removeFirst();
        /**
         * 
         * @return
         */
        public T removeLast();
        /**
         * 
         * @param pIndex
         * @return
         * @throws IndexOutOfBoundsException
         */
        public T remove(final int pIndex) throws IndexOutOfBoundsException;
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
