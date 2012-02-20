package org.andengine.util.adt.pool;

import java.util.ArrayList;
import java.util.Collections;

import org.andengine.util.debug.Debug;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Valentin Milea
 * @author Nicolas Gramlich
 * 
 * @since 22:19:55 - 31.08.2010
 */
public abstract class GenericPool<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<T> mAvailableItems;
	private final int mGrowth;
	private final int mAvailableItemCountMaximum;

	private int mUnrecycledItemCount;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public GenericPool() {
		this(0);
	}

        /**
         * 
         * @param pInitialSize
         */
        public GenericPool(final int pInitialSize) {
		this(pInitialSize, 1);
	}

        /**
         * 
         * @param pInitialSize
         * @param pGrowth
         */
        public GenericPool(final int pInitialSize, final int pGrowth) {
		this(pInitialSize, pGrowth, Integer.MAX_VALUE);
	}

        /**
         * 
         * @param pInitialSize
         * @param pGrowth
         * @param pAvailableItemsMaximum
         */
        public GenericPool(final int pInitialSize, final int pGrowth, final int pAvailableItemsMaximum) {
		if(pGrowth <= 0) {
			throw new IllegalArgumentException("pGrowth must be greater than 0!");
		}
		if(pAvailableItemsMaximum < 0) {
			throw new IllegalArgumentException("pAvailableItemsMaximum must be at least 0!");
		}

		this.mGrowth = pGrowth;
		this.mAvailableItemCountMaximum = pAvailableItemsMaximum;
		this.mAvailableItems = new ArrayList<T>(pInitialSize);

		if(pInitialSize > 0) {
			this.batchAllocatePoolItems(pInitialSize);
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public synchronized int getUnrecycledItemCount() {
		return this.mUnrecycledItemCount;
	}

        /**
         * 
         * @return
         */
        public synchronized int getAvailableItemCount() {
		return this.mAvailableItems.size();
	}

        /**
         * 
         * @return
         */
        public int getAvailableItemCountMaximum() {
		return this.mAvailableItemCountMaximum;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        protected abstract T onAllocatePoolItem();

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pItem every item passes this method just before it gets recycled.
	 */
	protected void onHandleRecycleItem(final T pItem) {

	}

        /**
         * 
         * @return
         */
        protected T onHandleAllocatePoolItem() {
		return this.onAllocatePoolItem();
	}

	/**
	 * @param pItem every item that was just obtained from the pool, passes this method.
	 */
	protected void onHandleObtainItem(final T pItem) {

	}

        /**
         * 
         * @param pCount
         */
        public synchronized void batchAllocatePoolItems(final int pCount) {
		final ArrayList<T> availableItems = this.mAvailableItems;

		int allocationCount = this.mAvailableItemCountMaximum - availableItems.size();
		if(pCount < allocationCount) {
			allocationCount = pCount;
		}

		for(int i = allocationCount - 1; i >= 0; i--) {
			availableItems.add(this.onHandleAllocatePoolItem());
		}
	}

        /**
         * 
         * @return
         */
        public synchronized T obtainPoolItem() {
		final T item;

		if(this.mAvailableItems.size() > 0) {
			item = this.mAvailableItems.remove(this.mAvailableItems.size() - 1);
		} else {
			if(this.mGrowth == 1 || this.mAvailableItemCountMaximum == 0) {
				item = this.onHandleAllocatePoolItem();
			} else {
				this.batchAllocatePoolItems(this.mGrowth);
				item = this.mAvailableItems.remove(this.mAvailableItems.size() - 1);
			}
//			if(BuildConfig.DEBUG) { // TODO Bring back when ADT-17 is released
				Debug.v(this.getClass().getName() + "<" + item.getClass().getSimpleName() +"> was exhausted, with " + this.mUnrecycledItemCount + " item not yet recycled. Allocated " + this.mGrowth + " more.");
//			}
		}
		this.onHandleObtainItem(item);

		this.mUnrecycledItemCount++;
		return item;
	}

        /**
         * 
         * @param pItem
         */
        public synchronized void recyclePoolItem(final T pItem) {
		if(pItem == null) {
			throw new IllegalArgumentException("Cannot recycle null item!");
		}

		this.onHandleRecycleItem(pItem);

		if(this.mAvailableItems.size() < this.mAvailableItemCountMaximum) {
			this.mAvailableItems.add(pItem);
		}

		this.mUnrecycledItemCount--;

		if(this.mUnrecycledItemCount < 0) {
			Debug.e("More items recycled than obtained!");
		}
	}

        /**
         * 
         */
        public synchronized void shufflePoolItems() {
		Collections.shuffle(this.mAvailableItems);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
