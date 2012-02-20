package org.andengine.util.algorithm.path;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 23:00:24 - 16.08.2010
 */
public interface ICostFunction<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     * @param pPathFinderMap
     * @param pFromX
     * @param pFromY
     * @param pToX
     * @param pToY
     * @param pEntity
     * @return
     */
    public float getCost(final IPathFinderMap<T> pPathFinderMap, final int pFromX, final int pFromY, final int pToX, final int pToY, final T pEntity);
}
