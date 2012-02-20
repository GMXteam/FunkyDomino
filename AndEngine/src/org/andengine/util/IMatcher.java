package org.andengine.util;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 12:32:22 - 26.12.2010
 */
public interface IMatcher<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pObject
     * @return
     */
    public boolean matches(final T pObject);
}

