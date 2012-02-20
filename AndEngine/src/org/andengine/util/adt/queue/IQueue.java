package org.andengine.util.adt.queue;

import org.andengine.util.adt.list.IList;

/**
 * (c) Zynga 2012
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 15:00:30 - 24.01.2012
 */
public interface IQueue<T> extends IList<T> {
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
    public T peek();
    /**
     * 
     * @return
     */
    public T poll();
        /**
         * 
         * @param pItem
         */
        public void enter(final T pItem);
        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        public void enter(final int pIndex, final T pItem) throws IndexOutOfBoundsException;
}
