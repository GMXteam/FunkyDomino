package org.andengine.engine.handler;

import org.andengine.entity.IEntity;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 14:00:25 - 24.12.2010
 */
public abstract class BaseEntityUpdateHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private IEntity mEntity;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pEntity
         */
        public BaseEntityUpdateHandler(final IEntity pEntity) {
		this.mEntity = pEntity;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public IEntity getEntity() {
		return this.mEntity;
	}

        /**
         * 
         * @param pEntity
         */
        public void setEntity(final IEntity pEntity) {
		this.mEntity = pEntity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         * @param pEntity
         */
        protected abstract void onUpdate(final float pSecondsElapsed, final IEntity pEntity);

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	public final void onUpdate(final float pSecondsElapsed) {
		this.onUpdate(pSecondsElapsed, this.mEntity);
	}

        /**
         * 
         */
        @Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
