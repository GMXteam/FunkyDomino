package org.andengine.util.adt.map;

import android.util.SparseArray;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 11:51:29 - 20.08.2010
 * @param <T>
 */
public class Library<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected final SparseArray<T> mItems;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public Library() {
		this.mItems = new SparseArray<T>();
	}

        /**
         * 
         * @param pInitialCapacity
         */
        public Library(final int pInitialCapacity) {
		this.mItems = new SparseArray<T>(pInitialCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @param pID
         * @param pItem
         */
        public void put(final int pID, final T pItem) {
		final T existingItem = this.mItems.get(pID);
		if(existingItem == null) {
			this.mItems.put(pID, pItem);
		} else {
			throw new IllegalArgumentException("ID: '" + pID + "' is already associated with item: '" + existingItem.toString() + "'.");
		}
	}

        /**
         * 
         * @param pID
         */
        public void remove(final int pID) {
		this.mItems.remove(pID);
	}

        /**
         * 
         * @param pID
         * @return
         */
        public T get(final int pID) {
		return this.mItems.get(pID);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
