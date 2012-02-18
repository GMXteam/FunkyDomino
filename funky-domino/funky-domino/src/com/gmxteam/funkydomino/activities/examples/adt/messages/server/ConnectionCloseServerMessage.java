package com.gmxteam.funkydomino.activities.examples.adt.messages.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.examples.adt.messages.server.ServerMessageFlags;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.ServerMessage;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:11:40 - 11.03.2011
 */
public class ConnectionCloseServerMessage extends ServerMessage implements ServerMessageFlags {
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
     * 
     */
    public ConnectionCloseServerMessage() {

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    /**
     * 
     * @return
     */
    @Override
	public short getFlag() {
		return FLAG_MESSAGE_SERVER_CONNECTION_CLOSE;
	}

        /**
         * 
         * @param pDataInputStream
         * @throws IOException
         */
        @Override
	protected void onReadTransmissionData(final DataInputStream pDataInputStream) throws IOException {
		/* Nothing to read. */
	}

        /**
         * 
         * @param pDataOutputStream
         * @throws IOException
         */
        @Override
	protected void onWriteTransmissionData(final DataOutputStream pDataOutputStream) throws IOException {
		/* Nothing to write. */
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
