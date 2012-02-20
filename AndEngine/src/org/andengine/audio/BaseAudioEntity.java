package org.andengine.audio;

import org.andengine.audio.exception.AudioException;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:35:37 - 13.06.2010
 */
public abstract class BaseAudioEntity implements IAudioEntity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IAudioManager<? extends IAudioEntity> mAudioManager;

        /**
         * 
         */
        protected float mLeftVolume = 1.0f;
        /**
         * 
         */
        protected float mRightVolume = 1.0f;

	private boolean mReleased;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pAudioManager
         */
        public BaseAudioEntity(final IAudioManager<? extends IAudioEntity> pAudioManager) {
		this.mAudioManager = pAudioManager;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public boolean isReleased() {
		return this.mReleased;
	}

        /**
         * 
         * @return
         * @throws AudioException
         */
        protected IAudioManager<? extends IAudioEntity> getAudioManager() throws AudioException {
		this.assertNotReleased();

		return this.mAudioManager;
	}

        /**
         * 
         * @return
         * @throws AudioException
         */
        public float getActualLeftVolume() throws AudioException {
		this.assertNotReleased();

		return this.mLeftVolume * this.getMasterVolume();
	}

        /**
         * 
         * @return
         * @throws AudioException
         */
        public float getActualRightVolume() throws AudioException {
		this.assertNotReleased();

		return this.mRightVolume * this.getMasterVolume();
	}

        /**
         * 
         * @return
         * @throws AudioException
         */
        protected float getMasterVolume() throws AudioException {
		this.assertNotReleased();

		return this.mAudioManager.getMasterVolume();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @throws AudioException
         */
        protected abstract void throwOnReleased() throws AudioException ;

        /**
         * 
         * @return
         * @throws AudioException
         */
        @Override
	public float getVolume() throws AudioException {
		this.assertNotReleased();

		return (this.mLeftVolume + this.mRightVolume) * 0.5f;
	}

        /**
         * 
         * @return
         * @throws AudioException
         */
        @Override
	public float getLeftVolume() throws AudioException {
		this.assertNotReleased();

		return this.mLeftVolume;
	}

        /**
         * 
         * @return
         * @throws AudioException
         */
        @Override
	public float getRightVolume() throws AudioException {
		this.assertNotReleased();

		return this.mRightVolume;
	}

        /**
         * 
         * @param pVolume
         * @throws AudioException
         */
        @Override
	public final void setVolume(final float pVolume) throws AudioException {
		this.assertNotReleased();

		this.setVolume(pVolume, pVolume);
	}

        /**
         * 
         * @param pLeftVolume
         * @param pRightVolume
         * @throws AudioException
         */
        @Override
	public void setVolume(final float pLeftVolume, final float pRightVolume) throws AudioException {
		this.assertNotReleased();

		this.mLeftVolume = pLeftVolume;
		this.mRightVolume = pRightVolume;
	}

        /**
         * 
         * @param pMasterVolume
         * @throws AudioException
         */
        @Override
	public void onMasterVolumeChanged(final float pMasterVolume) throws AudioException {
		this.assertNotReleased();
	}

        /**
         * 
         * @throws AudioException
         */
        @Override
	public void play() throws AudioException {
		this.assertNotReleased();
	}

        /**
         * 
         * @throws AudioException
         */
        @Override
	public void pause() throws AudioException {
		this.assertNotReleased();
	}

        /**
         * 
         * @throws AudioException
         */
        @Override
	public void resume() throws AudioException {
		this.assertNotReleased();
	}

        /**
         * 
         * @throws AudioException
         */
        @Override
	public void stop() throws AudioException {
		this.assertNotReleased();
	}

        /**
         * 
         * @param pLooping
         * @throws AudioException
         */
        @Override
	public void setLooping(final boolean pLooping) throws AudioException {
		this.assertNotReleased();
	}

        /**
         * 
         * @throws AudioException
         */
        @Override
	public void release() throws AudioException {
		this.assertNotReleased();

		this.mReleased = true;
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @throws AudioException
         */
        protected void assertNotReleased() throws AudioException {
		if(this.mReleased) {
			this.throwOnReleased();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
