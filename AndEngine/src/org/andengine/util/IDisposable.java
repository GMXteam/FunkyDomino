package org.andengine.util;

import org.andengine.util.exception.AndEngineRuntimeException;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 00:45:48 - 17.01.2012
 */
public interface IDisposable {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @return
     */
    public boolean isDisposed();
    /**
     * 
     * @throws org.andengine.util.IDisposable.AlreadyDisposedException
     */
    public void dispose() throws AlreadyDisposedException;

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

    /**
     * 
     */
    public class AlreadyDisposedException extends AndEngineRuntimeException {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final long serialVersionUID = 5796912098160771249L;

		// ===========================================================
		// Fields
		// ===========================================================

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 */
                public AlreadyDisposedException() {
			
		}

                /**
                 * 
                 * @param pMessage
                 */
                public AlreadyDisposedException(String pMessage) {
			super(pMessage);
		}

                /**
                 * 
                 * @param pThrowable
                 */
                public AlreadyDisposedException(Throwable pThrowable) {
			super(pThrowable);
		}
		
                /**
                 * 
                 * @param pMessage
                 * @param pThrowable
                 */
                public AlreadyDisposedException(String pMessage, Throwable pThrowable) {
			super(pMessage, pThrowable);
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
	}
}
