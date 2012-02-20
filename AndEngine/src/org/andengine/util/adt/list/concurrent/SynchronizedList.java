package org.andengine.util.adt.list.concurrent;

import org.andengine.util.adt.list.IList;

/**
 * (c) Zynga 2012
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:23:50 - 01.02.2012
 */
public class SynchronizedList<T> implements IList<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected final IList<T> mList;

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     * @param mList
     */
    public SynchronizedList(final IList<T> mList) {
		this.mList = mList;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public synchronized boolean isEmpty() {
		return this.mList.isEmpty();
	}

        /**
         * 
         * @param pIndex
         * @return
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized T get(final int pIndex) throws IndexOutOfBoundsException {
		return this.mList.get(pIndex);
	}

        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized void set(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mList.set(pIndex, pItem);
	}

        /**
         * 
         * @param pItem
         * @return
         */
        @Override
	public synchronized int indexOf(final T pItem) {
		return this.mList.indexOf(pItem);
	}

        /**
         * 
         * @param pItem
         */
        @Override
	public synchronized void add(final T pItem) {
		this.mList.add(pItem);
	}

        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized void add(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mList.add(pIndex, pItem);
	}

        /**
         * 
         * @return
         */
        @Override
	public synchronized T removeFirst() {
		return this.mList.removeFirst();
	}

	@Override
	public synchronized T removeLast() {
		return this.mList.removeLast();
	}

        /**
         * 
         * @param pItem
         * @return
         */
        @Override
	public synchronized boolean remove(final T pItem) {
		return this.mList.remove(pItem);
	}

	@Override
	public synchronized T remove(final int pIndex) throws IndexOutOfBoundsException {
		return this.mList.remove(pIndex);
	}

        /**
         * 
         * @return
         */
        @Override
	public synchronized int size() {
		return this.mList.size();
	}

        /**
         * 
         */
        @Override
	public synchronized void clear() {
		this.mList.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
