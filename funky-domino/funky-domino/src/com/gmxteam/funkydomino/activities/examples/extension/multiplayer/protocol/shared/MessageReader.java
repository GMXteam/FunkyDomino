package org.anddev.andengine.extension.multiplayer.protocol.shared;

import java.io.DataInputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.IMessage;
import org.anddev.andengine.extension.multiplayer.protocol.util.MessagePool;

import android.util.SparseArray;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <C> 
 * @param <CC> 
 * @param <M> 
 * @author Nicolas Gramlich
 * @since 11:05:58 - 21.09.2009
 */
public abstract class MessageReader<C extends Connection, CC extends Connector<C>, M extends IMessage> implements IMessageReader<C, CC, M> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final MessagePool<M> mMessagePool = new MessagePool<M>();
	private final SparseArray<IMessageHandler<C, CC, M>> mMessageHandlers = new SparseArray<IMessageHandler<C, CC, M>>();

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
         * @param pFlag
         * @param pMessageClass
         */
        @Override
	public void registerMessage(final short pFlag, final Class<? extends M> pMessageClass) {
		this.mMessagePool.registerMessage(pFlag, pMessageClass);
	}

        /**
         * 
         * @param pFlag
         * @param pMessageHandler
         */
        @Override
	public void registerMessageHandler(final short pFlag, final IMessageHandler<C, CC, M> pMessageHandler) {
		this.mMessageHandlers.put(pFlag, pMessageHandler);
	}

        /**
         * 
         * @param pFlag
         * @param pMessageClass
         * @param pMessageHandler
         */
        @Override
	public void registerMessage(final short pFlag, final Class<? extends M> pMessageClass, final IMessageHandler<C, CC, M> pMessageHandler) {
		this.registerMessage(pFlag, pMessageClass);
		this.registerMessageHandler(pFlag, pMessageHandler);
	}

        /**
         * 
         * @param pDataInputStream
         * @return
         * @throws IOException
         */
        @Override
	public M readMessage(final DataInputStream pDataInputStream) throws IOException {
		final short flag = pDataInputStream.readShort();
		return this.mMessagePool.obtainMessage(flag, pDataInputStream);
	}

        /**
         * 
         * @param pConnector
         * @param pMessage
         * @throws IOException
         */
        @Override
	public void handleMessage(final CC pConnector, final M pMessage) throws IOException {
		final IMessageHandler<C, CC, M> messageHandler = this.mMessageHandlers.get(pMessage.getFlag());
		if(messageHandler != null) {
			messageHandler.onHandleMessage(pConnector, pMessage);
		}
	}

        /**
         * 
         * @param pMessage
         */
        @Override
	public void recycleMessage(final M pMessage) {
		this.mMessagePool.recycleMessage(pMessage);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
