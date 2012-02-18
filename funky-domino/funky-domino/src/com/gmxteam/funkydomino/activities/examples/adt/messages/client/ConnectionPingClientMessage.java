package org.anddev.andengine.examples.adt.messages.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.ClientMessage;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 12:23:37 - 21.05.2011
 */
public class ConnectionPingClientMessage extends ClientMessage implements ClientMessageFlags {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private long mTimestamp;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @deprecated
         */
        @Deprecated
	public ConnectionPingClientMessage() {

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
        /**
         * 
         * @return
         */
        public long getTimestamp() {
		return this.mTimestamp;
	}

        /**
         * 
         * @param pTimestamp
         */
        public void setTimestamp(final long pTimestamp) {
		this.mTimestamp = pTimestamp;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public short getFlag() {
		return FLAG_MESSAGE_CLIENT_CONNECTION_PING;
	}

        /**
         * 
         * @param pDataInputStream
         * @throws IOException
         */
        @Override
	protected void onReadTransmissionData(final DataInputStream pDataInputStream) throws IOException {
		this.mTimestamp = pDataInputStream.readLong();
	}

        /**
         * 
         * @param pDataOutputStream
         * @throws IOException
         */
        @Override
	protected void onWriteTransmissionData(final DataOutputStream pDataOutputStream) throws IOException {
		pDataOutputStream.writeLong(this.mTimestamp);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
