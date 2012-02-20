package org.andengine.entity.particle.modifier;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;
import org.andengine.util.math.MathUtils;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 21:21:10 - 14.03.2010
 */
public class ExpireParticleModifier<T extends IEntity> implements IParticleModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mMinLifeTime;
	private float mMaxLifeTime;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pLifeTime
         */
        public ExpireParticleModifier(final float pLifeTime) {
		this(pLifeTime, pLifeTime);
	}

        /**
         * 
         * @param pMinLifeTime
         * @param pMaxLifeTime
         */
        public ExpireParticleModifier(final float pMinLifeTime, final float pMaxLifeTime) {
		this.mMinLifeTime = pMinLifeTime;
		this.mMaxLifeTime = pMaxLifeTime;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getMinLifeTime() {
		return this.mMinLifeTime;
	}

        /**
         * 
         * @return
         */
        public float getMaxLifeTime() {
		return this.mMaxLifeTime;
	}

        /**
         * 
         * @param pLifeTime
         */
        public void setLifeTime(final float pLifeTime) {
		this.mMinLifeTime = pLifeTime;
		this.mMaxLifeTime = pLifeTime;
	}

        /**
         * 
         * @param pMinLifeTime
         * @param pMaxLifeTime
         */
        public void setLifeTime(final float pMinLifeTime, final float pMaxLifeTime) {
		this.mMinLifeTime = pMinLifeTime;
		this.mMaxLifeTime = pMaxLifeTime;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pParticle
         */
        @Override
	public void onInitializeParticle(final Particle<T> pParticle) {
		pParticle.setExpireTime((MathUtils.RANDOM.nextFloat() * (this.mMaxLifeTime - this.mMinLifeTime) + this.mMinLifeTime));
	}

        /**
         * 
         * @param pParticle
         */
        @Override
	public void onUpdateParticle(final Particle<T> pParticle) {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
