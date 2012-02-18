package org.anddev.andengine.extension.multiplayer.protocol.client;

import java.io.DataInputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.IServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.client.connector.ServerConnector;
import org.anddev.andengine.extension.multiplayer.protocol.shared.Connection;
import org.anddev.andengine.extension.multiplayer.protocol.shared.IMessageHandler;
import org.anddev.andengine.extension.multiplayer.protocol.shared.IMessageReader;
import org.anddev.andengine.extension.multiplayer.protocol.shared.MessageReader;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <C> 
 * @author Nicolas Gramlich
 * @since 13:11:07 - 02.03.2011
 */
public interface IServerMessageReader<C extends Connection> extends IMessageReader<C, ServerConnector<C>, IServerMessage> {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pFlag
     * @param pServerMessageClass
     */
    @Override
	public void registerMessage(final short pFlag, final Class<? extends IServerMessage> pServerMessageClass);

        /**
         * 
         * @param pFlag
         * @param pServerMessageClass
         * @param pServerMessageHandler
         */
        @Override
	public void registerMessage(final short pFlag, final Class<? extends IServerMessage> pServerMessageClass, final IMessageHandler<C, ServerConnector<C>, IServerMessage> pServerMessageHandler);

        /**
         * 
         * @param pFlag
         * @param pServerMessageHandler
         */
        @Override
	public void registerMessageHandler(final short pFlag, final IMessageHandler<C, ServerConnector<C>, IServerMessage> pServerMessageHandler);

        /**
         * 
         * @param pDataInputStream
         * @return
         * @throws IOException
         */
        @Override
	public IServerMessage readMessage(final DataInputStream pDataInputStream) throws IOException;

        /**
         * 
         * @param pServerConnector
         * @param pServerMessage
         * @throws IOException
         */
        @Override
	public void handleMessage(final ServerConnector<C> pServerConnector, final IServerMessage pServerMessage) throws IOException;

        /**
         * 
         * @param pServerMessage
         */
        @Override
	public void recycleMessage(final IServerMessage pServerMessage);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         * @param <C>
         */
        public class ServerMessageReader<C extends Connection> extends MessageReader<C, ServerConnector<C>, IServerMessage> implements IServerMessageReader<C> {
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

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
