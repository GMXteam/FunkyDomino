package org.andengine.opengl.util;

import java.nio.ByteBuffer;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 23:06:51 - 11.08.2011
 */
public class BufferUtils {
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
         * @param pCapacity
         * @return
         */
        public static ByteBuffer allocateDirect(final int pCapacity) {
		return BufferUtils.jniAllocateDirect(pCapacity);
	}

        /**
         * 
         * @param pByteBuffer
         */
        public static void freeDirect(final ByteBuffer pByteBuffer) {
		BufferUtils.jniFreeDirect(pByteBuffer);
	}

	private native static ByteBuffer jniAllocateDirect(final int pCapacity);
	private native static void jniFreeDirect(final ByteBuffer pByteBuffer);

	/**
	 * @param pByteBuffer must be a direct Buffer.
	 * @param pSource
	 * @param pLength to copy in pSource.
	 * @param pOffset in pSource.
	 */
	public static void put(final ByteBuffer pByteBuffer, final float[] pSource, final int pLength, final int pOffset) {
		BufferUtils.jniPut(pByteBuffer, pSource, pLength, pOffset);
		pByteBuffer.position(0);
		pByteBuffer.limit(pLength << 2);
	}

	private native static void jniPut(final ByteBuffer pByteBuffer, final float[] pSource, final int pLength, final int pOffset);

        /**
         * 
         * @param pByteBuffer
         * @return
         */
        public static short getUnsignedByte(final ByteBuffer pByteBuffer) {
		return (short) (pByteBuffer.get() & 0xFF);
	}

        /**
         * 
         * @param pByteBuffer
         * @param pValue
         */
        public static void putUnsignedByte(final ByteBuffer pByteBuffer, final int pValue) {
		pByteBuffer.put((byte) (pValue & 0xFF));
	}

        /**
         * 
         * @param pByteBuffer
         * @param pPosition
         * @return
         */
        public static short getUnsignedByte(final ByteBuffer pByteBuffer, final int pPosition) {
		return (short) (pByteBuffer.get(pPosition) & (short) 0xFF);
	}

        /**
         * 
         * @param pByteBuffer
         * @param pPosition
         * @param pValue
         */
        public static void putUnsignedByte(final ByteBuffer pByteBuffer, final int pPosition, final int pValue) {
		pByteBuffer.put(pPosition, (byte) (pValue & 0xFF));
	}

        /**
         * 
         * @param pByteBuffer
         * @return
         */
        public static int getUnsignedShort(final ByteBuffer pByteBuffer) {
		return pByteBuffer.getShort() & 0xFFFF;
	}

        /**
         * 
         * @param pByteBuffer
         * @param pValue
         */
        public static void putUnsignedShort(final ByteBuffer pByteBuffer, final int pValue) {
		pByteBuffer.putShort((short) (pValue & 0xFFFF));
	}

        /**
         * 
         * @param pByteBuffer
         * @param pPosition
         * @return
         */
        public static int getUnsignedShort(final ByteBuffer pByteBuffer, final int pPosition) {
		return pByteBuffer.getShort(pPosition) & 0xFFFF;
	}

        /**
         * 
         * @param pByteBuffer
         * @param pPosition
         * @param pValue
         */
        public static void putUnsignedShort(final ByteBuffer pByteBuffer, final int pPosition, final int pValue) {
		pByteBuffer.putShort(pPosition, (short) (pValue & 0xFFFF));
	}

        /**
         * 
         * @param pByteBuffer
         * @return
         */
        public static long getUnsignedInt(final ByteBuffer pByteBuffer) {
		return pByteBuffer.getInt() & 0xFFFFFFFFL;
	}

        /**
         * 
         * @param pByteBuffer
         * @param pValue
         */
        public static void putUnsignedInt(final ByteBuffer pByteBuffer, final long pValue) {
		pByteBuffer.putInt((int) (pValue & 0xFFFFFFFFL));
	}

        /**
         * 
         * @param pByteBuffer
         * @param pPosition
         * @return
         */
        public static long getUnsignedInt(final ByteBuffer pByteBuffer, final int pPosition) {
		return pByteBuffer.getInt(pPosition) & 0xFFFFFFFFL;
	}

        /**
         * 
         * @param pByteBuffer
         * @param pPosition
         * @param pValue
         */
        public static void putUnsignedInt(final ByteBuffer pByteBuffer, final int pPosition, final long pValue) {
		pByteBuffer.putInt(pPosition, (short) (pValue & 0xFFFFFFFFL));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
