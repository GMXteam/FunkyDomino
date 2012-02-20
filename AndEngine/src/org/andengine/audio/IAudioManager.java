package org.andengine.audio;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 15:02:06 - 13.06.2010
 */
public interface IAudioManager<T extends IAudioEntity> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @return
     */
    public float getMasterVolume();
    /**
     * 
     * @param pMasterVolume
     */
    public void setMasterVolume(final float pMasterVolume);

        /**
         * 
         * @param pAudioEntity
         */
        public void add(final T pAudioEntity);
        /**
         * 
         * @param pAudioEntity
         * @return
         */
        public boolean remove(final T pAudioEntity);

        /**
         * 
         */
        public void releaseAll();
}
