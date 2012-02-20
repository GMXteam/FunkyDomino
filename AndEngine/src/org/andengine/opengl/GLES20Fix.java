package org.andengine.opengl;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 17:44:43 - 04.09.2011
 */
public class GLES20Fix {
	// ===========================================================
	// Constants
	// ===========================================================

	static {
		System.loadLibrary("andengine");
	}

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	private GLES20Fix() {

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

        /**
         * 
         * @param pIndex
         * @param pSize
         * @param pType
         * @param pNormalized
         * @param pStride
         * @param pOffset
         */
        native public static void glVertexAttribPointer(final int pIndex, final int pSize, final int pType, final boolean pNormalized, final int pStride, final int pOffset);
        /**
         * 
         * @param pMode
         * @param pCount
         * @param pType
         * @param pOffset
         */
        native public static void glDrawElements(final int pMode, final int pCount, final int pType, final int pOffset);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
