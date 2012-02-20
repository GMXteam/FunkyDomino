package org.andengine.extension.tmx.util.exception;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 18:37:53 - 08.08.2010
 */
public class TSXLoadException extends TMXException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 10055223972707703L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public TSXLoadException() {
		super();
	}

        /**
         * 
         * @param pDetailMessage
         * @param pThrowable
         */
        public TSXLoadException(final String pDetailMessage, final Throwable pThrowable) {
		super(pDetailMessage, pThrowable);
	}

        /**
         * 
         * @param pDetailMessage
         */
        public TSXLoadException(final String pDetailMessage) {
		super(pDetailMessage);
	}

        /**
         * 
         * @param pThrowable
         */
        public TSXLoadException(final Throwable pThrowable) {
		super(pThrowable);
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
