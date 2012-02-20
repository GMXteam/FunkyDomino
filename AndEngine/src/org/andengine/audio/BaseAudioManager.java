package org.andengine.audio;

import java.util.ArrayList;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 18:07:02 - 13.06.2010
 */
public abstract class BaseAudioManager<T extends IAudioEntity> implements IAudioManager<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected final ArrayList<T> mAudioEntities = new ArrayList<T>();

        /**
         * 
         */
        protected float mMasterVolume = 1.0f;

	// ===========================================================
	// Constructors
	// ===========================================================

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
	public float getMasterVolume() {
		return this.mMasterVolume;
	}

        /**
         * 
         * @param pMasterVolume
         */
        @Override
	public void setMasterVolume(final float pMasterVolume) {
		this.mMasterVolume = pMasterVolume;

		final ArrayList<T> audioEntities = this.mAudioEntities;
		for(int i = audioEntities.size() - 1; i >= 0; i--) {
			final T audioEntity = audioEntities.get(i);

			audioEntity.onMasterVolumeChanged(pMasterVolume);
		}
	}

        /**
         * 
         * @param pAudioEntity
         */
        @Override
	public void add(final T pAudioEntity) {
		this.mAudioEntities.add(pAudioEntity);
	}

        /**
         * 
         * @param pAudioEntity
         * @return
         */
        @Override
	public boolean remove(final T pAudioEntity) {
		return this.mAudioEntities.remove(pAudioEntity);
	}

        /**
         * 
         */
        @Override
	public void releaseAll() {
		final ArrayList<T> audioEntities = this.mAudioEntities;
		for(int i = audioEntities.size() - 1; i >= 0; i--) {
			final T audioEntity = audioEntities.get(i);

			audioEntity.stop();
			audioEntity.release();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
