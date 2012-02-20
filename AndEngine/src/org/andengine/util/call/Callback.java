package org.andengine.util.call;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 09:40:55 - 14.12.2009
 */
public interface Callback<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pCallbackValue
     */
    public void onCallback(final T pCallbackValue);
}