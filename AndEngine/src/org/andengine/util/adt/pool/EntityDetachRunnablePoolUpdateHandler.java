package org.andengine.util.adt.pool;

import org.andengine.entity.IEntity;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 23:16:25 - 31.08.2010
 */
public class EntityDetachRunnablePoolUpdateHandler extends RunnablePoolUpdateHandler<EntityDetachRunnablePoolItem> {
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
    public EntityDetachRunnablePoolUpdateHandler() {
		super();
	}

        /**
         * 
         * @param pInitialPoolSize
         */
        public EntityDetachRunnablePoolUpdateHandler(final int pInitialPoolSize) {
		super(pInitialPoolSize);
	}

        /**
         * 
         * @param pInitialPoolSize
         * @param pGrowth
         */
        public EntityDetachRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth) {
		super(pInitialPoolSize, pGrowth);
	}

        /**
         * 
         * @param pInitialPoolSize
         * @param pGrowth
         * @param pAvailableItemCountMaximum
         */
        public EntityDetachRunnablePoolUpdateHandler(final int pInitialPoolSize, final int pGrowth, final int pAvailableItemCountMaximum) {
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
	protected EntityDetachRunnablePoolItem onAllocatePoolItem() {
		return new EntityDetachRunnablePoolItem();
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pEntity
         */
        public void scheduleDetach(final IEntity pEntity) {
		final EntityDetachRunnablePoolItem entityDetachRunnablePoolItem = this.obtainPoolItem();
		entityDetachRunnablePoolItem.setEntity(pEntity);
		this.postPoolItem(entityDetachRunnablePoolItem);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
