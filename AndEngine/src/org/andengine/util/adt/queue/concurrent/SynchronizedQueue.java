package org.andengine.util.adt.queue.concurrent;

import org.andengine.util.adt.queue.IQueue;

/**
 * (c) Zynga 2012
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:23:50 - 01.02.2012
 */
public class SynchronizedQueue<T> implements IQueue<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IQueue<T> mQueue;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pQueue
         */
        public SynchronizedQueue(final IQueue<T> pQueue) {
		this.mQueue = pQueue;
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
		return this.mQueue.isEmpty();
	}

        /**
         * 
         * @param pIndex
         * @return
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized T get(final int pIndex) throws IndexOutOfBoundsException {
		return this.mQueue.get(pIndex);
	}

        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized void set(int pIndex, T pItem) throws IndexOutOfBoundsException {
		this.mQueue.set(pIndex, pItem);
	}

        /**
         * 
         * @param pItem
         * @return
         */
        @Override
	public synchronized int indexOf(final T pItem) {
		return this.mQueue.indexOf(pItem);
	}

	@Override
	public synchronized void add(final T pItem) {
		this.mQueue.add(pItem);
	}

        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized void add(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mQueue.add(pIndex, pItem);
	}

        /**
         * 
         * @return
         */
        @Override
	public synchronized T peek() {
		return this.mQueue.peek();
	}

        /**
         * 
         * @return
         */
        @Override
	public synchronized T poll() {
		return this.mQueue.poll();
	}

        /**
         * 
         * @param pItem
         */
        @Override
	public synchronized void enter(final T pItem) {
		this.mQueue.enter(pItem);
	}

        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized void enter(final int pIndex, final T pItem) throws IndexOutOfBoundsException{
		this.mQueue.enter(pIndex, pItem);
	}

        /**
         * 
         * @return
         */
        @Override
	public synchronized T removeFirst() {
		return this.mQueue.removeFirst();
	}

	@Override
	public synchronized T removeLast() {
		return this.mQueue.removeLast();
	}

        /**
         * 
         * @param pItem
         * @return
         */
        @Override
	public synchronized boolean remove(final T pItem) {
		return this.mQueue.remove(pItem);
	}

        /**
         * 
         * @param pIndex
         * @return
         * @throws IndexOutOfBoundsException
         */
        @Override
	public synchronized T remove(final int pIndex) throws IndexOutOfBoundsException{
		return this.mQueue.remove(pIndex);
	}

        /**
         * 
         * @return
         */
        @Override
	public synchronized int size() {
		return this.mQueue.size();
	}

        /**
         * 
         */
        @Override
	public synchronized void clear() {
		this.mQueue.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
