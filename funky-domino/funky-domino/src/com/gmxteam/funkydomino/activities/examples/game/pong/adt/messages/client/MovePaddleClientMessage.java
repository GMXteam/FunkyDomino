package com.gmxteam.funkydomino.activities.examples.game.pong.adt.messages.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.examples.game.pong.util.constants.PongConstants;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.ClientMessage;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 19:52:27 - 28.02.2011
 */
public class MovePaddleClientMessage extends ClientMessage implements PongConstants {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    public int mPaddleID;
    /**
     * 
     */
    public float mY;

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     */
    public MovePaddleClientMessage() {

	}

        /**
         * 
         * @param pID
         * @param pY
         */
        public MovePaddleClientMessage(final int pID, final float pY) {
		this.mPaddleID = pID;
		this.mY = pY;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @param pPaddleID
         * @param pY
         */
        public void setPaddleID(final int pPaddleID, final float pY) {
		this.mPaddleID = pPaddleID;
		this.mY = pY;
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
		return FLAG_MESSAGE_CLIENT_MOVE_PADDLE;
	}

        /**
         * 
         * @param pDataInputStream
         * @throws IOException
         */
        @Override
	protected void onReadTransmissionData(DataInputStream pDataInputStream) throws IOException {
		this.mPaddleID = pDataInputStream.readInt();
		this.mY = pDataInputStream.readFloat();
	}

        /**
         * 
         * @param pDataOutputStream
         * @throws IOException
         */
        @Override
	protected void onWriteTransmissionData(final DataOutputStream pDataOutputStream) throws IOException {
		pDataOutputStream.writeInt(this.mPaddleID);
		pDataOutputStream.writeFloat(this.mY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}