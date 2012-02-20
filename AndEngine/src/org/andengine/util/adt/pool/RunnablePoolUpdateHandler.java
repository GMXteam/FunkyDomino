package org.andengine.util.adt.pool;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Valentin Milea
 * @author Nicolas Gramlich
 * 
 * @since 23:03:58 - 21.08.2010
 */
public abstract class RunnablePoolUpdateHandler<T extends RunnablePoolItem> extends PoolUpdateHandler<T> {
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
    public RunnablePoolUpdateHandler() {

	}

    /**
     * 
     * @param pInitialPoolSize
     */
    public RunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

    /**
     * 
     * @param pInitialPoolSize
     * @param pGrowth
     */
    public RunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

        /**
         * 
         * @param pInitialPoolSize
         * @param pGrowth
         * @param pAvailableItemCountMaximum
         */
        public RunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
		super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
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
	protected abstract T onAllocatePoolItem();

        /**
         * 
         * @param pRunnablePoolItem
         */
        @Override
	protected void onHandlePoolItem(final T pRunnablePoolItem) {
		pRunnablePoolItem.run();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
