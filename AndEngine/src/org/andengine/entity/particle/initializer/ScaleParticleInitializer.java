package org.andengine.entity.particle.initializer;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;


/**
 * (c) Zynga 2011
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 10:03:29 - 19.11.2011
 */
public class ScaleParticleInitializer<T extends IEntity> extends BaseSingleValueParticleInitializer<T> {
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
     * @param pScale
     */
    public ScaleParticleInitializer(final float pScale) {
		super(pScale, pScale);
	}

    /**
     * 
     * @param pMinScale
     * @param pMaxScale
     */
    public ScaleParticleInitializer(final float pMinScale, final float pMaxScale) {
		super(pMinScale, pMaxScale);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    /**
     * 
     * @param pParticle
     * @param pScale
     */
    @Override
	protected void onInitializeParticle(final Particle<T> pParticle, final float pScale) {
		pParticle.getEntity().setScale(pScale);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
