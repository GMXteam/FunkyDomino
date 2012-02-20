package org.andengine.opengl.texture.region;


import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.ITextureAtlas;
import org.andengine.opengl.texture.atlas.source.ITextureAtlasSource;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 18:15:14 - 09.03.2010
 */
public class TextureRegionFactory {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
    /**
     * 
     * @param pTexture
     * @return
     */
    public static ITextureRegion extractFromTexture(final ITexture pTexture) {
		return TextureRegionFactory.extractFromTexture(pTexture, false);
	}

    /**
     * 
     * @param pTexture
     * @param pTextureX
     * @param pTextureY
     * @param pWidth
     * @param pHeight
     * @return
     */
    public static ITextureRegion extractFromTexture(final ITexture pTexture, final int pTextureX, final int pTextureY, final int pWidth, final int pHeight) {
		return TextureRegionFactory.extractFromTexture(pTexture, pTextureX, pTextureY, pWidth, pHeight, false);
	}

    /**
     * 
     * @param pTexture
     * @param pRotated
     * @return
     */
    public static ITextureRegion extractFromTexture(final ITexture pTexture, final boolean pRotated) {
		return new TextureRegion(pTexture, 0, 0, pTexture.getWidth(), pTexture.getHeight(), pRotated);
	}

        /**
         * 
         * @param pTexture
         * @param pTextureX
         * @param pTextureY
         * @param pWidth
         * @param pHeight
         * @param pRotated
         * @return
         */
        public static ITextureRegion extractFromTexture(final ITexture pTexture, final int pTextureX, final int pTextureY, final int pWidth, final int pHeight, final boolean pRotated) {
		return new TextureRegion(pTexture, pTextureX, pTextureY, pWidth, pHeight, pRotated);
	}

        /**
         * 
         * @param <T>
         * @param pTextureAtlas
         * @param pTextureAtlasSource
         * @param pTextureX
         * @param pTextureY
         * @return
         */
        public static <T extends ITextureAtlasSource> ITextureRegion createFromSource(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final int pTextureX, final int pTextureY) {
		return TextureRegionFactory.createFromSource(pTextureAtlas, pTextureAtlasSource, pTextureX, pTextureY, false);
	}

        /**
         * 
         * @param <T>
         * @param pTextureAtlas
         * @param pTextureAtlasSource
         * @param pTextureX
         * @param pTextureY
         * @param pRotated
         * @return
         */
        public static <T extends ITextureAtlasSource> ITextureRegion createFromSource(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final int pTextureX, final int pTextureY, final boolean pRotated) {
		final TextureRegion textureRegion = new TextureRegion(pTextureAtlas, pTextureX, pTextureY, pTextureAtlasSource.getTextureWidth(), pTextureAtlasSource.getTextureHeight(), pRotated);
		pTextureAtlas.addTextureAtlasSource(pTextureAtlasSource, pTextureX, pTextureY);
		return textureRegion;
	}

        /**
         * 
         * @param <T>
         * @param pTextureAtlas
         * @param pTextureAtlasSource
         * @param pTextureX
         * @param pTextureY
         * @param pTileColumns
         * @param pTileRows
         * @return
         */
        public static <T extends ITextureAtlasSource> TiledTextureRegion createTiledFromSource(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final int pTextureX, final int pTextureY, final int pTileColumns, final int pTileRows) {
		return TextureRegionFactory.createTiledFromSource(pTextureAtlas, pTextureAtlasSource, pTextureX, pTextureY, pTileColumns, pTileRows, false);
	}

        /**
         * 
         * @param <T>
         * @param pTextureAtlas
         * @param pTextureAtlasSource
         * @param pTextureX
         * @param pTextureY
         * @param pTileColumns
         * @param pTileRows
         * @param pRotated
         * @return
         */
        public static <T extends ITextureAtlasSource> TiledTextureRegion createTiledFromSource(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final int pTextureX, final int pTextureY, final int pTileColumns, final int pTileRows, final boolean pRotated) {
		final TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(pTextureAtlas, pTextureX, pTextureY, pTextureAtlasSource.getTextureWidth(), pTextureAtlasSource.getTextureHeight(), pTileColumns, pTileRows, pRotated);
		pTextureAtlas.addTextureAtlasSource(pTextureAtlasSource, pTextureX, pTextureY);
		return tiledTextureRegion;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
