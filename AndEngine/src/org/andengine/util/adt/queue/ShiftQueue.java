package org.andengine.util.adt.queue;

import org.andengine.util.adt.list.ShiftList;

/**
 * (c) Zynga 2012
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 17:06:49 - 03.01.2012
 */
public class ShiftQueue<T> extends ShiftList<T> implements IQueue<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     */
    public ShiftQueue() {
		super();
	}

        /**
         * 
         * @param pInitialCapacity
         */
        public ShiftQueue(final int pInitialCapacity) {
		super(pInitialCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public T peek() {
		if(this.isEmpty()) {
			return null;
		} else {
			return this.get(0);
		}
	}

        /**
         * 
         * @return
         */
        @Override
	public T poll() {
		if(this.isEmpty()) {
			return null;
		} else {
			return this.remove(0);
		}
	}

        /**
         * 
         * @param pItem
         */
        @Override
	public void enter(final T pItem) {
		this.add(pItem);
	}

        /**
         * 
         * @param pIndex
         * @param pItem
         * @throws IndexOutOfBoundsException
         */
        @Override
	public void enter(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.add(pIndex, pItem);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
