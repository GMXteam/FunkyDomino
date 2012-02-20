package org.andengine.engine.handler.physics;

import org.andengine.engine.handler.BaseEntityUpdateHandler;
import org.andengine.entity.IEntity;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:53:07 - 24.12.2010
 */
public class PhysicsHandler extends BaseEntityUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mEnabled = true;

        /**
         * 
         */
        protected float mAccelerationX = 0;
        /**
         * 
         */
        protected float mAccelerationY = 0;

        /**
         * 
         */
        protected float mVelocityX = 0;
        /**
         * 
         */
        protected float mVelocityY = 0;

        /**
         * 
         */
        protected float mAngularVelocity = 0;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pEntity
         */
        public PhysicsHandler(final IEntity pEntity) {
		super(pEntity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public boolean isEnabled() {
		return this.mEnabled;
	}

        /**
         * 
         * @param pEnabled
         */
        public void setEnabled(final boolean pEnabled) {
		this.mEnabled = pEnabled;
	}

        /**
         * 
         * @return
         */
        public float getVelocityX() {
		return this.mVelocityX;
	}

        /**
         * 
         * @return
         */
        public float getVelocityY() {
		return this.mVelocityY;
	}

        /**
         * 
         * @param pVelocityX
         */
        public void setVelocityX(final float pVelocityX) {
		this.mVelocityX = pVelocityX;
	}

        /**
         * 
         * @param pVelocityY
         */
        public void setVelocityY(final float pVelocityY) {
		this.mVelocityY = pVelocityY;
	}

        /**
         * 
         * @param pVelocity
         */
        public void setVelocity(final float pVelocity) {
		this.mVelocityX = pVelocity;
		this.mVelocityY = pVelocity;
	}

        /**
         * 
         * @param pVelocityX
         * @param pVelocityY
         */
        public void setVelocity(final float pVelocityX, final float pVelocityY) {
		this.mVelocityX = pVelocityX;
		this.mVelocityY = pVelocityY;
	}

        /**
         * 
         * @return
         */
        public float getAccelerationX() {
		return this.mAccelerationX;
	}

        /**
         * 
         * @return
         */
        public float getAccelerationY() {
		return this.mAccelerationY;
	}

        /**
         * 
         * @param pAccelerationX
         */
        public void setAccelerationX(final float pAccelerationX) {
		this.mAccelerationX = pAccelerationX;
	}

        /**
         * 
         * @param pAccelerationY
         */
        public void setAccelerationY(final float pAccelerationY) {
		this.mAccelerationY = pAccelerationY;
	}

        /**
         * 
         * @param pAccelerationX
         * @param pAccelerationY
         */
        public void setAcceleration(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX = pAccelerationX;
		this.mAccelerationY = pAccelerationY;
	}

        /**
         * 
         * @param pAcceleration
         */
        public void setAcceleration(final float pAcceleration) {
		this.mAccelerationX = pAcceleration;
		this.mAccelerationY = pAcceleration;
	}

        /**
         * 
         * @param pAccelerationX
         * @param pAccelerationY
         */
        public void accelerate(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX += pAccelerationX;
		this.mAccelerationY += pAccelerationY;
	}

        /**
         * 
         * @return
         */
        public float getAngularVelocity() {
		return this.mAngularVelocity;
	}

        /**
         * 
         * @param pAngularVelocity
         */
        public void setAngularVelocity(final float pAngularVelocity) {
		this.mAngularVelocity = pAngularVelocity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onUpdate(final float pSecondsElapsed, final IEntity pEntity) {
		if(this.mEnabled) {
			/* Apply linear acceleration. */
			final float accelerationX = this.mAccelerationX;
			final float accelerationY = this.mAccelerationY;
			if(accelerationX != 0 || accelerationY != 0) {
				this.mVelocityX += accelerationX * pSecondsElapsed;
				this.mVelocityY += accelerationY * pSecondsElapsed;
			}

			/* Apply angular velocity. */
			final float angularVelocity = this.mAngularVelocity;
			if(angularVelocity != 0) {
				pEntity.setRotation(pEntity.getRotation() + angularVelocity * pSecondsElapsed);
			}

			/* Apply linear velocity. */
			final float velocityX = this.mVelocityX;
			final float velocityY = this.mVelocityY;
			if(velocityX != 0 || velocityY != 0) {
				pEntity.setPosition(pEntity.getX() + velocityX * pSecondsElapsed, pEntity.getY() + velocityY * pSecondsElapsed);
			}
		}
	}

        /**
         * 
         */
        @Override
	public void reset() {
		this.mAccelerationX = 0;
		this.mAccelerationY = 0;
		this.mVelocityX = 0;
		this.mVelocityY = 0;
		this.mAngularVelocity = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
