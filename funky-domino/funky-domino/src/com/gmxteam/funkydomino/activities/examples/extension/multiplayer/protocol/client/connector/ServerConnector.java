package org.anddev.andengine.extension.multiplayer.protocol.client.connector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.adt.message.client.IClientMessage;
import org.anddev.andengine.extension.multiplayer.protocol.adt.message.server.IServerMessage;
import org.anddev.andengine.extension.multiplayer.protocol.client.IServerMessageHandler;
import org.anddev.andengine.extension.multiplayer.protocol.client.IServerMessageReader;
import org.anddev.andengine.extension.multiplayer.protocol.client.IServerMessageReader.ServerMessageReader;
import org.anddev.andengine.extension.multiplayer.protocol.shared.Connection;
import org.anddev.andengine.extension.multiplayer.protocol.shared.Connector;
import org.anddev.andengine.util.ParameterCallable;
import org.anddev.andengine.util.SmartList;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <C> 
 * @author Nicolas Gramlich
 * @since 21:40:51 - 18.09.2009
 */
public class ServerConnector<C extends Connection> extends Connector<C> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IServerMessageReader<C> mServerMessageReader;

	private final ParameterCallable<IServerConnectorListener<C>> mOnStartedParameterCallable = new ParameterCallable<ServerConnector.IServerConnectorListener<C>>() {
		@Override
		public void call(final IServerConnectorListener<C> pServerConnectorListener) {
			pServerConnectorListener.onStarted(ServerConnector.this);
		}
	};

	private final ParameterCallable<IServerConnectorListener<C>> mOnTerminatedParameterCallable = new ParameterCallable<ServerConnector.IServerConnectorListener<C>>() {
		@Override
		public void call(final IServerConnectorListener<C> pServerConnectorListener) {
			pServerConnectorListener.onTerminated(ServerConnector.this);
		}
	};

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pConnection
         * @param pServerConnectorListener
         * @throws IOException
         */
        public ServerConnector(final C pConnection, final IServerConnectorListener<C> pServerConnectorListener) throws IOException {
		this(pConnection, new ServerMessageReader<C>(), pServerConnectorListener);
	}

        /**
         * 
         * @param pConnection
         * @param pServerMessageReader
         * @param pServerConnectorListener
         * @throws IOException
         */
        public ServerConnector(final C pConnection, final IServerMessageReader<C> pServerMessageReader, final IServerConnectorListener<C> pServerConnectorListener) throws IOException {
		super(pConnection);
		this.mServerMessageReader = pServerMessageReader;
		this.addServerConnectorListener(pServerConnectorListener);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public IServerMessageReader<C> getServerMessageReader() {
		return this.mServerMessageReader;
	}

        /**
         * 
         * @return
         */
        @SuppressWarnings("unchecked")
	@Override
	public SmartList<IServerConnectorListener<C>> getConnectorListeners() {
		return (SmartList<IServerConnectorListener<C>>) super.getConnectorListeners();
	}

        /**
         * 
         * @param pServerConnectorListener
         */
        public void addServerConnectorListener(final IServerConnectorListener<C> pServerConnectorListener) {
		super.addConnectorListener(pServerConnectorListener);
	}

        /**
         * 
         * @param pServerConnectorListener
         * @return
         */
        public boolean removeServerConnectorListener(final IServerConnectorListener<C> pServerConnectorListener) {
		return super.removeConnectorListener(pServerConnectorListener);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pConnection
         */
        @Override
	public void onStarted(final Connection pConnection) {
		this.getConnectorListeners().call(this.mOnStartedParameterCallable);
	}

        /**
         * 
         * @param pConnection
         */
        @Override
	public void onTerminated(final Connection pConnection) {
		this.getConnectorListeners().call(this.mOnTerminatedParameterCallable);
	}

        /**
         * 
         * @param pDataInputStream
         * @throws IOException
         */
        @Override
	public void read(final DataInputStream pDataInputStream) throws IOException {
		final IServerMessage serverMessage = this.mServerMessageReader.readMessage(pDataInputStream);
		this.mServerMessageReader.handleMessage(this, serverMessage);
		this.mServerMessageReader.recycleMessage(serverMessage);
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pFlag
         * @param pServerMessageClass
         */
        public void registerServerMessage(final short pFlag, final Class<? extends IServerMessage> pServerMessageClass) {
		this.mServerMessageReader.registerMessage(pFlag, pServerMessageClass);
	}

        /**
         * 
         * @param pFlag
         * @param pServerMessageClass
         * @param pServerMessageHandler
         */
        public void registerServerMessage(final short pFlag, final Class<? extends IServerMessage> pServerMessageClass, final IServerMessageHandler<C> pServerMessageHandler) {
		this.mServerMessageReader.registerMessage(pFlag, pServerMessageClass, pServerMessageHandler);
	}

        /**
         * 
         * @param pFlag
         * @param pServerMessageHandler
         */
        public void registerServerMessageHandler(final short pFlag, final IServerMessageHandler<C> pServerMessageHandler) {
		this.mServerMessageReader.registerMessageHandler(pFlag, pServerMessageHandler);
	}

        /**
         * 
         * @param pClientMessage
         * @throws IOException
         */
        public synchronized void sendClientMessage(final IClientMessage pClientMessage) throws IOException {
		final DataOutputStream dataOutputStream = this.mConnection.getDataOutputStream();
		pClientMessage.write(dataOutputStream);
		dataOutputStream.flush();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         * @param <T>
         */
        public static interface IServerConnectorListener<T extends Connection> extends IConnectorListener<ServerConnector<T>> {
		// ===========================================================
		// Final Fields
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

            /**
             * 
             * @param pServerConnector
             */
            @Override
		public void onStarted(final ServerConnector<T> pServerConnector);

                /**
                 * 
                 * @param pServerConnector
                 */
                @Override
		public void onTerminated(final ServerConnector<T> pServerConnector);
	}
}
