package org.andengine.audio;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 14:53:29 - 13.06.2010
 */
public interface IAudioEntity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     */
    public void play();
        /**
         * 
         */
        public void pause();
        /**
         * 
         */
        public void resume();
        /**
         * 
         */
        public void stop();

        /**
         * 
         * @return
         */
        public float getVolume();
        /**
         * 
         * @param pVolume
         */
        public void setVolume(final float pVolume);

        /**
         * 
         * @return
         */
        public float getLeftVolume();
        /**
         * 
         * @return
         */
        public float getRightVolume();
        /**
         * 
         * @param pLeftVolume
         * @param pRightVolume
         */
        public void setVolume(final float pLeftVolume, final float pRightVolume);

        /**
         * 
         * @param pMasterVolume
         */
        public void onMasterVolumeChanged(final float pMasterVolume);

        /**
         * 
         * @param pLooping
         */
        public void setLooping(final boolean pLooping);

        /**
         * 
         */
        public void release();
}
