package org.anddev.andengine.extension.multiplayer.protocol.shared;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.util.StreamUtils;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 18:01:23 - 26.06.2011
 */
public interface IDiscoveryData {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pDataInputStream
     * @throws IOException
     */
    public void read(final DataInputStream pDataInputStream) throws IOException;
    /**
     * 
     * @param pDataOutputStream
     * @throws IOException
     */
    public void write(final DataOutputStream pDataOutputStream) throws IOException;

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public class DiscoveryDataFactory {
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

            /**
             * 
             * @param pDiscoveryData
             * @return
             * @throws IOException
             */
            public static byte[] write(final IDiscoveryData pDiscoveryData) throws IOException {
			final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

			try {
				pDiscoveryData.write(dataOutputStream);
				return byteArrayOutputStream.toByteArray();
			} finally {
				StreamUtils.close(dataOutputStream);
			}
		}

                /**
                 * 
                 * @param pData
                 * @param pDiscoveryData
                 * @throws IOException
                 */
                public static void read(final byte[] pData, final IDiscoveryData pDiscoveryData) throws IOException {
			final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pData);
			final DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

			try {
				pDiscoveryData.read(dataInputStream);
			} finally {
				StreamUtils.close(dataInputStream);
			}
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}

        /**
         * 
         */
        public class DefaultDiscoveryData implements IDiscoveryData {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private byte[] mServerIP;
		private int mServerPort;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @deprecated
                 */
                @Deprecated
		public DefaultDiscoveryData() {

		}

                /**
                 * 
                 * @param pServerIP
                 * @param pServerPort
                 */
                public DefaultDiscoveryData(final byte[] pServerIP, final int pServerPort) {
			this.mServerIP = pServerIP;
			this.mServerPort = pServerPort;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

                /**
                 * 
                 * @return
                 */
                public final byte[] getServerIP() {
			return this.mServerIP;
		}

                /**
                 * 
                 * @return
                 */
                public final int getServerPort() {
			return this.mServerPort;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

                /**
                 * 
                 * @param pDataInputStream
                 * @throws IOException
                 */
                @Override
		public void read(final DataInputStream pDataInputStream) throws IOException {
			final byte serverIPLength = pDataInputStream.readByte();
			this.mServerIP = new byte[serverIPLength];
			for(int i = 0; i < this.mServerIP.length; i++) {
				this.mServerIP[i] = pDataInputStream.readByte();
			}
			this.mServerPort = pDataInputStream.readShort();
		}

                /**
                 * 
                 * @param pDataOutputStream
                 * @throws IOException
                 */
                @Override
		public void write(final DataOutputStream pDataOutputStream) throws IOException {
			pDataOutputStream.writeByte((byte)this.mServerIP.length);
			pDataOutputStream.write(this.mServerIP);
			pDataOutputStream.writeShort((short)this.mServerPort);
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}