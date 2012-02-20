package org.andengine.engine.options;

/** 
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 23:13:07 - 22.11.2011
 */
public class AudioOptions {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private SoundOptions mSoundOptions = new SoundOptions();
	private MusicOptions mMusicOptions = new MusicOptions();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public SoundOptions getSoundOptions() {
		return this.mSoundOptions;
	}

        /**
         * 
         * @return
         */
        public MusicOptions getMusicOptions() {
		return this.mMusicOptions;
	}

        /**
         * 
         * @return
         */
        public boolean needsSound() {
		return this.mSoundOptions.needsSound();
	}

        /**
         * 
         * @param pNeedsSound
         * @return
         */
        public AudioOptions setNeedsSound(final boolean pNeedsSound) {
		this.mSoundOptions.setNeedsSound(pNeedsSound);
		return this;
	}

        /**
         * 
         * @return
         */
        public boolean needsMusic() {
		return this.mMusicOptions.needsMusic();
	}

        /**
         * 
         * @param pNeedsMusic
         * @return
         */
        public AudioOptions setNeedsMusic(final boolean pNeedsMusic) {
		this.mMusicOptions.setNeedsMusic(pNeedsMusic);
		return this;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
