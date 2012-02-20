package org.andengine.opengl.texture.atlas.bitmap;

import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.BuildableTextureAtlas;
import org.andengine.opengl.texture.atlas.source.ITextureAtlasSource;
import org.andengine.opengl.texture.bitmap.BitmapTexture.BitmapTextureFormat;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:51:46 - 12.07.2011
 */
public class BuildableBitmapTextureAtlas extends BuildableTextureAtlas<IBitmapTextureAtlasSource, BitmapTextureAtlas> {
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
	 * Uses {@link BitmapTextureFormat#RGBA_8888}.
     * 
     * @param pTextureManager 
     * @param pHeight
     * @param pWidth  
     */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight) {
		this(pTextureManager, pWidth, pHeight, BitmapTextureFormat.RGBA_8888);
	}

	/**
         * @param pTextureManager 
         * @param pWidth 
         * @param pBitmapTextureFormat use {@link BitmapTextureFormat#RGBA_8888} or {@link BitmapTextureFormat#RGBA_4444}  for a {@link BitmapTextureAtlas} with transparency and {@link BitmapTextureFormat#RGB_565} for a {@link BitmapTextureAtlas} without transparency.
         * @param pHeight  
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final BitmapTextureFormat pBitmapTextureFormat) {
		this(pTextureManager, pWidth, pHeight, pBitmapTextureFormat, TextureOptions.DEFAULT, null);
	}

	/**
	 * Uses {@link BitmapTextureFormat#RGBA_8888}.
	 *
         * @param pTextureManager 
         * @param pWidth 
         * @param pHeight 
         * @param pTextureStateListener to be informed when this {@link BitmapTextureAtlas} is loaded, unloaded or a {@link ITextureAtlasSource} failed to load.
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final ITextureAtlasStateListener<IBitmapTextureAtlasSource> pTextureStateListener) {
		this(pTextureManager, pWidth, pHeight, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, pTextureStateListener);
	}

	/**
         * @param pTextureManager 
         * @param pWidth 
         * @param pBitmapTextureFormat use {@link BitmapTextureFormat#RGBA_8888} or {@link BitmapTextureFormat#RGBA_4444}  for a {@link BitmapTextureAtlas} with transparency and {@link BitmapTextureFormat#RGB_565} for a {@link BitmapTextureAtlas} without transparency.
         * @param pHeight 
         * @param pTextureStateListener to be informed when this {@link BitmapTextureAtlas} is loaded, unloaded or a {@link ITextureAtlasSource} failed to load.
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final BitmapTextureFormat pBitmapTextureFormat, final ITextureAtlasStateListener<IBitmapTextureAtlasSource> pTextureStateListener) {
		this(pTextureManager, pWidth, pHeight, pBitmapTextureFormat, TextureOptions.DEFAULT, pTextureStateListener);
	}

	/**
	 * Uses {@link BitmapTextureFormat#RGBA_8888}.
	 *
         * @param pTextureManager 
         * @param pTextureOptions the (quality) settings of the BitmapTexture.
         * @param pWidth 
         * @param pHeight 
         * @throws IllegalArgumentException  
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final TextureOptions pTextureOptions) throws IllegalArgumentException {
		this(pTextureManager, pWidth, pHeight, BitmapTextureFormat.RGBA_8888, pTextureOptions, null);
	}

	/**
         * @param pTextureManager 
         * @param pBitmapTextureFormat use {@link BitmapTextureFormat#RGBA_8888} or {@link BitmapTextureFormat#RGBA_4444}  for a {@link BitmapTextureAtlas} with transparency and {@link BitmapTextureFormat#RGB_565} for a {@link BitmapTextureAtlas} without transparency.
         * @param pWidth 
         * @param pHeight 
         * @param pTextureOptions the (quality) settings of the BitmapTexture.
         * @throws IllegalArgumentException  
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final BitmapTextureFormat pBitmapTextureFormat, final TextureOptions pTextureOptions) throws IllegalArgumentException {
		this(pTextureManager, pWidth, pHeight, pBitmapTextureFormat, pTextureOptions, null);
	}

	/**
	 * Uses {@link BitmapTextureFormat#RGBA_8888}.
	 *
         * @param pTextureManager 
         * @param pTextureOptions the (quality) settings of the BitmapTexture.
         * @param pHeight 
         * @param pWidth 
         * @param pTextureStateListener to be informed when this {@link BitmapTextureAtlas} is loaded, unloaded or a {@link ITextureAtlasSource} failed to load.
         * @throws IllegalArgumentException  
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final TextureOptions pTextureOptions, final ITextureAtlasStateListener<IBitmapTextureAtlasSource> pTextureStateListener) throws IllegalArgumentException {
		this(pTextureManager, pWidth, pHeight, BitmapTextureFormat.RGBA_8888, pTextureOptions, pTextureStateListener);
	}

	/**
         * @param pTextureManager 
         * @param pBitmapTextureFormat use {@link BitmapTextureFormat#RGBA_8888} or {@link BitmapTextureFormat#RGBA_4444}  for a {@link BitmapTextureAtlas} with transparency and {@link BitmapTextureFormat#RGB_565} for a {@link BitmapTextureAtlas} without transparency.
         * @param pWidth 
         * @param pTextureOptions the (quality) settings of the BitmapTexture.
         * @param pHeight 
         * @param pTextureStateListener to be informed when this {@link BitmapTextureAtlas} is loaded, unloaded or a {@link ITextureAtlasSource} failed to load.
         * @throws IllegalArgumentException  
	 */
	public BuildableBitmapTextureAtlas(final TextureManager pTextureManager, final int pWidth, final int pHeight, final BitmapTextureFormat pBitmapTextureFormat, final TextureOptions pTextureOptions, final ITextureAtlasStateListener<IBitmapTextureAtlasSource> pTextureStateListener) throws IllegalArgumentException {
		super(new BitmapTextureAtlas(pTextureManager, pWidth, pHeight, pBitmapTextureFormat, pTextureOptions, pTextureStateListener));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

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
