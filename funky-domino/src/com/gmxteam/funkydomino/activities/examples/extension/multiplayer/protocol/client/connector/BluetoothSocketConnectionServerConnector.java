package org.anddev.andengine.extension.multiplayer.protocol.client.connector;

import java.io.IOException;

import org.anddev.andengine.extension.multiplayer.protocol.client.IServerMessageReader;
import org.anddev.andengine.extension.multiplayer.protocol.shared.BluetoothSocketConnection;
import org.anddev.andengine.util.Debug;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:45:57 - 04.03.2011
 */
public class BluetoothSocketConnectionServerConnector extends ServerConnector<BluetoothSocketConnection> {
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
     * @param pBluetoothSocketConnection
     * @param pBlutetoothSocketConnectionServerConnectorListener
     * @throws IOException
     */
    public BluetoothSocketConnectionServerConnector(final BluetoothSocketConnection pBluetoothSocketConnection, final IBluetoothSocketConnectionServerConnectorListener pBlutetoothSocketConnectionServerConnectorListener) throws IOException {
		super(pBluetoothSocketConnection, pBlutetoothSocketConnectionServerConnectorListener);
	}

        /**
         * 
         * @param pBluetoothSocketConnection
         * @param pServerMessageReader
         * @param pBlutetoothSocketConnectionServerConnectorListener
         * @throws IOException
         */
        public BluetoothSocketConnectionServerConnector(final BluetoothSocketConnection pBluetoothSocketConnection, final IServerMessageReader<BluetoothSocketConnection> pServerMessageReader, final IBluetoothSocketConnectionServerConnectorListener pBlutetoothSocketConnectionServerConnectorListener) throws IOException {
		super(pBluetoothSocketConnection, pServerMessageReader, pBlutetoothSocketConnectionServerConnectorListener);
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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
        /**
         * 
         */
        public static interface IBluetoothSocketConnectionServerConnectorListener extends IServerConnectorListener<BluetoothSocketConnection> {
		
	}
	
        /**
         * 
         */
        public static class DefaultBluetoothConnectionSocketServerConnectorListener implements IBluetoothSocketConnectionServerConnectorListener {
            /**
             * 
             * @param pServerConnector
             */
            @Override
		public void onStarted(final ServerConnector<BluetoothSocketConnection> pServerConnector) {
			Debug.d("Accepted Server-Connection from: '" + pServerConnector.getConnection().getBluetoothSocket().getRemoteDevice().getAddress());
		}

            /**
             * 
             * @param pServerConnector
             */
            @Override
		public void onTerminated(final ServerConnector<BluetoothSocketConnection> pServerConnector) {
			Debug.d("Closed Server-Connection from: '" + pServerConnector.getConnection().getBluetoothSocket().getRemoteDevice().getAddress());
		}
	}
}
