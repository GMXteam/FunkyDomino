package org.anddev.andengine.extension.multiplayer.protocol.adt.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 18:24:50 - 19.09.2009
 */
public interface IMessage {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @return
     */
    public short getFlag();

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
}
