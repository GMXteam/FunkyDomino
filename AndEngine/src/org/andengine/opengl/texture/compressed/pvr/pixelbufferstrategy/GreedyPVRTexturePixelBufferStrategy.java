package org.andengine.opengl.texture.compressed.pvr.pixelbufferstrategy;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import org.andengine.opengl.texture.PixelFormat;
import org.andengine.opengl.texture.compressed.pvr.PVRTexture;
import org.andengine.opengl.texture.compressed.pvr.PVRTexture.PVRTextureHeader;

import android.opengl.GLES20;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 11:26:07 - 27.09.2011
 */
public class GreedyPVRTexturePixelBufferStrategy implements IPVRTexturePixelBufferStrategy {
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

	@Override
	public IPVRTexturePixelBufferStrategyBufferManager newPVRTexturePixelBufferStrategyManager(final PVRTexture pPVRTexture) throws IOException {
		return new GreedyPVRTexturePixelBufferStrategyBufferManager(pPVRTexture);
	}

        /**
         * 
         * @param pPVRTexturePixelBufferStrategyManager
         * @param pWidth
         * @param pHeight
         * @param pBytesPerPixel
         * @param pPixelFormat
         * @param pLevel
         * @param pCurrentPixelDataOffset
         * @param pCurrentPixelDataSize
         * @throws IOException
         */
        @Override
	public void loadPVRTextureData(final IPVRTexturePixelBufferStrategyBufferManager pPVRTexturePixelBufferStrategyManager, final int pWidth, final int pHeight, final int pBytesPerPixel, final PixelFormat pPixelFormat, final int pLevel, final int pCurrentPixelDataOffset, final int pCurrentPixelDataSize) throws IOException {
		/* Adjust buffer. */
		final Buffer pixelBuffer = pPVRTexturePixelBufferStrategyManager.getPixelBuffer(PVRTextureHeader.SIZE + pCurrentPixelDataOffset, pCurrentPixelDataSize);

		/* Send to hardware. */
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, pLevel, pPixelFormat.getGLInternalFormat(), pWidth, pHeight, 0, pPixelFormat.getGLFormat(), pPixelFormat.getGLType(), pixelBuffer);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static class GreedyPVRTexturePixelBufferStrategyBufferManager implements IPVRTexturePixelBufferStrategyBufferManager {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final ByteBuffer mByteBuffer;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pPVRTexture
                 * @throws IOException
                 */
                public GreedyPVRTexturePixelBufferStrategyBufferManager(final PVRTexture pPVRTexture) throws IOException {
			this.mByteBuffer = pPVRTexture.getPVRTextureBuffer();
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

                /**
                 * 
                 * @param pStart
                 * @param pByteCount
                 * @return
                 */
                @Override
		public ByteBuffer getPixelBuffer(final int pStart, final int pByteCount) {
			this.mByteBuffer.position(pStart);
			this.mByteBuffer.limit(pStart + pByteCount);

			return this.mByteBuffer.slice();
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}