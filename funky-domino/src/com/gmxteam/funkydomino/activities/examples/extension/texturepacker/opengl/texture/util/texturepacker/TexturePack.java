package org.anddev.andengine.extension.texturepacker.opengl.texture.util.texturepacker;

import org.anddev.andengine.opengl.texture.ITexture;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 23:23:47 - 30.07.2011
 */
public class TexturePack {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ITexture mTexture;
	private final TexturePackTextureRegionLibrary mTexturePackTextureRegionLibrary;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pTexture
         * @param pTexturePackTextureRegionLibrary
         */
        public TexturePack(final ITexture pTexture, final TexturePackTextureRegionLibrary pTexturePackTextureRegionLibrary) {
		this.mTexture = pTexture;
		this.mTexturePackTextureRegionLibrary = pTexturePackTextureRegionLibrary;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public ITexture getTexture() {
		return this.mTexture;
	}

        /**
         * 
         * @return
         */
        public TexturePackTextureRegionLibrary getTexturePackTextureRegionLibrary() {
		return this.mTexturePackTextureRegionLibrary;
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