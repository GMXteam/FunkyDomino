package org.andengine.opengl.texture;

import java.io.IOException;

import org.andengine.opengl.texture.atlas.source.ITextureAtlasSource;
import org.andengine.opengl.util.GLState;
import org.andengine.util.debug.Debug;

import android.opengl.GLES20;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:01:03 - 11.07.2011
 */
public interface ITexture {
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
    public int getWidth();
        /**
         * 
         * @return
         */
        public int getHeight();

        /**
         * 
         * @return
         */
        public int getHardwareTextureID();

        /**
         * 
         * @return
         */
        public boolean isLoadedToHardware();
        /**
         * 
         */
        public void setNotLoadedToHardware();

        /**
         * 
         * @return
         */
        public boolean isUpdateOnHardwareNeeded();
        /**
         * 
         * @param pUpdateOnHardwareNeeded
         */
        public void setUpdateOnHardwareNeeded(final boolean pUpdateOnHardwareNeeded);

        /**
         * 
         */
        public void load();
        /**
         * 
         */
        public void unload();

        /**
         * 
         * @param pGLState
         * @throws IOException
         */
        public void loadToHardware(final GLState pGLState) throws IOException;
        /**
         * 
         * @param pGLState
         */
        public void unloadFromHardware(final GLState pGLState);
        /**
         * 
         * @param pGLState
         * @throws IOException
         */
        public void reloadToHardware(final GLState pGLState) throws IOException;

        /**
         * 
         * @param pGLState
         */
        public void bind(final GLState pGLState);
	/**
         * @param pGLState 
         * @param pGLActiveTexture from {@link GLES20#GL_TEXTURE0} to {@link GLES20#GL_TEXTURE31}. 
	 */
	public void bind(final GLState pGLState, final int pGLActiveTexture);

        /**
         * 
         * @return
         */
        public PixelFormat getPixelFormat();
        /**
         * 
         * @return
         */
        public TextureOptions getTextureOptions();

        /**
         * 
         * @return
         */
        public boolean hasTextureStateListener();
        /**
         * 
         * @return
         */
        public ITextureStateListener getTextureStateListener();
        /**
         * 
         * @param pTextureStateListener
         */
        public void setTextureStateListener(final ITextureStateListener pTextureStateListener);

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         */
        public static interface ITextureStateListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

            /**
             * 
             * @param pTexture
             */
            public void onLoadedToHardware(final ITexture pTexture);
                /**
                 * 
                 * @param pTexture
                 */
                public void onUnloadedFromHardware(final ITexture pTexture);

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================

                /**
                 * 
                 * @param <T>
                 */
                public static class TextureStateAdapter<T extends ITextureAtlasSource> implements ITextureStateListener {
                    /**
                     * 
                     * @param pTexture
                     */
                    @Override
			public void onLoadedToHardware(final ITexture pTexture) { }

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
                public static class DebugTextureStateListener<T extends ITextureAtlasSource> implements ITextureStateListener {
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
                     * @param pTexture
                     */
                    @Override
			public void onUnloadedFromHardware(final ITexture pTexture) {
				Debug.d("Texture unloaded: " + pTexture.toString());
			}
		}
	}
}