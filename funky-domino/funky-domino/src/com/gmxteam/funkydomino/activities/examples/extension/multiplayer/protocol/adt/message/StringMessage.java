package org.anddev.andengine.extension.multiplayer.protocol.adt.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:49:25 - 21.09.2009
 */
public abstract class StringMessage extends Message {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected String mString;

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     * @param pString
     */
    public StringMessage(final String pString) {
		this.mString = pString;
	}

        /**
         * 
         * @param pDataInputStream
         * @throws IOException
         */
        public StringMessage(final DataInputStream pDataInputStream) throws IOException {
		this.read(pDataInputStream);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public String getString() {
		return this.mString;
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
		this.mString = pDataInputStream.readUTF();
	}

	@Override
	protected void onAppendTransmissionDataForToString(final StringBuilder pStringBuilder) {
		pStringBuilder.append(", getString()=").append('\"').append(this.getString()).append('\"');
	}

        /**
         * 
         * @param pDataOutputStream
         * @throws IOException
         */
        @Override
	public void onWriteTransmissionData(final DataOutputStream pDataOutputStream) throws IOException {
		pDataOutputStream.writeUTF(this.getString());
	}

        /**
         * 
         * @param obj
         * @return
         */
        @Override
	public boolean equals(final Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}

		final StringMessage other = (StringMessage) obj;

		return this.getFlag() == other.getFlag() && this.getString() == other.getString();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
