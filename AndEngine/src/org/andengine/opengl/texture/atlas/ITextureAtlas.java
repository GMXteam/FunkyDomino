package org.andengine.opengl.texture.atlas;

import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.source.ITextureAtlasSource;
import org.andengine.util.debug.Debug;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 14:24:29 - 14.07.2011
 */
public interface ITextureAtlas<T extends ITextureAtlasSource> extends ITexture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pTextureAtlasSource
     * @param pTextureX
     * @param pTextureY
     * @throws IllegalArgumentException
     */
    public void addTextureAtlasSource(final T pTextureAtlasSource, final int pTextureX, final int pTextureY) throws IllegalArgumentException;
        /**
         * 
         * @param pTextureAtlasSource
         * @param pTextureX
         * @param pTextureY
         * @param pTextureAtlasSourcePadding
         * @throws IllegalArgumentException
         */
        public void addTextureAtlasSource(final T pTextureAtlasSource, final int pTextureX, final int pTextureY, final int pTextureAtlasSourcePadding) throws IllegalArgumentException;
        /**
         * 
         * @param pTextureX
         * @param pTextureY
         * @param pWidth
         * @param pHeight
         */
        public void addEmptyTextureAtlasSource(final int pTextureX, final int pTextureY, final int pWidth, final int pHeight);
        /**
         * 
         * @param pTextureAtlasSource
         * @param pTextureX
         * @param pTextureY
         */
        public void removeTextureAtlasSource(final T pTextureAtlasSource, final int pTextureX, final int pTextureY);
        /**
         * 
         */
        public void clearTextureAtlasSources();

        /**
         * 
         * @return
         * @deprecated
         */
        @Deprecated
	@Override
	public boolean hasTextureStateListener();
        /**
         * 
         * @return
         */
        public boolean hasTextureAtlasStateListener();

        /**
         * 
         * @return
         * @deprecated
         */
        @Deprecated
	@Override
	public ITextureAtlasStateListener<T> getTextureStateListener();
        /**
         * 
         * @return
         */
        public ITextureAtlasStateListener<T> getTextureAtlasStateListener();

        /**
         * 
         * @param pTextureStateListener
         * @deprecated
         */
        @Deprecated
	@Override
	public void setTextureStateListener(final ITextureStateListener pTextureStateListener);
        /**
         * 
         * @param pTextureAtlasStateListener
         */
        public void setTextureAtlasStateListener(final ITextureAtlasStateListener<T> pTextureAtlasStateListener);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         * @param <T>
         */
        public static interface ITextureAtlasStateListener<T extends ITextureAtlasSource> extends ITextureStateListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

            /**
             * 
             * @param pTextureAtlas
             * @param pTextureAtlasSource
             */
            public void onTextureAtlasSourceLoaded(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource);
                /**
                 * 
                 * @param pTextureAtlas
                 * @param pTextureAtlasSource
                 * @param pThrowable
                 */
                public void onTextureAtlasSourceLoadExeption(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final Throwable pThrowable);

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================

                /**
                 * 
                 * @param <T>
                 */
                public static class TextureAtlasStateAdapter<T extends ITextureAtlasSource> implements ITextureAtlasStateListener<T> {
                    /**
                     * 
                     * @param pTexture
                     */
                    @Override
			public void onLoadedToHardware(final ITexture pTexture) { }

                    /**
                     * 
                     * @param pTextureAtlas
                     * @param pTextureAtlasSource
                     */
                    @Override
			public void onTextureAtlasSourceLoaded(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource) { }

			@Override
			public void onTextureAtlasSourceLoadExeption(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final Throwable pThrowable) { }

                        /**
                         * 
                         * @param pTexture
                         */
                        @Override
			public void onUnloadedFromHardware(final ITexture pTexture) { }
		}

                /**
                 * 
                 * @param <T>
                 */
                public static class DebugTextureAtlasStateListener<T extends ITextureAtlasSource> implements ITextureAtlasStateListener<T> {
                    /**
                     * 
                     * @param pTexture
                     */
                    @Override
			public void onLoadedToHardware(final ITexture pTexture) {
				Debug.d("Texture loaded: " + pTexture.toString());
			}

                        /**
                         * 
                         * @param pTextureAtlas
                         * @param pTextureAtlasSource
                         */
                        @Override
			public void onTextureAtlasSourceLoaded(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource) {
				Debug.e("Loaded TextureAtlasSource. TextureAtlas: " + pTextureAtlas.toString() + " TextureAtlasSource: " + pTextureAtlasSource.toString());
			}

                        /**
                         * 
                         * @param pTextureAtlas
                         * @param pTextureAtlasSource
                         * @param pThrowable
                         */
                        @Override
			public void onTextureAtlasSourceLoadExeption(final ITextureAtlas<T> pTextureAtlas, final T pTextureAtlasSource, final Throwable pThrowable) {
				Debug.e("Exception loading TextureAtlasSource. TextureAtlas: " + pTextureAtlas.toString() + " TextureAtlasSource: " + pTextureAtlasSource.toString(), pThrowable);
			}

                        /**
                         * 
                         * @param pTexture
                         */
                        @Override
			public void onUnloadedFromHardware(final ITexture pTexture) {
				Debug.d("Texture unloaded: " + pTexture.toString());
			}
		}
	}
}
