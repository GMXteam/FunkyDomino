package org.andengine.entity.particle.modifier;


import org.andengine.engine.camera.Camera;
import org.andengine.entity.particle.Particle;
import org.andengine.entity.shape.RectangularShape;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 21:21:10 - 14.03.2010
 */
public class OffCameraExpireParticleModifier<T extends RectangularShape> implements IParticleModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final Camera mCamera;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pCamera
         */
        public OffCameraExpireParticleModifier(final Camera pCamera) {
		this.mCamera = pCamera;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public Camera getCamera() {
		return this.mCamera;
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

	}

        /**
         * 
         * @param pParticle
         */
        @Override
	public void onUpdateParticle(final Particle<T> pParticle) {
		if(!this.mCamera.isRectangularShapeVisible(pParticle.getEntity())) {
			pParticle.setExpired(true);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
