package org.anddev.andengine.extension.multiplayer.protocol.shared;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.anddev.andengine.util.SocketUtils;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 21:40:51 - 18.09.2009
 */
public class SocketConnection extends Connection {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final Socket mSocket;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pIPAddress
         * @param pPort
         * @param pTimeoutMilliseconds
         * @return
         * @throws IOException
         */
        public static SocketConnection create(final byte[] pIPAddress, final int pPort, final int pTimeoutMilliseconds) throws IOException {
		return SocketConnection.create(new InetSocketAddress(InetAddress.getByAddress(pIPAddress), pPort), pTimeoutMilliseconds);
	}

        /**
         * 
         * @param pSocketAddress
         * @param pTimeoutMilliseconds
         * @return
         * @throws IOException
         */
        public static SocketConnection create(final SocketAddress pSocketAddress, final int pTimeoutMilliseconds) throws IOException {
		final Socket socket = new Socket();
		socket.connect(pSocketAddress, pTimeoutMilliseconds);
		return new SocketConnection(socket);
	}

        /**
         * 
         * @param pSocket
         * @throws IOException
         */
        public SocketConnection(final Socket pSocket) throws IOException {
		super(new DataInputStream(pSocket.getInputStream()), new DataOutputStream(pSocket.getOutputStream()));

		this.mSocket = pSocket;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public Socket getSocket() {
		return this.mSocket;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         */
        @Override
	protected void onTerminate() {
		/* Ensure Socket is really closed. */
		SocketUtils.closeSocket(this.mSocket);
		super.onTerminate();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
